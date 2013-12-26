package org.nbgames.core.dice;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JPanel;
import org.openide.util.ImageUtilities;
import se.trixon.almond.util.AGraphics;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
class DiceBoardPanel extends JPanel {

    private final Dimension MAX_DIMENSION = new Dimension(1200, 200);
    private final String PATH = "org/nbgames/core/dice/data/image/board/";
    private BufferedImage backGroundImage;

    DiceBoardPanel() {
        init();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backGroundImage, 0, 0, this);
        g2.drawImage(AGraphics.flipBufferedImageX(backGroundImage), backGroundImage.getWidth(), 0, this);
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    private void init() {
        BufferedImage bufferedImage = (BufferedImage) ImageUtilities.loadImage(PATH + "default.jpg");
        backGroundImage = bufferedImage;
//        setBackGroundImage(this.getClass().getResource(PATH + "default.jpg"));
        setMaximumSize(MAX_DIMENSION);
    }

    void setBackGroundImage(URL aBackGroundImageUrl) {
        this.backGroundImage = AGraphics.getBufferedImage(aBackGroundImageUrl);
    }
}
