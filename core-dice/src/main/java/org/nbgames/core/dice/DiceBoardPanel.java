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
package org.nbgames.core.dice;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.openide.util.ImageUtilities;
import se.trixon.almond.util.GraphicsHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
class DiceBoardPanel extends JPanel {

    private final String DEFAULT_IMAGE_PATH = "org/nbgames/core/dice/data/image/board/default.jpg";
    private final Dimension MAX_DIMENSION = new Dimension(1200, 200);
    private final Dimension PREFERRED_DIMENSION = new Dimension(1000, 200);
    private BufferedImage mBackGroundImage;

    DiceBoardPanel() {
        super();
        init();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(mBackGroundImage, 0, 0, this);
        g2.drawImage(GraphicsHelper.flipBufferedImageX(mBackGroundImage), mBackGroundImage.getWidth(), 0, this);
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    void setBackGroundImage(String imagePath) {
        mBackGroundImage = (BufferedImage) ImageUtilities.loadImage(imagePath);
    }

    private void init() {
        setBackGroundImage(DEFAULT_IMAGE_PATH);
        setMaximumSize(MAX_DIMENSION);
        setPreferredSize(PREFERRED_DIMENSION);
        setLayout(new GridLayout(1, 1));
    }
}
