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
import org.nbgames.core.presenter.InfoProvider;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson
 */
@ActionID(category = "Game",
        id = "org.nbgames.core.actions.InfoAction")
@ActionRegistration(displayName = "#CTL_InfoAction")
@NbBundle.Messages("CTL_InfoAction=Information")
public final class InfoAction extends NbGameAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        getTopComponent().show(InfoProvider.getInstance());
    }
}
