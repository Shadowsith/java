import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RadioButtonDemo extends JFrame {
    public RadioButtonDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JRadioButton size48Button = new JRadioButton("Size48");
        size48Button.setActionCommand("AC_SIZE48");

        JRadioButton size52Button = new JRadioButton("Size52");
        size52Button.setActionCommand("AC_SIZE52");
        size52Button.setSelected(true);

        JRadioButton size56Button = new JRadioButton("Size56");
        size56Button.setActionCommand("AC_SIZE56");

        // Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(size48Button);
        group.add(size52Button);
        group.add(size56Button);

        // Register a listener for all the radio button.
        RadioButtonListener rbl = new RadioButtonListener();
        size48Button.addActionListener(rbl);
        size52Button.addActionListener(rbl);
        size56Button.addActionListener(rbl);

        // Put the radio buttons into a panel
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(size48Button);
        radioPanel.add(size52Button);
        radioPanel.add(size56Button);

        // Add the panel to the frame
        this.add(radioPanel);

        // Pack and display
        this.pack();
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new RadioButtonDemo();
    }
}