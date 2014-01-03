package org.nbgames.core.game;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import org.nbgames.core.actions.AboutCallbackAction;
import org.nbgames.core.actions.NewGameCallbackAction;
import org.nbgames.core.actions.OptionsCallbackAction;
import org.netbeans.api.options.OptionsDisplayer;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public abstract class GameController {

    private ActionMap mActionMap;
    private GamePanel mGamePanel;
    private GameTopComponent mGameTopComponent;
    private String mInfoCopyright;
    private String mInfoDescription;
    private String mInfoName;
    private String mInfoVersion;
    private String mOptionsPath;

    public GameController(GameTopComponent gameTopComponent, String infoName, String infoVersion, String infoDescription, String infoCopyright, String optionsPath) {
        mGameTopComponent = gameTopComponent;
        mActionMap = gameTopComponent.getActionMap();
        mInfoName = infoName;
        mInfoVersion = infoVersion;
        mInfoDescription = infoDescription;
        mInfoCopyright = infoCopyright;
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

    public void requestNewGame() {
    }

    public void setGamePanel(GamePanel gamePanel) {
        mGamePanel = gamePanel;
    }

    public void updateStatusBar() {
    }

    protected void setActiveInformation(boolean state) {

        if (state) {
            mActionMap.put(AboutCallbackAction.KEY, new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    showInformation();
                }
            });
        } else {
            mActionMap.remove(AboutCallbackAction.KEY);
        }
    }

    protected void setActiveNewGame(boolean state) {
        if (state) {
            mActionMap.put(NewGameCallbackAction.KEY, new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    requestNewGame();
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
        String message = String.format("%s %s\n%s\n\n%s", mInfoName, mInfoVersion, mInfoDescription, mInfoCopyright);

        NotifyDescriptor notifyDescriptor = new NotifyDescriptor.Message(message, NotifyDescriptor.INFORMATION_MESSAGE);
        notifyDescriptor.setTitle(NbBundle.getMessage(AboutCallbackAction.class, "CTL_GameAboutAction"));
        DialogDisplayer.getDefault().notify(notifyDescriptor);
    }

    private void showOptions() {
        OptionsDisplayer.getDefault().open(mOptionsPath);
    }
}
