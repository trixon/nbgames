/*
 * Copyright 2018 Patrik Karlström.
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
import org.nbgames.core.api.NbGames;
import org.nbgames.core.toolbar.AppToolBar;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlström
 */
@ActionID(
        category = "Game", id = "org.nbgames.core.actions.ToolbarAction"
)
@ActionRegistration(
        displayName = "#CTL_ToolbarAction"
)
@ActionReferences({
    @ActionReference(path = "Shortcuts", name = "D-T")
})
@NbBundle.Messages("CTL_ToolbarAction=Toogle toolbar visibility")
public final class ToolbarAction extends NbGameAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        AppToolBar toolbar = NbGames.getAppToolBar();
        toolbar.setVisible(!toolbar.isVisible());
//        getTopComponent().toogleToolbarVisibility();
    }
}
