/*
 * Copyright 2016 Patrik Karlsson.
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

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.nbgames.core.actions.CallbackHelpAction;
import org.nbgames.core.actions.CallbackNewRoundAction;
import org.nbgames.core.actions.CallbackOptionsAction;
import org.nbgames.core.api.GameProvider;
import org.nbgames.core.base.BaseTopComponent;
import org.nbgames.core.presenter.HelpProvider;
import org.nbgames.core.presenter.HomeProvider;
import org.nbgames.core.presenter.OptionsProvider;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.Actions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import se.trixon.almond.nbp.ActionHelper;
import se.trixon.almond.nbp.Almond;
import se.trixon.almond.util.AlmondOptions;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.icons.IconColor;
import se.trixon.almond.util.icons.material.MaterialIcon;
import org.nbgames.core.api.PresenterProvider;

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
public final class NbGamesTopComponent extends BaseTopComponent {

    private final IconColor mIconColor = AlmondOptions.getInstance().getIconColor();
    private final ActionMap mActionMap = getActionMap();
    private final CardLayout mCardLayout;
    private final Deque<PresenterProvider> mStack = new ArrayDeque<>();

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

        mCardLayout = (CardLayout) mainPanel.getLayout();
        init();

    }

    public JButton getSelectorButton() {
        return selectorButton;
    }

    public void showPrevious() {
        mStack.pop();
        show(mStack.pop());
    }

    public void show(PresenterProvider componentProvider) {
        if (componentProvider == mStack.peek()) {
            return;
        }

        if (componentProvider.getPanel().getParent() != mainPanel) {
            mainPanel.add(componentProvider.getPanel(), componentProvider.getId());
        }

        mStack.remove(componentProvider);
        mStack.addFirst(componentProvider);
        mCardLayout.show(mainPanel, componentProvider.getId());

        System.out.println("STACK");
        for (PresenterProvider componentProvider1 : mStack) {
            System.out.println(componentProvider1.getId());
        }
        System.out.println("");

        mActionMap.remove(CallbackHelpAction.KEY);
        mActionMap.remove(CallbackNewRoundAction.KEY);
        mActionMap.remove(CallbackOptionsAction.KEY);

        newButton.setEnabled(false);
//        helpButton.setEnabled(false);
//        optionsButton.setEnabled(false);

        if (componentProvider.getOptionsPanel() != null) {
            mActionMap.put(CallbackOptionsAction.KEY, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("give me the settings");
                    show(OptionsProvider.getInstance());
                }
            });
        }

        if (componentProvider.getHelp() != null) {
            mActionMap.put(CallbackHelpAction.KEY, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    show(HelpProvider.getInstance());
                }
            });
        }

//        mActionMap.put(CallbackInfoAction.KEY, new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("give me the info");
//            }
//        });
        if (componentProvider instanceof GameProvider) {
            mActionMap.put(CallbackNewRoundAction.KEY, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("start new round");
                }
            });
        }

        optionsButton.setEnabled(Actions.forID("Game", "org.nbgames.core.actions.OptionsAction").isEnabled());
        helpButton.setEnabled(Actions.forID("Game", "org.nbgames.core.actions.HelpAction").isEnabled());
    }

    private void init() {
        String category;
        String id;

        category = "Game";
        id = "org.nbgames.core.actions.NewRoundAction";
        initActionButton(category, id, newButton, DictNbg.NEW_ROUND.toString(), MaterialIcon._Av.PLAY_ARROW.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Av.PLAY_ARROW.get(Almond.ICON_SMALL, mIconColor));

        category = "Game";
        id = "org.nbgames.core.actions.SelectorAction";
        initActionButton(category, id, selectorButton, DictNbg.GAME_SELECTOR.toString(), MaterialIcon._Av.GAMES.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Av.GAMES.get(Almond.ICON_SMALL, mIconColor));

        category = "Game";
        id = "org.nbgames.core.actions.HomeAction";
        initActionButton(category, id, homeButton, DictNbg.GO_HOME.toString(), MaterialIcon._Action.HOME.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Action.HOME.get(Almond.ICON_SMALL, mIconColor));

        category = "Game";
        id = "org.nbgames.core.actions.PlayerManagerAction";
        initActionButton(category, id, playersButton, DictNbg.PLAYERS.toString(), MaterialIcon._Social.PEOPLE.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Social.PEOPLE.get(Almond.ICON_SMALL, mIconColor));

        category = "Game";
        id = "org.nbgames.core.actions.OptionsAction";
        initActionButton(category, id, optionsButton, Dict.OPTIONS.toString(), MaterialIcon._Action.SETTINGS.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Action.SETTINGS.get(Almond.ICON_SMALL, mIconColor));

        category = "Game";
        id = "org.nbgames.core.actions.InfoAction";
        initActionButton(category, id, infoButton, Dict.INFORMATION.toString(), MaterialIcon._Action.INFO_OUTLINE.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Action.INFO_OUTLINE.get(Almond.ICON_SMALL, mIconColor));

        category = "Game";
        id = "org.nbgames.core.actions.HelpAction";
        initActionButton(category, id, helpButton, Dict.HELP.toString(), MaterialIcon._Action.HELP_OUTLINE.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Action.HELP_OUTLINE.get(Almond.ICON_SMALL, mIconColor));

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
                MaterialIcon._Navigation fullscreenIcon = frame.isUndecorated() == true ? MaterialIcon._Navigation.FULLSCREEN_EXIT : MaterialIcon._Navigation.FULLSCREEN;

                ActionHelper.setIconSmall(fullScreenAction, fullscreenIcon.get(Almond.ICON_SMALL, mIconColor));
                ActionHelper.setIconLarge(fullScreenAction, fullscreenIcon.get(Almond.ICON_LARGE, mIconColor));
            }
        });

        WindowManager.getDefault().invokeWhenUIReady(() -> {
            frame.setTitle(Bundle.CTL_NbGamesTopComponent());
        });

        show(HomeProvider.getInstance());
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 32767));
        fullscreenButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        menuButton = new javax.swing.JButton();
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
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });
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
        toolBar.add(filler2);

        fullscreenButton.setFocusable(false);
        fullscreenButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fullscreenButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(fullscreenButton);
        toolBar.add(filler1);

        menuButton.setFocusable(false);
        menuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(menuButton);

        add(toolBar, java.awt.BorderLayout.NORTH);

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.CardLayout());
        add(mainPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        System.out.println("new");
    }//GEN-LAST:event_newButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton fullscreenButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton infoButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton menuButton;
    private javax.swing.JButton newButton;
    private javax.swing.JButton optionsButton;
    private javax.swing.JButton playersButton;
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
