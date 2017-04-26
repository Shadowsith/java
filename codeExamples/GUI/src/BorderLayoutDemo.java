import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class BorderLayoutDemo extends JFrame {
    public BorderLayoutDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new JButton("B1 in the North"), BorderLayout.NORTH);
        add(new JButton("Button 2 on Center Court ")); // Default CENTER
        add(new JButton("B3 West"), BorderLayout.WEST);
        add(new JButton("Long-Named Button 4 in the South"), BorderLayout.SOUTH);
        add(new JButton("B5 East"), BorderLayout.EAST);
        pack();
    }

    public static void main(String args[]) {
        BorderLayoutDemo bg = new BorderLayoutDemo();
        bg.setVisible(true);
    }
}
