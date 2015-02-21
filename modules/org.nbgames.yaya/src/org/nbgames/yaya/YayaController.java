/* 
 * Copyright 2015 Patrik Karlsson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nbgames.yaya;

import org.nbgames.core.game.GameController;
import org.nbgames.core.game.NewGameDialogManager;
import org.nbgames.core.game.NewGameDialogManager.NewGameController;
import org.openide.DialogDisplayer;
import org.openide.awt.StatusDisplayer;
import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class YayaController extends GameController implements NewGameController {

    public static final String LOG_TITLE = "Yaya";

    private final YayaPanel mGamePanel;
    private YayaTopComponent mGameTopComponent;

    public YayaController(YayaTopComponent gameTopComponent, String infoName, String infoVersion, String infoDescription, String infoCopyright, String optionsPath) {
        super(gameTopComponent, infoName, infoVersion, infoDescription, infoCopyright, optionsPath);
        mGameTopComponent = gameTopComponent;
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
        updateStatusBar();
        String name = NbBundle.getMessage(getClass(), "CTL_NameType", mGamePanel.getGameTitle());
        mGameTopComponent.setName(name);
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

    @Override
    public void updateStatusBar() {
        StatusDisplayer.getDefault().setStatusText(mGamePanel.getGameTitle(), StatusDisplayer.IMPORTANCE_ERROR_HIGHLIGHT);
    }
}
