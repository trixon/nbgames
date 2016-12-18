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
package org.nbgames.core.ui;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.prefs.PreferenceChangeEvent;
import org.nbgames.core.InstalledGames;
import org.nbgames.core.api.DictNbg;
import org.nbgames.core.api.GameController;
import org.nbgames.core.api.TriggerManager;
import org.nbgames.core.api.db.manager.PlayerManager;
import org.nbgames.core.api.service.GameProvider;
import org.openide.awt.Actions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import se.trixon.almond.util.Dict;

/**
 *
 * @author Patrik Karlsson
 */
public class HomePanel extends javax.swing.JPanel {

    private final InstalledGames mGames;
    private StringBuilder mCssBuilder;

    /**
     * Creates new form HomePanel
     */
    public HomePanel() {
        mGames = InstalledGames.getInstance();
        initComponents();
        TriggerManager.getInstance().getPreferences().addPreferenceChangeListener((PreferenceChangeEvent evt) -> {
            if (evt.getKey().equalsIgnoreCase(PlayerTrigger.class.getName())) {
                updatePlayerManagerPanel();
            }
        });
        init();
        updatePlayerManagerPanel();
        updateGameList();
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

        Lookup.Result<GameProvider> toolsResult = Lookup.getDefault().lookupResult(GameProvider.class);
        toolsResult.addLookupListener((LookupEvent ev) -> {
            updateGameList();
        });
    }

    private synchronized void updateGameList() {
        StringBuilder builder = new StringBuilder(mCssBuilder);
        Set<Map.Entry<String, ArrayList<GameController>>> installedGames = mGames.getGameControllersPerCategory().entrySet();
        if (installedGames.isEmpty()) {
            builder.append("<h1>").append(DictNbg.NO_INSTALLED_GAMES.toString()).append("</h1>");
        } else {
            builder.append("<h1>").append(DictNbg.INSTALLED_GAMES.toString()).append("</h1>");

            for (Map.Entry<String, ArrayList<GameController>> category : installedGames) {
                builder.append("<h1>").append(category.getKey()).append("</h1>");
                builder.append("<hr>");

                for (GameController controller : category.getValue()) {
                    String text = String.format("<h2>%s</h2>"
                            + "<i>- %s</i><br />"
                            + "- %s %s, %s",
                            controller.getName(),
                            controller.getDescription(),
                            Dict.VERSION.toString(),
                            controller.getVersion(),
                            controller.getCopyright()
                    );
                    builder.append(text);
                }
            }
        }

        String text = String.format("<p></p><p><tt><center>— %s —</center></tt></p>", DictNbg.INSTALL_GAMES.toString());
        builder.append(text);

        textPane.setText(builder.toString());
        textPane.setCaretPosition(0);
        scrollPane.getVerticalScrollBar().setValue(0);
    }

    private void updatePlayerManagerPanel() {
        playerManagerPanel.setVisible(PlayerManager.getInstance().select().isEmpty());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playerManagerPanel = new javax.swing.JPanel();
        playerManagerLabel = new javax.swing.JLabel();
        playerManagerButton = new javax.swing.JButton();
        copyrightLabel = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();

        setOpaque(false);

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

        copyrightLabel.setText(org.openide.util.NbBundle.getMessage(HomePanel.class, "HomePanel.copyrightLabel.text")); // NOI18N

        textPane.setEditable(false);
        textPane.setContentType("text/html"); // NOI18N
        scrollPane.setViewportView(textPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(copyrightLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playerManagerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 67, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playerManagerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(copyrightLabel)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void playerManagerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerManagerButtonActionPerformed
        Actions.forID("Game", "org.nbgames.core.actions.PlayerManagerAction").actionPerformed(null);
    }//GEN-LAST:event_playerManagerButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel copyrightLabel;
    private javax.swing.JButton playerManagerButton;
    private javax.swing.JLabel playerManagerLabel;
    private javax.swing.JPanel playerManagerPanel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables
}