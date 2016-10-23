package org.nbgames.core.card;

import org.nbgames.core.card.PlayingCard.Suit;
import org.nbgames.core.card.PlayingCard.Value;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class CardDeck extends CardList {

    public CardDeck() {
        initNewCollection();
    }

    public CardDeck(int numOfDecks) {
        for (int i = 0; i < numOfDecks; i++) {
            initNewCollection();
        }
    }

    public void reset() {
        clear();
        initNewCollection();
    }

    public void setBack(String path) {
        for (PlayingCard playingCard : this) {
            playingCard.setPathBack(path);

        }
    }

    public void setFront(String path) {
        for (PlayingCard playingCard : this) {
            playingCard.setPathFront(path);
        }
    }

    private void initNewCollection() {
        for (Suit s : Suit.values()) {
            for (Value v : Value.values()) {
                add(new PlayingCard(s, v));
            }
        }
    }
}
