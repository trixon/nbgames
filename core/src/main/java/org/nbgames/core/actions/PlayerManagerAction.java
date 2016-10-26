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
package org.nbgames.core.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.nbgames.core.DictNbg;
import org.nbgames.core.options.PlayersOptionsPanelController;
import org.nbgames.core.options.PlayersPanel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ActionID(
        category = "Game",
        id = "org.nbgames.core.actions.PlayerManagerAction"
)
@ActionRegistration(displayName = "#CTL_PlayerManagerAction")
@NbBundle.Messages("CTL_PlayerManagerAction=Players")
public final class PlayerManagerAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        PlayersOptionsPanelController controller = new PlayersOptionsPanelController();
        PlayersPanel playersPanel = new PlayersPanel(controller);
        playersPanel.load();
        DialogDescriptor d = new DialogDescriptor(playersPanel, DictNbg.PLAYERS.toString());
        Object retval = DialogDisplayer.getDefault().notify(d);

        if (retval == NotifyDescriptor.OK_OPTION) {
            playersPanel.store();
        } else {
        }
    }
}
