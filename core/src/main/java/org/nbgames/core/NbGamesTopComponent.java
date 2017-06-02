/*
 * Copyright 2017 Patrik Karlsson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nbgames.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.PreferenceChangeEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.nbgames.core.actions.CallbackHelpAction;
import org.nbgames.core.actions.CallbackInfoAction;
import org.nbgames.core.actions.CallbackNewRoundAction;
import org.nbgames.core.actions.CallbackOptionsAction;
import org.nbgames.core.api.DictNbg;
import org.nbgames.core.api.GameCategory;
import org.nbgames.core.api.GameController;
import org.nbgames.core.api.NbGames;
import org.nbgames.core.api.TriggerManager;
import org.nbgames.core.api.db.Db;
import org.nbgames.core.api.db.manager.PlayerManager;
import org.nbgames.core.api.service.PresenterProvider;
import org.nbgames.core.api.ui.DialogButtonManager;
import org.nbgames.core.api.ui.NewGamePanel;
import org.nbgames.core.api.ui.OptionsPanel;
import org.nbgames.core.ui.HelpPanel;
import org.nbgames.core.ui.HomeProvider;
import org.nbgames.core.ui.InfoPanel;
import org.nbgames.core.ui.InitPanel;
import org.nbgames.core.ui.PlayerTrigger;
import org.nbgames.core.ui.PlayersPanel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.Actions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import se.trixon.almond.nbp.ActionHelper;
import se.trixon.almond.nbp.Almond;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.SystemHelper;
import se.trixon.almond.util.icons.IconColor;
import se.trixon.almond.util.icons.material.MaterialIcon;
import se.trixon.almond.util.swing.SwingHelper;
import se.trixon.almond.util.swing.dialogs.about.AboutModel;
import se.trixon.almond.util.swing.dialogs.about.AboutPanel;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.nbgames.core//NbGamesTopComponent//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "NbGamesTopComponent",
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "org.nbgames.core.NbGamesTopComponent")
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_NbGamesTopComponentAction",
        preferredID = "NbGamesTopComponent"
)
@Messages({
    "CTL_NbGamesTopComponentAction=nbGames",
    "CTL_NbGamesTopComponent=nbGames",})
public final class NbGamesTopComponent extends TopComponent {

    private final NbgOptions mOptions = NbgOptions.getInstance();
    private final IconColor mIconColor = NbGames.getAlmondOptions().getIconColor();
    private final ActionMap mActionMap = getActionMap();
    private final DialogButtonManager mButtonManager = DialogButtonManager.getInstance();

    static {
        Almond.ICON_LARGE = 48;
        Almond.ICON_SMALL = 36;
    }

    public NbGamesTopComponent() {
        initComponents();
        setName(Bundle.CTL_NbGamesTopComponent());
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);

        init();
        initListeners();
    }

    public void editPlayers() {
        PlayersPanel playersPanel = new PlayersPanel();
        playersPanel.load();
        Object[] options = new Object[]{mButtonManager.getCancel(), mButtonManager.getOk()};

        NotifyDescriptor d = new NotifyDescriptor(
                playersPanel,
                DictNbg.PLAYERS.toString(),
                NotifyDescriptor.PLAIN_MESSAGE,
                NotifyDescriptor.DEFAULT_OPTION,
                options,
                mButtonManager.getOk());

        Object retVal = DialogDisplayer.getDefault().notify(d);
        if (retVal == mButtonManager.getOk()) {
            playersPanel.store();
        }
    }

    public JButton getSelectorButton() {
        return selectorButton;
    }

    public void show(PresenterProvider presenterProvider) {
        final JPanel panel = presenterProvider.getPanel();
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.repaint();
        mainPanel.revalidate();

        mActionMap.remove(CallbackHelpAction.KEY);
        mActionMap.remove(CallbackNewRoundAction.KEY);
        mActionMap.remove(CallbackOptionsAction.KEY);
        mActionMap.remove(CallbackInfoAction.KEY);

        if (presenterProvider.getOptionsPanel() != null) {
            // Options
            mActionMap.put(CallbackOptionsAction.KEY, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayOptionsdialog(presenterProvider);
                }
            });
        }

        if (presenterProvider.getHelp() != null) {
            mActionMap.put(CallbackHelpAction.KEY, new AbstractAction() {
                // Help
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayHelpDialog(presenterProvider);
                }
            });
        }

        mActionMap.put(CallbackInfoAction.KEY, new AbstractAction() {
            // Info
            @Override
            public void actionPerformed(ActionEvent e) {
                if (presenterProvider instanceof HomeProvider) {
                    displayAboutDialog();
                } else {
                    displayInfoDialog(presenterProvider);
                }
            }
        });

        if (presenterProvider instanceof GameController) {
            mActionMap.put(CallbackNewRoundAction.KEY, new AbstractAction() {
                // New
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayNewGameDialog((GameController) presenterProvider);
                }
            });

            if (((GameController) presenterProvider).isFirstRun()) {
                mActionMap.get(CallbackNewRoundAction.KEY).actionPerformed(null);
            }
        }

        optionsButton.setEnabled(Actions.forID("Game", "org.nbgames.core.actions.OptionsAction").isEnabled());
        helpButton.setEnabled(Actions.forID("Game", "org.nbgames.core.actions.HelpAction").isEnabled());
        newButton.setEnabled(Actions.forID("Game", "org.nbgames.core.actions.NewRoundAction").isEnabled());
    }

    private void displayAboutDialog() {
        AboutModel aboutModel = new AboutModel(SystemHelper.getBundle(getClass(), "about"),
                SystemHelper.getResourceAsImageIcon(getClass(), "nbgames.png"));

        AboutPanel aboutPanel = new AboutPanel(aboutModel);
        aboutPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 0, 16));

        DialogDescriptor dialogDescriptor = new DialogDescriptor(
                aboutPanel,
                String.format(Dict.ABOUT_S.toString(), aboutModel.getAppName()),
                false,
                new Object[]{DialogDescriptor.CLOSED_OPTION},
                DialogDescriptor.CLOSED_OPTION,
                DialogDescriptor.DEFAULT_ALIGN,
                null,
                null);

        DialogDisplayer.getDefault().notify(dialogDescriptor);
    }

    private void displayHelpDialog(PresenterProvider presenterProvider) {
        HelpPanel helpPanel = new HelpPanel();
        helpPanel.load(presenterProvider.getHelp());
        helpPanel.setPreferredSize(new Dimension(800, 600));

        Object[] options = new Object[]{mButtonManager.getOk()};

        NotifyDescriptor d = new NotifyDescriptor(
                helpPanel,
                Dict.HELP.toString(),
                NotifyDescriptor.PLAIN_MESSAGE,
                NotifyDescriptor.DEFAULT_OPTION,
                options,
                mButtonManager.getOk());

        DialogDisplayer.getDefault().notify(d);
    }

    private void displayInfoDialog(PresenterProvider presenterProvider) {
        InfoPanel infoPanel = new InfoPanel();
        infoPanel.load(presenterProvider);
        Object[] options = new Object[]{mButtonManager.getOk()};

        NotifyDescriptor d = new NotifyDescriptor(
                infoPanel,
                Dict.ABOUT.toString(),
                NotifyDescriptor.PLAIN_MESSAGE,
                NotifyDescriptor.DEFAULT_OPTION,
                options,
                mButtonManager.getOk());

        DialogDisplayer.getDefault().notify(d);
    }

    public void displayNewGameDialog(GameController controller) {
        NewGamePanel newGamePanel = controller.getNewGamePanel();
        newGamePanel.load();

        Object[] options = new Object[]{mButtonManager.getCancel(), mButtonManager.getOk()};
        NotifyDescriptor d = new NotifyDescriptor(
                newGamePanel,
                DictNbg.NEW_ROUND.toString(),
                NotifyDescriptor.PLAIN_MESSAGE,
                NotifyDescriptor.DEFAULT_OPTION,
                options,
                mButtonManager.getOk());

        Object retVal = DialogDisplayer.getDefault().notify(d);
        if (retVal == mButtonManager.getOk()) {
            newGamePanel.save();
            controller.onRequestNewGameStart();
            controller.setFirstRun(false);
        }
    }

    private void displayOptionsdialog(PresenterProvider presenterProvider) {
        OptionsPanel optionsPanel = presenterProvider.getOptionsPanel();
        optionsPanel.load();
        Object[] options = new Object[]{mButtonManager.getCancel(), mButtonManager.getOk()};

        NotifyDescriptor d = new NotifyDescriptor(
                optionsPanel,
                Dict.OPTIONS.toString(),
                NotifyDescriptor.PLAIN_MESSAGE,
                NotifyDescriptor.DEFAULT_OPTION,
                options,
                mButtonManager.getOk());

        Object retVal = DialogDisplayer.getDefault().notify(d);
        if (retVal == mButtonManager.getOk()) {
        }
    }

    public void toogleToolbarVisibility() {
        toolBar.setVisible(!toolBar.isVisible());
    }

    private void initListeners() {
        mOptions.getPreferences().addPreferenceChangeListener((PreferenceChangeEvent evt) -> {
            String key = evt.getKey();
            boolean needsRepiant = false;
            switch (key) {
                case NbgOptions.KEY_CUSTOM_WINDOW_BACKGROUND:
                    needsRepiant = true;
                    break;

                case NbgOptions.KEY_CUSTOM_TOOLBAR_BACKGROUND:
                    toolBar.setOpaque(mOptions.isCustomToolbarBackground());
                    needsRepiant = true;
                    break;

                case "color.toolbar":
                    toolBar.setBackground(mOptions.getColor(NbgOptions.ColorItem.TOOLBAR));
                    needsRepiant = true;
                    break;

                case "color.window_lower":
                    needsRepiant = true;
                    break;

                case "color.window_upper":
                    needsRepiant = true;
                    break;
            }

            if (needsRepiant) {
                revalidate();
                repaint();
            }
        });

        TriggerManager.getInstance().getPreferences().addPreferenceChangeListener((PreferenceChangeEvent evt) -> {
            if (evt.getKey().equalsIgnoreCase(PlayerTrigger.class.getName())) {
                setSelectorEnabled(!PlayerManager.getInstance().select().isEmpty());
            }
        });

    }

    private void setSelectorEnabled(boolean enabled) {
        selectorButton.setEnabled(enabled);
        Actions.forID("Game", "org.nbgames.core.actions.SelectorAction").setEnabled(enabled);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mOptions.isCustomWindowBackground()) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            int w = getWidth();
            int h = getHeight();
            Color upperColor = mOptions.getColor(NbgOptions.ColorItem.WINDOW_UPPER);
            Color lowerColor = mOptions.getColor(NbgOptions.ColorItem.WINDOW_LOWER);
            GradientPaint gp = new GradientPaint(0, 0, upperColor, 0, h, lowerColor);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }

    private void init() {
        String category;
        String id;

        category = "Game";
        id = "org.nbgames.core.actions.NewRoundAction";
        initActionButton(category, id, newButton, DictNbg.NEW_ROUND.toString(), MaterialIcon._Av.PLAY_ARROW.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Av.PLAY_ARROW.get(Almond.ICON_SMALL, mIconColor));

        id = "org.nbgames.core.actions.SelectorAction";
        initActionButton(category, id, selectorButton, DictNbg.GAME_SELECTOR.toString(), MaterialIcon._Av.GAMES.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Av.GAMES.get(Almond.ICON_SMALL, mIconColor));

        id = "org.nbgames.core.actions.HomeAction";
        initActionButton(category, id, homeButton, DictNbg.GO_HOME.toString(), MaterialIcon._Action.HOME.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Action.HOME.get(Almond.ICON_SMALL, mIconColor));

        id = "org.nbgames.core.actions.PlayerManagerAction";
        initActionButton(category, id, playersButton, DictNbg.PLAYERS.toString(), MaterialIcon._Social.PEOPLE.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Social.PEOPLE.get(Almond.ICON_SMALL, mIconColor));

        id = "org.nbgames.core.actions.OptionsAction";
        initActionButton(category, id, optionsButton, Dict.OPTIONS.toString(), MaterialIcon._Action.SETTINGS.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Action.SETTINGS.get(Almond.ICON_SMALL, mIconColor));

        id = "org.nbgames.core.actions.InfoAction";
        initActionButton(category, id, infoButton, Dict.INFORMATION.toString(), MaterialIcon._Action.INFO_OUTLINE.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Action.INFO_OUTLINE.get(Almond.ICON_SMALL, mIconColor));

        id = "org.nbgames.core.actions.HelpAction";
        initActionButton(category, id, helpButton, Dict.HELP.toString(), MaterialIcon._Action.HELP_OUTLINE.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Action.HELP_OUTLINE.get(Almond.ICON_SMALL, mIconColor));

        category = "File";
        id = "se.trixon.almond.nbp.actions.QuitAction";
        initActionButton(category, id, quitButton, Dict.QUIT.toString(), MaterialIcon._Navigation.CLOSE.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Navigation.CLOSE.get(Almond.ICON_SMALL, mIconColor));
        quitButton.setVisible(false);

        category = "Window";
        id = "org.netbeans.core.windows.actions.ToggleFullScreenAction";
        final Action fullScreenAction = Actions.forID(category, id);
        ActionHelper.setIconSmall(fullScreenAction, MaterialIcon._Navigation.FULLSCREEN.get(Almond.ICON_SMALL, mIconColor));
        ActionHelper.setIconLarge(fullScreenAction, MaterialIcon._Navigation.FULLSCREEN.get(Almond.ICON_LARGE, mIconColor));

        fullscreenButton.setAction(fullScreenAction);
        fullscreenButton.setText("");
        fullscreenButton.setToolTipText(Dict.FULL_SCREEN.toString());

        category = "System";
        id = "org.nbgames.core.actions.SystemMenuAction";
        initActionButton(category, id, menuButton, null, MaterialIcon._Navigation.MENU.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Navigation.MENU.get(Almond.ICON_SMALL, mIconColor));

        final JFrame frame = (JFrame) WindowManager.getDefault().getMainWindow();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                final boolean fullscreen = frame.isUndecorated();
                MaterialIcon._Navigation fullscreenIcon = fullscreen == true ? MaterialIcon._Navigation.FULLSCREEN_EXIT : MaterialIcon._Navigation.FULLSCREEN;

                ActionHelper.setIconSmall(fullScreenAction, fullscreenIcon.get(Almond.ICON_SMALL, mIconColor));
                ActionHelper.setIconLarge(fullScreenAction, fullscreenIcon.get(Almond.ICON_LARGE, mIconColor));
                quitButton.setVisible(fullscreen);
            }
        });

        menuButton.setVisible(false);
        WindowManager.getDefault().invokeWhenUIReady(() -> {
            frame.setTitle(Bundle.CTL_NbGamesTopComponent());
        });

        toolBar.setOpaque(mOptions.isCustomToolbarBackground());
        toolBar.setBackground(mOptions.getColor(NbgOptions.ColorItem.TOOLBAR));

        SwingHelper.borderPainted(toolBar, false);
        mainPanel.removeAll();
        mainPanel.add(new InitPanel());
        setSelectorEnabled(false);

        new Thread(() -> {
            Db.getInstance().getAutoCommitConnection();
            SwingUtilities.invokeLater(() -> {
                setSelectorEnabled(!PlayerManager.getInstance().select().isEmpty());
                show(HomeProvider.getInstance());
                GameController gameController;
                gameController = GameController.forID(GameCategory.DICE, "org.nbgames.hekaton.Hekaton");
//                gameController = GameController.forID(GameCategory.DICE, "org.nbgames.yaya.Yaya");
//                show(gameController);
            });
        }).start();

    }

    private void initActionButton(String category, String id, JButton button, String toolTip, Icon largeIcon, Icon smallIcon) {
        Action action = Actions.forID(category, id);
        ActionHelper.setIconSmall(action, smallIcon);
        ActionHelper.setIconLarge(action, largeIcon);

        button.setAction(action);
        button.setText("");

        if (toolTip == null) {
            button.setToolTipText(action.getValue(Action.NAME).toString());
        } else {
            button.setToolTipText(toolTip);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        homeButton = new javax.swing.JButton();
        playersButton = new javax.swing.JButton();
        selectorButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        optionsButton = new javax.swing.JButton();
        infoButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        fullscreenButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        menuButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        homeButton.setFocusable(false);
        homeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(homeButton);

        playersButton.setFocusable(false);
        playersButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playersButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(playersButton);

        selectorButton.setFocusable(false);
        selectorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        selectorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(selectorButton);

        newButton.setFocusable(false);
        newButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(newButton);

        optionsButton.setFocusable(false);
        optionsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        optionsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(optionsButton);

        infoButton.setFocusable(false);
        infoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        infoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(infoButton);

        helpButton.setFocusable(false);
        helpButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        helpButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(helpButton);

        fullscreenButton.setFocusable(false);
        fullscreenButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fullscreenButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(fullscreenButton);
        toolBar.add(filler1);

        menuButton.setFocusable(false);
        menuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(menuButton);

        quitButton.setFocusable(false);
        quitButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        quitButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(quitButton);

        add(toolBar, java.awt.BorderLayout.NORTH);

        mainPanel.setLayout(new java.awt.GridLayout(1, 0));
        add(mainPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton fullscreenButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton infoButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton menuButton;
    private javax.swing.JButton newButton;
    private javax.swing.JButton optionsButton;
    private javax.swing.JButton playersButton;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton selectorButton;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        requestActive();
        requestFocus();
    }

    @Override
    public void componentClosed() {
    }

    @Override
    protected void componentActivated() {
        String category = "Window";
        String id = "org.netbeans.core.windows.actions.ShowEditorOnlyAction";

        Actions.forID(category, id).actionPerformed(null);
    }

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }
}
