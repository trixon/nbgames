package org.nbgames.core.card;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class LabelListCellRenderer extends JLabel implements ListCellRenderer {

    private ImageIcon[] images;

    public LabelListCellRenderer(ImageIcon[] anImageArray) {
        images = anImageArray;

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setOpaque(true);
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
    }

    @Override
    public Component getListCellRendererComponent(JList aList, Object anObject, int anIndex, boolean anIsSelected, boolean anCellHasFocus) {
        int selectedIndex = ((Integer) anObject).intValue();

        if (anIsSelected) {
            setBackground(aList.getSelectionBackground());
            setForeground(aList.getSelectionForeground());
        } else {
            setBackground(aList.getBackground());
            setForeground(aList.getForeground());
        }

        ImageIcon icon = images[selectedIndex];
        setIcon(icon);
        if (icon != null) {
            setText(icon.getDescription());
            setFont(aList.getFont());
        }

        return this;
    }
}
