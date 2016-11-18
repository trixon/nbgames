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

import java.applet.AudioClip;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;
import org.nbgames.core.dice.data.sound.DiceSound;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
class Die {

    private final int MARGIN_Y = 20;
    private final int MAX_DR_1 = 4;
    private final int MAX_DR_2 = 20;
    private final int MAX_DR_3 = 4;
    private final String PATH = "org/nbgames/core/dice/data/image/dice/";
    private Thread animator = new Thread();
    private AudioClip audioClip;
    private BufferedImage bufferedImage;
    private int center;
    private int column;
    private DiceBoard diceBoard;
    private int diceToFloor = 0;
    private String imageFile;
    private int offsetX;
    private Random random = new Random();
    private boolean selected = true;
    private int storedY;
    private int value;
    private boolean visible;
    private int x;
    private int y;

    Die(DiceBoard aDiceBoard, int aColumn) {
        this.diceBoard = aDiceBoard;
        this.column = aColumn;
        init();
    }

    private int generateValue() {
        value = random.nextInt(6) + 1;
        int variant = random.nextInt(2) + 1;
        int mode = 0;
        imageFile = PATH + String.format("%d_%02d_%02d.png", mode, value, variant);

        setBufferedImage(imageFile);
        return value;
    }

    private void init() {
    }

    private void rotate(double aTheta) {
        AffineTransform affineTransform = bufferedImage.createGraphics().getTransform();
        affineTransform.rotate(aTheta, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC);

        bufferedImage = affineTransformOp.filter(bufferedImage, null);
    }

    private void scale() {
        double scaleFactor = y / 200.0 + 0.8;
        scaleFactor = Math.max(scaleFactor, 0.01);

        AffineTransform affineTransform = bufferedImage.createGraphics().getTransform();
        affineTransform.scale(scaleFactor, scaleFactor);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC);

        bufferedImage = affineTransformOp.filter(bufferedImage, null);
    }

    private void setBufferedImage(String imageFilePath) {
        bufferedImage = (BufferedImage) ImageUtilities.loadImage(imageFilePath);
    }

    Thread getAnimator() {
        return animator;
    }

    int getCenter() {
        return center;
    }

    int getColumn() {
        return column;
    }

    BufferedImage getImage() {
        return bufferedImage;
    }

    int getValue() {
        return value;
    }

    int getX() {
        return center + offsetX;
    }

    int getY() {
        return y;
    }

    boolean isSelected() {
        return selected;
    }

    boolean isVisible() {
        return visible;
    }

    void park() {
    }

    void reset() {
        setVisible(false);
        selected = true;
        animator.interrupt();
    }

    void roll() {

        if (animator.isAlive()) {
            return;
        }

        if (selected) {
            animator = new Thread(new RollRunner());
            animator.start();
        }

        selected = false;
    }

    void setCenter(int aCenterValue) {
        this.center = aCenterValue;
    }

    void setDiceTofloor(int aDiceToFlorFrequence) {
        diceToFloor = aDiceToFlorFrequence;
    }

    void setEnabled(boolean b) {
    }

    void setSelected(boolean selected) {

        if (animator.isAlive()) {
            return;
        }

        this.selected = selected;
        animator = new Thread(new SelectRunner());
        animator.start();
    }

    void setVisible(boolean visible) {
        this.visible = visible;
    }

    class RollRunner implements Runnable {

        @Override
        public void run() {

            /*
             * This random delay manage the start order of the dice.
             */
            try {
                Thread.sleep(random.nextInt(300 / (column + 1)));
            } catch (InterruptedException ex) {
            }

            x = 0;
            setVisible(true);
            /*
             * Roll the die from the hand to its column.
             */
            rollOut();
            /*
             * ...continue to generate values and a new positions.
             */
            spin();

//            if (y < 30 && diceToFloor > 0) {
//                if (random.nextInt(diceToFloor) == random.nextInt(diceToFloor)) {
//                    diceToFloor();
//                }
//                diceToFloor();
//            } else {
//                /*
//                 * ...final wobble, don't generate a new value.
//                 */
//                vibrate();
//            }
            vibrate();

            try {
                audioClip.stop();
            } catch (NullPointerException ex) {
            }

        }

        private void rollOut() {
            if (diceBoard.isPlaySound()) {
                variant = random.nextInt(MAX_DR_2) + 1;
                file = String.format("dr_2_%02d.au", variant);
                audioClip = DiceSound.getAudioClip(file);
                audioClip.loop();
            }

            while (x < center - 75) {
                x += random.nextInt(95) + 25;
                y = MARGIN_Y + random.nextInt(60);
                offsetX = x - center;

                value = generateValue();
                rotate(2 * Math.PI * random.nextDouble());
                scale();

                diceBoard.getPanel().repaint();

                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                }
            }

            try {
                audioClip.stop();
            } catch (NullPointerException ex) {
            }
        }

        private void spin() {
            baseY = y;
            loops = random.nextInt(7) + 5;

            if (diceBoard.isPlaySound()) {
                variant = random.nextInt(MAX_DR_2) + 1;
                file = String.format("dr_2_%02d.au", variant);
                audioClip = DiceSound.getAudioClip(file);
                audioClip.loop();
            }

            for (int i = 0; i < loops; i++) {
                value = generateValue();
                double factor = 1.2;
                double theta = factor * random.nextDouble();
                rotate(-factor + 2 * factor * theta);
                scale();

                int randomSize = Math.max(1, Painter.DIE_CELL_WIDTH - bufferedImage.getWidth());
                x = (center - Painter.DIE_CELL_WIDTH / 2) + random.nextInt(randomSize);
                y = baseY + random.nextInt(30) - 10;
                offsetX = x - center;

                diceBoard.getPanel().repaint();

                try {
                    Thread.sleep(random.nextInt(40) + 120);
                } catch (InterruptedException ex) {
                }
            }

            try {
                audioClip.stop();
            } catch (NullPointerException ex) {
            }
        }

        private void vibrate() {
            loops = random.nextInt(10) + 3;

            if (diceBoard.isPlaySound()) {
                variant = random.nextInt(MAX_DR_3) + 1;
                file = String.format("dr_3_%02d.au", variant);
                audioClip = DiceSound.getAudioClip(file);
                audioClip.loop();
            }

            baseX = x;
            baseY = y;

            for (int i = 0; i < loops; i++) {
                x = baseX + random.nextInt(6) - 3;
                y = baseY + random.nextInt(6) - 3;
                offsetX = x - center;

                setBufferedImage(imageFile);

                double factor = 0.2;
                double theta = factor * random.nextDouble();
                rotate(-factor + 2 * factor * theta);
                scale();

                diceBoard.getPanel().repaint();

                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                }
            }
            setBufferedImage(imageFile);
            scale();
            diceBoard.getPanel().repaint();
            storedY = y;

        }

        private void diceToFloor() {

            if (diceBoard.isPlaySound()) {
                audioClip = DiceSound.getAudioClip("dtf.au");
                audioClip.play();
            }

            while (y > -200) {
                y -= 10;
                setBufferedImage(imageFile);
                scale();

                diceBoard.getPanel().repaint();

                try {
                    Thread.sleep(80);
                } catch (InterruptedException ex) {
                }
            }

            diceBoard.setDiceOnFloor(true);
        }
        String file;
        int variant;
        int loops;
        int baseX;
        int baseY;
    }

    class RollToFloorRunner implements Runnable {

        @Override
        public void run() {
            if (diceBoard.isPlaySound()) {
                audioClip = DiceSound.getAudioClip("dtf.au");
                audioClip.play();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
            }
        }
    }

    class SelectRunner implements Runnable {

        @Override
        public void run() {
            if (diceBoard.isPlaySound() && false) {
                audioClip = DiceSound.getAudioClip("ds_01.au");
                audioClip.play();
                try {
                    Thread.sleep(120);
                } catch (InterruptedException ex) {
                }
            }

            int endPos = diceBoard.getPanel().getHeight();
            if (selected) {
                while (y < endPos) {
                    y = Math.min((int) (y * 1.3), endPos);
                    setBufferedImage(imageFile);
                    scale();

                    diceBoard.getPanel().repaint();

                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                    }
                }
            } else {
                while (y > storedY) {
                    y = Math.max(y - (int) (y * 0.3), storedY);
                    setBufferedImage(imageFile);
                    scale();

                    diceBoard.getPanel().repaint();

                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                    }
                }
            }
            diceBoard.getDicePainter().calcRollable();
        }
    }
}
