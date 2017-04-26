import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;

public class CheckBoxListener implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
        JCheckBox jb = (JCheckBox) e.getSource();
        String cmd = jb.getActionCommand();
        String action;
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            action = "deselected";
        } else {
            action = "selected";
        }
        if (cmd.equals("AC_TROUSERS")) {
            System.out.printf("Trousers check box was %s\n", action);
        } else if (cmd.equals("AC_SHIRT")) {
            System.out.printf("Shirt check box was %s\n", action);
        } else if (cmd.equals("AC_HAT")) {
            System.out.printf("Hat check box was %s\n", action);
        } else {
            System.out.printf("Unknow action detected\n");
        }
    }
}