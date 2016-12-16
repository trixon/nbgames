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
package org.nbgames.core.api;

import java.util.Locale;
import java.util.ResourceBundle;
import se.trixon.almond.util.SystemHelper;

/**
 *
 * @author Patrik Karlsson
 */
public enum DictNbg {

    GAME,
    GAME_OVER,
    GAME_SELECTOR,
    GAME_TYPE,
    GO_HOME,
    HOLD,
    INSTALL_GAMES,
    INSTALLED_GAMES,
    LEVEL,
    NEW_ROUND,
    NUMBER_OF_PLAYERS,
    NO_INSTALLED_GAMES,
    PLAYER,
    PLAYERS,
    SELECT_PLAYER,
    SHUFFLE,
    VARIANT,
    ZZZ;

    private final ResourceBundle mResourceBundle = ResourceBundle.getBundle(SystemHelper.getPackageAsPath(DictNbg.class) + "DictNbg", Locale.getDefault());

    @Override
    public String toString() {
        return mResourceBundle.getString(name().toLowerCase());
    }
}
