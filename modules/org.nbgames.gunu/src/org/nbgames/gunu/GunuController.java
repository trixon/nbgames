package org.nbgames.gunu;

import java.awt.Container;
import org.nbgames.core.game.GameController;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class GunuController extends GameController {

    private final GunuPanel mGamePanel;
    private final GunuTopComponent mTopComponent;
    private final Container mTopPanel;

    public GunuController(GunuTopComponent gunuTopComponent, String gameName, String gameVersion, String gameCopyright, String optionsPath) {
        super(gunuTopComponent, gameName, gameVersion, gameCopyright, optionsPath);
        mTopComponent = gunuTopComponent;
        mTopPanel = gunuTopComponent.getTopPanel();
        mGamePanel = new GunuPanel(this);
        setGamePanel(mGamePanel);
        mTopPanel.add(getGamePanel());
        getGamePanel().centerInParent();
    }

    @Override
    public void startNewGame() {
        mGamePanel.startNewGame();
    }
}
