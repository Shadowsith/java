// Enhanced method zahleAus()
// However, lousy error handling

public class GiroKonto {
    private String kontoNummer;
    private double kontoStand;
    private int geheimZahl;
    private double dispoLimit;

    public GiroKonto(String kontoNummer, int geheimZahl, double dispoLimit) {
        this.kontoNummer = kontoNummer;
        this.kontoStand = 0.0;
        this.geheimZahl = geheimZahl;
        this.dispoLimit = dispoLimit;
    }

    public void zahleEin(double betrag) {
        kontoStand += betrag;
    }

    public void zahleAus(double betrag) {
        if (kontoStand + dispoLimit >= betrag) {
            kontoStand -= betrag;
        } else {
            System.out.printf("GiroKonto %s: ",kontoNummer);
            System.out.printf("Versuch, Dispolimit zu ueberziehen\n");
            System.out.printf("  Kontostand: %8.2f\n", kontoStand);
            System.out.printf("  Dispolimit: %8.2f\n", dispoLimit);
            System.out.printf("      Betrag: %8.2f\n", betrag);
            System.out.printf("Ueberziehung: %8.2f\n", betrag - kontoStand
                    - dispoLimit);
        }
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
