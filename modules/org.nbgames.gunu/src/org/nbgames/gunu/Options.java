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
package org.nbgames.gunu;

import java.awt.Color;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;
import se.trixon.almond.GraphicsHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum Options {

    INSTANCE;
    public static final Color DEFAULT_COLOR_BACKGROUND = Color.decode("#656610");
    public static final long DEFAULT_MAX = 1000;
    public static final long DEFAULT_MIN = 0;
    public static final String KEY_COLOR_BACKGROUND = "background";
    public static final String KEY_MAX = "max";
    public static final String KEY_MIN = "min";
    public static final String KEY_PLAYER = "player";
    private Preferences mPreferences = NbPreferences.forModule(Options.class);

    public Color getColorBackground() {
        return Color.decode(mPreferences.get(KEY_COLOR_BACKGROUND, GraphicsHelper.colorToString(DEFAULT_COLOR_BACKGROUND)));
    }

    public long getMax() {
        return mPreferences.getLong(KEY_MAX, DEFAULT_MAX);
    }

    public long getMin() {
        return mPreferences.getLong(KEY_MIN, DEFAULT_MIN);
    }

    public String getPlayer() {
        return mPreferences.get(KEY_PLAYER, "");
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public void setColorBackground(Color color) {
        mPreferences.put(KEY_COLOR_BACKGROUND, GraphicsHelper.colorToString(color));
    }

    public void setMax(long max) {
        mPreferences.putLong(KEY_MAX, max);
    }

    public void setMin(long min) {
        mPreferences.putLong(KEY_MIN, min);
    }

    public void setPlayer(String player) {
        mPreferences.put(KEY_PLAYER, player);
    }

    public void setPreferences(Preferences preferences) {
        mPreferences = preferences;
    }
}
