package org.nbgames.yaya;

import org.nbgames.core.game.GameController;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class YayaController extends GameController {

    private final YayaPanel mGamePanel;

    public YayaController(YayaTopComponent gameTopComponent, String infoName, String infoVersion, String infoDescription, String infoCopyright, String optionsPath) {
        super(gameTopComponent, infoName, infoVersion, infoDescription, infoCopyright, optionsPath);
        mGamePanel = new YayaPanel(this);
        setGamePanel(mGamePanel);
        gameTopComponent.setGamePanel(mGamePanel);
    }

    @Override
    public void newGame() {
        mGamePanel.newGame();
    }
}
