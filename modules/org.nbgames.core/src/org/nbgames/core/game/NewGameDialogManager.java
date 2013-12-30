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
public class NewGameDialogManager {

    private final ActionListener mActionListener;
    private final JButton mCancelButton = new JButton();
    private final DialogDescriptor mDialogDescriptor;
    private final JButton mStartButton = new JButton();

    public NewGameDialogManager(final NewGamePanel newGamePanel, final NewGameController newGameController) {
        ResourceBundle bundle = NbBundle.getBundle(NewGameDialogManager.class);
        mCancelButton.setText(bundle.getString("cancelButton.text"));
        mStartButton.setText(bundle.getString("startButton.text"));
        Object[] buttons = new Object[]{mCancelButton, mStartButton};
        mActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == mStartButton) {
                    newGamePanel.saveState();
                    newGameController.onStartNewGame();
                } else if (actionEvent.getSource() == mCancelButton) {
                    newGameController.onCancelNewGame();
                }
            }
        };

        mDialogDescriptor = new DialogDescriptor(newGamePanel,
                bundle.getString("CTL_NewGameTitle"),
                true,
                buttons,
                mStartButton,
                DialogDescriptor.DEFAULT_ALIGN,
                HelpCtx.DEFAULT_HELP,
                mActionListener);

        mDialogDescriptor.setClosingOptions(buttons);
    }

    public DialogDescriptor getDialogDescriptor() {
        return mDialogDescriptor;
    }

    public interface NewGameController {

        public void onCancelNewGame();

        public void onStartNewGame();
    }
}
