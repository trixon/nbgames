/*
 * Copyright 2016 Patrik Karlsson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this mFile except in compliance with the License.
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
import javax.swing.SwingUtilities;
import org.nbgames.core.dice.data.image.DiceImage;
import org.nbgames.core.dice.data.sound.DiceSound;

/**
 *
 * @author Patrik Karlsson
 */
class Die {

    private static final int MARGIN_Y = 20;
    private static final int MAX_DR_1 = 4;
    private static final int MAX_DR_2 = 20;
    private static final int MAX_DR_3 = 4;
    private Thread mAnimatorThread = new Thread();
    private AudioClip mAudioClip;
    private BufferedImage mBufferedImage;
    private int mCenter;
    private int mColumn;
    private DiceBoard mDiceBoard;
    private int mDiceToFloor = 0;
    private String mImagePath;
    private int mOffsetX;
    private Random mRandom = new Random();
    private boolean mSelected = true;
    private int mStoredY;
    private int mValue;
    private boolean mVisible;
    private int mX;
    private int mY;

    Die(DiceBoard diceBoard, int column) {
        mDiceBoard = diceBoard;
        mColumn = column;
        init();
    }

    private int generateValue() {
        mValue = mRandom.nextInt(6) + 1;
        int variant = mRandom.nextInt(2) + 1;
        int mode = 0;
        mImagePath = String.format("dice/%d_%02d_%02d.png", mode, mValue, variant);

        setBufferedImage(mImagePath);

        return mValue;
    }

    private void init() {
    }

    private void repaintDiceBoard() {
        SwingUtilities.invokeLater(() -> {
            mDiceBoard.getPanel().repaint();
        });
    }

    private void rotate(double theta) {
        AffineTransform affineTransform = mBufferedImage.createGraphics().getTransform();
        affineTransform.rotate(theta, mBufferedImage.getWidth() / 2, mBufferedImage.getHeight() / 2);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC);

        mBufferedImage = affineTransformOp.filter(mBufferedImage, null);
    }

    private void scale() {
        double scaleFactor = mY / 200.0 + 0.8;
        scaleFactor = Math.max(scaleFactor, 0.01);

        AffineTransform affineTransform = mBufferedImage.createGraphics().getTransform();
        affineTransform.scale(scaleFactor, scaleFactor);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC);

        mBufferedImage = affineTransformOp.filter(mBufferedImage, null);
    }

    private void setBufferedImage(String imagePath) {
        mBufferedImage = DiceImage.get(imagePath);
    }

    Thread getAnimator() {
        return mAnimatorThread;
    }

    int getCenter() {
        return mCenter;
    }

    int getColumn() {
        return mColumn;
    }

    BufferedImage getImage() {
        return mBufferedImage;
    }

    int getValue() {
        return mValue;
    }

    int getX() {
        return mCenter + mOffsetX;
    }

    int getY() {
        return mY;
    }

    boolean isSelected() {
        return mSelected;
    }

    boolean isVisible() {
        return mVisible;
    }

    void park() {
    }

    void reset() {
        setVisible(false);
        mSelected = true;
        mAnimatorThread.interrupt();
    }

    void roll() {

        if (mAnimatorThread.isAlive()) {
            return;
        }

        if (mSelected) {
            mAnimatorThread = new Thread(new RollRunner());
            mAnimatorThread.start();
        }

        mSelected = false;
    }

    void setCenter(int centerValue) {
        mCenter = centerValue;
    }

    void setDiceTofloor(int diceToFlorFrequence) {
        mDiceToFloor = diceToFlorFrequence;
    }

    void setEnabled(boolean b) {
    }

    void setSelected(boolean selected) {

        if (mAnimatorThread.isAlive()) {
            return;
        }

        mSelected = selected;
        mAnimatorThread = new Thread(new SelectRunner());
        mAnimatorThread.start();
    }

    void setVisible(boolean visible) {
        mVisible = visible;
    }

    class RollRunner implements Runnable {

        int mBaseX;
        int mBaseY;
        String mFile;
        int mLoops;
        int mVariant;

        @Override
        public void run() {

            /*
             * This random delay manage the start order of the dice.
             */
            try {
                Thread.sleep(mRandom.nextInt(300 / (mColumn + 1)));
            } catch (InterruptedException ex) {
            }

            mX = 0;
            setVisible(true);
            /*
             * Roll the die from the hand to its mColumn.
             */
            rollOut();
            /*
             * ...continue to generate values and a new positions.
             */
            spin();

//            if (mY < 30 && diceToFloor > 0) {
//                if (random.nextInt(diceToFloor) == random.nextInt(diceToFloor)) {
//                    diceToFloor();
//                }
//                diceToFloor();
//            } else {
//                /*
//                 * ...final wobble, don't generate a new mValue.
//                 */
//                vibrate();
//            }
            vibrate();

            try {
                mAudioClip.stop();
            } catch (NullPointerException ex) {
            }

        }

        private void diceToFloor() {

            if (mDiceBoard.isPlaySound()) {
                mAudioClip = DiceSound.getAudioClip("dtf.au");
                mAudioClip.play();
            }

            while (mY > -200) {
                mY -= 10;
                setBufferedImage(mImagePath);
                scale();

                repaintDiceBoard();
                try {
                    Thread.sleep(80);
                } catch (InterruptedException ex) {
                }
            }

            mDiceBoard.setDiceOnFloor(true);
        }

        private void rollOut() {
            if (mDiceBoard.isPlaySound()) {
                mVariant = mRandom.nextInt(MAX_DR_2) + 1;
                mFile = String.format("dr_2_%02d.au", mVariant);
                mAudioClip = DiceSound.getAudioClip(mFile);
                mAudioClip.loop();
            }

            while (mX < mCenter - 75) {
                mX += mRandom.nextInt(95) + 25;
                mY = MARGIN_Y + mRandom.nextInt(60);
                mOffsetX = mX - mCenter;

                mValue = generateValue();
                rotate(2 * Math.PI * mRandom.nextDouble());
                scale();

                repaintDiceBoard();

                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                }
            }

            try {
                mAudioClip.stop();
            } catch (NullPointerException ex) {
            }
        }

        private void spin() {
            mBaseY = mY;
            mLoops = mRandom.nextInt(7) + 5;

            if (mDiceBoard.isPlaySound()) {
                mVariant = mRandom.nextInt(MAX_DR_2) + 1;
                mFile = String.format("dr_2_%02d.au", mVariant);
                mAudioClip = DiceSound.getAudioClip(mFile);
                mAudioClip.loop();
            }

            for (int i = 0; i < mLoops; i++) {
                mValue = generateValue();
                double factor = 1.2;
                double theta = factor * mRandom.nextDouble();
                rotate(-factor + 2 * factor * theta);
                scale();

                int randomSize = Math.max(1, Painter.DIE_CELL_WIDTH - mBufferedImage.getWidth());
                mX = (mCenter - Painter.DIE_CELL_WIDTH / 2) + mRandom.nextInt(randomSize);
                mY = mBaseY + mRandom.nextInt(30) - 10;
                mOffsetX = mX - mCenter;

                repaintDiceBoard();

                try {
                    Thread.sleep(mRandom.nextInt(40) + 120);
                } catch (InterruptedException ex) {
                }
            }

            try {
                mAudioClip.stop();
            } catch (NullPointerException ex) {
            }
        }

        private void vibrate() {
            mLoops = mRandom.nextInt(10) + 3;

            if (mDiceBoard.isPlaySound()) {
                mVariant = mRandom.nextInt(MAX_DR_3) + 1;
                mFile = String.format("dr_3_%02d.au", mVariant);
                mAudioClip = DiceSound.getAudioClip(mFile);
                mAudioClip.loop();
            }

            mBaseX = mX;
            mBaseY = mY;

            for (int i = 0; i < mLoops; i++) {
                mX = mBaseX + mRandom.nextInt(6) - 3;
                mY = mBaseY + mRandom.nextInt(6) - 3;
                mOffsetX = mX - mCenter;

                setBufferedImage(mImagePath);

                double factor = 0.2;
                double theta = factor * mRandom.nextDouble();
                rotate(-factor + 2 * factor * theta);
                scale();

                repaintDiceBoard();

                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                }
            }

            setBufferedImage(mImagePath);
            scale();
            repaintDiceBoard();
            mStoredY = mY;
        }
    }

    class RollToFloorRunner implements Runnable {

        @Override
        public void run() {
            if (mDiceBoard.isPlaySound()) {
                mAudioClip = DiceSound.getAudioClip("dtf.au");
                mAudioClip.play();
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
            if (mDiceBoard.isPlaySound() && false) {
                mAudioClip = DiceSound.getAudioClip("ds_01.au");
                mAudioClip.play();
                try {
                    Thread.sleep(120);
                } catch (InterruptedException ex) {
                }
            }

            int endPos = mDiceBoard.getPanel().getHeight();
            if (mSelected) {
                while (mY < endPos) {
                    mY = Math.min((int) (mY * 1.3), endPos);
                    setBufferedImage(mImagePath);
                    scale();

                    repaintDiceBoard();

                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                    }
                }
            } else {
                while (mY > mStoredY) {
                    mY = Math.max(mY - (int) (mY * 0.3), mStoredY);
                    setBufferedImage(mImagePath);
                    scale();

                    repaintDiceBoard();

                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                    }
                }
            }
            mDiceBoard.getDicePainter().calcRollable();
        }
    }
}
