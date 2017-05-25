public class UseFigur {
    public static void main(String[] args) {
        // Figur f1 = new Kreis(1.0);
        // Figur f1 = new Kreis(2.0);
        Figur f1 = new Quadrat(2.0);

        Figur f2 = new Kreis(1.0);
        // Figur f2 = new Kreis(1.1);
        // Figur f2 = new Kreis(1.2);

        System.out.printf("Flaeche von %s = %6.2f\n", f1.getClass().getName(),
                f1.flaeche());

        System.out.printf("Flaeche von %s = %6.2f\n", f2.getClass().getName(),
                f2.flaeche());

        if (f1.compareTo(f2) < 0) {
            System.out.printf("%s f1 ist kleiner als %s f2\n", f1.getClass()
                    .getName(), f2.getClass().getName());
        } else if (f1.compareTo(f2) > 0) {
            System.out.printf("%s f1 ist groesser als %s f2\n", f1.getClass()
                    .getName(), f2.getClass().getName());
        } else {
            System.out.printf("%s f1 und %s f2 sind gleich gross\n", f1
                    .getClass().getName(), f2.getClass().getName());
        }

    }
}
