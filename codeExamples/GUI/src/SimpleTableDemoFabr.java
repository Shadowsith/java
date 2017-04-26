/* FABR: For simplicity made some small changes to
 * http://docs.oracle.com/javase/tutorial/uiswing/examples/components/
 * SimpleTableDemoProject/src/components/SimpleTableDemo.java
 * 
 */

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class SimpleTableDemoFabr extends JFrame {
    public SimpleTableDemoFabr() {
        // Create and set up the window.
        setTitle("SimpleTableDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create column header
        String[] columnNames = { "First Name", "Last Name", "Sport",
                "# of Years", "Vegetarian" };

        // Create data model
        Object[][] data = {
                { "Kathy", "Smith", "Snowboarding", new Integer(5),
                        new Boolean(false) },
                { "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
                { "Sue", "Black", "Knitting", new Integer(2),
                        new Boolean(false) },
                { "Jane", "White", "Speed reading", new Integer(20),
                        new Boolean(true) },
                { "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };

        // Create table component
        JTable table = new JTable(data, columnNames);

        // Configure presentation of component
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to this panel.
        add(scrollPane);

        // Pack and display the window.
        pack();
        setVisible(true);

    }

    public static void main(String[] args) {
        new SimpleTableDemoFabr();
    }
}