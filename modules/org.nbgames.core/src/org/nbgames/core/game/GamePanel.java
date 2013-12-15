package org.nbgames.core.game;

import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.nbgames.core.NbGames;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class GamePanel extends JPanel {

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
//        Container container = getParent();
//        if (container != null) {
//            int height = (container.getHeight() - getHeight()) / 2;
//            int width = (container.getWidth() - getWidth()) / 2;
//            ((JComponent) container).setBorder(BorderFactory.createEmptyBorder(height, width, height, width));
//        }
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
