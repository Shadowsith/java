public class UseGiroKonto {
    public static void main(String args[]) {
        Logger consLogger = new ConsoleLogger(LogLevel.ERROR);
        Logger fileLogger = new FileLogger(LogLevel.INFO);
        LogMgr logManager = new LogMgr(); // manager that uses composition
        //LogMgr2 logManager = new LogMgr2(); // manager that uses inheritance
        
        logManager.attachLogger(consLogger);
        logManager.attachLogger(fileLogger);

        GiroKonto gk1 = new GiroKonto("1111111", 4711, 100.0,logManager);
        GiroKonto gk2 = new GiroKonto("2222222", 7973, 0.0,logManager);
        
        gk1.zahleEin(1000.0);
        gk1.zahleAus(1500.0);

        gk2.zahleEin(400.0);
        gk2.zahleAus(100.0);

        logManager.close();
    }
}
