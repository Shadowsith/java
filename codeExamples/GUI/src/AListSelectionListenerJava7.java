import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AListSelectionListenerJava7 implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) {
        JList<String> thelist = (JList<String>) e.getSource();
        if (e.getValueIsAdjusting() == false) {

            if (thelist.getSelectedIndex() >= 0) {
                System.out.printf("Item %d:%s was selected\n",
                        thelist.getSelectedIndex() + 1,
                        thelist.getSelectedValue()); // Yields String here
            }
        }
    }
}