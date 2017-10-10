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
package org.nbgames.core.ui;

import javax.swing.JPanel;
import org.nbgames.core.api.Player;
import org.nbgames.core.api.Player.Handedness;

/**
 *
 * @author Patrik Karlsson
 */
public class PlayerPanel extends JPanel {

    private Player mPlayer = new Player();

    /**
     * Creates new PlayerPanel
     */
    public PlayerPanel() {
        initComponents();
    }

    public Player getPlayer() {
        Handedness handedness = rightRadioButton.isSelected() ? Handedness.RIGHT : Handedness.LEFT;
        return new Player(mPlayer.getId(), nameTextField.getText(), handedness);
    }

    public void setPlayer(Player player) {
        mPlayer = player;
        nameTextField.setText(player.getName());

        if (player.getHandedness() == Player.Handedness.LEFT) {
            leftRadioButton.doClick();
        } else {
            rightRadioButton.doClick();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        rightRadioButton = new javax.swing.JRadioButton();
        leftRadioButton = new javax.swing.JRadioButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));

        org.openide.awt.Mnemonics.setLocalizedText(nameLabel, org.openide.util.NbBundle.getMessage(PlayerPanel.class, "PlayerPanel.nameLabel.text")); // NOI18N

        buttonGroup1.add(rightRadioButton);
        rightRadioButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(rightRadioButton, org.openide.util.NbBundle.getMessage(PlayerPanel.class, "PlayerPanel.rightRadioButton.text")); // NOI18N

        buttonGroup1.add(leftRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(leftRadioButton, org.openide.util.NbBundle.getMessage(PlayerPanel.class, "PlayerPanel.leftRadioButton.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameTextField)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nameLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(leftRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(rightRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rightRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leftRadioButton))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton leftRadioButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JRadioButton rightRadioButton;
    // End of variables declaration//GEN-END:variables
}
