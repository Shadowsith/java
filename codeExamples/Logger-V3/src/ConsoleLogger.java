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

    public ConsoleLogger(LogLevel logLevel) {
        super(logLevel);
    }

    public void logMsg(LogLevel level, String msg) {
        // log everything up to logLevel
        // Note: operation <= not defined on enum types
       if (level.compareTo(logLevel) <= 0) {
            System.out.print(prependLevel(level,msg) + "\n");
        }
    }
    
    public void close() {
        // Nothing to do for a console logger
    }
}
