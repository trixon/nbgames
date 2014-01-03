package org.nbgames.yaya.scorecard;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import javax.swing.SwingConstants;
import org.nbgames.yaya.Options;
import org.nbgames.yaya.gamedef.GameRow;
import org.nbgames.yaya.gamedef.GameRows;
import org.nbgames.yaya.gamedef.GameType;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class HeaderColumn {

    private final GameType mGameType;
    private ScoreCardRow[] mHiScores;
    private int[] mLimValues;
    private ScoreCardRow[] mMax;
    private int[] mMaxValues;
    private int mNumOfRows;
    private final Options mOptions = Options.INSTANCE;
    private ScoreCardRow[] mRows;
    private final ScoreCard mScoreCard;

    public HeaderColumn(ScoreCard scoreCard, GameType gameType) {
        mGameType = gameType;
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
        initLabelTexts();
        mOptions.getPreferences().addPreferenceChangeListener(new PreferenceChangeListener() {

            @Override
            public void preferenceChange(PreferenceChangeEvent evt) {
                if (evt.getKey().equals(Options.KEY_USE_SYMBOLS)) {
                    initLabelTexts();
                }
            }
        });

    }

    private void initLabelTexts() {
        for (int i = 0; i < mGameType.getGameRows().size(); i++) {
            String text;

            if (mOptions.isUsingSymbols() && !mGameType.getGameRows().get(i).getTitleSymbol().equalsIgnoreCase("")) {
                text = mGameType.getGameRows().get(i).getTitleSymbol();
            } else {
                text = mGameType.getGameRows().get(i).getTitle();
            }

            mRows[i].getLabel().setText(text);
        }
    }

    private void initRows() {
        boolean showMaxCol = Options.INSTANCE.isShowingMaxCol();
        boolean showHiCol = Options.INSTANCE.isShowingHiCol();

        GameRows rowsRule = mGameType.getGameRows();
        mLimValues = rowsRule.getLim();
        mMaxValues = rowsRule.getMax();

        mNumOfRows = rowsRule.size();
        mRows = new ScoreCardRow[mNumOfRows];
        mMax = new ScoreCardRow[mNumOfRows];
        mHiScores = new ScoreCardRow[mNumOfRows];

        for (int i = 0; i < mNumOfRows; i++) {
            GameRow gameRow = rowsRule.get(i);

            mRows[i] = new ScoreCardRow(mScoreCard, gameRow, i, true);
            mMax[i] = new ScoreCardRow(mScoreCard, gameRow, i, true);
            mHiScores[i] = new ScoreCardRow(mScoreCard, gameRow, i, true);

            mRows[i].getLabel().setHorizontalAlignment(SwingConstants.LEADING);
            mMax[i].getLabel().setText(Integer.toString(mMaxValues[i]));
            mHiScores[i].getLabel().setText(Integer.toString(mLimValues[i]));

            mMax[i].getLabel().setVisible(showMaxCol);
            mHiScores[i].getLabel().setVisible(showHiCol);

//            String toolTip = mRows[i].getGameRow().getToolTip();
//            mRows[i].getLabel().setToolTipText(toolTip);
//            mMax[i].getLabel().setToolTipText(toolTip);
//            mHiScores[i].getLabel().setToolTipText(toolTip);
        }

        int row = mGameType.getResultRow();
        mRows[row].getLabel().setFont(mRows[row].getLabel().getFont().deriveFont((16.0F)));
    }
}
