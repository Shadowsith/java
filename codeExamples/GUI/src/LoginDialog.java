// Example for components and layout
// No action listeners are registered
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginDialog extends JFrame {
    public LoginDialog() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fill the header line
        JLabel header = new JLabel("Anmeldung zum Disaster-Forum",
                JLabel.CENTER);
        this.add(header, BorderLayout.NORTH);

        // Create a central panel
        JPanel centerPanel = new JPanel(new GridLayout(0, 2));

        // Create components of center panel
        JLabel lblBenutzername = new JLabel("Benutzername:", JLabel.RIGHT);
        JLabel lblKennwort = new JLabel("Kennwort:", JLabel.RIGHT);
        JLabel lblSprache = new JLabel("Sprache:", JLabel.RIGHT);

        JTextField tfldBenutzername = new JTextField("");
        JPasswordField tfldKennwort = new JPasswordField("");
        JComboBox jcbSprache = new JComboBox();
        for (String s : "Deutsch, English, Francais".split(",")) {
            jcbSprache.addItem(s);
        }

        // Fill the grid of center panel in correct order
        // Use a filler label
        String filler = "     ";
        // Add fillers above
        centerPanel.add(new JLabel(filler));
        centerPanel.add(new JLabel(filler));

        centerPanel.add(lblBenutzername);
        centerPanel.add(tfldBenutzername);
        centerPanel.add(lblKennwort);
        centerPanel.add(tfldKennwort);
        centerPanel.add(lblSprache);
        centerPanel.add(jcbSprache);

        // Add fillers below
        centerPanel.add(new JLabel(filler));
        centerPanel.add(new JLabel(filler));

        // Add the center panel to the frame
        this.add(centerPanel, BorderLayout.CENTER);

        // Add fillers
        this.add(new JLabel(filler), BorderLayout.EAST);
        this.add(new JLabel(filler), BorderLayout.WEST);

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

    public static void main(String args[]) {
        new LoginDialog();
    }
}