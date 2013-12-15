package org.nbgames.core.actions;

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class AboutCallbackAction {

    @ActionID(category = "game",
            id = "org.nbgames.core.actions.AboutCallbackAction")
    @ActionRegistration(iconBase = "org/nbgames/core/res/dialog-information.png",
            displayName = "org.nbgames.core.actions.Bundle#CTL_GameAboutAction",
            iconInMenu = true)
    @ActionReferences({
        @ActionReference(path = "Menu/File", position = 30,separatorAfter = 31),
        @ActionReference(path = "Toolbars/File", position = 30),})
    public static final String KEY = "AboutCallbackAction";
}
