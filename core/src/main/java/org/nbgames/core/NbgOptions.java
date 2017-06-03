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
package org.nbgames.core;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import org.apache.commons.io.FileUtils;
import org.nbgames.core.api.GameCategory;
import org.openide.util.NbPreferences;
import se.trixon.almond.util.GraphicsHelper;
import se.trixon.almond.util.Xlog;

/**
 *
 * @author Patrik Karlsson
 */
public class NbgOptions {

    public static final String KEY_CURRENT_CATEGORY = "currentCategory";
    public static final String KEY_CURRENT_ID = "currentId";
    public static final String KEY_CUSTOM_TOOLBAR_BACKGROUND = "customToolbarBackground";
    public static final String KEY_CUSTOM_WINDOW_BACKGROUND = "customWindowBackground";
    private static final String DEFAULT_COLOR_TOOLBAR = "#009900";
    private static final String DEFAULT_COLOR_WINDOW_LOWER = "#003300";
    private static final String DEFAULT_COLOR_WINDOW_UPPER = "#009900";
    private static final boolean DEFAULT_CUSTOM_TOOLBAR_BACKGROUND = false;
    private static final boolean DEFAULT_CUSTOM_WINDOW_BACKGROUND = true;
    private final File mDbFile;
    private final File mDirectory;
    private final Preferences mPreferences = NbPreferences.forModule(NbgOptions.class);

    public static NbgOptions getInstance() {
        return Holder.INSTANCE;
    }

    private NbgOptions() {
        mDirectory = new File(System.getProperty("user.home"), ".nbgames");

        try {
            FileUtils.forceMkdir(mDirectory);
        } catch (IOException ex) {
            Xlog.timedErr(ex.getLocalizedMessage());
        }

        mDbFile = new File(mDirectory, "nbgames");
    }

    public Color getColor(ColorItem colorItem) {
        return Color.decode(mPreferences.get(colorItem.getKey(), colorItem.getDefaultColor()));
    }

    public GameCategory getCurrentCategory() {
        try {
            return GameCategory.valueOf(mPreferences.get(KEY_CURRENT_CATEGORY, ""));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getCurrentId() {
        return mPreferences.get(KEY_CURRENT_ID, "");
    }

    public File getDbFile() {
        return mDbFile;
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public boolean isCustomToolbarBackground() {
        return mPreferences.getBoolean(KEY_CUSTOM_TOOLBAR_BACKGROUND, DEFAULT_CUSTOM_TOOLBAR_BACKGROUND);
    }

    public boolean isCustomWindowBackground() {
        return mPreferences.getBoolean(KEY_CUSTOM_WINDOW_BACKGROUND, DEFAULT_CUSTOM_WINDOW_BACKGROUND);
    }

    public void setColor(ColorItem colorItem, Color color) {
        mPreferences.put(colorItem.getKey(), GraphicsHelper.colorToString(color));
    }

    public void setCurrentCategory(GameCategory value) {
        mPreferences.put(KEY_CURRENT_CATEGORY, value == null ? "" : value.toString());
    }

    public void setCurrentId(String value) {
        mPreferences.put(KEY_CURRENT_ID, value);
    }

    public void setCustomToolbarBackground(boolean value) {
        mPreferences.putBoolean(KEY_CUSTOM_TOOLBAR_BACKGROUND, value);
    }

    public void setCustomWindowBackground(boolean value) {
        mPreferences.putBoolean(KEY_CUSTOM_WINDOW_BACKGROUND, value);
    }

    public enum ColorItem {

        TOOLBAR(DEFAULT_COLOR_TOOLBAR),
        WINDOW_LOWER(DEFAULT_COLOR_WINDOW_LOWER),
        WINDOW_UPPER(DEFAULT_COLOR_WINDOW_UPPER);

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

    private static class Holder {

        private static final NbgOptions INSTANCE = new NbgOptions();
    }
}
