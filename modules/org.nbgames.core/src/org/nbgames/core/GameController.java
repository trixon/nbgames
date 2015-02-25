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
package org.nbgames.core;

import java.awt.event.ActionEvent;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import org.nbgames.core.GameCategory;
import org.nbgames.core.actions.GameInfoAction;
import org.nbgames.core.actions.NewGameCallbackAction;
import org.nbgames.core.actions.OptionsCallbackAction;
import org.nbgames.core.api.GameProvider;
import org.nbgames.core.base.GamePanel;
import org.nbgames.core.base.GameTopComponent;
import org.netbeans.api.options.OptionsDisplayer;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.modules.Modules;
import org.openide.util.NbBundle;
import se.trixon.almond.SystemHelper;
import se.trixon.almond.news.NewsProvider;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class GameController implements GameProvider, NewsProvider {

    private ActionMap mActionMap;
    private GamePanel mGamePanel;
    private GameTopComponent mGameTopComponent;

    public GameController() {
    }

    public GameController(GameTopComponent gameTopComponent) {
        mGameTopComponent = gameTopComponent;
        mActionMap = gameTopComponent.getActionMap();

        init();
    }

    @Override
    public String getActionCategory() {
        return getResource("Game-ActionCategory");
    }

    @Override
    public String getActionId() {
        return getResource("Game-ActionId");
    }

    @Override
    public GameCategory getCategory() {
        return null;
    }

    @Override
    public String getCopyright() {
        return getResource("Game-Copyright");
    }

    @Override
    public String getCredit() {
        return getResource("Game-Credit");
    }

    @Override
    public String getDescription() {
        return getResource("Game-Description");
    }

    public GamePanel getGamePanel() {
        return mGamePanel;
    }

    public GameTopComponent getGameTopComponent() {
        return mGameTopComponent;
    }

    @Override
    public String getLicense() {
        return getResource("Game-License");
    }

    @Override
    public String getModuleName() {
        return Modules.getDefault().ownerOf(this.getClass()).getDisplayName();
    }

    @Override
    public String getName() {
        return getResource("Game-Name");
    }

    @Override
    public ResourceBundle getNewsBundle() {
        return null;
    }

    @Override
    public String getOptionsPath() {
        return null;
    }

    public String getPackageAsPath() {
        return SystemHelper.getPackageAsPath(this.getClass());
    }

    public String getResource(String key) {
        try {
            return NbBundle.getMessage(this.getClass(), key);
        } catch (MissingResourceException e) {
            return "";
        }
    }

    @Override
    public String getShortDescription() {
        return getResource("Game-ShortDescription");
    }

    @Override
    public String getVersion() {
        return getResource("Game-Version");
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
            mActionMap.put(GameInfoAction.KEY, new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    showDescription();
                }
            });
        } else {
            mActionMap.remove(GameInfoAction.KEY);
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
        setActiveOptions(getOptionsPath() != null);
    }

    private void showDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName()).append(" ").append(getVersion()).append("\n");
        builder.append(getDescription()).append("\n\n");
        builder.append(getCopyright());
        if (!getCredit().isEmpty()) {
            builder.append("\n\n").append(getCredit());
        }

        NotifyDescriptor notifyDescriptor = new NotifyDescriptor.Message(builder.toString(), NotifyDescriptor.INFORMATION_MESSAGE);
        notifyDescriptor.setTitle(NbBundle.getMessage(GameInfoAction.class, "CTL_GameInfoAction"));
        DialogDisplayer.getDefault().notify(notifyDescriptor);
    }

    private void showOptions() {
        OptionsDisplayer.getDefault().open(getOptionsPath());
    }
}
