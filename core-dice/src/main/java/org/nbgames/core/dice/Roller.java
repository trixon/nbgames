/*
 * Copyright 2015 Patrik Karlsson.
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

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;
import org.openide.util.ImageUtilities;
import se.trixon.almond.util.GraphicsHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
class Roller {

    private final String PATH = "org/nbgames/core/dice/data/image/hand/closed/";
    private BufferedImage bufferedImage;
    private DiceBoard diceBoard;
    private int numOfDice;
    private Random random = new Random();
    private Thread releaseThread = new Thread();
    private Thread shakeThread = new Thread();
    private Thread slideThread = new Thread();
    private boolean visible;
    private int x;
    private int y;

    Roller(DiceBoard aDiceBoard) {
        this.diceBoard = aDiceBoard;
        init();
    }

    private void init() {
        setImage(5);
        x = -500;
        y = Painter.MARGIN_Y_ROLLER;
    }

    BufferedImage getImage() {
        return GraphicsHelper.flipBufferedImageX(bufferedImage);
    }

    int getNumOfDice() {
        return numOfDice;
    }

    Thread getShakeThread() {
        return shakeThread;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    boolean isVisible() {
        return visible;
    }

    void roll() {
        releaseThread = new Thread(new ReleaseRunner());
        releaseThread.start();
    }

    void setImage(int aRollCount) {
        int variant = random.nextInt(2) + 1;
        aRollCount = Math.min(aRollCount, 6);
        String file = String.format("%02d_%02d.png", aRollCount, variant);
//        bufferedImage = GraphicsHelper.getBufferedImage(this.getClass().getResource(PATH + file));
        bufferedImage = (BufferedImage) ImageUtilities.loadImage(PATH + file);
    }

    void setNumOfDice(int aNumOfDice) {
        this.numOfDice = aNumOfDice;
    }

    void setVisible(boolean visible) {
        this.visible = visible;
    }

    void shake(boolean aState) {
        visible = true;
        if (aState) {
            slideThread.interrupt();
            shakeThread = new Thread(new ShakeRunner());
            shakeThread.start();
        } else {
            shakeThread.interrupt();
        }
    }

    void slideIn() {
        slideThread.interrupt();
        shakeThread.interrupt();
        slideThread = new Thread(new SlideInRunner());
        slideThread.start();
    }

    void slideOut() {
        slideThread.interrupt();
        shakeThread.interrupt();
        slideThread = new Thread(new SlideOutRunner());
        slideThread.start();
    }

    class ReleaseRunner implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
            }
            slideOut();
        }
    }

    class ShakeRunner implements Runnable {

        @Override
        public void run() {
            BufferedImage originalBufferedImage;
            AffineTransformOp originalAffineTransformOp = new AffineTransformOp(bufferedImage.createGraphics().getTransform(), AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            originalBufferedImage = originalAffineTransformOp.filter(bufferedImage, null);

            while (!Thread.interrupted()) {
                x = Painter.MARGIN_X_ROLLER + random.nextInt(10);
                y = Painter.MARGIN_Y_ROLLER + random.nextInt(10);

                AffineTransform affineTransform = bufferedImage.createGraphics().getTransform();
                double theta = 0.03;
                affineTransform.rotate(-theta + 2 * theta * random.nextDouble());

                AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                bufferedImage = affineTransformOp.filter(bufferedImage, null);

                diceBoard.getPanel().repaint();

                try {
                    Thread.sleep(60);
                } catch (InterruptedException ex) {
                    bufferedImage = originalAffineTransformOp.filter(originalBufferedImage, null);
                    break;
                }

                bufferedImage = originalAffineTransformOp.filter(originalBufferedImage, null);

            }
        }
    }

    class SlideInRunner implements Runnable {

        @Override
        public void run() {

            while (!Thread.interrupted() && x < 0 + Painter.MARGIN_X_ROLLER) {
                x += 4 - x / 5;

                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    break;
                }

                diceBoard.getPanel().repaint();
            }

            visible = true;
        }
    }

    class SlideOutRunner implements Runnable {

        @Override
        public void run() {
            double accel = 1.011;
            int i = 0;

            while (!Thread.interrupted() && x >= -bufferedImage.getWidth()) {
                x -= 8 + accel * i;
                i++;
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    break;
                }
                diceBoard.getPanel().repaint();
            }

            visible = false;
        }
    }
}
