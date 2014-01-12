package org.nbgames.core.options;

import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import org.nbgames.core.Player;
import org.nbgames.core.PlayerManager;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
final class PlayersPanel extends javax.swing.JPanel {

    private final PlayersOptionsPanelController mController;
    private DefaultListModel mModel = new DefaultListModel();

    PlayersPanel(PlayersOptionsPanelController controller) {
        mController = controller;
        initComponents();
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
        list = new javax.swing.JList();

        setPreferredSize(new java.awt.Dimension(360, 160));

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/nbgames/core/res/list-add-user24.png"))); // NOI18N
        addButton.setToolTipText(org.openide.util.NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.add")); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        toolBar.add(addButton);

        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/nbgames/core/res/user-properties24.png"))); // NOI18N
        editButton.setToolTipText(org.openide.util.NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.edit")); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        toolBar.add(editButton);

        removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/nbgames/core/res/list-remove-user24.png"))); // NOI18N
        removeButton.setToolTipText(org.openide.util.NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.remove")); // NOI18N
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        toolBar.add(removeButton);

        removeAllButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/nbgames/core/res/user-group-delete24.png"))); // NOI18N
        removeAllButton.setToolTipText(org.openide.util.NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.removeAll")); // NOI18N
        removeAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllButtonActionPerformed(evt);
            }
        });
        toolBar.add(removeAllButton);

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
            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private boolean isValidPlayer(Player player) {
        return !player.getName().isEmpty();
    }

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        if (getSelectedPlayer() != null) {
            NotifyDescriptor d = new NotifyDescriptor(
                    NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.message.remove", getSelectedPlayer().getName()),
                    NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.remove"),
                    NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.WARNING_MESSAGE,
                    null,
                    null);
            Object retval = DialogDisplayer.getDefault().notify(d);

            if (retval == NotifyDescriptor.OK_OPTION) {
                mModel.removeElement(getSelectedPlayer());
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
                    null,
                    null);
            Object retval = DialogDisplayer.getDefault().notify(d);

            if (retval == NotifyDescriptor.OK_OPTION) {
                mModel.removeAllElements();
            }
        }
    }//GEN-LAST:event_removeAllButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        PlayerPanel playerPanel = new PlayerPanel();
        playerPanel.setPlayer(new Player());
        DialogDescriptor d = new DialogDescriptor(playerPanel, NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.add"));
        Object retval = DialogDisplayer.getDefault().notify(d);

        if (retval == NotifyDescriptor.OK_OPTION) {
            if (isValidPlayer(playerPanel.getPlayer())) {
                mModel.addElement(playerPanel.getPlayer());
                sortModel();
            } else {
                showInvalidPlayerDialog();
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if (getSelectedPlayer() != null) {
            PlayerPanel playerPanel = new PlayerPanel();
            playerPanel.setPlayer(getSelectedPlayer());
            DialogDescriptor d = new DialogDescriptor(playerPanel, NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.edit"));
            Object retval = DialogDisplayer.getDefault().notify(d);

            if (retval == NotifyDescriptor.OK_OPTION) {
                if (isValidPlayer(playerPanel.getPlayer())) {
                    mModel.set(mModel.indexOf(getSelectedPlayer()), playerPanel.getPlayer());
                    sortModel();
                } else {
                    showInvalidPlayerDialog();
                }
            }
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void showInvalidPlayerDialog() {
        NotifyDescriptor d = new NotifyDescriptor(
                NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.message.invalidPlayer"),
                NbBundle.getMessage(PlayersPanel.class, "PlayersDialog.title.invalidInput"),
                NotifyDescriptor.ERROR_MESSAGE,
                NotifyDescriptor.ERROR_MESSAGE,
                new JButton[]{new JButton("Ok")},
                null);
        DialogDisplayer.getDefault().notify(d);
    }

    private void sortModel() {
        Object[] players = mModel.toArray();
        Arrays.sort(players);
        mModel.clear();

        for (Object object : players) {
            mModel.addElement(object);
        }
    }
    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
            editButtonActionPerformed(null);
        }
    }//GEN-LAST:event_listMouseClicked

    private Player getSelectedPlayer() {
        return (Player) list.getSelectedValue();
    }

    void load() {
        mModel = PlayerManager.INSTANCE.load(mModel);
        mModel.addListDataListener(new ListDataListener() {

            @Override
            public void contentsChanged(ListDataEvent e) {
                mController.changed();
            }

            @Override
            public void intervalAdded(ListDataEvent e) {
                mController.changed();
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
                mController.changed();
            }
        });

        list.setModel(mModel);
    }

    void store() {
        PlayerManager.INSTANCE.store(mModel);
    }

    boolean valid() {
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton editButton;
    private javax.swing.JList list;
    private javax.swing.JButton removeAllButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
