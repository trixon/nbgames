/* 
 * Copyright 2018 Patrik Karlström.
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

import java.util.LinkedList;
import javax.swing.ImageIcon;
import org.nbgames.core.card.PlayingCard.Suit;
import org.nbgames.core.card.PlayingCard.Value;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;

/**
 *
 * @author Patrik Karlström
 */
public enum CardDeckManager {

    INSTANCE;
    public static final String DEFAULT_PATH_BACK_0 = "ro/nicubunu/cards/backs/back01.png";
    public static final String DEFAULT_PATH_BACK_1 = "ro/nicubunu/cards/backs/back02.png";
    public static final String DEFAULT_PATH_FRONT_0 = "ro/nicubunu/cards/ornamental/";
    public static final String DEFAULT_PATH_FRONT_1 = "ro/nicubunu/cards/ornamental/";
    private final LinkedList<ImageIcon> mBackImages = new LinkedList<ImageIcon>();
    private final LinkedList<String> mBackList = new LinkedList<String>();
    private final LinkedList<ImageIcon> mFrontImages = new LinkedList<ImageIcon>();
    private final LinkedList<String> mFrontList = new LinkedList<String>();

    public LinkedList<ImageIcon> getBackImages() {
        return mBackImages;
    }

    public LinkedList<String> getBackList() {
        return mBackList;
    }

    public LinkedList<ImageIcon> getFrontImages() {
        return mFrontImages;
    }

    public LinkedList<String> getFrontList() {
        return mFrontList;
    }

    public void init() {
        initBack();
        initFront();
    }

    private void initBack() {
        mBackList.clear();
        mBackImages.clear();
        for (CardBackSupplier cardBackSupplier : Lookup.getDefault().lookupAll(CardBackSupplier.class)) {
            String name = cardBackSupplier.getName();
            String path = cardBackSupplier.getPath();
            ImageIcon imageIcon = ImageUtilities.loadImageIcon(path, false);

            if (imageIcon != null) {
                mBackList.add(path);
                imageIcon.setDescription(name);
                mBackImages.add(imageIcon);
            } else {
                System.err.println("INVALID BACK:");
                System.err.println(name);
                System.err.println(path);
            }
        }
    }

    private void initFront() {
        mFrontList.clear();
        mFrontImages.clear();
        for (CardDeckSupplier cardDeckSupplier : Lookup.getDefault().lookupAll(CardDeckSupplier.class)) {
            String name = cardDeckSupplier.getName();
            String path = cardDeckSupplier.getPath();

            if (isValidDeck(path)) {
                mFrontList.add(path);
                PlayingCard playingCard = new PlayingCard(Suit.DIAMOND, Value.KING);
                playingCard.setPathFront(path);
                ImageIcon imageIcon = playingCard.getImageFront();
                imageIcon.setDescription(name);
                mFrontImages.add(imageIcon);
            } else {
                System.err.println("INVALID DECK:");
                System.err.println(name);
                System.err.println(path);
            }
        }
    }

    private boolean isValidDeck(String path) {
        CardDeck cardDeck = new CardDeck();
        boolean result = true;
        String file;

        for (PlayingCard playingCard : cardDeck) {
            file = path + playingCard.getFilename();

            if (ImageUtilities.loadImage(file) == null) {
                result = false;
                break;
            }
        }

        return result;
    }
}
