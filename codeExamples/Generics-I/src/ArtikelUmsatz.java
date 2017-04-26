
public class ArtikelUmsatz implements Comparable<ArtikelUmsatz>{
    private String artikel;
    private double preis;
    private int stueck;
    
    
    public ArtikelUmsatz(String artikel, double preis, int stueck) {
        this.artikel = artikel;
        this.preis = preis;
        this.stueck = stueck;
    }
    
    public void setArtikel(String artikel) {
        this.artikel = artikel;
    }
    public String getArtikel() {
        return artikel;
    }
    public void setPreis(double preis) {
        this.preis = preis;
    }
    public double getPreis() {
        return preis;
    }
    public void setStueck(int stueck) {
        this.stueck = stueck;
    }
    public int getStueck() {
        return stueck;
    }
    
    public double berechneUmsatz() {
        return stueck * preis;
    }
    
    public String toString() {
        return String.format("%s\t%6.2f\t%4d\t%9.2f",artikel,preis,stueck, berechneUmsatz());
    }

//    @Override
//    public int compareTo(ArtikelUmsatz o) {
//       // sortiere nach artikel
//       return this.artikel.compareTo(o.artikel);
//    }
    
//    @Override
//    public int compareTo(ArtikelUmsatz o) {
//        // sortiere nach stueck
//        return this.stueck - o.stueck;
//    }
    
    @Override
    public int compareTo(ArtikelUmsatz o) {
        // sortiere nach preis; ACHTUNG: double
        // Einfache Differenz
        //return (int) (this.preis - o.preis);
        //waere falsch wegen Wandlung von double nach int!
        
//        if (this.preis < o.preis) {
//            return -34;
//        } else if (this.preis > o.preis) {
//            return 42;
//        } else {
//            return 0;
//        }
        return Double.compare(this.preis, o.preis);
    }
}
