package org.nbgames.yaya.scorecard;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ScoreCard {

    private JPanel basePanel = new JPanel();
    private ScoreCardObservable observable = new ScoreCardObservable();
    private JPanel panel = new JPanel();
    private int numOfPlayers;

    public ScoreCard() {
        basePanel.setBackground(Color.decode("#333333"));
        basePanel.setPreferredSize(new Dimension(0, 500));

        this.numOfPlayers = 4;//diceGame.getNumOfPlayers();
        init();

    }
    private LinkedList<PlayerColumn> players = new LinkedList<PlayerColumn>();

    private void init() {
        players.clear();
        headerColumn = new HeaderColumn(this, settings.getRule());
        initActions();
        numOfRows = settings.getRule().getNumOfRows();

        initLayout();
        applyColors();
    }

    public JPanel getCard() {
        return basePanel;
    }

    public ScoreCardObservable getObservable() {
        return observable;
    }

    public boolean isRollable() {
        return true;
    }

    public void newGame() {
    }

    public void newRoll() {
    }

    public void parseDice(LinkedList<Integer> values) {
    }

    public void setEnabledUndo(boolean b) {
    }

}
