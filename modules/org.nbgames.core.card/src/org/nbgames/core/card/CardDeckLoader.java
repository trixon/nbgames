package org.nbgames.core.card;

import java.util.LinkedList;
import javax.swing.ImageIcon;
import org.nbgames.core.card.PlayingCard.Suit;
import org.nbgames.core.card.PlayingCard.Value;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class CardDeckLoader {

    public static final String DEFAULT_PATH_BACK_0 = "ro/nicubunu/cards/backs/back01.png";
    public static final String DEFAULT_PATH_BACK_1 = "ro/nicubunu/cards/backs/back02.png";
    public static final String DEFAULT_PATH_FRONT_0 = "ro/nicubunu/cards/ornamental/";
    public static final String DEFAULT_PATH_FRONT_1 = "ro/nicubunu/cards/ornamental/";
    private static LinkedList<ImageIcon> backImages = new LinkedList<ImageIcon>();
    private static LinkedList<ImageIcon> frontImages = new LinkedList<ImageIcon>();
    private static LinkedList<String> backList = new LinkedList<String>();
    private static LinkedList<String> frontList = new LinkedList<String>();

    public static LinkedList<ImageIcon> getBackImages() {
        return backImages;
    }

    public static LinkedList<String> getBackList() {
        return backList;
    }

    public static LinkedList<ImageIcon> getFrontImages() {
        return frontImages;
    }

    public static LinkedList<String> getFrontList() {
        return frontList;
    }

    public static void initialize() {
        initBack();
        initFront();
    }

    private static void initBack() {
        backList.clear();
        backImages.clear();
        for (CardBackSupplier cardBackSupplier : Lookup.getDefault().lookupAll(CardBackSupplier.class)) {
            String name = cardBackSupplier.getName();
            String path = cardBackSupplier.getPath();
            ImageIcon imageIcon = ImageUtilities.loadImageIcon(path, false);

            if (imageIcon != null) {
                backList.add(path);
                imageIcon.setDescription(name);
                backImages.add(imageIcon);
            } else {
                System.err.println("INVALID BACK:");
                System.err.println(name);
                System.err.println(path);
            }
        }
    }

    private static void initFront() {
        frontList.clear();
        frontImages.clear();
        for (CardDeckSupplier cardDeckSupplier : Lookup.getDefault().lookupAll(CardDeckSupplier.class)) {
            String name = cardDeckSupplier.getName();
            String path = cardDeckSupplier.getPath();

            if (isValidDeck(path)) {
                frontList.add(path);
                PlayingCard playingCard = new PlayingCard(Suit.DIAMOND, Value.KING);
                playingCard.setPathFront(path);
                ImageIcon imageIcon = playingCard.getImageFront();
                imageIcon.setDescription(name);
                frontImages.add(imageIcon);
            } else {
                System.err.println("INVALID DECK:");
                System.err.println(name);
                System.err.println(path);
            }
        }
    }

    private static boolean isValidDeck(String path) {
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
