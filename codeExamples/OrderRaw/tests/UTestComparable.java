import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class UTestComparable {
  public static Adresse a1, a2, a3, a4;

  @Before
  public void setup() {
    a1 = new Adresse("Muenchen", 80686, "Valpichlerstr. 67");
    a2 = new Adresse("Ingolstadt", 83456, "Esplanade 13");
    a3 = new Adresse("Muenchen", 81796, "Koenigsplatz 4");
    a4 = new Adresse("Ingolstadt", 84756, "Am Rathausplatz 23");
  }

  @Test
  public void test_comparable_01() {
    assertTrue("Vergleich nach PLZ", a1.compareTo(a2) < 0);
    assertTrue("Vergleich nach PLZ", a1.compareTo(a1) == 0);
    assertTrue("Vergleich nach PLZ", a2.compareTo(a1) > 0);
  }

  @Test
  public void test_comparable_02() throws Exception {
    try {
      a1.compareTo(null);
      fail("Erwarte NullPointerException");
    } catch (NullPointerException e) {
      // Expected
    }
  }

  @Test
  public void test_comparable_03() throws Exception {
    try {
      a1.compareTo("Bla");
      fail("Erwarte ClasCastException");
    } catch (ClassCastException e) {
      // Expected
    }
  }

  @Test
  public void test_comparable_10() {
    Adresse[] field = new Adresse[] { a1, a2, a3, a4 };

    Arrays.sort(field); // Sortiere Array gemaess Standard-Ordnung

    for (Adresse a : field) {
      System.out.println(a);
    }

  }

  @Test
  public void test_comparable_11() {
    Adresse[] field = new Adresse[] { a1, a2, a3, a4 };

    Arrays.sort(field); // Sortiere Array gemaess Standard-Ordnung

    Adresse[] expField = new Adresse[] { a1, a3, a2, a4 };
    assertArrayEquals("Falsche Sortierung nach PLZ ", expField, field);
  }
}
