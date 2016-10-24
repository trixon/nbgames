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
import org.nbgames.core.options.PlayersOptionsPanelController;
import org.netbeans.api.options.OptionsDisplayer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ActionID(
        category = "File",
        id = "org.nbgames.core.actions.PlayerManagerAction"
)
@ActionRegistration(
        iconBase = "org/nbgames/core/res/system-users.png",
        displayName = "#CTL_PlayerManagerAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 30)
    ,
    @ActionReference(path = "Toolbars/File", position = 130)
})
@NbBundle.Messages("CTL_PlayerManagerAction=Players")
public final class PlayerManagerAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        OptionsDisplayer.getDefault().open(PlayersOptionsPanelController.ID);
    }
}
