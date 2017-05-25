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

    public int compareTo(Figur that) {
        // Note: we no longer need a cast for parameter 'that'
        // since the type of parameter 'that' is restricted to Figur
        if (this.flaeche() > that.flaeche()) {
            return 1;
        } else if (this.flaeche() < that.flaeche()) {
            return -1;
        } else {
            return 0;
        }
    }
}