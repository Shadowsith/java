public class GiroKonto {
  private String kontoNummer;
  private double kontoStand;
  private int geheimZahl;

  public GiroKonto(String kNum, int gZahl) {
    kontoNummer = kNum;
    kontoStand = 0.0;
    geheimZahl = gZahl;
  }

  public void zahleEin(double betrag) {
    kontoStand += betrag;
  }

  public void zahleAus(double betrag) {
    kontoStand -= betrag;
  }

  public double getKontoStand() {
    return kontoStand;
  }

  public String getKontoNummer() {
    return kontoNummer;
  }

  public void druckeKontoAuszug() {
    System.out.printf("#------------------\n");
    System.out.printf("Kontonummer: %s\n", kontoNummer);
    System.out.printf("Kontostand: %8.2f\n", kontoStand);
    System.out.printf("\n");
  }
}
