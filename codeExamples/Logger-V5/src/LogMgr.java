import java.util.ArrayList;
import java.util.List;

public class LogMgr implements Logger {
    private List<Logger> myLoggers;

    public LogMgr() {
        this.myLoggers = new ArrayList<Logger>();

    }
    
    public void attachLogger(Logger logger) {
        myLoggers.add(logger);
    }

    public void detachLogger(Logger logger) {
        int i = myLoggers.indexOf(logger);
        if (i >= 0) {
            myLoggers.remove(i);
        }
    }

    // implement methods of interface Logger
    
    public void logMsg(LogLevel logLevel, String msg) {
        // Use a conventional loop
        for (int i=0; i < myLoggers.size(); i++) {
            Logger logger = myLoggers.get(i);
            logger.logMsg(logLevel, msg);
        }
    }

    public void close() {
        // Use the extended for loop of iterable interface
        for (Logger logger : myLoggers) {
            logger.close();
        }
    }

    public void setLogLevel(LogLevel logLevel) {
        // Use the extended for loop of iterable interface
        for (Logger logger : myLoggers) {
            logger.setLogLevel(logLevel);
        }
    }

    
}
