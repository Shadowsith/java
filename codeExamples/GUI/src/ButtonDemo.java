import javax.swing.JFrame;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonDemo extends JFrame {
    public ButtonDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton quit = new JButton("Beenden");
        this.add(quit);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new ButtonDemo();
    }
}
