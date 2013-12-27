package org.nbgames.yaya.scorecard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class FormulaParser {

    private static LinkedList<Integer> arg = new LinkedList<Integer>();
    private static LinkedList<Integer> dice;
    private static RowRule rowRule;

    public static int parseFormula(String aFormula, LinkedList<Integer> values, RowRule aRowRule) {
        dice = values;
        rowRule = aRowRule;
        int result = -1;
        String[] parseString = aFormula.split(" ");
        String command = parseString[0];
        arg.clear();

        for (int i = 1; i < parseString.length; i++) {
            arg.add(Integer.valueOf(parseString[i]));
        }

        try {
            Formula formula = Formula.valueOf(command.toUpperCase());
            result = processFormula(formula);
        } catch (IllegalArgumentException e) {
            System.err.println("Unknown command: " + command);
        }

        return result;
    }

    private static int getDuplicates(int aNumOfDuplicates, int face) {
        int freq = Collections.frequency(dice, face);
        int result = 0;
        if (freq >= aNumOfDuplicates) {
            result = aNumOfDuplicates * face;
        }
        return result;
    }

    private static int getDuplicates(int aNumOfDuplicates) {
        int result = 0;
        int cnt;
        for (int i = 6; i > 0; i--) {
            cnt = Collections.frequency(dice, i);
            if (cnt >= aNumOfDuplicates) {
                result = aNumOfDuplicates * i;
                if (rowRule.getMax() == rowRule.getLim()) {
                    result = rowRule.getMax();
                }
                break;
            }
        }
        return result;
    }

    private static int getHouse(int aMajorPart, int aMinorPart) {
        int result = 0;

        LinkedList<Integer> majorList = new LinkedList<Integer>();
        LinkedList<Integer> minorList = new LinkedList<Integer>();

        for (int i = 6; i > 0; i--) {
            int sum = getSumOf(i);
            int freq = sum / i;

            if (freq >= aMajorPart) {
                if (majorList.size() > 0) {
                    if (i > Collections.max(majorList)) {
                        majorList.add(i);
                    }
                } else {
                    majorList.add(i);
                }
            }

            if ((freq >= aMinorPart) && (majorList.indexOf(i) == -1)) {
                minorList.add(i);
            }
        }

        if (majorList.size() > 0 && minorList.size() > 0) {
            result = aMajorPart * Collections.max(majorList) + aMinorPart * Collections.max(minorList);
        }

        return result;
    }

    private static int getPair(int aNumOfPairs) {
        int result = 0;
        int pairCounter = 0;
        int startFace = 6;

        for (int i = 0; i < aNumOfPairs; i++) {
            for (int j = startFace; j > 0; j--) {
                int pairSum = getDuplicates(2, j);
                if (pairSum > 0) {
                    result += pairSum;
                    pairCounter++;
                    startFace = j - 1;
                    break;
                }
            }
        }
        if (pairCounter < aNumOfPairs) {
            result = 0;
        }
        return result;
    }

    private static int getStraight(int sizeOfStraight) {
        int result = 0;
        SortedSet<Integer> sortedSet = new TreeSet<Integer>();

        for (Integer integer : dice) {
            sortedSet.add(integer);
        }

        if (sortedSet.size() >= sizeOfStraight) {
            int setSum = 0;

            for (Integer integer : sortedSet) {
                setSum += integer;
            }

            if (sortedSet.size() > sizeOfStraight) {
                if (setSum >= rowRule.getLim()) {
                    result = rowRule.getMax();
                }
            } else {
                if (setSum == rowRule.getLim()) {
                    result = rowRule.getMax();
                }
            }
        }

        return result;
    }

    private static int getSum() {
        int result = 0;
        for (int face : dice) {
            result += face;
        }
        return result;
    }

    private static int getSumOf(int face) {
        return face * Collections.frequency(dice, face);
    }

    private static int processFormula(Formula aFormula) {
        int result = -1;

        switch (aFormula) {

            case DUPLICATES:
                result = getDuplicates(arg.get(0));
                break;

            case HOUSE:
                result = getHouse(arg.get(0), arg.get(1));
                break;

            case PAIR:
                result = getPair(arg.get(0));
                break;

            case STRAIGHT:
                result = getStraight(arg.get(0));
                break;

            case SUM:
                if (arg.size() == 0) {
                    result = getSum();
                } else {
                    result = getSumOf(arg.get(0));
                }
                break;
        }

        return result;
    }

    private FormulaParser() {
    }

    public enum Formula {

        DUPLICATES,
        PAIR,
        STRAIGHT,
        HOUSE,
        SUM,
    }
}
