/* 
 * Copyright 2015 Patrik Karlsson.
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
package org.nbgames.core.startpage;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.Preferences;
import org.nbgames.core.base.BaseTopComponent;
import org.nbgames.core.PlayerManager;
import org.nbgames.core.options.PlayersOptionsPanelController;
import org.netbeans.api.options.OptionsDisplayer;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;
import org.openide.windows.TopComponent;

@ConvertAsProperties(
        dtd = "-//org.nbgames.core//StartPage//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "StartPageTopComponent",
        iconBase = "org/nbgames/core/res/go-home.png",
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.nbgames.core.StartPageTopComponent")
@ActionReferences({
    @ActionReference(path = "Menu/Help"),
    @ActionReference(path = "Toolbars/File", position = 999)})
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_StartPageAction",
        preferredID = "StartPageTopComponent"
)
@Messages({
    "CTL_StartPageAction=Start Page",
})
/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public final class StartPageTopComponent extends BaseTopComponent {

    public static final String KEY_SHOW_START_PAGE_ON_STARTUP = "showStartPageOnStartup";
    private final Preferences mPreferences;

    public StartPageTopComponent() {
        mPreferences = NbPreferences.forModule(StartPageTopComponent.class);
        initComponents();
        setName(Bundle.CTL_StartPageTopComponent());
        PlayerManager.INSTANCE.getPreferences().addPreferenceChangeListener((PreferenceChangeEvent evt) -> {
            updatePlayerManagerPanel();
        });
    }

    private void updatePlayerManagerPanel() {
        playerManagerPanel.setVisible(PlayerManager.INSTANCE.getPlayers().isEmpty());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerLabel = new javax.swing.JLabel();
        startCheckBox = new javax.swing.JCheckBox();
        playerManagerPanel = new javax.swing.JPanel();
        playerManagerLabel = new javax.swing.JLabel();
        playerManagerButton = new javax.swing.JButton();

        headerLabel.setFont(new java.awt.Font("Lucida Grande", 3, 36)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(headerLabel, org.openide.util.NbBundle.getMessage(StartPageTopComponent.class, "StartPageTopComponent.headerLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(startCheckBox, org.openide.util.NbBundle.getMessage(StartPageTopComponent.class, "StartPageTopComponent.startCheckBox.text")); // NOI18N
        startCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startCheckBoxActionPerformed(evt);
            }
        });

        playerManagerPanel.setBackground(new java.awt.Color(255, 255, 0));

        org.openide.awt.Mnemonics.setLocalizedText(playerManagerLabel, org.openide.util.NbBundle.getMessage(StartPageTopComponent.class, "StartPageTopComponent.playerManagerLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(playerManagerButton, org.openide.util.NbBundle.getMessage(StartPageTopComponent.class, "StartPageTopComponent.playerManagerButton.text")); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerManagerButton)
                .addContainerGap(36, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startCheckBox))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(headerLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(playerManagerPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(startCheckBox)
                .addGap(4, 4, 4)
                .addComponent(headerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerManagerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(177, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void startCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startCheckBoxActionPerformed
        mPreferences.putBoolean(KEY_SHOW_START_PAGE_ON_STARTUP, startCheckBox.isSelected());
    }//GEN-LAST:event_startCheckBoxActionPerformed

    private void playerManagerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerManagerButtonActionPerformed
        OptionsDisplayer.getDefault().open(PlayersOptionsPanelController.ID);
    }//GEN-LAST:event_playerManagerButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel headerLabel;
    private javax.swing.JButton playerManagerButton;
    private javax.swing.JLabel playerManagerLabel;
    private javax.swing.JPanel playerManagerPanel;
    private javax.swing.JCheckBox startCheckBox;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        startCheckBox.setSelected(mPreferences.getBoolean(KEY_SHOW_START_PAGE_ON_STARTUP, true));
        updatePlayerManagerPanel();
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0"); //NOI18N
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version"); //NOI18N
    }
}
