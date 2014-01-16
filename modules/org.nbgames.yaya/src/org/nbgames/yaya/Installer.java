package org.nbgames.yaya;

import java.util.Arrays;
import java.util.prefs.BackingStoreException;
import org.nbgames.yaya.gamedef.GameDef;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        try {
            if (!Arrays.asList(Options.getPreferences().keys()).contains(Options.KEY_SHOW_INDICATORS)) {
                Options.INSTANCE.setShowIndicators(true);
            }
        } catch (BackingStoreException ex) {
            Exceptions.printStackTrace(ex);
        }

        GameDef.INSTANCE.init();
    }
}
