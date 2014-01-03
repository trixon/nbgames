package org.nbgames.yaya;

import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import org.nbgames.core.game.NewGamePanel;
import org.nbgames.yaya.gamedef.GameDef;
import org.nbgames.yaya.gamedef.GameType;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class YayaNewGamePanel extends NewGamePanel {

    private final GameDef mGameDef = GameDef.INSTANCE;
    private GameType mGameType;
    private final Options mOptions = Options.INSTANCE;

    /**
     * Creates new form YayaNewGamePanel
     */
    public YayaNewGamePanel() {
        initComponents();
        mGameDef.init();
        typeComboBox.setModel(new DefaultComboBoxModel(mGameDef.getTitleArray()));
        typeComboBox.setSelectedIndex(mGameDef.getIndexForId(mOptions.getGameTypeId()));
        numOfPlayersComboBox.setSelectedIndex(mOptions.getNumOfPlayers() - 1);
    }

    @Override
    protected void saveState() {
        String gameTypeId = mGameDef.getIdForIndex(typeComboBox.getSelectedIndex());

        mOptions.setGameTypeId(gameTypeId);
        String variantTitle = (String) variantComboBox.getSelectedItem();
        String variantByTitle = mGameType.getVariantByTitle(variantTitle);

        mOptions.setGameVariant(gameTypeId, variantByTitle);
        mOptions.setNumOfPlayers(numOfPlayersComboBox.getSelectedIndex() + 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        typeLabel = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox();
        byLineLabel = new javax.swing.JLabel();
        variantLabel = new javax.swing.JLabel();
        variantComboBox = new javax.swing.JComboBox();
        numOfPlayersLabel = new javax.swing.JLabel();
        numOfPlayersComboBox = new javax.swing.JComboBox();

        typeLabel.setLabelFor(typeComboBox);
        org.openide.awt.Mnemonics.setLocalizedText(typeLabel, org.openide.util.NbBundle.getMessage(YayaNewGamePanel.class, "YayaNewGamePanel.typeLabel.text")); // NOI18N

        typeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeComboBoxActionPerformed(evt);
            }
        });

        byLineLabel.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(byLineLabel, "byLine"); // NOI18N

        variantLabel.setLabelFor(variantComboBox);
        org.openide.awt.Mnemonics.setLocalizedText(variantLabel, org.openide.util.NbBundle.getMessage(YayaNewGamePanel.class, "YayaNewGamePanel.variantLabel.text")); // NOI18N

        numOfPlayersLabel.setLabelFor(numOfPlayersComboBox);
        org.openide.awt.Mnemonics.setLocalizedText(numOfPlayersLabel, org.openide.util.NbBundle.getMessage(YayaNewGamePanel.class, "YayaNewGamePanel.numOfPlayersLabel.text")); // NOI18N

        numOfPlayersComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numOfPlayersComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(typeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(variantComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(typeLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(numOfPlayersLabel))
                            .addComponent(variantLabel))
                        .addGap(0, 223, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(byLineLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(typeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(byLineLabel)
                .addGap(18, 18, 18)
                .addComponent(variantLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(variantComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numOfPlayersLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numOfPlayersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void typeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeComboBoxActionPerformed
        mGameType = mGameDef.getType(mGameDef.getIdForIndex(typeComboBox.getSelectedIndex()));
        String byLine = NbBundle.getMessage(getClass(), "ByLine", mGameType.getAuthor());
        byLineLabel.setText(byLine);
        String[] localizedVariant = mGameType.getLocalizedVariants().clone();
        Arrays.sort(localizedVariant);
        variantComboBox.setModel(new DefaultComboBoxModel(localizedVariant));
        variantComboBox.setSelectedIndex(mGameType.getLocalizedIndexForVariantId(mOptions.getGameVariant(mGameType.getId())));
    }//GEN-LAST:event_typeComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel byLineLabel;
    private javax.swing.JComboBox numOfPlayersComboBox;
    private javax.swing.JLabel numOfPlayersLabel;
    private javax.swing.JComboBox typeComboBox;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JComboBox variantComboBox;
    private javax.swing.JLabel variantLabel;
    // End of variables declaration//GEN-END:variables
}
