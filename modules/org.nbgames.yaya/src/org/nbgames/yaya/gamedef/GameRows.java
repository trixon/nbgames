package org.nbgames.yaya.gamedef;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
import java.util.LinkedList;

public class GameRows extends LinkedList<GameRow> {

    public int[] getLim() {
        int[] values = new int[size()];
        int i = 0;

        for (GameRow gameRow : this) {
            values[i] = gameRow.getLim();
            i++;
        }
        return values;
    }

    public int[] getMax() {
        int[] values = new int[size()];
        int i = 0;

        for (GameRow gameRow : this) {
            values[i] = gameRow.getMax();
            i++;
        }

        return values;
    }
}
