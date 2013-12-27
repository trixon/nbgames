package org.nbgames.gunu;

import org.nbgames.core.game.GameController;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class GunuController extends GameController {

    private final GunuPanel mGamePanel;

    public GunuController(GunuTopComponent gameTopComponent, String gameName, String gameVersion, String gameCopyright, String optionsPath) {
        super(gameTopComponent, gameName, gameVersion, gameCopyright, optionsPath);
        mGamePanel = new GunuPanel(this);
        setGamePanel(mGamePanel);
        gameTopComponent.setGamePanel(mGamePanel);
    }

    @Override
    public void newGame() {
        mGamePanel.newGame();
    }
}
