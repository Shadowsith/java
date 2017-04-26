import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
class ButtonDemo2 extends JFrame implements ActionListener {
    public ButtonDemo2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton quit = new JButton("Beenden");
        quit.addActionListener(this);
        this.add(quit);
        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public static void main(String args[]) {
        new ButtonDemo2();
    }
}