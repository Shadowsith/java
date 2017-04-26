public class UseQuadrat {
  public static void main(String[] args) {
    Quadrat q1;
    q1 = new Quadrat(2.0);

    double flVorher;
    flVorher = q1.berechneFlaeche();

    q1.setSeitenLaenge(4.0);
    double flNacher = q1.berechneFlaeche();

    System.out.println("Flaechendifferenz = " + (flNacher - flVorher));
  }
}
