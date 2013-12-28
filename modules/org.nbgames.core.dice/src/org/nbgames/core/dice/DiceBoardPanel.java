package org.nbgames.core.dice;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.openide.util.ImageUtilities;
import se.trixon.almond.GraphicsHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
class DiceBoardPanel extends JPanel {

    private final String DEFAULT_IMAGE_PATH = "org/nbgames/core/dice/data/image/board/default.jpg";
    private final Dimension MAX_DIMENSION = new Dimension(1200, 200);
    private final Dimension PREFERRED_DIMENSION = new Dimension(1000, 180);
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
