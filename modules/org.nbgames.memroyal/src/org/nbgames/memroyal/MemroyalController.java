package org.nbgames.memroyal;

import java.util.Observable;
import java.util.Observer;
import org.nbgames.core.game.GameController;
import org.nbgames.core.game.NewGameDialogManager;
import org.openide.DialogDisplayer;
import org.openide.awt.StatusDisplayer;
import org.openide.windows.WindowManager;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class MemroyalController extends GameController implements NewGameDialogManager.NewGameController, Observer {

    public static final String LOG_TITLE = "Memroyal";
    private final MemroyalPanel mGamePanel;
    private Rules rules;

    public MemroyalController(MemroyalTopComponent gameTopComponent, String infoName, String infoVersion, String infoDescription, String infoCopyright, String optionsPath) {
        super(gameTopComponent, infoName, infoVersion, infoDescription, infoCopyright, optionsPath);
        mGamePanel = new MemroyalPanel(this);
        setGamePanel(mGamePanel);
        gameTopComponent.setGamePanel(mGamePanel);
    }

    @Override
    public void onCancelNewGame() {
    }

    @Override
    public void onStartNewGame() {
        mGamePanel.newGame();
        updateStatusBar();
//                topPanel.removeAll();
//        memroyalPanel = new MemroyalPanel();
//        setGamePanel(memroyalPanel);
//        topPanel.add(getGamePanel());
//        memroyalPanel.reset();
//        memroyalPanel.getGameDeck().setObserver(this);
//        rules = new Rules(memroyalPanel.getGameDeck(), Options.getInstance().getVariation());
//        memroyalPanel.getGameDeck().applyDeckTheme();
//
//        getGamePanel().centerInParent();

    }

    @Override
    public void requestNewGame() {
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                NewGameDialogManager manager = new NewGameDialogManager(new MemroyalNewGamePanel(), MemroyalController.this);
                DialogDisplayer.getDefault().notify(manager.getDialogDescriptor());
            }
        });
    }

    @Override
    public void update(Observable obj, Object arg) {
        if (arg instanceof GameDeckObservable.GameCardEvent) {
            switch ((GameDeckObservable.GameCardEvent) arg) {
                case FLIP:
                    rules.parse();
                    break;
            }
        }
    }

    @Override
    public void updateStatusBar() {
        StatusDisplayer.getDefault().setStatusText(mGamePanel.getGameTitle(), StatusDisplayer.IMPORTANCE_ERROR_HIGHLIGHT);
    }
}
