package org.nbgames.core.game;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import org.nbgames.core.actions.InfoCallbackAction;
import org.nbgames.core.actions.NewGameCallbackAction;
import org.nbgames.core.actions.OptionsCallbackAction;
import org.netbeans.api.options.OptionsDisplayer;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public abstract class GameController {

    private ActionMap mActionMap;
    private String mGameCopyright = "{copyright}";
    private String mGameName = "{name}";
    private GamePanel mGamePanel;
    private GameTopComponent mGameTopComponent;
    private String mGameVersion = "{version}";
    private String mOptionsPath;

    public GameController(GameTopComponent gameTopComponent, String gameName, String gameVersion, String gameCopyright, String optionsPath) {
        mGameTopComponent = gameTopComponent;
        mActionMap = gameTopComponent.getActionMap();
        mGameName = gameName;
        mGameVersion = gameVersion;
        mGameCopyright = gameCopyright;
        mOptionsPath = optionsPath;

        init();
    }

    private GameController() {
    }

    public GamePanel getGamePanel() {
        return mGamePanel;
    }

    public GameTopComponent getGameTopComponent() {
        return mGameTopComponent;
    }

    public void setGamePanel(GamePanel gamePanel) {
        mGamePanel = gamePanel;
    }

    public abstract void startNewGame();

    protected void setActiveInformation(boolean state) {

        if (state) {
            mActionMap.put(InfoCallbackAction.KEY, new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    showInformation();
                }
            });
        } else {
            mActionMap.remove(InfoCallbackAction.KEY);
        }
    }

    protected void setActiveNewGame(boolean state) {
        if (state) {
            mActionMap.put(NewGameCallbackAction.KEY, new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    startNewGame();
                }
            });
        } else {
            mActionMap.remove(NewGameCallbackAction.KEY);
        }
    }

    protected void setActiveOptions(boolean state) {
        if (state) {
            mActionMap.put(OptionsCallbackAction.KEY, new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    showOptions();
                }
            });
        } else {
            mActionMap.remove(OptionsCallbackAction.KEY);
        }
    }

    private void init() {
        setActiveInformation(true);
        setActiveNewGame(true);
        setActiveOptions(mOptionsPath != null);
    }

    private void showInformation() {
        StringBuilder builder = new StringBuilder();
        builder.append(mGameName).append("\n\n");
        builder.append(mGameVersion).append("\n");
        builder.append(mGameCopyright);

        NotifyDescriptor notifyDescriptor = new NotifyDescriptor.Message(builder.toString(), NotifyDescriptor.INFORMATION_MESSAGE);
        DialogDisplayer.getDefault().notify(notifyDescriptor);
    }

    private void showOptions() {
        OptionsDisplayer.getDefault().open(mOptionsPath);
    }
}
