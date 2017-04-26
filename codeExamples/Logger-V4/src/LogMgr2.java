// Solution uses inheritance
// Class LogMgr is a list and stores the loggers 
import java.util.ArrayList;

@SuppressWarnings("serial")
public class LogMgr2 extends ArrayList<Logger> implements Logger {

   
    public void attachLogger(Logger logger) {
        this.add(logger);
    }

    public void detachLogger(Logger logger) {
        int i = this.indexOf(logger);
        if (i >= 0) {
            this.remove(i);
        }
    }

    // implement methods of interface Logger
    
    public void logMsg(LogLevel logLevel, String msg) {
        // Use a conventional loop
        for (int i=0; i < this.size(); i++) {
            Logger logger = this.get(i);
            logger.logMsg(logLevel, msg);
        }
    }

    public void close() {
        // Use the extended for loop of iterable interface
        for (Logger logger : this) {
            logger.close();
        }
    }

    public void setLogLevel(LogLevel logLevel) {
        // Use the extended for loop of iterable interface
        for (Logger logger : this) {
            logger.setLogLevel(logLevel);
        }
    }

    
}
