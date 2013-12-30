package org.nbgames.gunu;

import org.nbgames.core.game.GameController;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class GunuController extends GameController {

    private final GunuPanel mGamePanel;

    public GunuController(GunuTopComponent gameTopComponent, String infoName, String infoVersion, String infoDescription, String infoCopyright, String optionsPath) {
        super(gameTopComponent, infoName, infoVersion, infoDescription, infoCopyright, optionsPath);
        mGamePanel = new GunuPanel(this);
        setGamePanel(mGamePanel);
        gameTopComponent.setGamePanel(mGamePanel);
    }

    @Override
    public void requestNewGame() {
        mGamePanel.newGame();
    }
}
