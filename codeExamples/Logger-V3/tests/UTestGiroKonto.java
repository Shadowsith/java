import static org.junit.Assert.*;
import org.junit.Test;

public class UTestGiroKonto {
    @Test
    public void test_zahleAus_01() throws Exception {
        Logger logger = new ConsoleLogger(LogLevel.INFO);
        GiroKonto k = new GiroKonto("11111", 4711, 1000.0, logger);

        k.zahleEin(800.0);
        k.zahleAus(500.0);

        assertEquals("Falscher Saldo", 300.0, k.getKontoStand(), 0.01);
        logger.close();
    }

    @Test
    public void test_zahleAus_02() throws Exception {
        Logger logger = new ConsoleLogger(LogLevel.INFO);
        GiroKonto k = new GiroKonto("11111", 4711, 1000.0, logger);

        k.zahleEin(500.0);
        k.zahleAus(1000.0);

        assertEquals("Falscher Saldo", -500.0, k.getKontoStand(), 0.01);
        logger.close();
    }

    @Test
    public void test_zahleAus_03() throws Exception {
        Logger logger = new ConsoleLogger(LogLevel.INFO);
        GiroKonto k = new GiroKonto("11111", 4711, 1000.0, logger);

        k.zahleEin(500.0);
        // Surpass dispoLimit
        // No money should be drawn!
        k.zahleAus(2000.0);

        assertEquals("Falscher Saldo", 500.0, k.getKontoStand(), 0.01);
        logger.close();
    }
}
