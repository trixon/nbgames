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

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class GameInfoAction {

    @ActionID(category = "game",
            id = "org.nbgames.core.actions.AboutCallbackAction")
    @ActionRegistration(iconBase = "org/nbgames/core/res/dialog-information.png",
            displayName = "#CTL_GameInfoAction",
            iconInMenu = true)
    @ActionReferences({
        @ActionReference(path = "Menu/File", position = 30, separatorAfter = 31)
        ,
        @ActionReference(path = "Toolbars/File", position = 30),})
    @NbBundle.Messages("CTL_GameInfoAction=Game Information")
    public static final String KEY = "GameInfoAction";
}
