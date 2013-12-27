package org.nbgames.yaya.scorecard;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Stack;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import org.nbgames.yaya.Options;
import org.nbgames.yaya.scorecard.rule.RowRule;
import org.nbgames.yaya.scorecard.rule.RowsRule;
import org.nbgames.yaya.scorecard.rule.Rule;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class PlayerColumn {

    private boolean active;
    private JComboBox comboBox = new JComboBox();
    private int currentScore;
    private LinkedList<Integer> dice;
    private String name;
    private int numOfRolls;
    private int playOrder;
    private Stack<Integer> rowStack = new Stack<Integer>();
    private ScoreCardRow[] rows;
    private Rule rule;
    private ScoreCard scoreCard;

    public PlayerColumn(ScoreCard aScoreCard, int aPlayOrder, Rule aRule) {
        this.scoreCard = aScoreCard;
        this.playOrder = aPlayOrder;
        this.rule = aRule;
        init();
    }

    public void clearPreview() {
        for (ScoreCardRow scoreCardRow : rows) {
            scoreCardRow.clearPreview();
        }
    }

    public void decNumOfRolls() {
        numOfRolls--;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public String getName() {
        return "player_" + playOrder;
    }

    public int getNumOfRolls() {
        return numOfRolls;
    }

    public int getPlayOrder() {
        return playOrder;
    }

    public Stack<Integer> getRowStack() {
        return rowStack;
    }

    public ScoreCardRow[] getRows() {
        return rows;
    }

    public void incNumOfRolls() {
        numOfRolls++;
    }

    public void newGame() {
        numOfRolls = 0;
        rowStack.clear();
        setEnabled(false);
        for (ScoreCardRow scoreCardRow : rows) {
            scoreCardRow.newGame();
        }
        setRollCounterLabel();
    }

    public void parse(LinkedList<Integer> values) {
        dice = values;
        for (ScoreCardRow scoreCardRow : rows) {
            if (scoreCardRow.getRowRule().isRollCounter()) {
                String rolls = Integer.toString(getNumOfRolls());
                int maxRolls = Options.INSTANCE.getRule().getNumOfRolls();
                if (maxRolls > 0) {
//                    rolls += " (" + scoreCard.getNumOfRolls() + "/" + maxRolls + ")";
                    rolls = "STATUS BAR";
                }
                scoreCardRow.getLabel().setText(rolls);
            }

            String formula = scoreCardRow.getRowRule().getForumla();
            if (!formula.isEmpty() && !scoreCardRow.isRegistered()) {
                scoreCardRow.setPreview(FormulaParser.parseFormula(formula, dice, scoreCardRow.getRowRule()));
            }
            scoreCardRow.enableInput();
        }
    }

    public void register() {
        updateSums();
        setEnabled(false);
    }

    public void setEnabled(boolean aState) {
        active = aState;
        String text = (numOfRolls == 0) ? "0" : Integer.toString(numOfRolls);

        for (ScoreCardRow scoreCardRow : rows) {
            scoreCardRow.setEnabled(aState);
            if (scoreCardRow.getRowRule().isRollCounter()) {
                scoreCardRow.getLabel().setText(text);
            }
        }
    }

    public void setNumOfRolls(int numOfRolls) {
        this.numOfRolls = numOfRolls;
        Integer i;
    }

    public void setPlayOrder(int playOrder) {
        this.playOrder = playOrder;
    }

    public void setText() {
        for (ScoreCardRow scoreCardRow : rows) {
            scoreCardRow.setText();
        }
    }

    public void setVisibleHints(boolean aVisible) {
        for (ScoreCardRow scoreCardRow : rows) {
            scoreCardRow.setVisibleHint(aVisible);
        }
    }

    public void undo() {
        int undoRow = rowStack.pop();
        rows[undoRow].setRegistered(false);
        rows[undoRow].setValue(0);
        rows[undoRow].getLabel().setText("");
        decNumOfRolls();
        updateSums();
        setEnabled(true);

        for (ScoreCardRow scoreCardRow : rows) {
            scoreCardRow.enableHover();
        }
    }

    private void init() {
        RowsRule rowsRule = rule.getRowsRule();
        rows = new ScoreCardRow[rule.getNumOfRows()];
        Dimension d = comboBox.getPreferredSize();
        d.width = 90;
        comboBox.setPreferredSize(d);
        comboBox.setEditable(true);

        for (int i = 0; i < rows.length; i++) {
            RowRule rowRule = rowsRule.get(i);
            rows[i] = new ScoreCardRow(scoreCard, this, rowRule, i);

            if (i == 0) {
                rows[i].getLabel().setHorizontalAlignment(SwingConstants.CENTER);
            }
        }
    }

    private void setRollCounterLabel() {
        if (active) {
        } else {
        }
    }

    private void updateSums() {
        for (ScoreCardRow scoreCardRow : rows) {
            if (scoreCardRow.getRowRule().getSumSet() != null) {

                if (scoreCardRow.getRowRule().isBonus()) {
                    int sum = 0;

                    for (Integer row : scoreCardRow.getRowRule().getSumSet()) {
                        sum += rows[row].getValue();
                    }

                    if (sum >= scoreCardRow.getRowRule().getLim()) {
                        int bonus = scoreCardRow.getRowRule().getMax();
                        scoreCardRow.getLabel().setText(Integer.toString(bonus));
                        scoreCardRow.setValue(bonus);
                    }
                }

                if (scoreCardRow.getRowRule().isSum()) {
                    int sum = 0;

                    for (Integer row : scoreCardRow.getRowRule().getSumSet()) {
                        sum += rows[row].getValue();
                    }

                    if (scoreCardRow.getRow() == rule.getResultRow()) {
                        scoreCardRow.setValue(sum);
                        currentScore = sum;
                    }

                    if (!scoreCardRow.getRowRule().isBonus()) {
                        scoreCardRow.getLabel().setText(Integer.toString(sum));
                    }

                }
            }
        }
    }
}
