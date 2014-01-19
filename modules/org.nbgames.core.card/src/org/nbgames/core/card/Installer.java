package org.nbgames.core.card;

import org.openide.modules.ModuleInstall;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        CardDeckLoader.initialize();
    }
}
