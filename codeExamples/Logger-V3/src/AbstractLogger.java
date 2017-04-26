/*
 * Abstract class for a logging mechanism.
 */

public abstract class AbstractLogger implements Logger {
    protected LogLevel logLevel = LogLevel.ERROR;


    public AbstractLogger() {
        // set default log level
        setLogLevel(LogLevel.ERROR);
    }

    public AbstractLogger(LogLevel logLevel) {
        setLogLevel(logLevel);
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
    
    // Helper for derived classes
    // Prepend the log level to the message
    protected String prependLevel(LogLevel logLevel, String msg) {
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
    public abstract void logMsg(LogLevel level, String msg);
    
    // Close the log stream (e.g a file handle)
    // Should be called by every client of the class AbstractLogger
    public abstract void close();
}
