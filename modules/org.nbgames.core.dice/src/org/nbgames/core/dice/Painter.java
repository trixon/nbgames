package org.nbgames.core.dice;

import org.nbgames.core.HandMode;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
class Painter extends JPanel implements MouseInputListener, MouseWheelListener {

    protected static final int DIE_CELL_WIDTH = 140;
    protected static final int MARGIN_X_DICE_SET = 160;
    protected static final int MARGIN_X_ROLLER = 10;
    protected static final int MARGIN_Y_ROLLER = 20;
    private DiceBoard diceBoard;
    private Roller diceRoller;
    private int diceSetX;
    private int dieAreaWidth;
    private Graphics2D g2;
    private boolean rollable;
    private boolean selectable;

    Painter(DiceBoard aDiceBoard) {
        this.diceBoard = aDiceBoard;
        init();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (diceRoller.isVisible() == true) {
            diceRoller.slideOut();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point point = translateMousePoint(e.getPoint());
        int x = point.x;

        if (isRollable()) {
            if (x <= diceRoller.getImage().getWidth() && diceRoller.isVisible() == false) {
                diceRoller.slideIn();
            }

            if (x >= diceRoller.getImage().getWidth() && diceRoller.isVisible() == true) {
                diceRoller.slideOut();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = translateMousePoint(e.getPoint());
        int x = point.x;

        if (e.getButton() == MouseEvent.BUTTON1) {

            if (x <= diceRoller.getImage().getWidth() && isRollable()) {
                diceRoller.shake(true);
                return;
            }

            if (isSelectable()) {
                for (Die die : diceBoard.getDice()) {
                    if ((x - diceSetX >= DIE_CELL_WIDTH * die.getColumn()) && (x - diceSetX <= DIE_CELL_WIDTH * (die.getColumn() + 1))) {
                        die.setSelected(!die.isSelected());
                        break;
                    }
                }
            }
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            if (isSelectable() && isDiceStoped()) {
                for (Die die : diceBoard.getDice()) {
                    if ((x - diceSetX >= DIE_CELL_WIDTH * die.getColumn()) && (x - diceSetX <= DIE_CELL_WIDTH * (die.getColumn() + 1))) {
                    } else {
                        die.setSelected(!die.isSelected());
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point point = translateMousePoint(e.getPoint());
        int x = point.x;

        if (diceRoller.getShakeThread().isAlive()) {
            diceRoller.getShakeThread().interrupt();
            diceRoller.shake(false);

            if (x <= diceRoller.getImage().getWidth() && isRollable()) {
                diceBoard.rollPreOp();
            } else {
                diceRoller.slideOut();
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        if (isSelectable() && isDiceStoped()) {

            boolean select = true;

            if (e.getWheelRotation() == -1) {
                select = false;
            }

            for (Die die : diceBoard.getDice()) {
                die.setSelected(select);
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        g2 = (Graphics2D) g;
        AffineTransform originalAffineTransform = g2.getTransform();

        if (diceBoard.getHandMode() == HandMode.RIGHT) {
            AffineTransform affineTransform = originalAffineTransform;
            affineTransform.scale(-1, 1);
            affineTransform.translate(-this.getWidth(), 0);
            g2.setTransform(affineTransform);
        }

        paintDiceRoller();
        paintDice();
//        paintGrid();

        g2.setTransform(originalAffineTransform);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    private void init() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        addHierarchyBoundsListener(new HierarchyBoundsListener() {

            @Override
            public void ancestorMoved(HierarchyEvent evt) {
            }

            @Override
            public void ancestorResized(HierarchyEvent evt) {
                calcGrid();
            }
        });

        calcGrid();
    }

    private boolean isDiceStoped() {
        boolean stopped = true;

        for (Die die : diceBoard.getDice()) {
            if (die.getAnimator().isAlive()) {
                stopped = false;
                break;
            }
        }

        return stopped;
    }

    private void paintDice() {
        for (Die die : diceBoard.getDice()) {
            if (die.isVisible()) {
                g2.drawImage(die.getImage(), die.getX(), die.getY(), this);
            }
        }
    }

    private void paintDiceRoller() {
        diceRoller = diceBoard.getDiceRoller();

        if (diceRoller == null) {
            return;
        }

        g2.drawImage(diceRoller.getImage(), diceRoller.getX(), diceRoller.getY(), this);
    }

    private void paintGrid() {
        paintVertical(MARGIN_X_DICE_SET);
        paintVertical(MARGIN_X_DICE_SET + 5);
        paintVertical(diceSetX);

        for (int i = 0; i < diceBoard.getNumOfDice() + 1; i++) {
            paintVertical(diceSetX + i * DIE_CELL_WIDTH);
            try {
                g2.setColor(Color.red);
                paintVertical(diceBoard.getDice().get(i).getCenter());
                g2.setColor(Color.black);
            } catch (Exception e) {
            }
        }
    }

    private void paintVertical(int x) {
        g2.drawLine(x, 0, x, 200);
    }

    private Point translateMousePoint(Point aPoint) {
        Point point = aPoint;

        if (diceBoard.getHandMode() == HandMode.RIGHT) {
            point.x = this.getWidth() - point.x;
        }

        return point;
    }

    void calcGrid() {
        int diceSetWidth = DIE_CELL_WIDTH * diceBoard.getNumOfDice();
        dieAreaWidth = this.getWidth() - MARGIN_X_DICE_SET;
        diceSetX = (dieAreaWidth - diceSetWidth) / 2 + MARGIN_X_DICE_SET;

        for (int i = 0; i < diceBoard.getNumOfDice(); i++) {
            try {
                diceBoard.getDice().get(i).setCenter(diceSetX + i * DIE_CELL_WIDTH + DIE_CELL_WIDTH / 2);
            } catch (NullPointerException e) {
            }
        }
    }

    synchronized void calcRollable() {
        setRollable(diceBoard.getNumOfSelectedDice() > 0);
    }

    boolean isRollable() {
        return rollable;
    }

    boolean isSelectable() {
        return selectable;
    }

    void setRollable(boolean rollable) {
        this.rollable = rollable;
    }

    void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }
}
