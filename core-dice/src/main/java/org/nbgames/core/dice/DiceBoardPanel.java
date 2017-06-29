/* 
 * Copyright 2017 Patrik Karlsson.
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
package org.nbgames.core.dice;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import org.nbgames.core.dice.data.image.DiceImage;
import se.trixon.almond.util.GraphicsHelper;

/**
 *
 * @author Patrik Karlsson
 */
class DiceBoardPanel extends JPanel {

    private static final Dimension MAX_DIMENSION = new Dimension(1200, 200);
    private static final Dimension PREFERRED_DIMENSION = new Dimension(1000, 200);
    private BufferedImage mBackgroundImage;

    DiceBoardPanel() {
        init();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(mBackgroundImage, 0, 0, this);
        g2.drawImage(GraphicsHelper.flipBufferedImageX(mBackgroundImage), mBackgroundImage.getWidth(), 0, this);
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    private void init() {
        mBackgroundImage = DiceImage.get("board/default.jpg");
        setMaximumSize(MAX_DIMENSION);
        setPreferredSize(PREFERRED_DIMENSION);
        setLayout(new GridLayout(1, 1));

        addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                repaint();
                revalidate();
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

        });
    }
}
