import java.util.Comparator;

public class UmsatzComparator implements Comparator<ArtikelUmsatz>{
    @Override
    public int compare(ArtikelUmsatz o1, ArtikelUmsatz o2) {
        return Double.compare(o1.berechneUmsatz(), o2.berechneUmsatz());
    }
}
