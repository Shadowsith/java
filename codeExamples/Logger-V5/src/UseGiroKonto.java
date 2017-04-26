public class UseGiroKonto {
    public static void main(String args[]) {
        Logger consLogger = new ConsoleLogger(LogLevel.ERROR);

        Logger fileLoggerError   = new FileLogger(LogLevel.ERROR,"error.log");
        Logger fileLoggerWarning = new FileLogger(LogLevel.WARNING,"warning.log");
        Logger fileLoggerInfo    = new FileLogger(LogLevel.INFO,"info.log");

        LogMgr logManager = new LogMgr();
        
        logManager.attachLogger(consLogger);
        
        // wrap the file loggers such that only messages of one level are passed
        logManager.attachLogger(new LogFilter(fileLoggerError,LogLevel.ERROR));
        logManager.attachLogger(new LogFilter(fileLoggerWarning,LogLevel.WARNING));
        logManager.attachLogger(new LogFilter(fileLoggerInfo,LogLevel.INFO));

        GiroKonto gk1 = new GiroKonto("1111111", 4711, 100.0,logManager);
        GiroKonto gk2 = new GiroKonto("2222222", 7973, 0.0,logManager);
        
        gk1.zahleEin(1000.0);
        gk1.zahleAus(1500.0);

        gk2.zahleEin(400.0);
        gk2.zahleAus(100.0);

        logManager.close();
    }
}
