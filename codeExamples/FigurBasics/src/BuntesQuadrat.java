public class BuntesQuadrat extends Quadrat {
  private String farbe;

  public BuntesQuadrat(double seitenLaenge, String farbe) {
    super(seitenLaenge); // muss erste Anweisung sein!
    this.farbe = farbe;
  }
}
