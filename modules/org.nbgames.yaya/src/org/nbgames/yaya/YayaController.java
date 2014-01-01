package org.nbgames.yaya;

import org.nbgames.core.game.GameController;
import org.nbgames.core.game.NewGameDialogManager;
import org.nbgames.core.game.NewGameDialogManager.NewGameController;
import org.openide.DialogDisplayer;
import org.openide.windows.WindowManager;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class YayaController extends GameController implements NewGameController {

    public static final String LOG_TITLE = "Yaya";

    private final YayaPanel mGamePanel;

    public YayaController(YayaTopComponent gameTopComponent, String infoName, String infoVersion, String infoDescription, String infoCopyright, String optionsPath) {
        super(gameTopComponent, infoName, infoVersion, infoDescription, infoCopyright, optionsPath);
        mGamePanel = new YayaPanel(this);
        setGamePanel(mGamePanel);
        gameTopComponent.setGamePanel(mGamePanel);
    }

    @Override
    public void onCancelNewGame() {
    }

    @Override
    public void onStartNewGame() {
        mGamePanel.newGame();
    }

    @Override
    public void requestNewGame() {
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                NewGameDialogManager manager = new NewGameDialogManager(new YayaNewGamePanel(), YayaController.this);
                DialogDisplayer.getDefault().notify(manager.getDialogDescriptor());
            }
        });
    }
}
