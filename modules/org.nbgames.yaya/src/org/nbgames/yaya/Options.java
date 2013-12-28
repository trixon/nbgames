package org.nbgames.yaya;

import java.awt.Color;
import java.util.prefs.Preferences;
import org.nbgames.yaya.scorecard.rule.Rule;
import se.trixon.almond.GraphicsHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum Options {

    INSTANCE;
    private final Color[] DEFAULT_COLORS = new Color[ColorSelectors.values().length];
    private final int DEFAULT_NUMOFPLAYERS = 2;
    private final Rule DEFAULT_RULE = Rule.YAHTZEE;
    private final boolean DEFAULT_SHOWING_HICOL = false;
    private final boolean DEFAULT_SHOWING_HINTS = true;
    private final boolean DEFAULT_SHOWING_MAXCOL = false;
    private Color[] colors = new Color[ColorSelectors.values().length];
    private int numOfPlayers;
    private Preferences preferences = Preferences.userNodeForPackage(getClass());
    private Rule rule;
    private boolean showingHiCol;
    private boolean showingHints;
    private boolean showingMaxCol;

    private Options() {
        init();
    }

    public Color getColor(ColorSelectors aColorSelector) {
        return colors[aColorSelector.ordinal()];
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
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

        for (ColorSelectors colorSelectors : ColorSelectors.values()) {
            colors[colorSelectors.ordinal()] = DEFAULT_COLORS[colorSelectors.ordinal()];
        }
    }

    public void setColor(ColorSelectors aColorSelector, Color aColor) {
        colors[aColorSelector.ordinal()] = aColor;
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
        setNumOfPlayers(preferences.getInt("numOfPlayers", DEFAULT_NUMOFPLAYERS));
        setShowingHiCol(preferences.getBoolean("showHiCol", DEFAULT_SHOWING_HICOL));
        setShowingHints(preferences.getBoolean("showHints", DEFAULT_SHOWING_HINTS));
        setShowingMaxCol(preferences.getBoolean("showMaxCol", DEFAULT_SHOWING_MAXCOL));
        setRule(preferences.getInt("rule", DEFAULT_RULE.ordinal()));

        for (ColorSelectors colorSelectors : ColorSelectors.values()) {
            setColor(colorSelectors, Color.decode("#" + preferences.get(colorSelectors.name(), GraphicsHelper.colorToHex(DEFAULT_COLORS[colorSelectors.ordinal()]))));
        }
    }

    protected void saveToFile() {
        preferences.putInt("numOfPlayers", numOfPlayers);
        preferences.putBoolean("showHiCol", showingHiCol);
        preferences.putBoolean("showHints", showingHints);
        preferences.putBoolean("showMaxCol", showingMaxCol);
        preferences.putInt("rule", rule.ordinal());

        for (ColorSelectors colorSelectors : ColorSelectors.values()) {
            preferences.put(colorSelectors.name(), GraphicsHelper.colorToHex(colors[colorSelectors.ordinal()]));
        }
    }

    private void init() {
        DEFAULT_COLORS[ColorSelectors.BACKGROUND.ordinal()] = Color.BLACK;
        DEFAULT_COLORS[ColorSelectors.BOARD.ordinal()] = Color.decode("#333333");
        DEFAULT_COLORS[ColorSelectors.HEADER.ordinal()] = Color.ORANGE;
        DEFAULT_COLORS[ColorSelectors.HINT_HI.ordinal()] = Color.decode("#BBEEBB");
        DEFAULT_COLORS[ColorSelectors.HINT_LO.ordinal()] = Color.decode("#EEBBBB");
        DEFAULT_COLORS[ColorSelectors.ROW.ordinal()] = Color.WHITE;
        DEFAULT_COLORS[ColorSelectors.SCORECARD.ordinal()] = Color.decode("#666666");
        DEFAULT_COLORS[ColorSelectors.SUM.ordinal()] = Color.YELLOW;

        restoreDefaults();
    }

    public enum ColorSelectors {

        BACKGROUND,
        BOARD,
        SCORECARD,
        HEADER,
        SUM,
        ROW,
        HINT_HI,
        HINT_LO;

        public String getNameKey() {
            return "button.color." + name().toLowerCase();
        }
    }
}
