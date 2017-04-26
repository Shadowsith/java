import javax.swing.JComboBox;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class JComboBoxDemoJava7 extends JFrame {
    public JComboBoxDemoJava7() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox<String> jcb = new JComboBox<String>();
        jcb.addActionListener(new JCBListenerJava7());
        // for (String s : "opt1,opt2,opt3,opt4".split(",")) {
        // jcb.addItem(s);
        // }

        jcb.addItem("opt1"); // Hier nur noch String erlaubt
        jcb.addItem("opt2");
        jcb.addItem("opt3");
        jcb.addItem("opt4");

        this.add(jcb);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new JComboBoxDemoJava7();
    }
}
