package org.nbgames.core;

import se.trixon.almond.Monitor;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class NbGames {

    public static final String LOG_TITLE_DEFAULT = "global";
    private static final Monitor sMonitor = new Monitor(NbGames.LOG_TITLE_DEFAULT, false, true);

    public static Monitor getMonitor() {
        return sMonitor;
    }

    public static void log(String string) {
        sMonitor.outln(string);
    }

    public static void logErr(String string) {
        sMonitor.errln(string);
    }
}
