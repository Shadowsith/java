public class Quadrat {
    private double seitenLaenge;

    public Quadrat(double pSeitenLaenge) {
        seitenLaenge = pSeitenLaenge;
    }

    public void setSeitenLaenge(double seitenLaenge) {
        this.seitenLaenge = seitenLaenge;
    }

    public double getSeitenLaenge() {
        return seitenLaenge;
    }
    
    public double berechneFlaeche() {
        return seitenLaenge * seitenLaenge;
    }

    public double berechneUmfang() {
        return 4 * seitenLaenge;
    }
}
