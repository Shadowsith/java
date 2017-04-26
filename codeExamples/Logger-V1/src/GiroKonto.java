// Use AbtractLogger
public class GiroKonto {
  private String kontoNummer;
  private double kontoStand;
  private int geheimZahl;
  private double dispoLimit;

  private AbstractLogger logger;

  public GiroKonto(String kontoNummer, int geheimZahl, double dispoLimit,
      AbstractLogger logger) {
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
    logger.logMsg(AbstractLogger.INFO, msg);
  }

  public void zahleAus(double betrag) {
    if (kontoStand + dispoLimit >= betrag) {
      kontoStand -= betrag;

      String msg;
      msg = String.format("GiroKonto %s: ", kontoNummer);
      msg += String.format("Auszahlung: %8.2f", betrag);
      msg += String.format(" Kontostand: %8.2f", kontoStand);
      logger.logMsg(AbstractLogger.INFO, msg);

    } else {
      byte level = AbstractLogger.WARNING;
      logger.logMsg(level, String.format("GiroKonto %s: ", kontoNummer));
      logger.logMsg(level, String.format("Versuch, Dispolimit zu ueberziehen!"));
      logger.logMsg(level, String.format("  Kontostand: %8.2f", kontoStand));
      logger.logMsg(level, String.format("  Dispolimit: %8.2f", dispoLimit));
      logger.logMsg(level, String.format("      Betrag: %8.2f", betrag));
      logger.logMsg(level, String.format("Ueberziehung: %8.2f",
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
