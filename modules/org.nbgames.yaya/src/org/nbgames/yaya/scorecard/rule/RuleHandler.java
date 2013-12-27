package org.nbgames.yaya.scorecard.rule;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import se.trixon.almond.util.AUtil;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class RuleHandler extends DefaultHandler {

    private String mCurrentRule;
    private int mNumOfDice;
    private int mNumOfRolls;
    private int mResultRow;
    private RowRule mRowRule = new RowRule();
    private RowsRule mRowsRule = new RowsRule();
    private String mRule;

    RuleHandler(Rule rule) {
        mRule = rule.name().toLowerCase();
    }

    public int getNumOfDice() {
        return mNumOfDice;
    }

    public int getNumOfRolls() {
        return mNumOfRolls;
    }

    public int getNumOfRows() {
        return mRowsRule.size();
    }

    public int getResultRow() {
        return mResultRow;
    }

    public RowsRule getRowsRule() {
        return mRowsRule;
    }

    public String[] getRowsRuleArray() {
        return (String[]) mRowsRule.toArray();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("rule")) {
            mCurrentRule = attributes.getValue("name");
            if (mCurrentRule.equals(mRule)) {
                mNumOfDice = Integer.valueOf(attributes.getValue("dice"));
                mNumOfRolls = Integer.valueOf(attributes.getValue("rolls"));
                mResultRow = Integer.valueOf(attributes.getValue("resultRow"));
            }
        } else if (qName.equals("row")) {
            if (mCurrentRule.equals(mRule)) {
                mRowRule.setName(attributes.getValue("name"));
                mRowRule.setLim(attributes.getValue("lim"));
                mRowRule.setMax(attributes.getValue("max"));
                mRowRule.setPlayable(attributes.getIndex("isPlayable"));
                mRowRule.setRollCounter(attributes.getIndex("isRollCounter"));
                mRowRule.setSum(attributes.getIndex("isSum"));
                mRowRule.setBonus(attributes.getIndex("isBonus"));
                mRowRule.setToolTip(attributes.getValue("toolTip"));

                parseFormula(attributes, "parse");
                parseSum(attributes, "sum");

                mRowsRule.add(mRowRule);
                mRowRule = new RowRule();
            }
        }
    }

    private void parseFormula(Attributes attributes, String tag) {
        if (attributes.getIndex(tag) > -1) {
            mRowRule.setForumla(attributes.getValue(tag));
        }
    }

    private void parseSum(Attributes attributes, String tag) {
        if (attributes.getIndex(tag) > -1) {
            mRowRule.setSumSet(AUtil.getSet(attributes.getValue(tag)));
        }
    }
}
