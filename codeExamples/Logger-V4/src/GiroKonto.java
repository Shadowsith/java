// Use AbtractLogger from project Logger-V2
public class GiroKonto {
    private String kontoNummer;
    private double kontoStand;
    private int geheimZahl;
    private double dispoLimit;

    private Logger logger;

    public GiroKonto(String kontoNummer, int geheimZahl, double dispoLimit,
            Logger logger) {
        if (logger == null) {
            throw new RuntimeException("No logger provided");
        }
        this.logger = logger;
        this.kontoNummer = kontoNummer;
        this.kontoStand = 0.0;
        this.geheimZahl = geheimZahl;
        this.dispoLimit = dispoLimit;
    }

    public void zahleEin(double betrag) {
        kontoStand += betrag;

        String msg;
        msg = String.format("GiroKonto %s: ", kontoNummer);
        msg += String.format("Einzahlung: %8.2f", betrag);
        msg += String.format(" Kontostand: %8.2f", kontoStand);
        logger.logMsg(LogLevel.INFO, msg);
    }

    public void zahleAus(double betrag) {
        if (kontoStand + dispoLimit >= betrag) {
            kontoStand -= betrag;

            String msg;
            msg = String.format("GiroKonto %s: ", kontoNummer);
            msg += String.format("Auszahlung: %8.2f", betrag);
            msg += String.format(" Kontostand: %8.2f", kontoStand);
            logger.logMsg(LogLevel.INFO, msg);

        } else {
            logger.logMsg(LogLevel.WARNING, String.format("GiroKonto %s: ", kontoNummer));
            logger.logMsg(LogLevel.WARNING, String.format("Versuch, Dispolimit zu überziehen!"));
            logger.logMsg(LogLevel.WARNING, String.format(" Kontostand: %8.2f", kontoStand));
            logger.logMsg(LogLevel.WARNING, String.format(" Dispolimit: %8.2f", dispoLimit));
            logger.logMsg(LogLevel.WARNING, String.format("     Betrag: %8.2f", betrag));
            logger.logMsg(LogLevel.WARNING, String.format("Überziehung: %8.2f",
                    betrag - kontoStand - dispoLimit));
        }
    }

    public double getKontoStand() {
        return kontoStand;
    }

    public String getKontoNummer() {
        return kontoNummer;
    }

}
