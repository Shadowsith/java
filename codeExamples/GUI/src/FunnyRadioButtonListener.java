import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

public class FunnyRadioButtonListener implements ActionListener {
    String cmd;

    public void actionPerformed(ActionEvent e) {

        // Nachfolgende Programmierung ist schlechter Stil!
        // if (e.getSource() instanceof JRadioButton) {
        // cmd = ((JRadioButton)e.getSource()).getActionCommand();
        // } else if (e.getSource() instanceof JCheckBox) {
        // cmd = ((JCheckBox)e.getSource()).getActionCommand();
        // } else if (e.getSource() instanceof JButton) {
        // cmd = ((JButton)e.getSource()).getActionCommand();
        // }

        // So besser
        AbstractButton ab = (AbstractButton) e.getSource();
        cmd = ab.getActionCommand();

        // So ganz kurz
        // cmd = ((AbstractButton)e.getSource()).getActionCommand();

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