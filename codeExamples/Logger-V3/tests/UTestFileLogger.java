import org.junit.Ignore;
import org.junit.Test;

public class UTestFileLogger {
    @Test
    public void test_logMsg_01() throws Exception {
        FileLogger log1 = new FileLogger(LogLevel.DEBUG);
        int n = 10;
        int z = 5;
        String prefix = "logMsg_01: ";

        log1.logMsg(LogLevel.DEBUG, prefix + "Starting computation");
        if (z == 0) {
            log1.logMsg(LogLevel.ERROR, prefix + "Division by zero!");
        } else {
            log1.logMsg(LogLevel.INFO, prefix + n + " / " + z
                    + " = " + n / z);
        }
        log1.logMsg(LogLevel.DEBUG, "logMsg_01: Finished computation");
        log1.close();
    }

    @Test
    public void test_logMsg_02() throws Exception {
        FileLogger log1 = new FileLogger(LogLevel.DEBUG);
        int n = 10;
        int z = 0;
        String prefix = "logMsg_02: ";

        log1.logMsg(LogLevel.DEBUG, prefix + "Starting computation");
        if (z == 0) {
            log1.logMsg(LogLevel.ERROR, prefix + "Division by zero!");
        } else {
            log1.logMsg(LogLevel.INFO, prefix + + n + " / " + z
                    + " = " + n / z);
        }
        log1.logMsg(LogLevel.DEBUG, prefix + "Finished computation");
        log1.close();
    }

    @Test
    public void test_logMsg_03() throws Exception {
        FileLogger log1 = new FileLogger(LogLevel.INFO);
        int n = 10;
        int z = 5;
        String prefix = "logMsg_03: ";

        log1.logMsg(LogLevel.DEBUG, prefix + "Starting computation");
        if (z == 0) {
            log1.logMsg(LogLevel.ERROR, prefix + "Division by zero!");
        } else {
            log1.logMsg(LogLevel.INFO, prefix + n + " / " + z
                    + " = " + n / z);
        }
        log1.logMsg(LogLevel.DEBUG, prefix + "Finished computation");
        log1.close();
    }

    @Test
    public void test_logMsg_04() throws Exception {
        FileLogger log1 = new FileLogger(LogLevel.INFO);
        int n = 10;
        int z = 0;
        String prefix = "logMsg_04: ";

        log1.logMsg(LogLevel.DEBUG, prefix + "Starting computation");
        if (z == 0) {
            log1.logMsg(LogLevel.ERROR, prefix + "Division by zero!");
        } else {
            log1.logMsg(LogLevel.INFO, prefix + n + " / " + z
                    + " = " + n / z);
        }
        log1.logMsg(LogLevel.DEBUG, prefix + "Finished computation");
        log1.close();
    }

}
