package org.nbgames.gunu;

import org.nbgames.core.api.GameProvider;
import org.nbgames.core.api.LogicGameProvider;
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
    @ServiceProvider(service = LogicGameProvider.class)}
)
public class GunuController extends GameController implements LogicGameProvider, NewGameDialogManager.NewGameController {

    public static final String TAG = "Gunu";
    private final GunuPanel mGamePanel;

    public GunuController() {
        mGamePanel = null;
    }

    public GunuController(GunuTopComponent gameTopComponent) {
        super(gameTopComponent);
        mGamePanel = new GunuPanel(this);
        setGamePanel(mGamePanel);
        gameTopComponent.setGamePanel(mGamePanel);
    }

    @Override
    public String getOptionsPath() {
        return "Logic/Gunu";
    }

    @Override
    public void onCancelNewGame() {
    }

    @Override
    public void onStartNewGame() {
        mGamePanel.newGame();
        updateStatusBar();
    }

    @Override
    public void requestNewGame() {
        WindowManager.getDefault().invokeWhenUIReady(() -> {
            NewGameDialogManager manager = new NewGameDialogManager(new GunuNewGamePanel(), GunuController.this);
            DialogDisplayer.getDefault().notify(manager.getDialogDescriptor());
        });
    }

    @Override
    public void updateStatusBar() {
        StatusDisplayer.getDefault().setStatusText(mGamePanel.getGameTitle(), StatusDisplayer.IMPORTANCE_ERROR_HIGHLIGHT);
    }
}
