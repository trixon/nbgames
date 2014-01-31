package org.nbgames.core.card;

import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import org.nbgames.core.card.PlayingCard.Side;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class DeckComboBox extends JComboBox {

    private final Side mSide;

    public DeckComboBox(Side side) {
        mSide = side;
        init();
    }

    public void reset() {
        LinkedList<ImageIcon> imageList = new LinkedList<ImageIcon>();

        if (mSide == Side.BACK) {
            imageList.addAll(CardDeckManager.INSTANCE.getBackImages());
        } else {
            imageList.addAll(CardDeckManager.INSTANCE.getFrontImages());
        }

        int listSize = imageList.size();
        ImageIcon[] images = new ImageIcon[listSize];
        Integer[] items = new Integer[listSize];

        for (int i = 0; i < listSize; i++) {
            images[i] = imageList.get(i);
            items[i] = new Integer(i);
        }

        setModel(new DefaultComboBoxModel(items));
        LabelListCellRenderer labelListCellRenderer = new LabelListCellRenderer(images);
        setRenderer(labelListCellRenderer);
        setMaximumRowCount(3);
    }

    private void init() {
        reset();
    }
}
