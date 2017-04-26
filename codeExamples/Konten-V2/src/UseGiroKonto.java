public class UseGiroKonto {

    public static void main(String args[]) {
        GiroKonto gk1 = new GiroKonto("1111111", 4711, 100.0);
        GiroKonto gk2 = new GiroKonto("2222222", 7973, 0.0);

        gk1.druckeKontoAuszug();
        gk1.zahleEin(1000.0);
        gk1.druckeKontoAuszug();
        gk1.zahleAus(1500.0);
        gk1.druckeKontoAuszug();

        gk2.druckeKontoAuszug();
        gk2.zahleEin(400.0);
        gk2.druckeKontoAuszug();
        gk2.zahleAus(100.0);
        gk2.druckeKontoAuszug();
    }
}
