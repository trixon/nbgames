package org.nbgames.gunu;

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
        category = "File/Logic",
        id = "org.nbgames.gunu.GunuAction"
)
@ActionRegistration(
        displayName = "#CTL_Name"
)
@ActionReference(path = "Menu/File/Logic", position = 3333)
public final class GunuAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        GunuTopComponent topComponent = new GunuTopComponent();
        topComponent.open();
        topComponent.requestActive();
    }
}
