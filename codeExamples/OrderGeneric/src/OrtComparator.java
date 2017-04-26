import java.util.Comparator;

public class OrtComparator implements Comparator<Adresse> {
  public int compare(Adresse o1, Adresse o2) {
    return o1.getOrt().compareTo(o2.getOrt());
  }
}