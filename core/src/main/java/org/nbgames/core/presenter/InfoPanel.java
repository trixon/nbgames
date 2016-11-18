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

import org.nbgames.core.api.PresenterProvider;
import se.trixon.almond.util.Dict;

/**
 *
 * @author Patrik Karlsson
 */
public class InfoPanel extends javax.swing.JPanel {

    /**
     * Creates new form OptionsPanel
     */
    public InfoPanel() {
        initComponents();
        init();
    }

    public void load(PresenterProvider presenter) {
        String string = String.format("<html><h1>"
                + "%s</h1>"
                + "%s: %s<br />"
                + "%s: %s<br />"
                + "%s: %s<br />"
                + "%s: %s<br />"
                + "%s: %s"
                + "</html>",
                presenter.getName(),
                Dict.VERSION.toString(), presenter.getVersion(),
                Dict.COPYRIGHT.toString(), presenter.getCopyright(),
                Dict.DESCRIPTION.toString(), presenter.getDescription(),
                Dict.LICENSE.toString(), presenter.getLicense(),
                Dict.CREDIT.toString(), presenter.getCredit()
        );

        jTextPane1.setText(string);
    }

    private void init() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(0, 255, 204));
        setLayout(new java.awt.GridBagLayout());

        jTextPane1.setEditable(false);
        jTextPane1.setContentType("text/html"); // NOI18N
        jTextPane1.setFocusable(false);
        jScrollPane2.setViewportView(jTextPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

}
