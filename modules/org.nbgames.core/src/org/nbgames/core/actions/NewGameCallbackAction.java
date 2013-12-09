package org.nbgames.core.actions;

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class NewGameCallbackAction {

    @ActionID(category = "game",
            id = "org.nbgames.core.actions.NewGameAction")
    @ActionRegistration(iconBase = "org/nbgames/core/res/quickopen.png",
            displayName = "org.nbgames.core.actions.Bundle#CTL_GameNewAction",
            iconInMenu = true)
    @ActionReferences({
        @ActionReference(path = "Menu/File", position = 10),
        @ActionReference(path = "Toolbars/File", position = 10),
        @ActionReference(path = "Shortcuts", name = "D-N")

    })
    public static final String KEY = "NewGameCallbackAction";
}
