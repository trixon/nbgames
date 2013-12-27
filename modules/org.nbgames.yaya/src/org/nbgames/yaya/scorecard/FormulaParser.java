package org.nbgames.yaya.scorecard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;
import org.nbgames.yaya.scorecard.rule.RowRule;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class FormulaParser {

    private static LinkedList<Integer> mArgList = new LinkedList<>();
    private static LinkedList<Integer> mDiceList;
    private static RowRule mRowRule;

    public static int parseFormula(String formulaString, LinkedList<Integer> values, RowRule rowRule) {
        mDiceList = values;
        mRowRule = rowRule;
        int result = -1;
        String[] parseString = formulaString.split(" ");
        String command = parseString[0];
        mArgList.clear();

        for (int i = 1; i < parseString.length; i++) {
            mArgList.add(Integer.valueOf(parseString[i]));
        }

        try {
            Formula formula = Formula.valueOf(command.toUpperCase());
            result = processFormula(formula);
        } catch (IllegalArgumentException e) {
            System.err.println("Unknown command: " + command);
        }

        return result;
    }

    private static int getDuplicates(int numOfDuplicates, int face) {
        int freq = Collections.frequency(mDiceList, face);
        int result = 0;
        if (freq >= numOfDuplicates) {
            result = numOfDuplicates * face;
        }
        return result;
    }

    private static int getDuplicates(int numOfDuplicates) {
        int result = 0;
        int cnt;
        for (int i = 6; i > 0; i--) {
            cnt = Collections.frequency(mDiceList, i);
            if (cnt >= numOfDuplicates) {
                result = numOfDuplicates * i;
                if (mRowRule.getMax() == mRowRule.getLim()) {
                    result = mRowRule.getMax();
                }
                break;
            }
        }
        return result;
    }

    private static int getHouse(int majorPart, int minorPart) {
        int result = 0;

        LinkedList<Integer> majorList = new LinkedList<>();
        LinkedList<Integer> minorList = new LinkedList<>();

        for (int i = 6; i > 0; i--) {
            int sum = getSumOf(i);
            int freq = sum / i;

            if (freq >= majorPart) {
                if (majorList.size() > 0) {
                    if (i > Collections.max(majorList)) {
                        majorList.add(i);
                    }
                } else {
                    majorList.add(i);
                }
            }

            if ((freq >= minorPart) && (majorList.indexOf(i) == -1)) {
                minorList.add(i);
            }
        }

        if (majorList.size() > 0 && minorList.size() > 0) {
            result = majorPart * Collections.max(majorList) + minorPart * Collections.max(minorList);
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
        SortedSet<Integer> sortedSet = new TreeSet<>();

        for (Integer integer : mDiceList) {
            sortedSet.add(integer);
        }

        if (sortedSet.size() >= sizeOfStraight) {
            int setSum = 0;

            for (Integer integer : sortedSet) {
                setSum += integer;
            }

            if (sortedSet.size() > sizeOfStraight) {
                if (setSum >= mRowRule.getLim()) {
                    result = mRowRule.getMax();
                }
            } else {
                if (setSum == mRowRule.getLim()) {
                    result = mRowRule.getMax();
                }
            }
        }

        return result;
    }

    private static int getSum() {
        int result = 0;
        for (int face : mDiceList) {
            result += face;
        }
        return result;
    }

    private static int getSumOf(int face) {
        return face * Collections.frequency(mDiceList, face);
    }

    private static int processFormula(Formula formula) {
        int result = -1;

        switch (formula) {
            case DUPLICATES:
                result = getDuplicates(mArgList.get(0));
                break;

            case HOUSE:
                result = getHouse(mArgList.get(0), mArgList.get(1));
                break;

            case PAIR:
                result = getPair(mArgList.get(0));
                break;

            case STRAIGHT:
                result = getStraight(mArgList.get(0));
                break;

            case SUM:
                if (mArgList.size() == 0) {
                    result = getSum();
                } else {
                    result = getSumOf(mArgList.get(0));
                }
                break;
        }

        return result;
    }

    private FormulaParser() {
    }

    public enum Formula {

        DUPLICATES, HOUSE,
        PAIR,
        STRAIGHT, SUM,
    }
}
