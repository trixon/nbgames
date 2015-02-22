package org.nbgames.memroyal;

import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import org.nbgames.core.PlayerManager;
import org.nbgames.core.dictionary.DictNbg;
import org.nbgames.core.base.NewGamePanel;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class MemroyalNewGamePanel extends NewGamePanel {

    private final Options mOptions = Options.INSTANCE;

    /**
     * Creates new form GunuNewGamePanel
     */
    public MemroyalNewGamePanel() {
        initComponents();
        Object[] players = PlayerManager.INSTANCE.getPlayersArray();
        init();
    }

    @Override
    public void saveState() {
//        Options.INSTANCE.setPlayer(name);
        mOptions.setLevel(levelSlider.getValue());
        mOptions.setVariation(Rules.Variation.values()[variationComboBox.getSelectedIndex()]);
    }

    private void init() {
        LinkedList<String> variationNames = new LinkedList<String>();

        for (Rules.Variation variation : Rules.Variation.values()) {
            variationNames.add(NbBundle.getMessage(this.getClass(), variation.toString()));
        }

        variationComboBox.setModel(new DefaultComboBoxModel(variationNames.toArray()));
        variationComboBox.setSelectedIndex(mOptions.getVariationOrdinal());
        levelSlider.setValue(mOptions.getLevel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        levelLabel = new javax.swing.JLabel();
        levelSlider = new javax.swing.JSlider();
        variationComboBox = new javax.swing.JComboBox();
        variationLabel = new javax.swing.JLabel();
        visualPanel = new javax.swing.JPanel();

        org.openide.awt.Mnemonics.setLocalizedText(levelLabel, DictNbg.LEVEL.getString());

        levelSlider.setMajorTickSpacing(2);
        levelSlider.setMaximum(13);
        levelSlider.setMinimum(1);
        levelSlider.setMinorTickSpacing(1);
        levelSlider.setPaintLabels(true);
        levelSlider.setPaintTicks(true);
        levelSlider.setSnapToTicks(true);

        org.openide.awt.Mnemonics.setLocalizedText(variationLabel, DictNbg.GAME_TYPE.getString());

        visualPanel.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(visualPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(variationLabel)
                        .addComponent(levelLabel)
                        .addComponent(levelSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(variationComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(variationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(variationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(levelLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(levelSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(visualPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel levelLabel;
    private javax.swing.JSlider levelSlider;
    private javax.swing.JComboBox variationComboBox;
    private javax.swing.JLabel variationLabel;
    private javax.swing.JPanel visualPanel;
    // End of variables declaration//GEN-END:variables
}
