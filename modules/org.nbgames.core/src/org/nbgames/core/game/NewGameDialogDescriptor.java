package org.nbgames.core.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import org.openide.DialogDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public abstract class NewGameDialogDescriptor extends DialogDescriptor {

    private final JButton mCancelButton = new JButton();
    private NewGamePanel mNewGamePanel;
    private final JButton mStartButton = new JButton();

    public NewGameDialogDescriptor(NewGamePanel newGamePanel) {
        super(newGamePanel, null);
        ResourceBundle bundle = NbBundle.getBundle(NewGameDialogDescriptor.class);
        setTitle(bundle.getString("CTL_NewGameTitle"));
        mCancelButton.setText(bundle.getString("cancelButton.text"));
        mStartButton.setText(bundle.getString("startButton.text"));
        mNewGamePanel = newGamePanel;
        setModal(true);
        setValue(mStartButton);
        setOptions(new Object[]{mCancelButton, mStartButton});
        setButtonListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == mStartButton) {
                    mNewGamePanel.save();
                    onStartEvent();
                } else if (actionEvent.getSource() == mCancelButton) {
                    onCancelEvent();
                }
            }
        });
    }

    private NewGameDialogDescriptor(Object innerPane, String title, boolean isModal, ActionListener bl) {
        super(innerPane, title, isModal, bl);
    }

    private NewGameDialogDescriptor(Object innerPane, String title, boolean isModal, int optionType, Object initialValue, ActionListener bl) {
        super(innerPane, title, isModal, optionType, initialValue, bl);
    }

    private NewGameDialogDescriptor(Object innerPane, String title, boolean modal, Object[] options, Object initialValue, int optionsAlign, HelpCtx helpCtx, ActionListener bl) {
        super(innerPane, title, modal, options, initialValue, optionsAlign, helpCtx, bl);
    }

    private NewGameDialogDescriptor(Object innerPane, String title, boolean modal, Object[] options, Object initialValue, int optionsAlign, HelpCtx helpCtx, ActionListener bl, boolean leaf) {
        super(innerPane, title, modal, options, initialValue, optionsAlign, helpCtx, bl, leaf);
    }

    private NewGameDialogDescriptor(Object innerPane, String title, boolean isModal, int optionType, Object initialValue, int optionsAlign, HelpCtx helpCtx, ActionListener bl) {
        super(innerPane, title, isModal, optionType, initialValue, optionsAlign, helpCtx, bl);
    }

    protected abstract void onCancelEvent();

    protected abstract void onStartEvent();
}
