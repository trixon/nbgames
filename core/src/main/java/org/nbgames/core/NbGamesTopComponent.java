/*
 * Copyright 2016 pata.
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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.nbgames.core.api.GameProvider;
import org.nbgames.core.base.BaseTopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.Actions;
import org.openide.awt.DropDownButtonFactory;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import se.trixon.almond.nbp.ActionHelper;
import se.trixon.almond.nbp.Almond;
import se.trixon.almond.nbp.NbLog;
import se.trixon.almond.util.AlmondOptions;
import se.trixon.almond.util.icons.IconColor;
import se.trixon.almond.util.icons.material.MaterialIcon;

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
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_NbGamesTopComponentAction",
        preferredID = "NbGamesTopComponent"
)
@Messages({
    "CTL_NbGamesTopComponentAction=nbGames",
    "CTL_NbGamesTopComponent=nbGames",})
public final class NbGamesTopComponent extends BaseTopComponent {

    private JPopupMenu mDropDownMenu = new JPopupMenu();
    private final IconColor mIconColor = AlmondOptions.getInstance().getIconColor();
    private JMenuItem mDummyMenuItem;

    static {
        Almond.ICON_LARGE = 48;
    }

    public NbGamesTopComponent() {
        initComponents();
        setName(Bundle.CTL_NbGamesTopComponent());

        init();
    }

    private void init() {

        String category;
        String id;

        category = "Actions/Window";
        id = "org.nbgames.core.StartPageTopComponent";
        initActionButton(category, id, homeButton, MaterialIcon._Action.HOME.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Action.HOME.get(Almond.ICON_SMALL, mIconColor));

        category = "Actions/File";
        id = "org.nbgames.core.actions.PlayerManagerAction";
        initActionButton(category, id, playersButton, MaterialIcon._Social.PEOPLE.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Social.PEOPLE.get(Almond.ICON_SMALL, mIconColor));

        category = "Actions/File";
        id = "org.nbgames.core.actions.OptionsAction";
        initActionButton(category, id, optionsButton, MaterialIcon._Action.SETTINGS.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Action.SETTINGS.get(Almond.ICON_SMALL, mIconColor));

        category = "Actions/Window";
        id = "org.netbeans.core.windows.actions.ToggleFullScreenAction";
        final Action fullScreenAction = ActionHelper.getAction(category, id);
        ActionHelper.setIconSmall(fullScreenAction, MaterialIcon._Navigation.FULLSCREEN.get(Almond.ICON_SMALL, mIconColor));
        ActionHelper.setIconLarge(fullScreenAction, MaterialIcon._Navigation.FULLSCREEN.get(Almond.ICON_LARGE, mIconColor));

        fullscreenButton.setAction(fullScreenAction);
        fullscreenButton.setText("");

        category = "Actions/System";
        id = "org.nbgames.core.actions.SystemMenuAction";
        initActionButton(category, id, menuButton, MaterialIcon._Navigation.MENU.get(Almond.ICON_LARGE, mIconColor), MaterialIcon._Navigation.MENU.get(Almond.ICON_SMALL, mIconColor));

        mDummyMenuItem = new JMenuItem("No installed games");
        mDummyMenuItem.setEnabled(false);
        mDropDownMenu.add(mDummyMenuItem);
        mDropDownMenu.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                populateDropDown();
            }
        });
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

    }

    private void initActionButton(String category, String id, JButton button, Icon largeIcon, Icon smallIcon) {
        Action action = ActionHelper.getAction(category, id);
        ActionHelper.setIconSmall(action, smallIcon);
        ActionHelper.setIconLarge(action, largeIcon);

        button.setAction(action);
        button.setText("");
        button.setToolTipText(action.getValue(Action.NAME).toString());
    }

    private void populateDropDown() {
        NbLog.d(getClass(), "populate beg");
        mDropDownMenu.removeAll();
        Collection<? extends GameProvider> gameProviders = Lookup.getDefault().lookupAll(GameProvider.class);
        gameProviders.stream().forEach((gameProvider) -> {
            NbLog.d(getClass(), gameProvider.getName());
            JLabel label = new JLabel(gameProvider.getCategory().getString());
            label.setEnabled(false);
            mDropDownMenu.add(label);
            String category = gameProvider.getActionCategory();
            String id = gameProvider.getActionId();
            JMenuItem menuItem = new JMenuItem(Actions.forID(category, id));
            String text = String.format("<html><b>%s</b><br /> <i>- %s</i></html>", gameProvider.getName(), gameProvider.getShortDescription());
            menuItem.setText(text);

            mDropDownMenu.add(menuItem);
        });

        if (gameProviders.isEmpty()) {
            mDropDownMenu.add(mDummyMenuItem);
        }
        NbLog.d(getClass(), "populate end");
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
        newButton = DropDownButtonFactory.createDropDownButton(MaterialIcon._Av.GAMES.get(Almond.ICON_LARGE, mIconColor), mDropDownMenu);
        playersButton = new javax.swing.JButton();
        optionsButton = new javax.swing.JButton();
        fullscreenButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        menuButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        toolBar.setRollover(true);
        toolBar.setOpaque(false);

        homeButton.setFocusable(false);
        homeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(homeButton);

        newButton.setFocusable(false);
        newButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(newButton);

        playersButton.setFocusable(false);
        playersButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playersButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(playersButton);

        optionsButton.setFocusable(false);
        optionsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        optionsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(optionsButton);

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
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton fullscreenButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton menuButton;
    private javax.swing.JButton newButton;
    private javax.swing.JButton optionsButton;
    private javax.swing.JButton playersButton;
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
        super.componentActivated();
        String category = "Actions/Window";
        String id = "org.netbeans.core.windows.actions.ShowEditorOnlyAction";

        Action action = ActionHelper.getAction(category, id);
        action.actionPerformed(null);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
