import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;

public class RadioButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JRadioButton rb = (JRadioButton) e.getSource();
        String cmd = rb.getActionCommand();
        if (cmd.equals("AC_SIZE48")) {
            System.out.printf("Size button 48 is selected\n");
        } else if (cmd.equals("AC_SIZE52")) {
            System.out.printf("Size button 52 is selected\n");
        } else if (cmd.equals("AC_SIZE56")) {
            System.out.printf("Size button 56 is selected\n");
        } else {
            System.out.printf("Unknow action detected\n");
        }
    }
}