import java.util.Comparator;

public class OrtStrasseComparator implements Comparator {
  public int compare(Object o1, Object o2) {
    // Cast
    Adresse o1Add = (Adresse) o1;
    Adresse o2Add = (Adresse) o2;

    // Primaer nach Ort, Sekundaer nach Strasse
    int res = o1Add.getOrt().compareTo(o2Add.getOrt());
    if (res != 0) {
      return res;
    } else {
      return o1Add.getStrasse().compareTo(o2Add.getStrasse());
    }
  }
}
