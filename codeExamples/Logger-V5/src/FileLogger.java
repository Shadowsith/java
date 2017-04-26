import java.io.FileWriter;
import java.io.IOException;

/*
 * File logger: logs to a file in append mode
 * 
 * Depending on log level, prepends string to message:
 * Depending on log level, all messages with a higher level are ignored.
 * Higher level => message is less important
 */

public class FileLogger extends AbstractLogger {
    private final String DEFAULT_LOG_FILE_NAME = "logfile.log";
    private String logFileName;
    private FileWriter logWriter;

    public FileLogger() {
        super();
        this.logFileName = DEFAULT_LOG_FILE_NAME;
        openLogWriter();
    }

    public FileLogger(LogLevel logLevel) {
        super(logLevel);
        this.logFileName = DEFAULT_LOG_FILE_NAME;
        openLogWriter();
    }

    public FileLogger(LogLevel logLevel, String logFileName) {
        super(logLevel);
        this.logFileName = logFileName;
        openLogWriter();
    }

    private void openLogWriter() {
        logWriter = null;
        try {
            // Create a FileWriter
            // Open in append mode
            logWriter = new FileWriter(logFileName, true); 
            // Since this worked out we know that the file is writable
        } catch (IOException e) {
            throw new RuntimeException("Unable to open logfile \""
                    + logFileName + "\"", e);
        }
    }

    public void close() {
        try {
            // Close the Writer
            logWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to close logfile \""
                    + logFileName + "\"", e);
        }
    }

    public void logMsg(LogLevel level, String msg) {
        // log everything up to logLevel
        if (level.compareTo(logLevel) <= 0) {
            try {
                logWriter.write(prependLevel(level,msg) + "\n");
            } catch (IOException e) {
                throw new RuntimeException("Unable to log to logfile \""
                        + logFileName + "\"", e);
            }
        }
    }
}
