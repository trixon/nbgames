package org.nbgames.yaya;

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
        category = "File/Dice",
        id = "org.nbgames.yaya.YayaAction"
)
@ActionRegistration(
        displayName = "#CTL_Name"
)
@ActionReference(path = "Menu/File/Dice", position = 3333)
public final class YayaAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        YayaTopComponent topComponent = new YayaTopComponent();
        topComponent.open();
        topComponent.requestActive();
    }
}
