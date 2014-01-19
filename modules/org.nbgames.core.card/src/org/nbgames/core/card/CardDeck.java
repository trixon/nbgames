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

    public CardDeck(int aNumOfDecks) {
        for (int i = 0; i < aNumOfDecks; i++) {
            initNewCollection();
        }
    }

    public void reset() {
        clear();
        initNewCollection();
    }

    public void setBack(String aPath) {
        for (PlayingCard playingCard : this) {
            playingCard.setPathBack(aPath);

        }
    }

    public void setFront(String aPath) {
        for (PlayingCard playingCard : this) {
            playingCard.setPathFront(aPath);
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
