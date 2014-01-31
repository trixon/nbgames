package org.nbgames.core.card;

import org.nbgames.core.card.PlayingCard.Side;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class CardPath {

    private String mPathBack;
    private String mPathFront;

    public CardPath() {
    }

    public CardPath(String front, String back) {
        mPathBack = back;
        mPathFront = front;
    }

    public String getPath(Side side) {
        String path;

        if (side == Side.BACK) {
            path = mPathBack;
        } else {
            path = mPathFront;
        }

        return path;
    }

    public void setPath(Side side, String path) {
        if (side == Side.BACK) {
            mPathBack = path;
        } else {
            mPathFront = path;
        }
    }
}
