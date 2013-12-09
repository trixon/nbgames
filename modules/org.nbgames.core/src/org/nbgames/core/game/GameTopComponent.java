package org.nbgames.core.game;

import javax.swing.JPanel;
import org.openide.windows.TopComponent;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public abstract class GameTopComponent extends TopComponent {

    public abstract JPanel getTopPanel();
}
