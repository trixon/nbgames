package org.nbgames.core.card;

import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public abstract class CardList extends LinkedList<PlayingCard> {

    public void applyThemeBack(String aPath) {
        for (PlayingCard card : this) {
            card.setPathBack(aPath);
        }
    }

    public void applyThemeFront(String aPath) {
        for (PlayingCard card : this) {
            card.setPathFront(aPath);
        }
    }

    public void shuffle() {
        Collections.shuffle(this);
    }
}
