package org.nbgames.core;

import se.trixon.almond.util.AMonitor;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class NbGames {

    public static final String LOG_TITLE_DEFAULT = "global";
    private static final AMonitor sMonitor = new AMonitor(NbGames.LOG_TITLE_DEFAULT, false, true);

    public static AMonitor getMonitor() {
        return sMonitor;
    }

    public static void log(String string) {
        sMonitor.outln(string);
    }

    public static void logErr(String string) {
        sMonitor.errln(string);
    }
}
