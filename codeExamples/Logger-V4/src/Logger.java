
public interface Logger {
    // Digest a log message, whatever digest may be in the implementation
    // E.g. print to console or to some file 
    void logMsg(LogLevel logLevel, String msg);
    
    // Close the log stream (e.g a file handle)
    // Should be called by any client of the class AbstractLogger
    void close();
    
    // Set log level of logger
    void setLogLevel(LogLevel logLevel);
}
