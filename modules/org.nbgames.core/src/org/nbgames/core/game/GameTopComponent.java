package org.nbgames.core.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import org.nbgames.core.NbGames;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public abstract class GameTopComponent extends TopComponent {

    private GamePanel mGamePanel;
    private JPanel mTopPanel;

    public GameTopComponent() {
        init();
    }

    public GameTopComponent(Lookup lookup) {
        super(lookup);
        init();
    }

    public GamePanel getGamePanel() {
        return mGamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        mGamePanel = gamePanel;
        mTopPanel.removeAll();
        mTopPanel.add(mGamePanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.decode(NbGames.COLOR_GRADIENT_COLOR1);
        Color color2 = Color.decode(NbGames.COLOR_GRADIENT_COLOR2);
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

    private void init() {
        setLayout(new BorderLayout());
        mTopPanel = new JPanel();
        mTopPanel.setLayout(new GridBagLayout());
        mTopPanel.setBackground(Color.red);
        mTopPanel.setOpaque(true);
        add(mTopPanel, BorderLayout.CENTER);
    }
}
