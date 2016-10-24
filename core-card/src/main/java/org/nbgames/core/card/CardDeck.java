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
