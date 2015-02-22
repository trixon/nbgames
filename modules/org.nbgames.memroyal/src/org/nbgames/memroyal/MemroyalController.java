package org.nbgames.memroyal;

import java.util.Observable;
import java.util.Observer;
import org.nbgames.core.api.CardGameProvider;
import org.nbgames.core.api.GameProvider;
import org.nbgames.core.base.GameController;
import org.nbgames.core.game.NewGameDialogManager;
import org.openide.DialogDisplayer;
import org.openide.awt.StatusDisplayer;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import org.openide.windows.WindowManager;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ServiceProviders(value = {
    @ServiceProvider(service = GameProvider.class),
    @ServiceProvider(service = CardGameProvider.class)}
)
public class MemroyalController extends GameController implements CardGameProvider, NewGameDialogManager.NewGameController, Observer {

    public static final String TAG = "Memroyal";
    private final MemroyalPanel mGamePanel;
    private Rules rules;

    public MemroyalController() {
        mGamePanel = null;
    }

    public MemroyalController(MemroyalTopComponent gameTopComponent) {
        super(gameTopComponent);
        mGamePanel = new MemroyalPanel(this);
        setGamePanel(mGamePanel);
        gameTopComponent.setGamePanel(mGamePanel);
    }

    @Override
    public String getOptionsPath() {
        return "Card/Memroyal";
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
