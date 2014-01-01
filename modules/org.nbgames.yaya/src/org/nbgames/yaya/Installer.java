package org.nbgames.yaya;

import org.nbgames.yaya.gamedef.GameDef;
import org.openide.modules.ModuleInstall;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        GameDef.INSTANCE.init();
    }
}
