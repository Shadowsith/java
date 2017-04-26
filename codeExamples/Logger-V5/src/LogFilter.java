// Wrap a Logger and only pass it messages of a certain level
public class LogFilter implements Logger {
        private Logger myLogger;
        private LogLevel myLogLevel;
        
    public LogFilter(Logger logger, LogLevel level) {
        this.myLogger = logger;
        this.myLogLevel = level;
    }

    // implement methods of interface Logger
    
    public void logMsg(LogLevel logLevel, String msg) {
        // Filter messages
        // Only pass messages with our logLevel to logger
        if (logLevel.compareTo(myLogLevel) == 0) {
            myLogger.logMsg(logLevel, msg);
        }
    }

    public void close() {
        myLogger.close();
    }
    
    public void setLogLevel(LogLevel logLevel) {
         myLogger.setLogLevel(logLevel);
    }
}
