package org.nbgames.yaya.scorecard;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import org.nbgames.yaya.Options;
import org.nbgames.yaya.scorecard.rule.RowRule;
import se.trixon.almond.util.AGraphics;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class ScoreCardRow {

    private final int COLOR_MASK = 0xEEEEEE;
    private boolean header = false;
    private RowLabel label = new RowLabel();
    private PlayerColumn playerColumn;
    private int preview;
    private boolean registered;
    private int row;
    private RowRule rowRule;
    private ScoreCard scoreCard;
    private int value;

    ScoreCardRow(ScoreCard aScoreCard, PlayerColumn aPlayerColumn, RowRule aRowRule, int aRow) {
        this.scoreCard = aScoreCard;
        this.playerColumn = aPlayerColumn;
        this.rowRule = aRowRule;
        this.row = aRow;
        init();
    }

    ScoreCardRow(ScoreCard aScoreCard, RowRule aRowRule, int aRow, boolean aHeader) {
        this.scoreCard = aScoreCard;
        this.rowRule = aRowRule;
        this.row = aRow;
        this.header = aHeader;
        init();
    }

    public void clearPreview() {
        if (isPlayable() && !isRegistered()) {
            label.setText("");
            label.setCurrentBackgroundColor(Options.INSTANCE.getColor(Options.ColorSelectors.ROW));

            label.setBackground();
        }
    }

    public void enableHover() {
        if (isPlayable() && !isRegistered()) {
            label.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent evt) {
                    mouseEnteredEvent(evt);
                }

                @Override
                public void mouseExited(MouseEvent evt) {
                    mouseExitedEvent(evt);
                }
            });
        }
    }

    public void enableInput() {
        if (isPlayable() && !isRegistered()) {
            label.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent evt) {
                    mousePressedEvent(evt);
                }
            });
        }
    }

    public RowLabel getLabel() {
        return label;
    }

    public int getRow() {
        return row;
    }

    public RowRule getRowRule() {
        return rowRule;
    }

    public int getValue() {
        return value;
    }

    public boolean isHeader() {
        return header;
    }

    public boolean isPlayable() {
        return rowRule.isPlayable();
    }

    public boolean isRegistered() {
        return registered;
    }

    public void newGame() {
        registered = false;
        label.setCurrentBackgroundColor(Options.INSTANCE.getColor(Options.ColorSelectors.ROW));
        label.setText(null);
        preview = 0;
        value = 0;
        if (rowRule.isRollCounter()) {
            label.setText("0");
        }
    }

    public void register() {
        value = preview;
        registered = true;
    }

    public void setEnabled(boolean aState) {
        if (rowRule.isPlayable()) {
            label.setFont(label.getFont().deriveFont(Font.PLAIN));
            label.setCurrentBackgroundColor(Options.INSTANCE.getColor(Options.ColorSelectors.ROW));
        }

        if (aState) {
            if (rowRule.isSum() || rowRule.isBonus()) {
                label.setBackground(Options.INSTANCE.getColor(Options.ColorSelectors.SUM));
            } else {
                label.setBackground(Options.INSTANCE.getColor(Options.ColorSelectors.ROW));
            }

            if (rowRule.isRollCounter()) {
                label.setFont(label.getFont().deriveFont(Font.BOLD));
            }

            enableHover();

        } else {
            if (rowRule.isSum() || rowRule.isBonus()) {
                label.setBackground(AGraphics.colorAndMask(Options.INSTANCE.getColor(Options.ColorSelectors.SUM), COLOR_MASK));
            } else {
                label.setBackground(AGraphics.colorAndMask(Options.INSTANCE.getColor(Options.ColorSelectors.ROW), COLOR_MASK));
            }

            if (rowRule.isRollCounter()) {
                label.setFont(label.getFont().deriveFont(Font.PLAIN));
            }

            MouseListener[] mouseListeners = (MouseListener[]) (label.getListeners(MouseListener.class));

            for (MouseListener mouseListener : mouseListeners) {
                label.removeMouseListener(mouseListener);
            }
        }
    }

    public void setPreview(int preview) {
        this.preview = preview;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public void setText() {
        String text = "";
        if (isPlayable()) {
            label.setFont(label.getFont().deriveFont(Font.PLAIN));
            if (isRegistered()) {
                label.setHorizontalAlignment(SwingConstants.TRAILING);
                text = (value == 0) ? "0" : Integer.toString(value);
            }
            label.setText(text);
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setVisibleHint(boolean aVisible) {
        if (preview == 0 || isRegistered()) {
            return;
        }

        String text = "";

        if (aVisible) {
            text = Integer.toString(preview);
            label.setFont(label.getFont().deriveFont(Font.BOLD));
            label.setHorizontalAlignment(SwingConstants.LEADING);

            if (preview < rowRule.getLim()) {
                label.setCurrentBackgroundColor(Options.INSTANCE.getColor(Options.ColorSelectors.HINT_LO));
            } else {
                label.setCurrentBackgroundColor(Options.INSTANCE.getColor(Options.ColorSelectors.HINT_HI));
            }
        } else {
            label.setCurrentBackgroundColor(Options.INSTANCE.getColor(Options.ColorSelectors.ROW));
        }

        label.setText(text);
        label.setBackground();
    }

    private void init() {
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.TRAILING);
        label.setBorder(new EmptyBorder(2, 10, 2, 10));

        if (rowRule.isSum() || rowRule.isBonus()) {
            label.setFont(label.getFont().deriveFont(Font.BOLD));
        }
    }

    private void mouseEnteredEvent(MouseEvent evt) {
        label.setBackground(AGraphics.colorAndMask(label.getCurrentBackgroundColor(), COLOR_MASK));
        scoreCard.hoverRowEntered(row);
    }

    private void mouseExitedEvent(MouseEvent evt) {
        label.setBackground(label.getCurrentBackgroundColor());
        scoreCard.hoverRowExited(row);
    }

    private void mousePressedEvent(MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            playerColumn.getRowStack().push(row);
            scoreCard.hoverRowExited(row);

            MouseListener[] mouseListeners = (MouseListener[]) (label.getListeners(MouseListener.class));

            for (MouseListener mouseListener : mouseListeners) {
                label.removeMouseListener(mouseListener);
            }

            register();
            playerColumn.setText();
            scoreCard.register();
        }
    }
}
