package org.nbgames.core.actions;

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class OptionsCallbackAction {

    @ActionID(category = "game",
            id = "org.nbgames.core.actions.OptionsAction")
    @ActionRegistration(iconBase = "org/nbgames/core/res/games-config-options.png",
            displayName = "org.nbgames.core.actions.Bundle#CTL_GameOptionsAction",
            iconInMenu = true)
    @ActionReferences({
        @ActionReference(path = "Menu/File", position = 20),
        @ActionReference(path = "Toolbars/File", position = 20),})
    public static final String KEY = "OptionsCallbackAction";
}
