/* 
 * Copyright 2015 Patrik Karlsson.
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
package org.nbgames.memroyal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ActionID(
        category = "File/Card",
        id = "org.nbgames.memroyal.MemroyalAction"
)
@ActionRegistration(
        displayName = "#CTL_Name"
)
@ActionReference(path = "Menu/File/Card")
public final class MemroyalAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MemroyalTopComponent topComponent = new MemroyalTopComponent();
        topComponent.open();
        topComponent.requestActive();
    }
}
