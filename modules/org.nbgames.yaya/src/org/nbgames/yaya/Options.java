package org.nbgames.yaya;

import java.awt.Color;
import java.util.prefs.Preferences;
import org.nbgames.yaya.gamedef.GameType;
import org.openide.util.NbPreferences;
import se.trixon.almond.GraphicsHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum Options {

    INSTANCE;
    public static final String KEY_GAME_TYPE_ID = "gameType";
    public static final String KEY_NUM_OF_PLAYERS = "numOfPlayers";
    public static final String KEY_SHOW_HINTS = "showHints";
    public static final String KEY_SHOW_HI_COL = "showHiCol";
    public static final String KEY_SHOW_MAX_COL = "showMaxCol";
    public static final String KEY_USE_SYMBOLS = "useSymbols";
    private static final String DEFAULT_COLOR_BACKGROUND = "#333333";
    private static final String DEFAULT_COLOR_HEADER = "#FFC800";
    private static final String DEFAULT_COLOR_HINT_HIGH = "#BBEEBB";
    private static final String DEFAULT_COLOR_HINT_LOW = "#EEBBBB";
    private static final String DEFAULT_COLOR_ROW = "#FFFFFF";
    private static final String DEFAULT_COLOR_SCORECARD = "#666666";
    private static final String DEFAULT_COLOR_SUM = "#FFFF00";
    private static final String DEFAULT_GAME_TYPE_ID = "default";
    private static final String DEFAULT_GAME_VARIANT = "standard";
    private static final int DEFAULT_NUM_OF_PLAYERS = 2;
    private static final boolean DEFAULT_SHOW_HINTS = true;
    private static final boolean DEFAULT_SHOW_HI_COL = false;
    private static final boolean DEFAULT_SHOW_MAXCOL = false;
    private static final boolean DEFAULT_USE_SYMBOLS = false;
    private final Preferences mPreferences = NbPreferences.forModule(getClass());
    private final Preferences mPreferencesColors = NbPreferences.forModule(getClass()).node("colors");

    private Options() {
        init();
    }

    public Color getColor(ColorItem colorItem) {
        return Color.decode(mPreferencesColors.get(colorItem.getKey(), colorItem.getDefaultColorAsString()));
    }

    public String getGameTypeId() {
        return mPreferences.get(KEY_GAME_TYPE_ID, DEFAULT_GAME_TYPE_ID);
    }

    public String getGameVariant(String type) {
        return mPreferences.get(GameType.VARIANT + type, DEFAULT_GAME_VARIANT);
    }

    public int getNumOfPlayers() {
        return mPreferences.getInt(KEY_NUM_OF_PLAYERS, DEFAULT_NUM_OF_PLAYERS);
    }

    public Preferences getPreferences() {
        return mPreferences;
    }

    public Preferences getPreferencesColors() {
        return mPreferencesColors;
    }

    public boolean isShowingHiCol() {
        return mPreferences.getBoolean(KEY_SHOW_HI_COL, DEFAULT_SHOW_HI_COL);
    }

    public boolean isShowingHints() {
        return mPreferences.getBoolean(KEY_SHOW_HINTS, DEFAULT_SHOW_HINTS);
    }

    public boolean isShowingMaxCol() {
        return mPreferences.getBoolean(KEY_SHOW_MAX_COL, DEFAULT_SHOW_MAXCOL);
    }

    public boolean isUsingSymbols() {
        return mPreferences.getBoolean(KEY_USE_SYMBOLS, DEFAULT_USE_SYMBOLS);
    }

    public void setColor(ColorItem colorItem, Color color) {
        mPreferencesColors.put(colorItem.getKey(), GraphicsHelper.colorToString(color));
    }

    public void setGameTypeId(String typeId) {
        mPreferences.put(KEY_GAME_TYPE_ID, typeId);
    }

    public void setGameVariant(String type, String variant) {
        mPreferences.put(GameType.VARIANT + type, variant);
    }

    public void setNumOfPlayers(int players) {
        mPreferences.putInt(KEY_NUM_OF_PLAYERS, players);
    }

    public void setShowHiCol(boolean state) {
        mPreferences.putBoolean(KEY_SHOW_HI_COL, state);
    }

    public void setShowHints(boolean state) {
        mPreferences.putBoolean(KEY_SHOW_HINTS, state);
    }

    public void setShowMaxCol(boolean state) {
        mPreferences.putBoolean(KEY_SHOW_MAX_COL, state);
    }

    public void setUseSymbols(boolean state) {
        mPreferences.putBoolean(KEY_USE_SYMBOLS, state);
    }

    private void init() {
    }

    public enum ColorItem {

        BACKGROUND(DEFAULT_COLOR_BACKGROUND),
        SCORECARD(DEFAULT_COLOR_SCORECARD),
        HEADER(DEFAULT_COLOR_HEADER),
        SUM(DEFAULT_COLOR_SUM),
        ROW(DEFAULT_COLOR_ROW),
        HINT_HIGH(DEFAULT_COLOR_HINT_HIGH),
        HINT_LOW(DEFAULT_COLOR_HINT_LOW);

        private final String mDefaultColor;

        ColorItem(String defaultColor) {
            mDefaultColor = defaultColor;
        }

        public Color getDefaultColor() {
            return Color.decode(mDefaultColor);
        }

        public String getDefaultColorAsString() {
            return mDefaultColor;
        }

        public String getKey() {
            return name().toLowerCase();
        }
    }
}
