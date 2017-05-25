/* 
 * Copyright 2016 Patrik Karlsson.
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
package org.nbgames.core.card;

import javax.swing.ImageIcon;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Patrik Karlsson
 */
public class PlayingCard implements Cloneable, Comparable<PlayingCard> {

    private boolean mAceHigh = true;
    private final Color mColor;
    private String mPathBack;
    private String mPathFront;
    private final String[] mPip = {"♣", "♦", "♥", "♠"};
    private Suit mSuit;
    private Value mValue;

    public PlayingCard() {
        init();
        mSuit = Suit.CLUB;
        mValue = Value.TWO;
        mColor = Color.BLACK;
    }

    public PlayingCard(Suit suit, Value value) {
        init();
        mSuit = suit;
        mValue = value;

        if ((suit == Suit.DIAMOND) || (suit == Suit.HEART)) {
            mColor = Color.RED;
        } else {
            mColor = Color.BLACK;
        }
    }

    @Override
    public PlayingCard clone() {

        try {
            return (PlayingCard) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }

    }

    @Override
    public int compareTo(PlayingCard playingCard) {
        Integer result = new Integer(mSuit.ordinal());
        result = result.compareTo(playingCard.mSuit.ordinal());

        if (result == 0) {
            result = mValue.ordinal();
            result = result.compareTo(playingCard.mValue.ordinal());
        }

        return result;
    }

    @Override
    public boolean equals(Object object) {

        if (object == null) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        final PlayingCard other = (PlayingCard) object;
        if (mValue != other.mValue) {
            return false;
        }

        if (mSuit != other.mSuit) {
            return false;
        }

        return true;
    }

    public boolean equalsColor(Object object) {

        if (object == null) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        final PlayingCard other = (PlayingCard) object;

        if (mColor != other.mColor) {
            return false;
        }

        return true;
    }

    public boolean equalsSuit(Object object) {

        if (object == null) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        final PlayingCard other = (PlayingCard) object;

        if (mSuit != other.mSuit) {
            return false;
        }

        return true;
    }

    public boolean equalsValue(Object object) {

        if (object == null) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        final PlayingCard other = (PlayingCard) object;

        if (mValue != other.mValue) {
            return false;
        }

        return true;
    }

    public Color getColor() {
        return mColor;
    }

    public String getFilename() {
        return mSuit.toString().substring(0, 1) + mValue.valueString + ".png";
    }

    public ImageIcon getImage(Side side) {
        ImageIcon imageIcon;

        if (side == Side.BACK) {
            imageIcon = getImageBack();

        } else {
            imageIcon = getImageFront();
        }

        return imageIcon;
    }

    public ImageIcon getImageBack() {
        ImageIcon imageIcon = ImageUtilities.loadImageIcon(getPathBack(), false);

        if (imageIcon == null) {
            setPathBack(CardDeckManager.DEFAULT_PATH_BACK_0);
            imageIcon = ImageUtilities.loadImageIcon(getPathBack(), false);
        }

        return imageIcon;
    }

    public ImageIcon getImageFront() {
        ImageIcon imageIcon = ImageUtilities.loadImageIcon(getPathFront() + getFilename(), false);

        if (imageIcon == null) {
            setPathFront(CardDeckManager.DEFAULT_PATH_FRONT_0);
            imageIcon = ImageUtilities.loadImageIcon(getPathFront() + getFilename(), false);
        }

        return imageIcon;
    }

    public String getPathBack() {
        return mPathBack;
    }

    public String getPathFront() {
        return mPathFront;
    }

    /**
     * Get the mValue of mSuit
     *
     * @return the mValue of mSuit
     */
    public Suit getSuit() {
        return mSuit;
    }

    /**
     * Get the mValue of mValue
     *
     * @return the mValue of mValue
     */
    public Value getValue() {
        return mValue;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + mValue.hashCode();
        hash = 71 * hash + mSuit.hashCode();
        return hash;
    }

    /**
     * Get the mValue of mAceHigh
     *
     * @return the mValue of mAceHigh
     */
    public boolean isAceHigh() {
        return mAceHigh;
    }

    /**
     * Set the mValue of mAceHigh
     *
     * @param aceHigh new mValue of mAceHigh
     */
    public void setAceHigh(boolean aceHigh) {
        mAceHigh = aceHigh;
    }

    public void setPathBack(String path) {
        mPathBack = path;
    }

    public void setPathFront(String path) {
        mPathFront = path;
    }

    /**
     * Set the mValue of mSuit
     *
     * @param suit
     */
    public void setSuit(Suit suit) {
        mSuit = suit;
    }

    /**
     * Set the mValue of mValue
     *
     * @param value
     */
    public void setValue(Value value) {
        mValue = value;
    }

    @Override
    public String toString() {
        return mPip[mSuit.ordinal()] + " " + mValue;
    }

    private void init() {
        mPathBack = CardDeckManager.DEFAULT_PATH_BACK_0;
        mPathFront = CardDeckManager.DEFAULT_PATH_FRONT_0;
    }

    public enum Color {

        BLACK,
        RED;
    }

    public enum Side {

        BACK,
        FRONT;
    }

    public enum Suit {

        CLUB,
        DIAMOND,
        HEART,
        SPADE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum Value {

        ACE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING;
        private String valueString = String.valueOf(toInt());

        public String getValueString() {
            return valueString;
        }

        public int toInt() {
            return ordinal() + 1;
        }

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
}
