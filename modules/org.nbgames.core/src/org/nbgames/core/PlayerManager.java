package org.nbgames.core;

import java.util.LinkedList;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum PlayerManager {

    INSTANCE;
    private final LinkedList<Player> mPlayers = new LinkedList<Player>();
    private final Preferences mPreferences = NbPreferences.forModule(PlayerManager.class);

    private PlayerManager() {
    }

    public LinkedList<Player> getPlayers() {
        return mPlayers;
    }

    public Preferences getPreferences() {
        return mPreferences;
    }
}
