import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ButtonGrid extends JFrame {
    public ButtonGrid() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLayout(new GridLayout(3, 2));
        setLayout(new GridLayout(3, 0));
        add(new JButton("Button 1"));
        add(new JButton("Button 2"));
        add(new JButton("Button 3"));
        add(new JButton("Long-Named Button 4"));
        add(new JButton("5"));
        pack();
    }

    public static void main(String args[]) {
        ButtonGrid bg = new ButtonGrid();
        bg.setVisible(true);
    }
}
