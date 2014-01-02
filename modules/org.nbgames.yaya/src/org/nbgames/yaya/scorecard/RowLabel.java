package org.nbgames.yaya.scorecard;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class RowLabel extends JLabel {

    private Color mCurrentBackgroundColor;

    public RowLabel() {
    }

    public Color getCurrentBackgroundColor() {
        return mCurrentBackgroundColor;
    }

    public void setBackground() {
        setBackground(mCurrentBackgroundColor);
    }

    public void setCurrentBackgroundColor(Color color) {
        mCurrentBackgroundColor = color;
    }
}
