package org.nbgames.core;

import se.trixon.almond.Monitor;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class NbGames {

    public static final String LOG_TITLE = "core";

    public static void errln(String name, String message) {
        new Monitor(name, false, true).errln(message);
    }

    public static void outln(String name, String message) {
        new Monitor(name, false, true).outln(message);
    }
}
