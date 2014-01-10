package org.nbgames.core.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.nbgames.core.options.PlayersOptionsPanelController;
import org.netbeans.api.options.OptionsDisplayer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ActionID(
        category = "File",
        id = "org.nbgames.core.actions.PlayerManagerAction"
)
@ActionRegistration(
        iconBase = "org/nbgames/core/res/system-users.png",
        displayName = "#CTL_PlayerManagerAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 30),
    @ActionReference(path = "Toolbars/File", position = 130)
})
public final class PlayerManagerAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        OptionsDisplayer.getDefault().open(PlayersOptionsPanelController.ID);
    }
}
