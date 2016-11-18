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
package org.nbgames.core.presenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.nbgames.core.NbGamesTopComponent;
import org.nbgames.core.NbgOptions;
import org.openide.windows.WindowManager;
import se.trixon.almond.nbp.Almond;
import se.trixon.almond.util.AlmondOptions;
import se.trixon.almond.util.icons.IconColor;
import se.trixon.almond.util.icons.material.MaterialIcon;
import se.trixon.almond.util.swing.SwingHelper;

/**
 *
 * @author Patrik Karlsson
 */
public class DialogPanel extends JPanel {

    protected final IconColor mIconColor = AlmondOptions.getInstance().getIconColor();
    protected final Dimension mMediumDimension = new Dimension(640, 480);
    protected final int mIconSize = (int) (Almond.ICON_LARGE / 1.5);
    private final NbGamesTopComponent mTopComponent = (NbGamesTopComponent) WindowManager.getDefault().findTopComponent("NbGamesTopComponent");
    private JComponent mComponent;
    private final NbgOptions mOptions = NbgOptions.getInstance();

    /**
     * Creates new form OptionsPanel
     */
    public DialogPanel() {
        initComponents();
        init();
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JComponent getComponent() {
        return mComponent;
    }

    public JPanel getInnerPanel() {
        return innerPanel;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setContent(JComponent component) {
        mComponent = component;
        panel.removeAll();
        panel.add(component);
    }

    public void setTitle(String title) {
        mTitleLabel.setText(title);
    }

    public void setButtonBarVisible(boolean state) {
        buttonBar.setVisible(state);
    }

    protected void close() {
        mTopComponent.closeDialog(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color upperColor = new Color(0x0, 0x0, 0x0, 0xAA);
        Color lowerColor = new Color(0x0, 0x0, 0x0, 0xAA);
        GradientPaint gp = new GradientPaint(0, 0, upperColor, 0, h, lowerColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

    private void init() {
        backButton.setIcon(MaterialIcon._Navigation.CANCEL.get(mIconSize, mIconColor));
        cancelButton.setIcon(MaterialIcon._Navigation.CLOSE.get(Almond.ICON_LARGE, mIconColor));
        okButton.setIcon(MaterialIcon._Action.DONE.get(Almond.ICON_LARGE, mIconColor));
        setOpaque(false);
//        setBackground(new Color(5, 5, 5, 0x55));

        setButtonBarVisible(false);
        SwingHelper.borderPainted(toolBar, false);
        SwingHelper.borderPainted(buttonBar, false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        innerPanel = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        backButton = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 32767));
        mTitleLabel = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        buttonBar = new javax.swing.JToolBar();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        cancelButton = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(32, 0), new java.awt.Dimension(32, 0), new java.awt.Dimension(32, 32767));
        okButton = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));

        setLayout(new java.awt.GridBagLayout());

        innerPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        innerPanel.setPreferredSize(new java.awt.Dimension(300, 384));
        innerPanel.setLayout(new java.awt.BorderLayout());

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        backButton.setFocusable(false);
        backButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        toolBar.add(backButton);
        toolBar.add(filler3);

        mTitleLabel.setFont(mTitleLabel.getFont().deriveFont(mTitleLabel.getFont().getSize()+6f));
        mTitleLabel.setText("UNSET"); // NOI18N
        toolBar.add(mTitleLabel);

        innerPanel.add(toolBar, java.awt.BorderLayout.PAGE_START);

        panel.setMaximumSize(new java.awt.Dimension(999, 999));
        panel.setOpaque(false);
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.LINE_AXIS));
        innerPanel.add(panel, java.awt.BorderLayout.CENTER);

        buttonBar.setFloatable(false);
        buttonBar.setRollover(true);
        buttonBar.add(filler1);

        cancelButton.setFocusable(false);
        cancelButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonBar.add(cancelButton);
        buttonBar.add(filler5);

        okButton.setFocusable(false);
        okButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        okButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonBar.add(okButton);
        buttonBar.add(filler4);

        innerPanel.add(buttonBar, java.awt.BorderLayout.PAGE_END);

        add(innerPanel, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        close();
    }//GEN-LAST:event_backButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        close();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JToolBar buttonBar;
    private javax.swing.JButton cancelButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.JPanel innerPanel;
    private javax.swing.JLabel mTitleLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel panel;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

}
