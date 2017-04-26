// Liste von Quadraten mit Raw-Type LinkedList
import java.util.LinkedList;

public class QuadratListeV1 {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        LinkedList quadratList = new LinkedList();
        quadratList.add(new Quadrat(2.0));
        quadratList.add(new Quadrat(3.0));
        quadratList.add(new Quadrat(4.0));
        //quadratList.add("Hello, world!"); // Fehler zur Laufzeit

        for (int i = 0; i < quadratList.size(); ++i) {
            Quadrat q = (Quadrat) quadratList.get(i);  // Cast notwendig
            System.out.println("Quadrat mit Seitenlaenge " + q.getSeitenLaenge());
        }
    }
}
