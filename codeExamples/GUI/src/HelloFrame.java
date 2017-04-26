import javax.swing.JFrame;

@SuppressWarnings("serial")
public class HelloFrame extends JFrame {
    public HelloFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Hallo!");
        this.setSize(200, 200);
    }

    public static void main(String[] args) {
        HelloFrame hf = new HelloFrame();
        hf.setVisible(true);
    }
}
