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
package org.nbgames.core.api.ui;

import javax.swing.JButton;
import org.nbgames.core.api.NbGames;
import se.trixon.almond.nbp.Almond;
import se.trixon.almond.util.icons.IconColor;
import se.trixon.almond.util.icons.material.MaterialIcon;

/**
 *
 * @author Patrik Karlsson
 */
public class DialogButtonManager {

    private final JButton mCancel;
    private final IconColor mIconColor = NbGames.getAlmondOptions().getIconColor();
    private final JButton mOk;

    public static DialogButtonManager getInstance() {
        return Holder.INSTANCE;
    }

    private DialogButtonManager() {
        mOk = new JButton(MaterialIcon._Action.DONE.get(Almond.ICON_LARGE, mIconColor));
        mCancel = new JButton(MaterialIcon._Navigation.CLOSE.get(Almond.ICON_LARGE, mIconColor));
    }

    public JButton getCancel() {
        return mCancel;
    }

    public JButton getOk() {
        return mOk;
    }

    private static class Holder {

        private static final DialogButtonManager INSTANCE = new DialogButtonManager();
    }
}
