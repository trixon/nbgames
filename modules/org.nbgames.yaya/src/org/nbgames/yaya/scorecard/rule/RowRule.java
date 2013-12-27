package org.nbgames.yaya.scorecard.rule;

import java.util.TreeSet;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class RowRule {

    private boolean mBonus;
    private String mForumla = "";
    private int mLim = 0;
    private int mMax = 0;
    private String mName;
    private boolean mPlayable;
    private boolean mRollCounter;
    private boolean mSum;
    private TreeSet<Integer> mSumSet;
    private String mToolTip;

    public String getForumla() {
        return mForumla;
    }

    public int getLim() {
        return mLim;
    }

    public int getMax() {
        return mMax;
    }

    public String getName() {
        return mName;
    }

    public TreeSet<Integer> getSumSet() {
        return mSumSet;
    }

    public String getToolTip() {
        return mToolTip;
    }

    public boolean isBonus() {
        return mBonus;
    }

    public boolean isPlayable() {
        return mPlayable;
    }

    public boolean isRollCounter() {
        return mRollCounter;
    }

    public boolean isSum() {
        return mSum;
    }

    public void setBonus(boolean bonus) {
        mBonus = bonus;
    }

    public void setBonus(int index) {
        mBonus = indexToBoolean(index);
    }

    public void setForumla(String forumla) {
        mForumla = forumla;
    }

    public void setLim(int lim) {
        mLim = lim;
    }

    public void setLim(String lim) {
        mLim = stringToInt(lim);
    }

    public void setMax(int max) {
        mMax = max;
    }

    public void setMax(String max) {
        mMax = stringToInt(max);
    }

    public void setName(String name) {
        mName = name;
    }

    public void setPlayable(boolean playable) {
        mPlayable = playable;
    }

    public void setPlayable(int index) {
        mPlayable = indexToBoolean(index);
    }

    public void setRollCounter(boolean rollCounter) {
        mRollCounter = rollCounter;
    }

    public void setRollCounter(int index) {
        mRollCounter = indexToBoolean(index);
    }

    public void setSum(boolean sum) {
        mSum = sum;
    }

    public void setSum(int index) {
        mSum = indexToBoolean(index);
    }

    public void setSumSet(TreeSet<Integer> sumSet) {
        mSumSet = sumSet;
    }

    public void setToolTip(String toolTip) {
        mToolTip = toolTip;
    }

    private boolean indexToBoolean(int index) {
        boolean result = false;

        if (index > -1) {
            result = true;
        }

        return result;
    }

    private int stringToInt(String string) {
        int result = 0;

        if (string != null) {
            result = Integer.valueOf(string);
        }

        return result;
    }
}
