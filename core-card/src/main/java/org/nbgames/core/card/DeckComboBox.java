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
