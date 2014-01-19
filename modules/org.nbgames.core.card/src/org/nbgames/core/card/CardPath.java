package org.nbgames.core.card;

import org.nbgames.core.card.PlayingCard.Side;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class CardPath {

    private String pathBack;
    private String pathFront;

    public CardPath() {
    }

    public CardPath(String aFront, String aBack) {
        pathBack = aBack;
        pathFront = aFront;
    }

    public String getPath(Side aSide) {
        String path;
        if (aSide == Side.BACK) {
            path = pathBack;
        } else {
            path = pathFront;
        }
        return path;
    }

    public void setPath(Side aSide, String aPath) {
        if (aSide == Side.BACK) {
            pathBack = aPath;
        } else {
            pathFront = aPath;
        }
    }
}
