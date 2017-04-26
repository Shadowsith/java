import java.util.Comparator;
public class StueckComparator implements Comparator<ArtikelUmsatz> {
    @Override
    public int compare(ArtikelUmsatz o1, ArtikelUmsatz o2) {
        return o1.getStueck() - o2.getStueck();
    }
}
