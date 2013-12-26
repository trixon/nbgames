package org.nbgames.core.dice;

import org.nbgames.core.HandMode;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Observable;
import javax.swing.JPanel;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class DiceBoard extends Observable {

    private final int PANEL_HEIGHT = 180;
    private final int PANEL_WIDTH = 1000;
    private LinkedList<Die> mDice = new LinkedList<Die>();
    private Collector mCollector = new Collector(this);
    private boolean mDiceOnFloor = false;
    private Painter dicePainter = new Painter(this);
    private Roller diceRoller = new Roller(this);
    private Thread dieWatcher;
    private HandMode handMode;
    private int maxRollCount = 3;
    private int numOfDice;
    private int numOfRolls = 0;
    private DiceBoardPanel panel = new DiceBoardPanel();
    private boolean playSound = true;

    public DiceBoard(int aNumOfDice) {
        setNumOfDice(aNumOfDice);
        init();
    }

    public void gameOver() {
        dicePainter.setRollable(false);
        dicePainter.setSelectable(false);
    }

    public int getMaxRollCount() {
        return maxRollCount;
    }

    public int getNumOfRolls() {
        return numOfRolls;
    }

    public JPanel getPanel() {
        return panel;
    }

    public LinkedList<Integer> getValues() {
        LinkedList<Integer> values = new LinkedList<Integer>();

        for (Die die : mDice) {
            values.add(die.getValue());
        }

        return values;
    }

    public void newTurn() {
        numOfRolls = 0;
        mCollector.collect();
        panel.repaint();

        dicePainter.setSelectable(false);
        dicePainter.setRollable(true);
        reset();
        diceRoller.setImage(getNumOfDice());
    }

    public void roll() {
        dicePainter.setRollable(false);
        dicePainter.setSelectable(false);
        diceRoller.roll();
        mDiceOnFloor = false;

        for (Die die : mDice) {
            die.roll();
        }

        diceRoller.setImage(getNumOfSelectedDice());
        dieWatcher = new Thread(new DieWatchRunner());
        dieWatcher.start();
    }

    public void setDiceTofloor(int aDiceToFloorFrequence) {
        for (Die die : mDice) {
            die.setDiceTofloor(aDiceToFloorFrequence);
        }
    }

    public void setHandMode(HandMode aHandMode) {
        this.handMode = aHandMode;
    }

    public void setMaxRollCount(int aMaxRollCount) {
        maxRollCount = aMaxRollCount;
    }

    public void setPlaySound(boolean playSound) {
        this.playSound = playSound;
    }

    public void undo() {
        dicePainter.setRollable(false);
        dicePainter.setSelectable(false);

        for (Die die : mDice) {
            die.setVisible(true);
            die.setEnabled(false);
        }

        panel.repaint();
        setChanged();
        notifyObservers(RollEvent.POST_OP);
    }

    private void endOfTurn() {

        for (Die die : mDice) {
            die.park();
            die.setEnabled(false);
        }

    }

    private void init() {
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        panel.setLayout(new GridLayout(1, 1));
        panel.add(dicePainter);

        setHandMode(HandMode.LEFT);
        setHandMode(HandMode.RIGHT);
    }

    private void reset() {
        for (Die die : mDice) {
            die.reset();
        }
    }

    private void setNumOfDice(int aNumOfDice) {
        this.numOfDice = aNumOfDice;
        mDice.clear();

        for (int i = 0; i < aNumOfDice; i++) {
            mDice.add(new Die(this, i));
        }
    }

    LinkedList<Die> getDice() {
        return mDice;
    }

    Painter getDicePainter() {
        return dicePainter;
    }

    Roller getDiceRoller() {
        return diceRoller;
    }

    HandMode getHandMode() {
        return handMode;
    }

    int getNumOfDice() {
        return numOfDice;
    }

    synchronized int getNumOfSelectedDice() {
        int result = 0;

        for (Die die : mDice) {
            if (die.isSelected()) {
                result++;
            }
        }
        diceRoller.setImage(result);

        return result;
    }

    boolean isDiceOnFloor() {
        return mDiceOnFloor;
    }

    boolean isPlaySound() {
        return playSound;
    }

    void rollPostOp() {
        numOfRolls++;
        boolean enable = false;

        if (numOfRolls == maxRollCount) {
            endOfTurn();
        } else {
            enable = true;
        }

        dicePainter.setRollable(false);
        dicePainter.setSelectable(enable);

        setChanged();
        notifyObservers(RollEvent.POST_OP);
    }

    void rollPreOp() {
        setChanged();
        notifyObservers(RollEvent.PRE_OP);
    }

    void setDiceOnFloor(boolean diceOnFloor) {
        this.mDiceOnFloor = diceOnFloor;
    }

    public enum RollEvent {

        PRE_OP,
        POST_OP;
    }

    class DieWatchRunner implements Runnable {

        @Override
        public void run() {
            for (Die die : mDice) {
                try {
                    die.getAnimator().join();
                } catch (InterruptedException ex) {
                }
            }

            if (!mDiceOnFloor) {
                rollPostOp();
            }
        }
    }
}
