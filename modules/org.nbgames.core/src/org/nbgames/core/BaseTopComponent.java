package org.nbgames.core;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public abstract class BaseTopComponent extends TopComponent {

    public BaseTopComponent() {
    }

    public BaseTopComponent(Lookup lookup) {
        super(lookup);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color upperColor = GlobalOptions.INSTANCE.getColor(GlobalOptions.ColorItem.WINDOW_UPPER);
        Color lowerColor = GlobalOptions.INSTANCE.getColor(GlobalOptions.ColorItem.WINDOW_LOWER);
        GradientPaint gp = new GradientPaint(0, 0, upperColor, 0, h, lowerColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
