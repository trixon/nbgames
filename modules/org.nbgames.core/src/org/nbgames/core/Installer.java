package org.nbgames.core;

import java.util.prefs.Preferences;
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
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                if (mPreferences.getBoolean(StartPageTopComponent.KEY_SHOW_ON_STARTUP, true)) {
                    openWindow("StartPageTopComponent");
                }
                openWindow("output");
            }
        });

        NbGames.log("Ready");
    }

    private void openWindow(String id) {
        TopComponent topComponent = WindowManager.getDefault().findTopComponent(id);
        if (topComponent != null) {
            topComponent.open();
        }
    }
}
