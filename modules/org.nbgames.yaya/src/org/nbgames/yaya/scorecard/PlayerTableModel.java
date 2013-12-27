package org.nbgames.yaya.scorecard;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Patrik Karlsson <patrik@trixon.se>
 */
public class PlayerTableModel extends AbstractTableModel {

    private String[] columns = {"active", "name", "lefty"};
    private Object[][] rows = {
        {new Boolean(true), "Pata", new Boolean(false)},
        {new Boolean(false), "Egon", new Boolean(true)}
    };

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return super.getColumnName(column);
    }

    @Override
    public int getRowCount() {
        return rows.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex < 2) {
            return false;
        } else {
            return true;
        }

//        return super.isCellEditable(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        super.setValueAt(aValue, rowIndex, columnIndex);
        rows[rowIndex][columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);

    }
}
