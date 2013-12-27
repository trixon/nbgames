package org.nbgames.yaya.scorecard;

import java.util.Comparator;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class PlayerColumnComparator implements Comparator<PlayerColumn> {

    public static final int ASCENDING = 1;
    public static final int DESCENDING = -1;
    private int sortOrder = ASCENDING;

    public PlayerColumnComparator() {
    }

    public PlayerColumnComparator(int aSortOrder) {
        this.sortOrder = aSortOrder;
    }

    @Override
    public int compare(PlayerColumn o1, PlayerColumn o2) {
        int result = 0;
        if (o1.getCurrentScore() < o2.getCurrentScore()) {
            result = -1 * getSortOrder();
        } else if (o1.getCurrentScore() > o2.getCurrentScore()) {
            result = 1 * getSortOrder();
        }
        return result;
    }

    public int getSortOrder() {
        return sortOrder;
    }
}
