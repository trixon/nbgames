/*
 * Copyright 2017 Patrik Karlsson.
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
package org.nbgames.core.ui.home;

import java.awt.event.ActionEvent;
import javax.swing.Action;

/**
 *
 * @author Patrik Karlsson
 */
public class SystemItem extends CategoryItem {

    public SystemItem(Action action) {
        setTitle((String) action.getValue(Action.NAME));

        getButton().addActionListener((ActionEvent evt) -> {
            action.actionPerformed(evt);
        });
    }

}
