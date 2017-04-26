import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class JCBListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JComboBox jcb = (JComboBox) e.getSource();
        String selected = jcb.getSelectedItem().toString();
        System.out.println("Selektiert: " + selected);
    }
}