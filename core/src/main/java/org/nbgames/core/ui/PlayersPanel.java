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

import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import org.apache.commons.lang3.StringUtils;
import org.nbgames.core.api.NbGames;
import org.nbgames.core.api.Player;
import org.nbgames.core.api.db.manager.PlayerManager;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import se.trixon.almond.nbp.Almond;
import se.trixon.almond.util.icons.IconColor;
import se.trixon.almond.util.icons.material.MaterialIcon;

/**
 *
 * @author Patrik Karlsson
 */
public final class PlayersPanel extends javax.swing.JPanel {

    protected final IconColor mIconColor = NbGames.getAlmondOptions().getIconColor();
    protected final int mIconSize = (int) (Almond.ICON_LARGE / 1.5);
    private DefaultListModel<Player> mModel = new DefaultListModel();
    private final PlayerManager mPlayerManager = PlayerManager.getInstance();
    private final HashSet<Player> mChangeSet = new HashSet<>();
    private final HashSet<Player> mDeleteSet = new HashSet<>();
    private JButton mDialogCancelButton;
    private JButton mDialogOkButton;
    private Object[] mDialogOptions;

    public PlayersPanel() {
        initComponents();
        init();
    }

    public void load() {
        mModel = mPlayerManager.getListModel();
        list.setModel(mModel);
        mChangeSet.clear();
        mDeleteSet.clear();
    }

    public void store() {
        try {
            mPlayerManager.save(mChangeSet, mDeleteSet);
        } catch (ClassNotFoundException | SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private void edit(Player player) {
        PlayerPanel playerPanel = new PlayerPanel();
        String title = NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.edit");

        boolean doEdit = true;
        if (player == null) {
            player = new Player();
            title = NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.add");
            doEdit = false;
        }

        playerPanel.setPlayer(player);

        NotifyDescriptor d = new NotifyDescriptor(
                playerPanel,
                title,
                NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE,
                mDialogOptions,
                mDialogOkButton);

        Object retVal = DialogDisplayer.getDefault().notify(d);
        if (retVal == mDialogOkButton) {
            if (isValidPlayer(playerPanel.getPlayer())) {
                mChangeSet.add(playerPanel.getPlayer());
                if (doEdit) {
                    mModel.set(mModel.indexOf(getSelectedPlayer()), playerPanel.getPlayer());
                } else {
                    mModel.addElement(playerPanel.getPlayer());
                }
                sortModel();
            } else {
                showInvalidPlayerDialog();
            }
        }
    }

    private Player getSelectedPlayer() {
        return list.getSelectedValue();
    }

    private void init() {
        mDialogOkButton = new JButton(MaterialIcon._Action.DONE.get(Almond.ICON_LARGE, mIconColor));
        mDialogCancelButton = new JButton(MaterialIcon._Navigation.CLOSE.get(Almond.ICON_LARGE, mIconColor));
        mDialogOptions = new Object[]{mDialogCancelButton, mDialogOkButton};

        addButton.setIcon(MaterialIcon._Content.ADD.get(mIconSize, mIconColor));
        editButton.setIcon(MaterialIcon._Content.CREATE.get(mIconSize, mIconColor));
        removeButton.setIcon(MaterialIcon._Content.REMOVE.get(mIconSize, mIconColor));
        removeAllButton.setIcon(MaterialIcon._Content.CLEAR.get(mIconSize, mIconColor));
    }

    private boolean isValidPlayer(Player player) {
        return !player.getName().isEmpty();
    }

    private void showInvalidPlayerDialog() {
        NotifyDescriptor d = new NotifyDescriptor(
                NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.message.invalidPlayer"),
                NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.invalidInput"),
                NotifyDescriptor.ERROR_MESSAGE,
                NotifyDescriptor.ERROR_MESSAGE,
                new JButton[]{mDialogOkButton},
                mDialogOkButton);
        DialogDisplayer.getDefault().notify(d);
    }

    private void sortModel() {
        Object[] players = mModel.toArray();
        Arrays.sort(players);
        mModel.clear();

        for (Object object : players) {
            mModel.addElement((Player) object);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        removeAllButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();

        setPreferredSize(new java.awt.Dimension(360, 280));

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        addButton.setToolTipText(org.openide.util.NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.add")); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        toolBar.add(addButton);

        editButton.setToolTipText(org.openide.util.NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.edit")); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        toolBar.add(editButton);

        removeButton.setToolTipText(org.openide.util.NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.remove")); // NOI18N
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        toolBar.add(removeButton);

        removeAllButton.setToolTipText(org.openide.util.NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.removeAll")); // NOI18N
        removeAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllButtonActionPerformed(evt);
            }
        });
        toolBar.add(removeAllButton);

        list.setFont(list.getFont().deriveFont(list.getFont().getSize()+6f));
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(list);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        if (getSelectedPlayer() != null) {
            NotifyDescriptor d = new NotifyDescriptor(
                    NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.message.remove", getSelectedPlayer().getName()),
                    NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.remove"),
                    NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.WARNING_MESSAGE,
                    mDialogOptions,
                    mDialogOkButton);

            Object retVal = DialogDisplayer.getDefault().notify(d);
            if (retVal == mDialogOkButton) {
                Player player = getSelectedPlayer();
                if (player.getId() != null) {
                    mDeleteSet.add(player);
                }

                for (Player p : mChangeSet) {
                    if (StringUtils.equals(player.getName(), p.getName())) {
                        mChangeSet.remove(p);
                        break;
                    }
                }

                mModel.removeElement(player);
            }
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    private void removeAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllButtonActionPerformed
        if (!mModel.isEmpty()) {
            NotifyDescriptor d = new NotifyDescriptor(
                    NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.message.removeAll"),
                    NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.removeAll"),
                    NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.WARNING_MESSAGE,
                    mDialogOptions,
                    mDialogOkButton);

            Object retVal = DialogDisplayer.getDefault().notify(d);
            if (retVal == mDialogOkButton) {
                for (int i = 0; i < mModel.size(); i++) {
                    Player player = mModel.get(i);
                    if (player.getId() != null) {
                        mDeleteSet.add(player);
                    }
                }
                mChangeSet.clear();
                mModel.removeAllElements();
            }
        }
    }//GEN-LAST:event_removeAllButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        edit(null);
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if (getSelectedPlayer() != null) {
            edit(getSelectedPlayer());
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
            editButtonActionPerformed(null);
        }
    }//GEN-LAST:event_listMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton editButton;
    private javax.swing.JList<Player> list;
    private javax.swing.JButton removeAllButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
