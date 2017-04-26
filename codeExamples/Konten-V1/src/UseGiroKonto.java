public class UseGiroKonto {

  public static void main(String args[]) {
    GiroKonto gk1;

    gk1 = new GiroKonto("070781", 4711);

    gk1.druckeKontoAuszug();

    gk1.zahleEin(1000.0);
    gk1.druckeKontoAuszug();

    gk1.zahleAus(1500.0);
    gk1.druckeKontoAuszug();
  }
}
