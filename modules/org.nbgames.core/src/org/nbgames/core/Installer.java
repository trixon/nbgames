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

import java.util.prefs.Preferences;
import org.nbgames.core.about.AboutInitializer;
import org.nbgames.core.startpage.StartPageTopComponent;
import org.openide.modules.ModuleInstall;
import org.openide.util.NbPreferences;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Installer extends ModuleInstall {

    private final Preferences mPreferences;

    public Installer() {
        mPreferences = NbPreferences.forModule(StartPageTopComponent.class);
    }

    @Override
    public void restored() {
        AboutInitializer.init();

        WindowManager.getDefault().invokeWhenUIReady(() -> {
            if (mPreferences.getBoolean(StartPageTopComponent.KEY_SHOW_START_PAGE_ON_STARTUP, false)) {
                openWindow("StartPageTopComponent");
            }
            openWindow("output");
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
