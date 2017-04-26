/*
 * Console logger
 * 
 * Depending on log level, prepends string to message:
 * Depending on log level, all messages with a higher level are ignored.
 * Higher level => message is less important
 */

public class ConsoleLogger {
  private byte logLevel = ERROR;

  // Constants for log levels
  public static final byte ERROR = 10;
  public static final byte WARNING = 20;
  public static final byte INFO = 30;
  public static final byte DEBUG = 40;

  public ConsoleLogger() {
    // set default log level
    setLogLevel(ERROR);
  }

  public ConsoleLogger(byte logLevel) {
    setLogLevel(logLevel);
  }

  public void setLogLevel(byte logLevel) {
    this.logLevel = logLevel;
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
}
