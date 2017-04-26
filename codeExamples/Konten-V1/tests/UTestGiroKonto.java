import static org.junit.Assert.*;
import org.junit.Test;

public class UTestGiroKonto {
  @Test
  public void test_zahleAus_01() throws Exception {
    GiroKonto k = new GiroKonto("716578", 4711);

    k.zahleEin(800.0);
    k.zahleAus(500.0);

    assertEquals("Falscher Saldo", 300.0, k.getKontoStand(), 0.01);

  }

  @Test
  public void test_zahleAus_02() throws Exception {
    GiroKonto k = new GiroKonto("716578", 4711);

    k.zahleEin(500.0);
    k.zahleAus(1000.0);

    assertEquals("Falscher Saldo", -500.0, k.getKontoStand(), 0.01);

  }

}
