package org.nbgames.yaya;

import java.awt.Color;
import java.util.prefs.Preferences;
import org.nbgames.yaya.scorecard.rule.Rule;
import org.openide.util.NbPreferences;
import se.trixon.almond.GraphicsHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum Options {

    INSTANCE;

    private static final String DEFAULT_COLOR_BACKGROUND = "#333333";
    private static final String DEFAULT_COLOR_HEADER = "#FFC800";
    private static final String DEFAULT_COLOR_HINT_HIGH = "#BBEEBB";
    private static final String DEFAULT_COLOR_HINT_LOW = "#EEBBBB";
    private static final String DEFAULT_COLOR_ROW = "#FFFFFF";
    private static final String DEFAULT_COLOR_SCORECARD = "#666666";
    private static final String DEFAULT_COLOR_SUM = "#FF0000";
    private final int DEFAULT_NUMOFPLAYERS = 2;
    private final Rule DEFAULT_RULE = Rule.YAHTZEE;
    private final boolean DEFAULT_SHOWING_HICOL = false;
    private final boolean DEFAULT_SHOWING_HINTS = true;
    private final boolean DEFAULT_SHOWING_MAXCOL = false;
    private final Preferences mPreferences = NbPreferences.forModule(getClass());
    private final Preferences mPreferencesColors = NbPreferences.forModule(getClass()).node("colors");
    private int numOfPlayers;
    private Rule rule;
    private boolean showingHiCol;
    private boolean showingHints;
    private boolean showingMaxCol;

    private Options() {
        init();
    }

    public Color getColor(ColorItem colorItem) {
        return Color.decode(mPreferencesColors.get(colorItem.getKey(), colorItem.getDefaultColorAsString()));
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public Preferences getPreferencesColors() {
        return mPreferencesColors;
    }

    public Rule getRule() {
        return rule;
    }

    public boolean isShowingHiCol() {
        return showingHiCol;
    }

    public boolean isShowingHints() {
        return showingHints;
    }

    public boolean isShowingMaxCol() {
        return showingMaxCol;
    }

    public void restoreDefaults() {
        numOfPlayers = DEFAULT_NUMOFPLAYERS;
        rule = DEFAULT_RULE;
        showingHints = DEFAULT_SHOWING_HINTS;
        showingHiCol = DEFAULT_SHOWING_HICOL;
        showingMaxCol = DEFAULT_SHOWING_MAXCOL;
    }

    public void setColor(ColorItem colorItem, Color color) {
        mPreferencesColors.put(colorItem.getKey(), GraphicsHelper.colorToString(color));
    }

    public void setNumOfPlayers(int players) {
        this.numOfPlayers = players;
    }

    public void setRule(Rule aRule) {
        this.rule = aRule;
    }

    public void setRule(int ordinal) {
        ordinal = Math.min(ordinal, Rule.values().length - 1);
        this.rule = Rule.class.getEnumConstants()[ordinal];
    }

    public void setShowingHiCol(boolean showingHiCol) {
        this.showingHiCol = showingHiCol;
    }

    public void setShowingHints(boolean showingHints) {
        this.showingHints = showingHints;
    }

    public void setShowingMaxCol(boolean showingMaxCol) {
        this.showingMaxCol = showingMaxCol;
    }

    protected void loadFromFile() {
        setNumOfPlayers(mPreferences.getInt("numOfPlayers", DEFAULT_NUMOFPLAYERS));
        setShowingHiCol(mPreferences.getBoolean("showHiCol", DEFAULT_SHOWING_HICOL));
        setShowingHints(mPreferences.getBoolean("showHints", DEFAULT_SHOWING_HINTS));
        setShowingMaxCol(mPreferences.getBoolean("showMaxCol", DEFAULT_SHOWING_MAXCOL));
        setRule(mPreferences.getInt("rule", DEFAULT_RULE.ordinal()));
    }

    protected void saveToFile() {
        mPreferences.putInt("numOfPlayers", numOfPlayers);
        mPreferences.putBoolean("showHiCol", showingHiCol);
        mPreferences.putBoolean("showHints", showingHints);
        mPreferences.putBoolean("showMaxCol", showingMaxCol);
        mPreferences.putInt("rule", rule.ordinal());
    }

    private void init() {
        restoreDefaults();
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
