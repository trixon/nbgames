package org.nbgames.gunu;

import java.awt.Color;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;
import se.trixon.almond.util.AGraphics;

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
    private Preferences mPreferences = NbPreferences.forModule(Options.class);

    public Color getColorBackground() {
        return Color.decode(mPreferences.get(KEY_COLOR_BACKGROUND, AGraphics.colorToString(DEFAULT_COLOR_BACKGROUND)));
    }

    public long getMax() {
        return mPreferences.getLong(KEY_MAX, DEFAULT_MAX);
    }

    public long getMin() {
        return mPreferences.getLong(KEY_MIN, DEFAULT_MIN);
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public void setColorBackground(Color color) {
        mPreferences.put(KEY_COLOR_BACKGROUND, AGraphics.colorToString(color));
    }

    public void setMax(long max) {
        mPreferences.putLong(KEY_MAX, max);
    }

    public void setMin(long min) {
        mPreferences.putLong(KEY_MIN, min);
    }

    public void setPreferences(Preferences preferences) {
        mPreferences = preferences;
    }
}
