package org.nbgames.yaya.scorecard;

import javax.swing.SwingConstants;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class HeaderColumn {

    private ScoreCardRow[] hiScores;
    private int[] limValues;
    private ScoreCardRow[] max;
    private int[] maxValues;
    private int numOfRows;
    private ScoreCardRow[] rows;
    private Rule rule;
    private ScoreCard scoreCard;

    public HeaderColumn(ScoreCard aScoreCard, Rule aRule) {
        this.rule = aRule;
        this.scoreCard = aScoreCard;
        init();
    }

    public ScoreCardRow[] getHiScores() {
        return hiScores;
    }

    public ScoreCardRow[] getMax() {
        return max;
    }

    public ScoreCardRow[] getRows() {
        return rows;
    }

    public void setVisibleColumnHiscore(boolean aVisible) {
        for (int i = 0; i < numOfRows; i++) {
            hiScores[i].getLabel().setVisible(aVisible);
        }
    }

    public void setVisibleColumnMax(boolean aVisible) {
        for (int i = 0; i < numOfRows; i++) {
            max[i].getLabel().setVisible(aVisible);
        }
    }

    private void init() {
        initRows();
        initLexicons();
    }

    private void initLexicons() {
        for (int i = 0; i < rule.getNumOfRows(); i++) {
            scoreCard.getLexicon().setPair(rows[i].getLabel(), rule.getKey()[i]);
        }
    }

    private void initRows() {
        boolean showMaxCol = scoreCard.getSettings().isShowingMaxCol();
        boolean showHiCol = scoreCard.getSettings().isShowingHiCol();

        RowsRule rowsRule = rule.getRowsRule();
        limValues = rule.getLim();
        maxValues = rule.getMax();

        numOfRows = rule.getNumOfRows();
        rows = new ScoreCardRow[numOfRows];
        max = new ScoreCardRow[numOfRows];
        hiScores = new ScoreCardRow[numOfRows];

        for (int i = 0; i < numOfRows; i++) {
            RowRule rowRule = rowsRule.get(i);

            rows[i] = new ScoreCardRow(scoreCard, rowRule, i, true);
            max[i] = new ScoreCardRow(scoreCard, rowRule, i, true);
            hiScores[i] = new ScoreCardRow(scoreCard, rowRule, i, true);

            rows[i].getLabel().setHorizontalAlignment(SwingConstants.LEADING);
            max[i].getLabel().setText(Integer.toString(maxValues[i]));
            hiScores[i].getLabel().setText(Integer.toString(limValues[i]));

            max[i].getLabel().setVisible(showMaxCol);
            hiScores[i].getLabel().setVisible(showHiCol);

            String toolTip = rows[i].getRowRule().getToolTip();
            rows[i].getLabel().setToolTipText(toolTip);
            max[i].getLabel().setToolTipText(toolTip);
            hiScores[i].getLabel().setToolTipText(toolTip);
        }

        int row = scoreCard.getSettings().getRule().getResultRow();
        rows[row].getLabel().setFont(rows[row].getLabel().getFont().deriveFont((16.0F)));
    }
}
