package org.nbgames.numberguesser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ActionID(
        category = "File/Logic",
        id = "org.nbgames.numberguesser.GameAction"
)
@ActionRegistration(
        displayName = "#CTL_GameTitle"
)
@ActionReference(path = "Menu/File/Logic", position = 3333)
@Messages("CTL_GameTitle=Number Guesser")
public final class GameAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        GameTopComponent topComponent = new GameTopComponent();
        topComponent.open();
        topComponent.requestActive();
    }
}
