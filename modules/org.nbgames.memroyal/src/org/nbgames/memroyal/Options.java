package org.nbgames.memroyal;

import java.awt.Color;
import java.util.prefs.Preferences;
import org.nbgames.memroyal.Rules.Variation;
import org.openide.util.NbPreferences;
import se.trixon.almond.GraphicsHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum Options {

    INSTANCE;
    public static final String KEY_COLOR_BACKGROUND = "background";
    public static final String KEY_LEVEL = "level";
    public static final String KEY_VARIATION = "variation";
    public static final Color DEFAULT_COLOR_BACKGROUND = Color.decode("#656610");
    public static final int DEFAULT_LEVEL = 13;
    public static final boolean DEFAULT_SHOW_COUNTERS = true;
    public static final Variation DEFAULT_VARIATION = Variation.ONE_DECK_ANY_COLOR;
    private final Preferences mPreferences = NbPreferences.forModule(MemroyalAction.class);
    private final Preferences mPreferencesColor = mPreferences.node("color");

    private Options() {
    }

    public Color getColorBackground() {
        return Color.decode(mPreferencesColor.get(KEY_COLOR_BACKGROUND, GraphicsHelper.colorToString(DEFAULT_COLOR_BACKGROUND)));
    }

    public int getLevel() {
        return mPreferences.getInt(Options.KEY_LEVEL, Options.DEFAULT_LEVEL);
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public Preferences getPreferencesColor() {
        return mPreferencesColor;
    }

    public Variation getVariation() {
        return Variation.values()[getVariationOrdinal()];
    }

    public int getVariationOrdinal() {
        return mPreferences.getInt(Options.KEY_VARIATION, Options.DEFAULT_VARIATION.ordinal());
    }

    public void setColorBackground(Color color) {
        mPreferencesColor.put(KEY_COLOR_BACKGROUND, GraphicsHelper.colorToString(color));
    }

    public void setLevel(int level) {
        mPreferences.putInt(Options.KEY_LEVEL, level);
    }

    public void setVariation(Variation variation) {
        mPreferences.putInt(Options.KEY_VARIATION, variation.ordinal());
    }
}
