package org.nbgames.core;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class CircularInt {

    private int mMax;
    private int mMin;
    private int mValue;

    public CircularInt(int min, int max) {
        mMax = max;
        mMin = min;
        mValue = 0;
    }

    public CircularInt(int min, int max, int value) {
        this(min, max);
        mValue = value;
    }

    public int dec() {
        mValue = peekPrev();
        return mValue;
    }

    public int get() {
        return mValue;
    }

    public int inc() {
        mValue = peekNext();
        return mValue;
    }

    public int peekNext() {
        int peek = mValue + 1;
        if (peek > mMax) {
            peek = 0;
        }
        return peek;
    }

    public int peekPrev() {
        int peek = mValue - 1;
        if (peek < mMin) {
            peek = mMax;
        }
        return peek;
    }

    public void set(int aValue) {
        mValue = Math.min(mMax, Math.max(mMin, aValue));
    }
}
