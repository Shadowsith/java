import java.util.Comparator;

public class OrtComparator implements Comparator {
  public int compare(Object o1, Object o2) {
    // Cast
    Adresse o1Add = (Adresse) o1;
    Adresse o2Add = (Adresse) o2;

    return o1Add.getOrt().compareTo(o2Add.getOrt());
  }
}
