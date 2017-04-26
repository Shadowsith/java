// TableModel for example TableRowAddDeleteDemo.java
// We build a custom model because not cells are editable.
// Here, the entire first column is not editable.

import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class MyTableModel extends AbstractTableModel {
    private boolean DEBUG = true; // For demo

    private String[] columnNames = { "Id", "First Name", "Last Name", "Sport",
            "#Years", "Vegi?" };
    private final Class columnClass[] = { Integer.class, String.class,
            String.class, String.class, Integer.class, Boolean.class };
    private List<RowModel> data;

    public MyTableModel(List<RowModel> data) {
        this.data = data;
    }

    public void addRow(RowModel rowData) {
        if (DEBUG) {
            System.out.printf("Appending row without selection where size = %d\n", data.size());
        }
        data.add(rowData);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void addRow(int index, RowModel rowData) {
        if (index < data.size()) {
            if (DEBUG) {
                System.out.printf("Inserting row at index %d where size = %d\n",
                        index, data.size());
            }
            data.add(index, rowData);
            fireTableRowsInserted(index, index);
        } else {
            if (DEBUG) {
                System.out.printf(
                        "Appending row where index %d and size = %d\n", index,
                        data.size());
            }
            data.add(rowData);
            fireTableRowsInserted(data.size() - 1, data.size() - 1);
        }
    }

    public void removeRow(int index) {
        if (index < data.size()) {
            if (DEBUG) {
                System.out.printf("Removing row at index %d where size = %d\n",
                        index, data.size());
            }
            data.remove(index);
            fireTableRowsDeleted(index, index);
        } else {
            if (DEBUG) {
                System.out.printf(
                        "Ignored removal of row at index %d where size = %d\n",
                        index, data.size());
            }
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object result = null;
        RowModel rm = data.get(row);
        switch (col) {
        case 0:
            result = rm.getId();
            break;
        case 1:
            result = rm.getFirstname();
            break;
        case 2:
            result = rm.getLastname();
            break;
        case 3:
            result = rm.getSport();
            break;
        case 4:
            result = rm.getNumyears();
            break;
        case 5:
            result = rm.getIsvegi();
            break;
        }
        return result;
    }

    /*
     * JTable uses this method to determine the default renderer/ editor for
     * each cell. If we didn't implement this method, then the last column would
     * contain text ("true"/"false"), rather than a check box.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    /*
     * Don't need to implement this method unless your table's editable.
     */
    public boolean isCellEditable(int row, int col) {
        // Note that the data/cell address is constant,
        // no matter where the cell appears on screen.
        return (col > 0);
    }

    /*
     * Don't need to implement this method unless your table's data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col + " to "
                    + value + " (an instance of " + value.getClass() + ")");
        }

        RowModel rm = data.get(row);
        switch (col) {
        // Field id is not editable!
        // case 0:
        // rm.setId((Integer) value);
        // break;

        case 1:
            rm.setFirstname((String) value);
            break;
        case 2:
            rm.setLastname((String) value);
            break;
        case 3:
            rm.setSport((String) value);
            break;
        case 4:
            rm.setNumyears((Integer) value);
            break;
        case 5:
            rm.setIsvegi((Boolean) value);
            break;
        }

        fireTableCellUpdated(row, col);

        if (DEBUG) {
            System.out.println("New state of data model:");
            printDebugData();
        }
    }

    // For demo debugging
    private void printDebugData() {
        int numRows = getRowCount();
        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            RowModel rm = data.get(i);
            System.out.println(rm.toString());
        }
        System.out.println("--------------------------");
    }
}
