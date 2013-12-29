package org.nbgames.core.game;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import org.nbgames.core.BaseTopComponent;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public abstract class GameTopComponent extends BaseTopComponent {

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

    protected void setActionCategoryVisible(String category, boolean visible) {
//TODO
//Hide/Show menus and toolbars
        final String FOLDER = "Actions/" + category + "";
        Lookup pathLookup = Lookups.forPath(FOLDER);

    }

    private void init() {
        setLayout(new BorderLayout());
        mTopPanel = new JPanel();
        mTopPanel.setLayout(new GridBagLayout());
        add(mTopPanel, BorderLayout.CENTER);
    }
}
