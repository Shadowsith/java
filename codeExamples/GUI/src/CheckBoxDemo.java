import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CheckBoxDemo extends JFrame {
    public CheckBoxDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JCheckBox trousersButton = new JCheckBox("Trousers");
        trousersButton.setActionCommand("AC_TROUSERS");
        trousersButton.setSelected(true);

        JCheckBox shirtButton = new JCheckBox("Shirt");
        shirtButton.setActionCommand("AC_SHIRT");
        shirtButton.setSelected(true);

        JCheckBox hatButton = new JCheckBox("Hat");
        hatButton.setActionCommand("AC_HAT");
        hatButton.setSelected(true);

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

        // Add the panel to the frame
        this.add(checkPanel);

        // Pack and display
        this.pack();
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new CheckBoxDemo();
    }
}