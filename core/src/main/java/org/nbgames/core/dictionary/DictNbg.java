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
package org.nbgames.core.dictionary;

import java.util.Locale;
import java.util.ResourceBundle;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum DictNbg {

    GAME_OVER,
    GAME_TYPE,
    LEVEL,
    SHUFFLE;
    private final ResourceBundle mDefaultResourceBundle = ResourceBundle.getBundle("org/nbgames/core/dictionary/Bundle", Locale.getDefault());
    private final ResourceBundle mResourceBundle = NbBundle.getBundle(DictNbg.class);

    public String getString() {
        return mResourceBundle.getString(name().toLowerCase());
    }

    public String getStringDefault() {
        return mDefaultResourceBundle.getString(name().toLowerCase());
    }
}
