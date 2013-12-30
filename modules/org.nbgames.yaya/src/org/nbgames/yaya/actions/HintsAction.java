package org.nbgames.yaya.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.nbgames.core.NbGames;
import org.nbgames.yaya.gamedef.GameDef;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
@ActionID(
        category = "Yaya",
        id = "org.nbgames.yaya.actions.HintsAction"
)
@ActionRegistration(
        displayName = "#CTL_HintsAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/View", position = 0),
    @ActionReference(path = "Shortcuts", name = "D-A")
})
@Messages("CTL_HintsAction=Hints")
public final class HintsAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        GameDef.INSTANCE.init();
        NbGames.logErr(GameDef.INSTANCE.dump());
    }
}
