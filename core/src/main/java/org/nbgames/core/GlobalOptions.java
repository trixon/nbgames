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
package org.nbgames.core;

import java.awt.Color;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;
import se.trixon.almond.util.GraphicsHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum GlobalOptions {

    INSTANCE;
    private static final String DEFAULT_COLOR_WINDOW_LOWER = "#003300";
    private static final String DEFAULT_COLOR_WINDOW_UPPER = "#009900";
    private Preferences mPreferences = NbPreferences.forModule(GlobalOptions.class);

    public Color getColor(ColorItem colorItem) {
        return Color.decode(mPreferences.get(colorItem.getKey(), colorItem.getDefaultColor()));
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public void setColor(ColorItem colorItem, Color color) {
        mPreferences.put(colorItem.getKey(), GraphicsHelper.colorToString(color));
    }

    public void setPreferences(Preferences preferences) {
        mPreferences = preferences;
    }

    public enum ColorItem {

        WINDOW_LOWER(DEFAULT_COLOR_WINDOW_LOWER), WINDOW_UPPER(DEFAULT_COLOR_WINDOW_UPPER);
        private final String mDefaultColor;

        ColorItem(String defaultColor) {
            mDefaultColor = defaultColor;
        }

        public String getDefaultColor() {
            return mDefaultColor;
        }

        public String getKey() {
            return "color." + name().toLowerCase();
        }
    }
}
