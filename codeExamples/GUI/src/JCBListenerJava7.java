import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class JCBListenerJava7 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> jcb = (JComboBox<String>) e.getSource();
        // Java6:
        // String selected = jcb.getSelectedItem().toString();
        //
        // Java 7: getItemAt() yields appropriate type.
        // Here it is String due to JComboBox<String>
        String selected = jcb.getItemAt(jcb.getSelectedIndex());
        System.out.println("Selektiert: " + selected);
    }
}