package org.nbgames.yaya;

import java.awt.Container;
import org.nbgames.core.game.GameController;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class YayaController extends GameController {

    private final YayaPanel mGamePanel;
    private final YayaTopComponent mTopComponent;
    private final Container mTopPanel;

    public YayaController(YayaTopComponent gameTopComponent, String gameName, String gameVersion, String gameCopyright, String optionsPath) {
        super(gameTopComponent, gameName, gameVersion, gameCopyright, optionsPath);
        mTopComponent = gameTopComponent;
        mTopPanel = gameTopComponent.getTopPanel();
        mGamePanel = new YayaPanel(this);
        setGamePanel(mGamePanel);
        mTopPanel.add(getGamePanel());
        getGamePanel().centerInParent();
    }

    @Override
    public void startNewGame() {
        mGamePanel.startNewGame();
    }
}
