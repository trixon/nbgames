package org.nbgames.yaya;

import org.nbgames.core.game.GameController;
import org.nbgames.core.game.NewGameDialogDescriptor;
import org.nbgames.core.game.NewGamePanel;
import org.openide.DialogDisplayer;
import org.openide.windows.WindowManager;

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
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                YayaNewGameDialogDescriptor descriptor = new YayaNewGameDialogDescriptor(new YayaNewGamePanel());
                DialogDisplayer.getDefault().notify(descriptor);
            }
        });
    }

    class YayaNewGameDialogDescriptor extends NewGameDialogDescriptor {

        public YayaNewGameDialogDescriptor(NewGamePanel newGamePanel) {
            super(newGamePanel);
        }

        @Override
        protected void onCancelEvent() {
        }

        @Override
        protected void onStartEvent() {
            mGamePanel.newGame();
        }
    }
}
