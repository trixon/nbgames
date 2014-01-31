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

    private final ImageIcon[] mImages;

    public LabelListCellRenderer(ImageIcon[] anImageArray) {
        mImages = anImageArray;

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setOpaque(true);
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object object, int index, boolean isSelected, boolean cellHasFocus) {
        int selectedIndex = ((Integer) object).intValue();

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        ImageIcon icon = mImages[selectedIndex];
        setIcon(icon);

        if (icon != null) {
            setText(icon.getDescription());
            setFont(list.getFont());
        }

        return this;
    }
}
