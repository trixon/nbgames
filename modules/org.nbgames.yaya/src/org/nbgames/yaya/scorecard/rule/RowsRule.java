package org.nbgames.yaya.scorecard.rule;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
import java.util.LinkedList;

public class RowsRule extends LinkedList<RowRule> {

    public String[] getKeys() {
        LinkedList<String> l = new LinkedList();
        String[] strings = new String[this.size()];
        int i = 0;

        for (RowRule rowRule : this) {
            strings[i] = rowRule.getName();
            i++;
        }

        return strings;
    }

    public int[] getLim() {
        int[] values = new int[this.size()];
        int i = 0;

        for (RowRule rowRule : this) {
            values[i] = rowRule.getLim();
            i++;
        }
        return values;
    }

    public int[] getMax() {
        int[] values = new int[this.size()];
        int i = 0;

        for (RowRule rowRule : this) {
            values[i] = rowRule.getMax();
            i++;
        }

        return values;
    }
}
