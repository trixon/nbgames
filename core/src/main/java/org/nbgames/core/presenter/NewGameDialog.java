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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.nbgames.core.DictNbg;
import org.nbgames.core.GameController;

/**
 *
 * @author Patrik Karlsson
 */
public class NewGameDialog extends DialogPanel {

    public NewGameDialog() {
        setTitle(DictNbg.NEW_ROUND.toString());
        getInnerPanel().setPreferredSize(mMediumDimension);
        setButtonBarVisible(true);
    }

    public void setGameController(final GameController controller) {
        for (ActionListener actionListener : getOkButton().getActionListeners()) {
            getOkButton().removeActionListener(actionListener);
        }

        getOkButton().addActionListener((ActionEvent e) -> {
            controller.getNewGamePanel().saveState();
            controller.onRequestNewGameStart();
            close();
        });
    }
}
