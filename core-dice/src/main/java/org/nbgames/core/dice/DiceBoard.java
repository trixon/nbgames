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
package org.nbgames.core.dice;

import java.util.LinkedList;
import java.util.Observable;
import javax.swing.JPanel;
import org.nbgames.core.Player.Handedness;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class DiceBoard extends Observable {

    private final Collector mCollector = new Collector(this);
    private final LinkedList<Die> mDice = new LinkedList<>();
    private final DiceBoardPanel mDiceBoardPanel = new DiceBoardPanel();
    private boolean mDiceOnFloor = false;
    private Thread mDieWatcherThread;
    private Handedness mHandMode;
    private int mMaxRollCount = 3;
    private int mNumOfDice;
    private int mNumOfRolls = 0;
    private final Painter mPainter = new Painter(this);
    private boolean mPlaySound = true;
    private final Roller mRoller = new Roller(this);

    public DiceBoard(int numOfDice) {
        setNumOfDice(numOfDice);
        init();
    }

    public void gameOver() {
        mPainter.setRollable(false);
        mPainter.setSelectable(false);
    }

    public int getMaxRollCount() {
        return mMaxRollCount;
    }

    public int getNumOfRolls() {
        return mNumOfRolls;
    }

    public JPanel getPanel() {
        return mDiceBoardPanel;
    }

    public LinkedList<Integer> getValues() {
        LinkedList<Integer> values = new LinkedList<>();

        for (Die die : mDice) {
            values.add(die.getValue());
        }

        return values;
    }

    public void newTurn() {
        mNumOfRolls = 0;
        mCollector.collect();
        mDiceBoardPanel.repaint();

        mPainter.setSelectable(false);
        mPainter.setRollable(true);
        reset();
        mRoller.setImage(getNumOfDice());
    }

    public void roll() {
        mPainter.setRollable(false);
        mPainter.setSelectable(false);
        mRoller.roll();
        mDiceOnFloor = false;

        for (Die die : mDice) {
            die.roll();
        }

        mRoller.setImage(getNumOfSelectedDice());
        mDieWatcherThread = new Thread(new DieWatchRunner());
        mDieWatcherThread.start();
    }

    public void setDiceTofloor(int frequency) {
        for (Die die : mDice) {
            die.setDiceTofloor(frequency);
        }
    }

    public void setHandMode(Handedness handMode) {
        mHandMode = handMode;
    }

    public void setMaxRollCount(int maxRollCount) {
        mMaxRollCount = maxRollCount;
    }

    public void setPlaySound(boolean playSound) {
        mPlaySound = playSound;
    }

    public void undo() {
        mPainter.setRollable(false);
        mPainter.setSelectable(false);

        for (Die die : mDice) {
            die.setVisible(true);
            die.setEnabled(false);
        }

        mDiceBoardPanel.repaint();
        setChanged();
        notifyObservers(RollEvent.POST_ROLL);
    }

    LinkedList<Die> getDice() {
        return mDice;
    }

    Painter getDicePainter() {
        return mPainter;
    }

    Roller getDiceRoller() {
        return mRoller;
    }

    Handedness getHandMode() {
        return mHandMode;
    }

    int getNumOfDice() {
        return mNumOfDice;
    }

    synchronized int getNumOfSelectedDice() {
        int result = 0;

        for (Die die : mDice) {
            if (die.isSelected()) {
                result++;
            }
        }

        mRoller.setImage(result);

        return result;
    }

    boolean isDiceOnFloor() {
        return mDiceOnFloor;
    }

    boolean isPlaySound() {
        return mPlaySound;
    }

    void rollPostOp() {
        mNumOfRolls++;
        boolean enable = false;

        if (mNumOfRolls == mMaxRollCount) {
            endOfTurn();
        } else {
            enable = true;
        }

        mPainter.setRollable(false);
        mPainter.setSelectable(enable);

        setChanged();
        notifyObservers(RollEvent.POST_ROLL);
    }

    void rollPreOp() {
        setChanged();
        notifyObservers(RollEvent.PRE_ROLL);
    }

    void setDiceOnFloor(boolean diceOnFloor) {
        mDiceOnFloor = diceOnFloor;
    }

    private void endOfTurn() {
        for (Die die : mDice) {
            die.park();
            die.setEnabled(false);
        }
    }

    private void init() {
        mDiceBoardPanel.add(mPainter);

        setHandMode(Handedness.LEFT);
        setHandMode(Handedness.RIGHT);
    }

    private void reset() {
        for (Die die : mDice) {
            die.reset();
        }
    }

    private void setNumOfDice(int numOfDice) {
        mNumOfDice = numOfDice;
        mDice.clear();

        for (int i = 0; i < numOfDice; i++) {
            mDice.add(new Die(this, i));
        }
    }

    public enum RollEvent {

        PRE_ROLL,
        POST_ROLL;
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
