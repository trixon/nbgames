package org.nbgames.memroyal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ActionID(
        category = "File/Card",
        id = "org.nbgames.memroyal.MemroyalAction"
)
@ActionRegistration(
        displayName = "#CTL_Name"
)
@ActionReference(path = "Menu/File/Card")
public final class MemroyalAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MemroyalTopComponent topComponent = new MemroyalTopComponent();
        topComponent.open();
        topComponent.requestActive();
    }
}
