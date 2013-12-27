package org.nbgames.yaya.scorecard;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import org.nbgames.core.CircularInt;
import org.nbgames.yaya.Options;
import org.nbgames.yaya.scorecard.ScoreCardObservable.ScoreCardEvent;
import se.trixon.almond.util.AGraphics;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ScoreCard {

    private int activePlayer;
    private JPanel basePanel = new JPanel();
    private CircularInt currentPlayer;
    private HeaderColumn headerColumn;
    private int mNumOfPlayers;
    private int numOfRolls;
    private int numOfRows;
    private ScoreCardObservable observable = new ScoreCardObservable();
    private JPanel panel = new JPanel();
    private LinkedList<PlayerColumn> playerPositions;
    private LinkedList<PlayerColumn> players = new LinkedList<>();
    private boolean registerable = false;
    private AbstractAction undoAction;
    private JButton undoButton;
    private boolean viewHint;

    public ScoreCard() {
        mNumOfPlayers = Options.INSTANCE.getNumOfPlayers();

        init();
    }

    public JPanel getCard() {
        return basePanel;
    }

    public HeaderColumn getHeaderColumn() {
        return headerColumn;
    }

    public int getNumOfRolls() {
        return numOfRolls;
    }

    public ScoreCardObservable getObservable() {
        return observable;
    }

    public AbstractAction getUndoAction() {
        return undoAction;
    }

    public boolean isRollable() {
        return true;
    }

    public boolean isViewHint() {
        return viewHint;
    }

    public void newGame() {
        registerable = false;
        undoAction.setEnabled(false);
        activePlayer = 0;
        numOfRolls = 0;
        currentPlayer = new CircularInt(0, mNumOfPlayers - 1);

        for (PlayerColumn playerColumn : players) {
            playerColumn.newGame();
        }

        players.get(activePlayer).setEnabled(true);
    }

    public void newRoll() {
        players.get(activePlayer).clearPreview();
    }

    public void parseDice(LinkedList<Integer> values) {
        setEnabledRegister(true);
        numOfRolls++;
        players.get(activePlayer).incNumOfRolls();
        players.get(activePlayer).parse(values);

        if (Options.INSTANCE.isShowingHints()) {
            players.get(activePlayer).setVisibleHints(true);
        }
    }

    public void setEnabledRegister(boolean aRegisterableState) {
        registerable = aRegisterableState;
    }

    public void setEnabledUndo(boolean anUndoableState) {
        undoAction.setEnabled(anUndoableState);
    }

    public void setVisibleHints(boolean viewHint) {
        this.viewHint = viewHint;
        players.get(activePlayer).setVisibleHints(viewHint);
    }

    protected void actionPerformedUndo() {
        registerable = true;
        players.get(activePlayer).setEnabled(false);
        activePlayer = currentPlayer.dec();
        players.get(activePlayer).undo();

        observable.notify(ScoreCardEvent.UNDO);
    }

    protected void hoverRowEntered(int aRow) {
        Color activeColor = AGraphics.colorAndMask(Options.INSTANCE.getColor(Options.ColorSelectors.HEADER), 0xEEEEEE);

        headerColumn.getRows()[aRow].getLabel().setBackground(activeColor);
        headerColumn.getHiScores()[aRow].getLabel().setBackground(activeColor);
        headerColumn.getMax()[aRow].getLabel().setBackground(activeColor);
    }

    protected void hoverRowExited(int aRow) {
        headerColumn.getRows()[aRow].getLabel().setBackground(Options.INSTANCE.getColor(Options.ColorSelectors.HEADER));
        headerColumn.getHiScores()[aRow].getLabel().setBackground(Options.INSTANCE.getColor(Options.ColorSelectors.HEADER));
        headerColumn.getMax()[aRow].getLabel().setBackground(Options.INSTANCE.getColor(Options.ColorSelectors.HEADER));
    }

    protected void register() {
        if (registerable) {
            registerable = false;
            numOfRolls = 0;
            players.get(activePlayer).register();

            updatePolePosition();

            if (isGameOver()) {
                observable.notify(ScoreCardEvent.GAME_OVER);
                gameOver();

            } else {
                undoAction.setEnabled(true);
                observable.notify(ScoreCardEvent.REGISTER);

                activePlayer = currentPlayer.inc();
                players.get(activePlayer).setEnabled(true);
            }
        }
    }

    private void applyColors() {
        panel.setBackground(Options.INSTANCE.getColor(Options.ColorSelectors.SCORECARD));
        basePanel.setBackground(Options.INSTANCE.getColor(Options.ColorSelectors.BOARD));
        Color color;

        for (int i = 0; i < numOfRows; i++) {

            if (headerColumn.getRows()[i].getRowRule().isSum() || headerColumn.getRows()[i].getRowRule().isBonus()) {
                color = Options.INSTANCE.getColor(Options.ColorSelectors.SUM);
            } else {
                color = Options.INSTANCE.getColor(Options.ColorSelectors.HEADER);
            }

            headerColumn.getRows()[i].getLabel().setBackground(color);
            headerColumn.getHiScores()[i].getLabel().setBackground(color);
            headerColumn.getMax()[i].getLabel().setBackground(color);

        }

        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < numOfRows; j++) {
                if (players.get(i).getRows()[j].getRowRule().isSum() || headerColumn.getRows()[j].getRowRule().isBonus()) {
                    color = Options.INSTANCE.getColor(Options.ColorSelectors.SUM);
                } else {
                    color = Options.INSTANCE.getColor(Options.ColorSelectors.ROW);
                }
                players.get(i).getRows()[j].getLabel().setBackground(color);
                players.get(i).getRows()[j].getLabel().setCurrentBackgroundColor(color);
            }
        }

        if (Options.INSTANCE.isShowingHints()) {
            setVisibleHints(true);
        }

    }

    private void gameOver() {

        while (!players.get(mNumOfPlayers - 1).getRowStack().empty()) {

            for (PlayerColumn playerColumn : players) {
                int row = playerColumn.getRowStack().pop();
            }
        }

//        AGameOver gameOver = new AGameOver();
//        gameOver.getUI().setPreferredSize(new Dimension(300, 280));
//        gameOver.getUI().pack();
//        GridBagLayout gridBagLayout = new GridBagLayout();
//        JPanel gameOverPanel = new JPanel(gridBagLayout);
//
//        GridBagConstraints gridBagConstraints;
//
//        Font font = new Font("Dialog", Font.BOLD, 26);
//        for (PlayerColumn playerColumn : playerPositions) {
//            JLabel label = new JLabel(playerColumn.getName());
//            label.setFont(font);
//
//            gridBagConstraints = new GridBagConstraints();
//            gridBagConstraints.gridx = 0;
//            gridBagConstraints.ipadx = 50;
//            gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
//            gridBagConstraints.anchor = GridBagConstraints.LINE_START;
//
//            gridBagLayout.setConstraints(label, gridBagConstraints);
//            gameOverPanel.add(label, gridBagConstraints);
//
//            label = new JLabel(Integer.toString(playerColumn.getCurrentScore()));
//            label.setFont(font);
//
//            gridBagConstraints = new GridBagConstraints();
//            gridBagConstraints.gridx = 1;
//            gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
//            gridBagConstraints.anchor = GridBagConstraints.LINE_END;
//
//            gridBagLayout.setConstraints(label, gridBagConstraints);
//            gameOverPanel.add(label, gridBagConstraints);
//        }
//
//        gameOver.setPanel(gameOverPanel);
//        gameOver.getUI().centerInOwner();
    }

    private void init() {
//        DiceGame.getAApplication().getLexiconNotificationList().add(lexicon);

        players.clear();
        headerColumn = new HeaderColumn(this, Options.INSTANCE.getRule());
        initActions();
        numOfRows = Options.INSTANCE.getRule().getNumOfRows();

        initLayout();
        applyColors();
    }

    private void initActions() {

        undoAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                undoAction.setEnabled(false);
                actionPerformedUndo();
            }
        };
        undoAction.setEnabled(false);
        undoButton = new JButton(undoAction);
    }

    private void initLayout() {
        EmptyBorder basePanelBorder = new EmptyBorder(10, 10, 10, 10);
        EmptyBorder emptyBorder = new EmptyBorder(8, 8, 8, 8);
        SoftBevelBorder bevelBorder = new SoftBevelBorder(BevelBorder.RAISED);
        SoftBevelBorder loweredBevelBorder = new SoftBevelBorder(BevelBorder.LOWERED);
        CompoundBorder compoundBorder = new CompoundBorder(bevelBorder, emptyBorder);

        panel.setBorder(compoundBorder);
        panel.setBorder(new CompoundBorder(compoundBorder, loweredBevelBorder));
        basePanel.setBorder(basePanelBorder);
        basePanel.add(panel);

        basePanel.setOpaque(true);
        panel.setOpaque(true);

        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints;

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagLayout.setConstraints(undoButton, gridBagConstraints);
        panel.add(undoButton);

        Insets insets = new Insets(1, 0, 0, 0);

        for (int i = 0; i < numOfRows; i++) {
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.anchor = GridBagConstraints.LINE_START;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.insets = insets;

            gridBagConstraints.gridy = i + 1;
            gridBagLayout.setConstraints(headerColumn.getRows()[i].getLabel(), gridBagConstraints);
            panel.add(headerColumn.getRows()[i].getLabel());

            gridBagConstraints.gridx = 1;
            gridBagLayout.setConstraints(headerColumn.getMax()[i].getLabel(), gridBagConstraints);
            panel.add(headerColumn.getMax()[i].getLabel());

            gridBagConstraints.gridx = 2;
            gridBagLayout.setConstraints(headerColumn.getHiScores()[i].getLabel(), gridBagConstraints);
            panel.add(headerColumn.getHiScores()[i].getLabel());
        }

        int startGridX = 3;
        insets.left = 1;
        for (int i = 0; i < mNumOfPlayers; i++) {
            players.add(new PlayerColumn(this, i, Options.INSTANCE.getRule()));
            ScoreCardRow[] column = players.get(i).getRows();

            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = startGridX + i;
            gridBagConstraints.anchor = GridBagConstraints.LINE_START;
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = insets;

            gridBagLayout.setConstraints(players.get(i).getComboBox(), gridBagConstraints);
            panel.add(players.get(i).getComboBox());
            for (int j = 0; j < numOfRows; j++) {
                gridBagConstraints.gridy = j + 1;

                gridBagLayout.setConstraints(column[j].getLabel(), gridBagConstraints);
                panel.add(column[j].getLabel());
            }
        }
    }

    private boolean isGameOver() {
        boolean gameOver;
        if (activePlayer == mNumOfPlayers - 1) {
            gameOver = true;
            for (int i = 0; i < players.get(mNumOfPlayers - 1).getRows().length; i++) {
                ScoreCardRow scoreCardRow = players.get(mNumOfPlayers - 1).getRows()[i];

                if (scoreCardRow.isPlayable() && !scoreCardRow.isRegistered()) {
                    gameOver = false;
                    break;
                }
            }
        } else {
            gameOver = false;
        }

        return gameOver;
    }

    private void updatePolePosition() {
        playerPositions = (LinkedList<PlayerColumn>) players.clone();
        PlayerColumnComparator pcc = new PlayerColumnComparator(PlayerColumnComparator.DESCENDING);
        Collections.sort(playerPositions, pcc);

        float reducer = 0.F;
        Font font = headerColumn.getRows()[Options.INSTANCE.getRule().getResultRow()].getLabel().getFont();

        for (PlayerColumn playerColumn : playerPositions) {
            JLabel label = playerColumn.getRows()[Options.INSTANCE.getRule().getResultRow()].getLabel();
            label.setFont(font.deriveFont((16.0F - reducer)));
            reducer += 1.0;
        }
    }
}
