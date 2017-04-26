import javax.swing.JComboBox;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class JComboBoxDemo extends JFrame {
    public JComboBoxDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox jcb = new JComboBox();
        jcb.addActionListener(new JCBListener());
        // for (String s : "opt1,opt2,opt3,opt4".split(",")) {
        // jcb.addItem(s);
        // }

        jcb.addItem("opt1");
        jcb.addItem("opt2");
        jcb.addItem("opt3");
        jcb.addItem("opt4");

        this.add(jcb);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new JComboBoxDemo();
    }
}
