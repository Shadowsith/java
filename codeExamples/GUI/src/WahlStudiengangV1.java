// Example for components and layout
// No action listeners are registered
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class WahlStudiengangV1 extends JFrame implements ActionListener {
    public WahlStudiengangV1() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fill the header line
        JLabel header = new JLabel("Wahl des Studiengangs", JLabel.CENTER);
        this.add(header, BorderLayout.NORTH);

        // Create a center panel

        JPanel centerPanel = new JPanel(new GridLayout(0, 2));

        // Create a panel for the radio buttons
        JRadioButton bachelorButton = new JRadioButton("Bachelor");
        bachelorButton.setActionCommand("AC_BACHELOR");
        JRadioButton masterButton = new JRadioButton("Master");
        masterButton.setActionCommand("AC_MASTER");
        // Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(bachelorButton);
        group.add(masterButton);

        // Register a listener for all the radio buttons
        bachelorButton.addActionListener(this);
        masterButton.addActionListener(this);
        // Put the radio buttons into a panel
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(bachelorButton);
        radioPanel.add(masterButton);

        // Add the panel to the West of the main frame
        // Show feature LINE_START:
        // Position component in slot at the start of line
        // of BorderLayout
        // In western countries (LR-Countries) this is
        // equivalent with the position WEST
        // this.add(radioPanel, BorderLayout.WEST);
        this.add(radioPanel, BorderLayout.LINE_START);

        // Create components of EASTern position
        JComboBox studiengang = new JComboBox();
        for (String s : "Informatik, Mechatronik, Elektro un Informationstechnik"
                .split(",")) {
            studiengang.addItem(s);
        }
        this.add(studiengang, BorderLayout.LINE_END);

        // Use a filler label
        String filler = "     ";

        // Add fillers below
        // centerPanel.add(new JLabel(filler));
        // centerPanel.add(new JLabel(filler));

        // Add fillers
        // this.add(new JLabel(filler), BorderLayout.EAST);
        // this.add(new JLabel(filler), BorderLayout.WEST);

        // Add login Button in the SOUTH section
        // Use fillers
        JPanel bottomPanel = new JPanel(new GridLayout(2, 3));
        this.add(bottomPanel, BorderLayout.SOUTH);

        JButton btnLogin = new JButton("LOGIN");
        bottomPanel.add(new JLabel(filler));
        bottomPanel.add(btnLogin, BorderLayout.SOUTH);
        bottomPanel.add(new JLabel(filler));
        bottomPanel.add(new JLabel(filler));
        bottomPanel.add(new JLabel(filler));

        // Pack and display
        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // TODO
    }

    public static void main(String args[]) {
        new WahlStudiengangV1();
    }
}