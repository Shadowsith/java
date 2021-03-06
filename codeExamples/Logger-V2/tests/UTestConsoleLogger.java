import org.junit.Ignore;
import org.junit.Test;

public class UTestConsoleLogger {
    @Test
    public void test_logMsg_01() throws Exception {
        ConsoleLogger log1 = new ConsoleLogger(AbstractLogger.DEBUG);
        int n = 10;
        int z = 5;
        String prefix = "logMsg_01: ";

        log1.logMsg(AbstractLogger.DEBUG, prefix + "Starting computation");
        if (z == 0) {
            log1.logMsg(AbstractLogger.ERROR, prefix + "Division by zero!");
        } else {
            log1.logMsg(AbstractLogger.INFO, prefix + n + " / " + z
                    + " = " + n / z);
        }
        log1.logMsg(AbstractLogger.DEBUG, "logMsg_01: Finished computation");
    }

    @Test
    public void test_logMsg_02() throws Exception {
        ConsoleLogger log1 = new ConsoleLogger(AbstractLogger.DEBUG);
        int n = 10;
        int z = 0;
        String prefix = "logMsg_02: ";

        log1.logMsg(AbstractLogger.DEBUG, prefix + "Starting computation");
        if (z == 0) {
            log1.logMsg(AbstractLogger.ERROR, prefix + "Division by zero!");
        } else {
            log1.logMsg(AbstractLogger.INFO, prefix + + n + " / " + z
                    + " = " + n / z);
        }
        log1.logMsg(AbstractLogger.DEBUG, prefix + "Finished computation");
    }

    @Test
    public void test_logMsg_03() throws Exception {
        ConsoleLogger log1 = new ConsoleLogger(AbstractLogger.INFO);
        int n = 10;
        int z = 5;
        String prefix = "logMsg_03: ";

        log1.logMsg(AbstractLogger.DEBUG, prefix + "Starting computation");
        if (z == 0) {
            log1.logMsg(AbstractLogger.ERROR, prefix + "Division by zero!");
        } else {
            log1.logMsg(AbstractLogger.INFO, prefix + n + " / " + z
                    + " = " + n / z);
        }
        log1.logMsg(AbstractLogger.DEBUG, prefix + "Finished computation");
    }

    @Test
    public void test_logMsg_04() throws Exception {
        ConsoleLogger log1 = new ConsoleLogger(AbstractLogger.INFO);
        int n = 10;
        int z = 0;
        String prefix = "logMsg_04: ";

        log1.logMsg(AbstractLogger.DEBUG, prefix + "Starting computation");
        if (z == 0) {
            log1.logMsg(AbstractLogger.ERROR, prefix + "Division by zero!");
        } else {
            log1.logMsg(AbstractLogger.INFO, prefix + n + " / " + z
                    + " = " + n / z);
        }
        log1.logMsg(AbstractLogger.DEBUG, prefix + "Finished computation");
    }
}

