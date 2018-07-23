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
package org.nbgames.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JToolBar;

/**
 *
 * @author Patrik Karlström
 */
public class NbgToolBar extends JToolBar {

    private BufferedImage mBackgroundImage;
    private final Color mColor;
    private final Color mDefaultColor = new JToolBar().getBackground();

    public NbgToolBar() {
        mColor = new Color(mDefaultColor.getRed(), mDefaultColor.getGreen(), mDefaultColor.getBlue(), 0x88);
    }

    public BufferedImage getBackgroundImage() {
        return mBackgroundImage;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        if (mBackgroundImage != null) {
            for (int i = 0; i < (getHeight() / mBackgroundImage.getHeight()) + 1; i++) {
                if (getWidth() < mBackgroundImage.getWidth()) {
                    g2.drawImage(mBackgroundImage, 0, i * mBackgroundImage.getHeight(), null);
                } else {
                    g2.drawImage(mBackgroundImage, 0, i * mBackgroundImage.getHeight(), getWidth(), mBackgroundImage.getHeight(), null);
                }
            }
            g2.setPaint(mColor);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        super.paint(g);
        g2.dispose();
    }

    public void setBackgroundImage(BufferedImage backgroundImage) {
        mBackgroundImage = backgroundImage;
    }
}
