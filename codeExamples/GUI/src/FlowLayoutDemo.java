import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FlowLayoutDemo extends JFrame {
    public FlowLayoutDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(new JButton("Button 1"));
        add(new JButton("Button 2"));
        add(new JButton("Button 3"));
        add(new JButton("Long-Named Button 4"));
        add(new JButton("5"));
        pack();
        setVisible(true);
    }

    public static void main(String args[]) {
        new FlowLayoutDemo();
    }
}
