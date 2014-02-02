package org.nbgames.memroyal;

import org.nbgames.core.game.GamePanel;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class MemroyalPanel extends GamePanel {

    private MemroyalController mMemroyalController;

    /**
     * Creates new form MemroyalPanel
     */
    public MemroyalPanel() {
        initComponents();
    }

    public MemroyalPanel(MemroyalController memroyalController) {
        this();

        mMemroyalController = memroyalController;
    }

    String getGameTitle() {
        return "Memroyal";
    }

    void newGame() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
