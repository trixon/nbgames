package org.nbgames.core.card;

import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public abstract class CardList extends LinkedList<PlayingCard> {

    public void applyThemeBack(String path) {
        for (PlayingCard card : this) {
            card.setPathBack(path);
        }
    }

    public void applyThemeFront(String path) {
        for (PlayingCard card : this) {
            card.setPathFront(path);
        }
    }

    public void shuffle() {
        Collections.shuffle(this);
    }
}
