package org.nbgames.yaya.scorecard.rule;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.openide.util.Exceptions;
import org.xml.sax.SAXException;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public enum Rule {

    MAXI_YAHTZEE,//    ONEHUNDRED,
    QUICKIE,
    STRAIGHT,
    YAHTZEE;
    private final String RESOURCE_PATH = "/org/nbgames/yaya/scorecard/rule/ruleDefinitions.xml";
    private RuleHandler mRuleHandler;

    Rule() {
        initRuleHandler();
    }

    public String[] getKey() {
        return mRuleHandler.getRowsRule().getKeys();
    }

    public int[] getLim() {
        return mRuleHandler.getRowsRule().getLim();
    }

    public int[] getMax() {
        return mRuleHandler.getRowsRule().getMax();
    }

    public String getNameKey() {
        return "menuItem.game." + name().toLowerCase();
    }

    public int getNumOfDice() {
        return mRuleHandler.getNumOfDice();
    }

    public int getNumOfRolls() {
        return mRuleHandler.getNumOfRolls();
    }

    public int getNumOfRows() {
        return mRuleHandler.getNumOfRows();
    }

    public int getResultRow() {
        return mRuleHandler.getResultRow();
    }

    public RowsRule getRowsRule() {
        return mRuleHandler.getRowsRule();
    }

    public RuleHandler getRuleHandler() {
        return mRuleHandler;
    }

    private void initRuleHandler() {
        mRuleHandler = new RuleHandler(this);
        InputStream inputStream = getClass().getResourceAsStream(RESOURCE_PATH);

        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setValidating(false);

        try {
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(inputStream, mRuleHandler);
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(Rule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
