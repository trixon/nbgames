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
        id = "org.nbgames.yaya.actions.ShowMaxColumnAction"
)
@ActionRegistration(
        displayName = "#CTL_ShowMaxColumnAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/View", position = 2),
    @ActionReference(path = "Shortcuts", name = "F6")
})
public final class ShowMaxColumnAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Options.INSTANCE.toggleMaxColumn();
    }
}
