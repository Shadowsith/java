// Liste von Quadraten mit Instanz der generischen LinkedList<T>
import java.util.LinkedList;

public class QuadratListeV2 {
    public static void main(String[] args) {
        LinkedList<Quadrat> quadratList = new LinkedList<Quadrat>();
        quadratList.add(new Quadrat(2.0));
        quadratList.add(new Quadrat(3.0));
        quadratList.add(new Quadrat(4.0));
        // quadratList.add("Hello, world!"); //Fehler bei Uebersetzung

        for (int i = 0; i < quadratList.size(); ++i) {
            Quadrat q = quadratList.get(i); // kein Cast mehr notwendig
            System.out.println("Quadrat mit Seitenlaenge " + q.getSeitenLaenge());
        }
    }
}
