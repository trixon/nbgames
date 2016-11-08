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
package org.nbgames.core;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.openide.modules.ModuleInstall;
import org.openide.util.ImageUtilities;
import org.openide.util.NbPreferences;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import se.trixon.almond.nbp.about.AboutAction;
import se.trixon.almond.nbp.swing.NoTabsTabDisplayerUI;
import se.trixon.almond.util.AlmondOptions;
import se.trixon.almond.util.BundleHelper;
import se.trixon.almond.util.SystemHelper;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Installer extends ModuleInstall {

    public Installer() {
    }

    @Override
    public void restored() {
        AlmondOptions.getInstance().setPreferences(NbPreferences.forModule(Installer.class));

        AboutAction.setAboutBundle(BundleHelper.getBundle(getClass(), "about"));
        AboutAction.setLicenseBundle(BundleHelper.getBundle(getClass(), "license"));
        AboutAction.setImageIcon(ImageUtilities.loadImageIcon(SystemHelper.getPackageAsPath(getClass()) + "nbgames.png", false));

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
