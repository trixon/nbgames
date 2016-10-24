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
package org.nbgames.core.game;

import org.nbgames.core.base.NewGamePanel;
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
        
        mActionListener = (ActionEvent actionEvent) -> {
            if (actionEvent.getSource() == mStartButton) {
                newGamePanel.saveState();
                newGameController.onRequestNewGameStart();
            } else if (actionEvent.getSource() == mCancelButton) {
                newGameController.onRequestNewGameCancel();
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
}
