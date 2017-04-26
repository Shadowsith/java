import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class UTestOrtComparator {
  public static Adresse a1, a2, a3, a4;

  @Before
  public void setup() {
    a1 = new Adresse("Muenchen", 80686, "Valpichlerstr. 67");
    a2 = new Adresse("Ingolstadt", 83456, "Esplanade 13");
    a3 = new Adresse("Muenchen", 81796, "Koenigsplatz 4");
    a4 = new Adresse("Ingolstadt", 84756, "Am Rathausplatz 23");
  }

  @Test
  public void test_OrtComparator_01() {
    OrtComparator oc = new OrtComparator();

    assertTrue("Vergleich nach Ort", oc.compare(a1, a2) > 0);
    assertTrue("Vergleich nach Ort", oc.compare(a1, a1) == 0);
    assertTrue("Vergleich nach Ort", oc.compare(a2, a1) < 0);

    // Gleiche Orte
    assertTrue("Vergleich nach Ort", oc.compare(a1, a3) == 0);
    assertTrue("Vergleich nach Ort", oc.compare(a2, a4) == 0);

  }

  @Test
  public void test_OrtComparator_10() throws Exception {
    OrtComparator oc = new OrtComparator();
    try {
      oc.compare(null, a1);
      fail("Erwarte NullPointerException");
    } catch (NullPointerException e) {
      // Expected
    }
  }

  @Test
  public void test_OrtComparator_11() throws Exception {
    OrtComparator oc = new OrtComparator();
    try {
      oc.compare(a1, null);
      fail("Erwarte NullPointerException");
    } catch (NullPointerException e) {
      // Expected
    }
  }

  @Test
  public void test_OrtComparator_12() throws Exception {
    OrtComparator oc = new OrtComparator();
    try {
      oc.compare(null, null);
      fail("Erwarte NullPointerException");
    } catch (NullPointerException e) {
      // Expected
    }
  }

  @Test
  public void test_OrtComparator_20() {
    // Man kann normale Arrays nicht per Comparator sortieren!
    // Daher verwenden wir hier eine (raw) Collection
    List alist = new ArrayList();
    alist.add(a1);
    alist.add(a2);
    alist.add(a3);
    alist.add(a4);

    OrtComparator oc = new OrtComparator();

    Collections.sort(alist, oc); // Sortiere Liste gemaess Comparator

    for (Object a : alist) {
      System.out.println(a); // was passiert hier?
    }

  }

}
