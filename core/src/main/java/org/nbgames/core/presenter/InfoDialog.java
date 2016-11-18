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

import java.awt.Dimension;
import se.trixon.almond.util.Dict;

/**
 *
 * @author Patrik Karlsson
 */
public class InfoDialog extends DialogPanel {

    public InfoDialog() {
        setTitle(Dict.INFORMATION.toString());
//        getInnerPanel().setPreferredSize(mMediumDimension);
        getInnerPanel().setMinimumSize(new Dimension(20, 20));
        setContent(new InfoPanel());
    }
}
