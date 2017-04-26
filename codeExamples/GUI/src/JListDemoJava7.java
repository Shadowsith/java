import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class JListDemoJava7 extends JFrame {
    public JListDemoJava7() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultListModel<String> listModel = new DefaultListModel<String>();
        listModel.addElement("GdP 2 Vorlesung besuchen");
        listModel.addElement("Auf Klausur GdP 2 lernen");
        listModel.addElement("In der Mensa essen");
        listModel.addElement("Kaffee trinken");
        listModel.addElement("Einkaufen gehen");
        listModel.addElement("Baden an der Donau");
        listModel.addElement("Zuhause bleiben und ausschlafen");

        // Create the list and put it in a scroll pane.
        JList<String> list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        list.addListSelectionListener(new AListSelectionListenerJava7());

        // Add the panel to the frame
        this.add(listScrollPane, BorderLayout.CENTER);
        // Pack and display
        this.pack();
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new JListDemoJava7();
    }
}