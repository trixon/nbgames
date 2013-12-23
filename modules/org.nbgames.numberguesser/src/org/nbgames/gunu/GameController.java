package org.nbgames.gunu;

import java.awt.Container;
import org.nbgames.core.game.GameTopComponent;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class GameController extends org.nbgames.core.game.GameController {

    private GamePanel mGamePanel;
    private GameTopComponent mTopComponent;
    private Container mTopPanel;

    public GameController(GameTopComponent gameTopComponent, String gameName, String gameVersion, String gameCopyright, String optionsPath) {
        super(gameTopComponent, gameName, gameVersion, gameCopyright, optionsPath);
        mTopComponent = gameTopComponent;
        mTopPanel = gameTopComponent.getTopPanel();
        mGamePanel = new GamePanel(this);
        setGamePanel(mGamePanel);
        mTopPanel.add(getGamePanel());
        getGamePanel().centerInParent();
    }

    @Override
    public void startNewGame() {
        mGamePanel.startNewGame();
    }
}
