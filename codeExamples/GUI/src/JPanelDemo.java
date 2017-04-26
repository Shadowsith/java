import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class JPanelDemo extends JFrame {
    public JPanelDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JCheckBox trousersButton = new JCheckBox("Trousers");
        trousersButton.setActionCommand("AC_TROUSERS");
        trousersButton.setSelected(false);

        JCheckBox shirtButton = new JCheckBox("Shirt");
        shirtButton.setActionCommand("AC_SHIRT");
        shirtButton.setSelected(false);

        JCheckBox hatButton = new JCheckBox("Hat");
        hatButton.setActionCommand("AC_HAT");
        hatButton.setSelected(false);

        // Register a listener for all the check boxes.
        CheckBoxListener jbl = new CheckBoxListener();
        trousersButton.addItemListener(jbl);
        shirtButton.addItemListener(jbl);
        hatButton.addItemListener(jbl);

        // Put the check boxes in a panel
        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        checkPanel.add(trousersButton);
        checkPanel.add(shirtButton);
        checkPanel.add(hatButton);

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

        // Register a listener for all the check boxes.
        RadioButtonListener rbl = new RadioButtonListener();
        size48Button.addActionListener(rbl);
        size52Button.addActionListener(rbl);
        size56Button.addActionListener(rbl);

        // Put the check boxes in a panel
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(size48Button);
        radioPanel.add(size52Button);
        radioPanel.add(size56Button);

        // Add the panels to the frame
        // Position checkPanel in slot at the start of line
        // of BorderLayout
        // In western countries (LR-Countries) this is
        // equivalent with the position WEST
        // Dito, there is LINE_END for EAST
        //
        // this.add(checkPanel, BorderLayout.WEST);
        // this.add(radioPanel, BorderLayout.EAST);
        this.add(checkPanel, BorderLayout.LINE_START);
        this.add(radioPanel, BorderLayout.LINE_END);

        // Pack and display
        this.pack();
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new JPanelDemo();
    }
}