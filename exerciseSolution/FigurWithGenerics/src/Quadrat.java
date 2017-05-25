public class Quadrat implements Figur {
    private double seitenLaenge;

    public Quadrat(double seitenLaenge) {
        this.seitenLaenge = seitenLaenge;
    }

    public double umfang() {
        return 4 * seitenLaenge;
    }

    public double addiereFlaeche(Figur f) {
        return this.flaeche() + f.flaeche();
    }

    public double flaeche() {
        return seitenLaenge * seitenLaenge;
    }

    public int compareTo(Figur that) {
        // Note: we no longer need a cast for parameter 'that'
        // since the type of parameter 'that' is restricted to Figur
        //
        // If we compute the difference of the two areas
        // we need to use signum() since an int cast will discard fractions.
        // We would get wrong results for differences d with abs(d) < 1;
        //
        // return (int) Math.signum(this.flaeche() - that.flaeche());
        //
        // However, a simpler solution is to use the static method
        // int java.lang.Double.compare(double d1, double d2)
        //
        return Double.compare(this.flaeche(),that.flaeche());
    }
}
