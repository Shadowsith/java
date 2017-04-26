import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class FormatTool extends JFrame {
    public FormatTool() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Formatting Tool v0.01 alpha");

        JPanel options = new JPanel();
        options.setLayout(new GridLayout(2, 1));
        add(options, BorderLayout.WEST);
        options.add(new JLabel("Format hard disk?", JLabel.CENTER));

        JPanel rbuttons = new JPanel();
        rbuttons.setLayout(new FlowLayout());
        options.add(rbuttons);

        ButtonGroup modeGroup = new ButtonGroup();
        JRadioButton rbFast = new JRadioButton("Fast", true);
        modeGroup.add(rbFast);
        rbuttons.add(rbFast);

        JRadioButton rbReliable = new JRadioButton("Reliable", false);
        modeGroup.add(rbReliable);
        rbuttons.add(rbReliable);

        JRadioButton rbOther = new JRadioButton("Other", false);
        modeGroup.add(rbOther);
        rbuttons.add(rbOther);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(3, 1));
        add(buttons, BorderLayout.EAST);
        buttons.add(new JButton("Now"));
        buttons.add(new JButton("Later"));
        buttons.add(new JButton("Don't know"));
        pack();
    }

    public static void main(String args[]) {
        FormatTool ft = new FormatTool();
        ft.setVisible(true);
    }

}
