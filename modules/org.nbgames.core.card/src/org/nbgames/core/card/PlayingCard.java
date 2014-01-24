package org.nbgames.core.card;

import javax.swing.ImageIcon;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class PlayingCard implements Cloneable, Comparable<PlayingCard> {

    private String pathBack;
    private String pathFront;
    private Color color;
    private Suit suit;
    private Value value;
    private String[] pip = {"♣", "♦", "♥", "♠"};
    private boolean aceHigh = true;

    public PlayingCard() {
        init();
        suit = Suit.CLUB;
        value = Value.TWO;
        color = Color.BLACK;
    }

    public PlayingCard(Suit aSuit, Value aValue) {
        init();
        suit = aSuit;
        value = aValue;

        if ((aSuit == Suit.DIAMOND) || (aSuit == Suit.HEART)) {
            color = Color.RED;
        } else {
            color = Color.BLACK;
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
    public int compareTo(PlayingCard aCard) {
        Integer result = new Integer(this.suit.ordinal());
        result = result.compareTo(aCard.suit.ordinal());

        if (result == 0) {
            result = this.value.ordinal();
            result = result.compareTo(aCard.value.ordinal());
        }

        return result;
    }

    @Override
    public boolean equals(Object anObject) {

        if (anObject == null) {
            return false;
        }

        if (getClass() != anObject.getClass()) {
            return false;
        }

        final PlayingCard other = (PlayingCard) anObject;
        if (this.value != other.value) {
            return false;
        }

        if (this.suit != other.suit) {
            return false;
        }

        return true;
    }

    public boolean equalsSuit(Object anObject) {

        if (anObject == null) {
            return false;
        }

        if (getClass() != anObject.getClass()) {
            return false;
        }

        final PlayingCard other = (PlayingCard) anObject;

        if (this.suit != other.suit) {
            return false;
        }

        return true;
    }

    public boolean equalsValue(Object anObject) {

        if (anObject == null) {
            return false;
        }

        if (getClass() != anObject.getClass()) {
            return false;
        }

        final PlayingCard other = (PlayingCard) anObject;

        if (this.value != other.value) {
            return false;
        }

        return true;
    }

    public boolean equalsColor(Object anObject) {

        if (anObject == null) {
            return false;
        }

        if (getClass() != anObject.getClass()) {
            return false;
        }

        final PlayingCard other = (PlayingCard) anObject;

        if (this.color != other.color) {
            return false;
        }

        return true;
    }

    public Color getColor() {
        return color;
    }

    public String getFilename() {
        return suit.toString().substring(0, 1) + value.valueString + ".png";
    }

    public ImageIcon getImage(Side aSide) {
        String path = "";
        ImageIcon imageIcon = null;

        switch (aSide) {
            case BACK:
                imageIcon = getImageBack();
                break;
            case FRONT:
                imageIcon = getImageFront();
                break;
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
        return pathBack;
    }

    public String getPathFront() {
        return pathFront;
    }

    /**
     * Get the value of suit
     *
     * @return the value of suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Get the value of value
     *
     * @return the value of value
     */
    public Value getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.value.hashCode();
        hash = 71 * hash + this.suit.hashCode();
        return hash;
    }

    /**
     * Get the value of aceHigh
     *
     * @return the value of aceHigh
     */
    public boolean isAceHigh() {
        return aceHigh;
    }

    /**
     * Set the value of aceHigh
     *
     * @param aceHigh new value of aceHigh
     */
    public void setAceHigh(boolean anAceHigh) {
        aceHigh = anAceHigh;
    }

    public void setPathBack(String aPath) {
        pathBack = aPath;
    }

    public void setPathFront(String aPath) {
        pathFront = aPath;
    }

    /**
     * Set the value of suit
     *
     * @param aSuit
     */
    public void setSuit(Suit aSuit) {
        suit = aSuit;
    }

    /**
     * Set the value of value
     *
     * @param aValue
     */
    public void setValue(Value aValue) {
        value = aValue;
    }

    @Override
    public String toString() {
        return pip[suit.ordinal()] + " " + value;
    }

    private void init() {
        pathBack = CardDeckManager.DEFAULT_PATH_BACK_0;
        pathFront = CardDeckManager.DEFAULT_PATH_FRONT_0;
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
