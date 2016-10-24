/* 
 * Copyright 2016 Patrik Karlsson.
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
package org.nbgames.core.base;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public abstract class GameTopComponent extends BaseTopComponent {

    protected ResourceBundle mBundle;
    protected String mGameName;
    private GamePanel mGamePanel;
    private JPanel mTopPanel;

    public GameTopComponent() {
        init();
    }

    public GameTopComponent(Lookup lookup) {
        super(lookup);
        init();
    }

    public GamePanel getGamePanel() {
        return mGamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        mGamePanel = gamePanel;
        mTopPanel.removeAll();
        mTopPanel.add(mGamePanel);
    }

    @Override
    protected void componentDeactivated() {
        super.componentDeactivated();
        StatusDisplayer.getDefault().setStatusText("", StatusDisplayer.IMPORTANCE_ERROR_HIGHLIGHT);
    }

    protected void setActionCategoryVisible(String category, boolean visible) {
//TODO
//Hide/Show menus and toolbars
        final String FOLDER = "Actions/" + category + "";
        Lookup pathLookup = Lookups.forPath(FOLDER);

    }

    private void init() {
        setLayout(new BorderLayout());
        mTopPanel = new JPanel();
        mTopPanel.setLayout(new GridBagLayout());
        add(mTopPanel, BorderLayout.CENTER);
    }
}
