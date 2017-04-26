/* FABR: This is an adaption of example
 * http://docs.oracle.com/javase/tutorial/uiswing/examples/components/
 *  TableDemoProject/src/components/TableDemo.java
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.LinkedList;

/**
 * TableRowAddDeleteDemo is just like TableDemo2, except that we add buttons for
 * adding and deleting rows. In addition we separated the classes.
 */

@SuppressWarnings("serial")
public class TableRowAddDeleteDemo extends JFrame implements ActionListener {
    private JButton addButton, deleteButton;
    private MyJTable table;
    private MyTableModel model;

    public TableRowAddDeleteDemo(List<RowModel> mydata) {
        // Create and set up the window.
        setTitle("TableDemo with add/delete option");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define the table model
        model = new MyTableModel(mydata);
        // Create the JTable component
        table = new MyJTable(model);
        // Create the scroll pane around the table.
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the center 0f the boarder layout
        add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("add empty row");
        deleteButton = new JButton("delete selected row");
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Display the window.
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            int index = table.getSelectedRow();
            if (index != -1) {
                model.addRow(index + 1, new RowModel("", "", "", 0, false));
                // AddOn: Scroll new row into view
                scrollToVisible(table, index+1, 0);
            } else {
                model.addRow(new RowModel("", "", "", 0, false));
                // AddOn: Scroll new row into view
                scrollToVisible(table, model.getRowCount()-1, 0);
            }
            
        } else if (e.getSource() == deleteButton) {
            int index = table.getSelectedRow();

            if (index != -1) {
                model.removeRow(index);
                // AddOn: Select position where we removed the last row
                if (index < model.getRowCount()) {
                    ListSelectionModel selectionModel = table
                            .getSelectionModel();
                    selectionModel.setSelectionInterval(index, index);
                }
            }
        }
    }
    
    
    public static void main(String[] args) {
        List<RowModel> theTableData = new LinkedList<RowModel>();
        theTableData.add(new RowModel("Kathy", "Smith", "Snowboarding",
                new Integer(5), new Boolean(false)));
        theTableData.add(new RowModel("John", "Doe", "Rowing", new Integer(3),
                new Boolean(true)));
        theTableData.add(new RowModel("Sue", "Black", "Knitting",
                new Integer(2), new Boolean(false)));
        theTableData.add(new RowModel("Jane", "White", "Speed reading",
                new Integer(20), new Boolean(true)));
        theTableData.add(new RowModel("Joe", "Brown", "Pool", new Integer(10),
                new Boolean(false)));

        new TableRowAddDeleteDemo(theTableData);
    }

    // ----------------------------------------------------------
    // AddOn: scroll to a certain postion and make it visible

    public void scrollToVisible(JTable table, int rowIndex, int vColIndex) {
        scrollToVisibleV2(table, rowIndex, vColIndex);
    }

    public void scrollToVisibleV1(JTable table, int rowIndex, int vColIndex) {
        // Adaption of 
        // http://stackoverflow.com/questions/853020/
        //      jtable-scrolling-to-a-specified-row-index
        if (!(table.getParent() instanceof JViewport)) {
            System.out.println("scrollToVisible: bailing out");
            return;
        }
        JViewport viewport = (JViewport)table.getParent();

        // This rectangle is relative to the table where the
        // northwest corner of cell (0,0) is always (0,0).
        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);

        // The location of the viewport relative to the table
        Point pt = viewport.getViewPosition();

        // Translate the cell location so that it is relative
        // to the view, assuming the northwest corner of the
        // view is (0,0)
        rect.setLocation(rect.x+pt.x, rect.y+pt.y);

        table.scrollRectToVisible(rect);
        // Scroll the area into view
        //viewport.scrollRectToVisible(rect);

    }

    public void scrollToVisibleV2(JTable table, int rowIndex, int vColIndex) {
        // Adaption of 
        // http://stackoverflow.com/questions/853020/
        //      jtable-scrolling-to-a-specified-row-index

        // This rectangle is relative to the table where the
        // northwest corner of cell (0,0) is always (0,0).
        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
        rect.setLocation(rect.x, rect.y);
        table.scrollRectToVisible(rect);
    }
}
