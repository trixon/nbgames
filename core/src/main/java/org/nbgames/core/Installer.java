/* 
 * Copyright 2017 Patrik Karlsson.
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

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.nbgames.core.api.NbGames;
import org.nbgames.core.api.db.Db;
import org.nbgames.core.api.db.DbCreator;
import org.openide.modules.ModuleInstall;
import org.openide.util.NbPreferences;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import se.trixon.almond.nbp.swing.NoTabsTabDisplayerUI;

/**
 *
 * @author Patrik Karlsson
 */
public class Installer extends ModuleInstall {

    private final Db mDb = Db.getInstance();
    private final NbgOptions mOptions = NbgOptions.getInstance();

    public Installer() {
        if (!mDb.exists()) {
            DbCreator.getInstance().initDb();
        }
    }

    @Override
    public void restored() {
        NbGames.getAlmondOptions().setPreferences(NbPreferences.forModule(Installer.class));

        SwingUtilities.invokeLater(() -> {
            UIManager.put("EditorTabDisplayerUI", NoTabsTabDisplayerUI.ID);
            UIManager.put("NbMainWindow.showCustomBackground", Boolean.TRUE);
            System.setProperty("netbeans.winsys.status_line.path", "");
        });

        WindowManager.getDefault().invokeWhenUIReady(() -> {
            openWindow("NbGamesTopComponent");
        });

        NbGames.outln(NbGames.LOG_TITLE, "nbGames Platform loaded");
    }

    private void openWindow(String id) {
        TopComponent topComponent = WindowManager.getDefault().findTopComponent(id);
        if (topComponent != null) {
            topComponent.open();
        }
    }
}
