// HashMap: Schluessel sind unsortiert
// Vergleiche TreeMapDemo2

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        // Beachte: Verwendung von Interface-Name und konkreter Implementierung
        Map<String, Double> kpreise = new HashMap<String, Double>();

        kpreise.put("NoName Hose", 15.0);
        kpreise.put("Marken Hose", 70.0);
        kpreise.put("NoName Hemd", 10.0);
        kpreise.put("Marken Hemd", 70.0);
        kpreise.put("NoName Schuhe", 20.0);
        kpreise.put("Marken Schuhe", 110.0);

        // Erweiterte for-Schleife ueber Schluessel-Set
        // 'uebliche' Verwendung von Maps
        // Eintraege wegen Hash-Map unsortiert
        System.out.println("Preisliste 1 fuer Marken-Artikel:");
        for (String key : kpreise.keySet()) {
            if (key.contains("Marken")) {
                System.out.printf("%s\t%6.2f\n", key, kpreise.get(key));
            }
        }

        System.out.println();

        // Erweiterte for-Schleife ueber Schluessel-Set
        // 'uebliche' Verwendung von Maps
        // Eintraege wegen Hash-Map unsortiert
        System.out.println("Preisliste 2 fuer NoName-Artikel:");
        for (String key : kpreise.keySet()) {
            if (key.contains("NoName")) {
                System.out.printf("%s\t%6.2f\n", key, kpreise.get(key));
            }
        }
    }
}
