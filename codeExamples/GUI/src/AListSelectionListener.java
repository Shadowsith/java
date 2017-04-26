import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AListSelectionListener implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) {
        JList thelist = (JList) e.getSource();
        if (e.getValueIsAdjusting() == false) {

            if (thelist.getSelectedIndex() >= 0) {
                System.out.printf("Item %d:%s was selected\n",
                        thelist.getSelectedIndex() + 1,
                        thelist.getSelectedValue());
            }
        }
    }
}