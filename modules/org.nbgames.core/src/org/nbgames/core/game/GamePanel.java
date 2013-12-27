package org.nbgames.core.game;

import java.awt.LayoutManager;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import javax.swing.JPanel;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public abstract class GamePanel extends JPanel {

    public GamePanel() {
        super();
        init();
    }

    public GamePanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        init();
    }

    public GamePanel(LayoutManager layout) {
        super(layout);
        init();
    }

    public GamePanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        init();
    }

    public void centerInParent() {
        JPanel topPanel = (JPanel) getParent();
        if (topPanel != null && topPanel.getHeight() > 0 && topPanel.getWidth() > 0) {
        }
    }

    private void init() {

        addHierarchyBoundsListener(new HierarchyBoundsListener() {

            @Override
            public void ancestorMoved(HierarchyEvent evt) {
            }

            @Override
            public void ancestorResized(HierarchyEvent evt) {
                centerInParent();
            }
        });
    }
}
