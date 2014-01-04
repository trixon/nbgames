package org.nbgames.yaya.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.nbgames.yaya.Options;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ActionID(
        category = "Yaya",
        id = "org.nbgames.yaya.actions.ShowIndicatorsAction"
)
@ActionRegistration(
        displayName = "#CTL_ShowIndicatorsAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/View", position = 1),
    @ActionReference(path = "Shortcuts", name = "F5")
})
public final class ShowIndicatorsAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Options.INSTANCE.toggleIndicators();
    }
}
