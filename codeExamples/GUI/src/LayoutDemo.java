import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LayoutDemo extends JFrame {
    public LayoutDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Wir benutzen auch den Header
        this.setTitle("Winterurlaub in Kanada");

        // NORTH
        JPanel np = new JPanel(new GridLayout(0, 4)); // FlowLayout ist default
        np.setBackground(Color.lightGray);
        this.add(np, BorderLayout.NORTH);

        np.add(new JLabel("Anreise"));
        np.add(new JLabel("Unterkunft"));
        np.add(new JLabel("Events"));
        np.add(new JLabel("Finanzierung"));

        // Fuer die vier Saeulen ein inner Panel ip im CENTER
        JPanel ip = new JPanel(new GridLayout(0, 4));
        this.add(ip); // CENTER ist default

        // Die einzelnen Saeulen
        JPanel ipA = new JPanel(new GridLayout(3, 0));
        JPanel ipU = new JPanel(new GridLayout(5, 0));
        JPanel ipE = new JPanel(new GridLayout(3, 0));
        JPanel ipB = new JPanel(new GridLayout(4, 0));
        ip.add(ipA);
        ip.add(ipU);
        ip.add(ipE);
        ip.add(ipB);

        // ipA
        JRadioButton rbA1 = new JRadioButton("Schiff");
        JRadioButton rbA2 = new JRadioButton("Flugzeug");
        JRadioButton rbA3 = new JRadioButton("Beamen");
        ipA.add(rbA1);
        ipA.add(rbA2);
        ipA.add(rbA3);
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(rbA1);
        bg1.add(rbA2);
        bg1.add(rbA3);

        // ipU
        JCheckBox cbU1 = new JCheckBox("Fruestueck");
        JCheckBox cbU2 = new JCheckBox("Lunch-Paket");
        JCheckBox cbU3 = new JCheckBox("Abend");
        JCheckBox cbU4 = new JCheckBox("Bar-Flat");
        JCheckBox cbU5 = new JCheckBox("Fitness-Studio");
        ipU.add(cbU1);
        ipU.add(cbU2);
        ipU.add(cbU3);
        ipU.add(cbU4);
        ipU.add(cbU5);

        // ipE
        JCheckBox cbE1 = new JCheckBox("Heli Ski");
        JCheckBox cbE2 = new JCheckBox("Midnight Dinner");
        JCheckBox cbE3 = new JCheckBox("Schlittenrennen");
        ipE.add(cbE1);
        ipE.add(cbE2);
        ipE.add(cbE3);

        // ipB
        JRadioButton rbB1 = new JRadioButton("Bank ausrauben");
        // rbB1.setHorizontalAlignment(JRadioButton.CENTER);
        JRadioButton rbB2 = new JRadioButton("Lotto spielen");
        JRadioButton rbB3 = new JRadioButton("Darlehen aufnehmen");
        JRadioButton rbB4 = new JRadioButton("abarbeiten");
        ipB.add(rbB1);
        ipB.add(rbB2);
        ipB.add(rbB3);
        ipB.add(rbB4);
        ButtonGroup bg3 = new ButtonGroup();
        bg3.add(rbB1);
        bg3.add(rbB2);
        bg3.add(rbB3);
        bg3.add(rbB4);

        // Im Sueden
        JPanel sp = new JPanel(); // FlowLayout ist default
        this.add(sp, BorderLayout.SOUTH);

        sp.add(new JButton("OK"));
        sp.add(new JButton("Abbrechen"));

        pack();
        setVisible(true);
    }

    static public void main(String[] args) {
        new LayoutDemo();
    }
}
