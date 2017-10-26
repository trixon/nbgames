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
package org.nbgames.core.ui.home;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.prefs.PreferenceChangeEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.nbgames.core.InstalledGames;
import org.nbgames.core.api.GameController;
import org.nbgames.core.api.TriggerManager;
import org.nbgames.core.api.db.manager.PlayerManager;
import org.nbgames.core.ui.PlayerTrigger;
import org.openide.awt.Actions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import se.trixon.almond.util.Dict;
import se.trixon.almond.util.GraphicsHelper;
import se.trixon.almond.util.icons.IconColor;
import se.trixon.almond.util.icons.material.MaterialIcon;
import se.trixon.almond.util.swing.SwingHelper;

/**
 *
 * @author Patrik Karlsson
 */
public class HomePanel extends javax.swing.JPanel {

    private final InstalledGames mGames;
    private StringBuilder mCssBuilder;
    private final PlayerManager mPlayerManager = PlayerManager.getInstance();
    private final CategoryPanel mSystemCategoryPanel = new CategoryPanel();

    /**
     * Creates new form HomePanel
     */
    public HomePanel() {
        mGames = InstalledGames.getInstance();
        initComponents();
        playerManagerPanel.setVisible(false);

        TriggerManager.getInstance().getPreferences().addPreferenceChangeListener((PreferenceChangeEvent evt) -> {
            if (evt.getKey().equalsIgnoreCase(PlayerTrigger.class.getName())) {
                updatePlayerManagerPanel();
            }
        });
        init();
        SwingUtilities.invokeLater(() -> {
            updateGameList();
            updatePlayerManagerPanel();
        });
        //scrollPane.setVisible(false);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void init() {
        mCssBuilder = new StringBuilder("<html>");
        mCssBuilder.append("<head><style>");
        mCssBuilder.append("h1 { font-size: x-large; margin-bottom: 0px; }");
        mCssBuilder.append("h2 { font-size: large; margin-bottom: 0px; }");
        mCssBuilder.append("body {margin-left: 16px;margin-right: 16px; font-size: medium; }");
        mCssBuilder.append("p {margin-bottom: 4px;margin-top: 4px;}");
        mCssBuilder.append("ul { margin-left: 16px; }");
        mCssBuilder.append("li { }");
        mCssBuilder.append("</style></head>");

        Lookup.Result<GameController> toolsResult = Lookup.getDefault().lookupResult(GameController.class);
        toolsResult.addLookupListener((LookupEvent ev) -> {
            updateGameList();
        });

        mSystemCategoryPanel.setHeader(Dict.SYSTEM.toString());
        SystemItem playerSystemItem = new SystemItem(Actions.forID("Game", "org.nbgames.core.actions.PlayerManagerAction"));
        playerSystemItem.setImage(GraphicsHelper.toBufferedImage(MaterialIcon._Social.PEOPLE.get(96, IconColor.getDefault()).getImage()));
        mSystemCategoryPanel.add(playerSystemItem);

        SystemItem pluginSystemItem = new SystemItem(Actions.forID("Game", "org.nbgames.core.actions.PluginAction"));
        pluginSystemItem.setImage(GraphicsHelper.toBufferedImage(MaterialIcon._Av.GAMES.get(96, IconColor.getDefault()).getImage()));
        mSystemCategoryPanel.add(pluginSystemItem);
    }

    private synchronized void updateGameList() {
        panel.removeAll();
        Set<Map.Entry<String, ArrayList<GameController>>> installedGames = mGames.getGameControllersPerCategory().entrySet();
        if (installedGames.isEmpty()) {
//            builder.append("<h1>").append(DictNbg.NO_INSTALLED_GAMES.toString()).append("</h1>");
        } else {
            installedGames.forEach((category) -> {
                CategoryPanel categoryPanel = new CategoryPanel();
                categoryPanel.setHeader(category.getKey());
                panel.add(categoryPanel);

                category.getValue().forEach((controller) -> {
                    categoryPanel.add(new GameItem(controller));
                });
            });
        }

        panel.add(mSystemCategoryPanel);
        panel.add(new JPanel()); //Fixes a layout issue
    }

    private void updatePlayerManagerPanel() {
        SwingHelper.enableComponents(panel, !mPlayerManager.select().isEmpty(), mSystemCategoryPanel);
        playerManagerPanel.setVisible(mPlayerManager.select().isEmpty());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playerManagerPanel = new javax.swing.JPanel();
        playerManagerLabel = new javax.swing.JLabel();
        playerManagerButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        playerManagerLabel.setText(org.openide.util.NbBundle.getMessage(HomePanel.class, "HomePanel.playerManagerLabel.text")); // NOI18N

        playerManagerButton.setText(org.openide.util.NbBundle.getMessage(HomePanel.class, "HomePanel.playerManagerButton.text")); // NOI18N
        playerManagerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerManagerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout playerManagerPanelLayout = new javax.swing.GroupLayout(playerManagerPanel);
        playerManagerPanel.setLayout(playerManagerPanelLayout);
        playerManagerPanelLayout.setHorizontalGroup(
            playerManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerManagerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playerManagerLabel)
                .addGap(18, 18, 18)
                .addComponent(playerManagerButton)
                .addContainerGap())
        );
        playerManagerPanelLayout.setVerticalGroup(
            playerManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerManagerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(playerManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerManagerLabel)
                    .addComponent(playerManagerButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(playerManagerPanel, java.awt.BorderLayout.NORTH);

        mainPanel.setLayout(new java.awt.BorderLayout());

        scrollPane.setMinimumSize(new java.awt.Dimension(0, 0));
        scrollPane.setPreferredSize(new java.awt.Dimension(0, 0));

        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.PAGE_AXIS));
        scrollPane.setViewportView(panel);

        mainPanel.add(scrollPane, java.awt.BorderLayout.CENTER);

        add(mainPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void playerManagerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerManagerButtonActionPerformed
        Actions.forID("Game", "org.nbgames.core.actions.PlayerManagerAction").actionPerformed(null);
    }//GEN-LAST:event_playerManagerButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panel;
    private javax.swing.JButton playerManagerButton;
    private javax.swing.JLabel playerManagerLabel;
    private javax.swing.JPanel playerManagerPanel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
