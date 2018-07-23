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
package org.nbgames.core.ui.home;

import java.awt.event.ActionEvent;
import org.nbgames.core.actions.NbGameAction;
import org.nbgames.core.api.GameController;
import se.trixon.almond.util.Dict;

/**
 *
 * @author Patrik Karlström
 */
public class GameItem extends CategoryItem {

    private GameController mController;

    public GameItem(GameController controller) {
        mController = controller;
        setTitle(controller.getName());

        String text = String.format("<html><h2>%s</h2>"
                + "<i>%s</i><br />"
                + "%s %s, %s</html>",
                controller.getName(),
                controller.getDescription(),
                Dict.VERSION.toString(),
                controller.getVersion(),
                controller.getCopyright()
        );

        getButton().setToolTipText(text);
        getButton().addActionListener((ActionEvent evt) -> {
            NbGameAction.getTopComponent().show(mController);
        });

        setImage(controller.getImage());
    }
}
