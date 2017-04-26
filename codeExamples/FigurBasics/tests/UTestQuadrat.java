import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UTestQuadrat {

  @Test
  public void test_berechneFlaeche_01() {
    Quadrat q = new Quadrat(1.5);
    assertEquals("Flaeche falsch", 2.25, q.berechneFlaeche(), 0.1);
  }

  @Test
  public void test_berechneFlaeche_02() {
    Quadrat q = new Quadrat(0.0);
    assertEquals("Flaeche falsch", 0.0, q.berechneFlaeche(), 0.1);
  }

  @Test
  public void test_passeLaengeAn_01() {
    Quadrat a = new Quadrat(2.);
    Quadrat b = new Quadrat(3.);
    a.passeLaengeAn(b);
    System.out.printf("Seitenlaenge=%f\n", b.getSeitenLaenge());
  }

  @Test
  public void test_uberschreibe_01() {
    Quadrat a = new Quadrat(2.);
    Quadrat b = new Quadrat(3.);
    a.ueberschreibe(b);
    System.out.printf("Seitenlaenge=%f\n", b.getSeitenLaenge());
  }

}
