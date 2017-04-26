// Adapted component JTable for example TableRowAddDeleteDemo.java

import java.awt.Dimension;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class MyJTable extends JTable {

    public MyJTable(MyTableModel model) {
        super(model);

        // Configure the JTable component
        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);
        int columnWidth[] = { 10, 100, 100, 100, 40, 30 };
        for (int i = 0; i < columnWidth.length; ++i) {
            getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
            //System.out.printf("Col=%d pw=%d\n", i, getColumnModel()
            //        .getColumn(i).getPreferredWidth());
        }
    }
}
