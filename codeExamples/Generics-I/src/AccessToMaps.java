// Zeige diverse Moeglichkeiten, um auf die Eintraege
// einer Map zuzugreifen.
// Bemerkung: nicht alles was moeglich ist, ist auch gut!
// Die empfohlene Verwendung beim Umgang mit Maps zeigt
// "Preisliste 1 fuer NoName-Artikel:"

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AccessToMaps {
    @SuppressWarnings("unchecked")
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
        // Bemerkung: das ist die uebliche und empfohlene Verwendung von Maps
        System.out.println("Preisliste 1 fuer NoName-Artikel:");
        for (String key : kpreise.keySet()) {
            if (key.contains("NoName")) {
                System.out.printf("%s\t%6.2f\n", key, kpreise.get(key));
            }
        }

        System.out.println();

        // Konventionelle for-Schleife ueber alle Schluessel/Wert-Paare
        // Zeigt Zusammenhang zwischen Collections und normalen Arrays
        // Vorsicht: Cast notwendig
        // Bemerkung: das ist zwar technisch moeglich, aber
        // unueblich und nicht empfehlenswert!

        System.out.println("Preisliste 2 fuer Hosen:");
        // Umwandlung der Paar-Menge in Array von Objekten
        Object[] kva = kpreise.entrySet().toArray();
        // Konventionelle Schleife ueber Array von Objekten
        for (int i = 0; i < kva.length; i++) {
            // Zugriff auf i-tes Element im Array und Cast auf Map.Entry<String,
            // Double>
            Map.Entry<String, Double> entry = (Map.Entry<String, Double>) kva[i];
            if (entry.getKey().contains("Hose")) {
                System.out.printf("%s\t%6.2f\n", entry.getKey(),
                        entry.getValue());
            }
        }

        System.out.println();

        // Erweiterte for-Schleife ueber Schluessel/Wert-Paare
        // Nutzung der toString() Methode fuer Map.Entry
        // Ausgabe Key=Value
        // Bemerkung: unuebliche Verwendung des Entry-Set
        Set<Map.Entry<String, Double>> kvSet;
        kvSet = kpreise.entrySet();
        System.out.println("Preisliste 3 fuer alle Artikel:");
        for (Map.Entry<String, Double> kvp : kvSet) {
            System.out.printf("%s\n", kvp);
        }

        System.out.println();

        // Erweiterte for-Schleife ueber Schluessel/Wert-Paare
        // Gezielter Zugriff auf Key und Value
        // Bemerkung: unuebliche Verwendung des Entry-Set
        kvSet = kpreise.entrySet();
        System.out.println("Preisliste 4 fuer Marken-Artikel:");
        for (Map.Entry<String, Double> kvp : kvSet) {
            if (kvp.getKey().contains("Marken")) {
                System.out.printf("%s\t%6.2f\n", kvp.getKey(), kvp.getValue());
            }
        }

    }
}
