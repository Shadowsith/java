public class Quadrat {
  private double seitenLaenge;

  // Konstruktor
  public Quadrat(double pSeitenLaenge) {
    seitenLaenge = pSeitenLaenge;
  }

  public void setSeitenLaenge(double pSeitenLaenge) {
    seitenLaenge = pSeitenLaenge;
  }

  public double berechneFlaeche() {
    return seitenLaenge * seitenLaenge;
  }

  public double getSeitenLaenge() {
    return seitenLaenge;
  }

  public void passeLaengeAn(Quadrat p) {
    p.seitenLaenge = seitenLaenge;
  }

  public void ueberschreibe(Quadrat p) {
    p = this;
  }

}
