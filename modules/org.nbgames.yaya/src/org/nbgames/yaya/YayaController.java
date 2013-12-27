package org.nbgames.yaya;

import org.nbgames.core.game.GameController;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class YayaController extends GameController {

    private final YayaPanel mGamePanel;

    public YayaController(YayaTopComponent gameTopComponent, String gameName, String gameVersion, String gameCopyright, String optionsPath) {
        super(gameTopComponent, gameName, gameVersion, gameCopyright, optionsPath);
        mGamePanel = new YayaPanel(this);
        setGamePanel(mGamePanel);
        gameTopComponent.setGamePanel(mGamePanel);
    }

    @Override
    public void newGame() {
        mGamePanel.newGame();
    }
}
