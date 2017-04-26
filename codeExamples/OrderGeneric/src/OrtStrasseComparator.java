import java.util.Comparator;

public class OrtStrasseComparator implements Comparator<Adresse> {
  public int compare(Adresse o1, Adresse o2) {
    // Primaer nach Ort, Sekundaer nach Strasse
    int res = o1.getOrt().compareTo(o2.getOrt());
    if (res != 0) {
      return res;
    } else {
      return o1.getStrasse().compareTo(o2.getStrasse());
    }
  }
}
