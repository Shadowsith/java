public class Kreis implements Figur {
    private double radius;

    public Kreis(double radius) {
        this.radius = radius;
    }

    public double umfang() {
        return 2 * radius * Math.PI;
    }

    public double addiereFlaeche(Figur f) {
        return this.flaeche() + f.flaeche();
    }

    public double flaeche() {
        return radius * radius * Math.PI;
    }

    public int compareTo(Object that) {
        // Attention: we need a cast here
        double thatFlaeche = ((Figur) that).flaeche();
        if (this.flaeche() > thatFlaeche) {
            return 1;
        } else if (this.flaeche() < thatFlaeche) {
            return -1;
        } else {
            return 0;
        }
    }
}
