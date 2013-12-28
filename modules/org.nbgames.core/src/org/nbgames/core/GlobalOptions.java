package org.nbgames.core;

import java.awt.Color;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;
import se.trixon.almond.util.AGraphics;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum GlobalOptions {

    INSTANCE;
    private static final String DEFAULT_COLOR_WINDOW_LOWER = "#003300";
    private static final String DEFAULT_COLOR_WINDOW_UPPER = "#00EE00";
    private Preferences mPreferences = NbPreferences.forModule(GlobalOptions.class);

    public Color getColor(ColorItem colorItem) {
        return Color.decode(mPreferences.get(colorItem.getKey(), colorItem.getDefaultColor()));
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public void setColor(ColorItem colorItem, Color color) {
        mPreferences.put(colorItem.getKey(), AGraphics.colorToString(color));
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
