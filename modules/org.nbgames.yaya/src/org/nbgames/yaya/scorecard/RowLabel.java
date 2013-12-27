package org.nbgames.yaya.scorecard;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class RowLabel extends JLabel {

    private Color currentBackgroundColor;

    public RowLabel() {
    }

    public Color getCurrentBackgroundColor() {
        return currentBackgroundColor;
    }

    public void setBackground() {
        setBackground(currentBackgroundColor);
    }

    public void setCurrentBackgroundColor(Color currentBackgroundColor) {
        this.currentBackgroundColor = currentBackgroundColor;
    }
}
