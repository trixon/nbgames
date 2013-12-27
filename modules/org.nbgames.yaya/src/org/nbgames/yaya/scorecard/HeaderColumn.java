package org.nbgames.yaya.scorecard;

import javax.swing.SwingConstants;
import org.nbgames.yaya.Options;
import org.nbgames.yaya.scorecard.rule.RowRule;
import org.nbgames.yaya.scorecard.rule.RowsRule;
import org.nbgames.yaya.scorecard.rule.Rule;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class HeaderColumn {

    private ScoreCardRow[] mHiScores;
    private int[] mLimValues;
    private ScoreCardRow[] mMax;
    private int[] mMaxValues;
    private int mNumOfRows;
    private ScoreCardRow[] mRows;
    private final Rule mRule;
    private final ScoreCard mScoreCard;

    public HeaderColumn(ScoreCard scoreCard, Rule rule) {
        mRule = rule;
        mScoreCard = scoreCard;
        init();
    }

    public ScoreCardRow[] getHiScores() {
        return mHiScores;
    }

    public ScoreCardRow[] getMax() {
        return mMax;
    }

    public ScoreCardRow[] getRows() {
        return mRows;
    }

    public void setVisibleColumnHiscore(boolean visible) {
        for (int i = 0; i < mNumOfRows; i++) {
            mHiScores[i].getLabel().setVisible(visible);
        }
    }

    public void setVisibleColumnMax(boolean visible) {
        for (int i = 0; i < mNumOfRows; i++) {
            mMax[i].getLabel().setVisible(visible);
        }
    }

    private void init() {
        initRows();
        initLexicons();
    }

    private void initLexicons() {
        for (int i = 0; i < mRule.getNumOfRows(); i++) {
//            mScoreCard.getLexicon().setPair(mRows[i].getLabel(), mRule.getKey()[i]);
//            TODO
        }
    }

    private void initRows() {
        boolean showMaxCol = Options.INSTANCE.isShowingMaxCol();
        boolean showHiCol = Options.INSTANCE.isShowingHiCol();

        RowsRule rowsRule = mRule.getRowsRule();
        mLimValues = mRule.getLim();
        mMaxValues = mRule.getMax();

        mNumOfRows = mRule.getNumOfRows();
        mRows = new ScoreCardRow[mNumOfRows];
        mMax = new ScoreCardRow[mNumOfRows];
        mHiScores = new ScoreCardRow[mNumOfRows];

        for (int i = 0; i < mNumOfRows; i++) {
            RowRule rowRule = rowsRule.get(i);

            mRows[i] = new ScoreCardRow(mScoreCard, rowRule, i, true);
            mMax[i] = new ScoreCardRow(mScoreCard, rowRule, i, true);
            mHiScores[i] = new ScoreCardRow(mScoreCard, rowRule, i, true);

            mRows[i].getLabel().setHorizontalAlignment(SwingConstants.LEADING);
            mMax[i].getLabel().setText(Integer.toString(mMaxValues[i]));
            mHiScores[i].getLabel().setText(Integer.toString(mLimValues[i]));

            mMax[i].getLabel().setVisible(showMaxCol);
            mHiScores[i].getLabel().setVisible(showHiCol);

            String toolTip = mRows[i].getRowRule().getToolTip();
            mRows[i].getLabel().setToolTipText(toolTip);
            mMax[i].getLabel().setToolTipText(toolTip);
            mHiScores[i].getLabel().setToolTipText(toolTip);
        }

        int row = Options.INSTANCE.getRule().getResultRow();
        mRows[row].getLabel().setFont(mRows[row].getLabel().getFont().deriveFont((16.0F)));
    }
}
