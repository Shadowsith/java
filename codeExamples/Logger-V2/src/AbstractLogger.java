/*
 * Abstract class for a logging mechanism.
 * Defines log levels and interface
 */

public abstract class AbstractLogger {
    protected byte logLevel = ERROR;

    // Constants for log levels
    public static final byte ERROR = 10;
    public static final byte WARNING = 20;
    public static final byte INFO = 30;
    public static final byte DEBUG = 40;

    public AbstractLogger() {
        // set default log level
        setLogLevel(ERROR);
    }

    public AbstractLogger(byte logLevel) {
        setLogLevel(logLevel);
    }

    public void setLogLevel(byte logLevel) {
        this.logLevel = logLevel;
    }
    
    // Helper for derived classes
    // Prepend the log level to the message
    protected String prependLevel(byte logLevel, String msg) {
        String logMsg = "";
        switch (logLevel) {
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
        return logMsg;
    }
    
    // Abstract methods to be implemented by non-abstract derived classes

    // Digest a log message, whatever digest may be in the implementation
    // E.g. print to console or to some file 
    public abstract void logMsg(byte level, String msg);
    
    // Close the log stream (e.g a file handle)
    // Should be called by every client of the class AbstractLogger
    public abstract void close();
}
