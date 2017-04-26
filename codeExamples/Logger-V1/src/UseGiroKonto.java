public class UseGiroKonto {
    public static void main(String args[]) {
        AbstractLogger logger = new ConsoleLogger(AbstractLogger.INFO);
        //AbstractLogger logger = new ConsoleLogger(AbstractLogger.ERROR);
        //AbstractLogger logger = new FileLogger(AbstractLogger.INFO);
        
        GiroKonto gk1 = new GiroKonto("1111111", 4711, 100.0,logger);
        GiroKonto gk2 = new GiroKonto("2222222", 7973, 0.0,logger);
        
        gk1.zahleEin(1000.0);
        gk1.zahleAus(1500.0);

        gk2.zahleEin(400.0);
        gk2.zahleAus(100.0);

        logger.close();
    }
}
