/*
 * Console logger
 * 
 * Depending on log level, prepends string to message:
 * Depending on log level, all messages with a higher level are ignored.
 * Higher level => message is less important
 */

public class ConsoleLogger extends AbstractLogger {
    public ConsoleLogger() {
        super();
    }

    public ConsoleLogger(byte logLevel) {
        super(logLevel);
    }

    public void logMsg(byte level, String msg) {
        String logMsg = "";

        switch (level) {
        case ERROR:
            logMsg = "ERROR  :" + msg;
            break;
        case WARNING:
            logMsg = "WARNING:" + msg;
            break;
        case INFO:
            logMsg = "INFO   :" + msg;
            break;
        case DEBUG:
            logMsg = "DEBUG  :" + msg;
            break;
        }

        // log everything up to logLevel
        if (level <= logLevel) {
            System.out.println(logMsg);
        }
    }
    
    public void close() {
        // Nothing to do for a console logger
    }
}
