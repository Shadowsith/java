// Einbau zweier Comparatoren
// Vergleiche mit normaler LinkedListDemo
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemoComparator {
    private List <ArtikelUmsatz> monatsUmsaetze = new LinkedList<ArtikelUmsatz>();
    private List <ArtikelUmsatz> ladenHueter = new LinkedList<ArtikelUmsatz>();

    public void addUmsatz(ArtikelUmsatz au) {
        if (au != null) {
            monatsUmsaetze.add(au);
        }
    }

    public List <ArtikelUmsatz> getLadenHueter() {
            return ladenHueter;
    }
    
    public void druckeMonatsUmsatz() {
        for (ArtikelUmsatz au: monatsUmsaetze) {
            System.out.printf("%s\n", au);
        }
    }

    public void druckeLadenhueter() {
        for (ArtikelUmsatz au: ladenHueter) {
            System.out.printf("%s\n", au);
        }
    }
  
    public void extrahiereLadenHueter3() {
        // Verschiebe alle ArtikelUmsaetze mit weniger als
        // 50 verkauften Stueck in Liste ladenHueter
        // Loesche diese aus normaler Liste der Monatsumsaetze
        
        // Erweiterte for-Schleife
        // Wir verwenden eine temporaere Hilfsliste
        // Dadurch kein Problem mit java.util.ConcurrentModificationException
        // Das ist einfach und robust!
        List <ArtikelUmsatz> topSeller = new LinkedList<ArtikelUmsatz>();
        for(ArtikelUmsatz au: monatsUmsaetze) {
            if (au.getStueck() < 50) {
                // zu Liste ladenHueter hinzufuegen
                ladenHueter.add(au);
            } else {
                // zu Liste topSeller hinzufuegen
                topSeller.add(au);
            }
        }
        // Und nun lassen wir die Referenz monatsUmsaetze
        // auf die HilfsListe zeigen.
        // Da die Objekte auf dem Heap allokiert werden
        // ist das kein Problem!
        monatsUmsaetze = topSeller;
    }

    public static void main(String[] args) {
        // Erzeuge Objekt der Klasse LinkdeListDemo
        LinkedListDemoComparator ald = new LinkedListDemoComparator();
        
        // Fuege Umsaetze hinzu
        ald.addUmsatz(new ArtikelUmsatz("Marken  Hose", 20.99, 30));
        ald.addUmsatz(new ArtikelUmsatz("NoName  Hose", 15.0, 300));
        ald.addUmsatz(new ArtikelUmsatz("Marken  Hemd", 20.0, 45));
        ald.addUmsatz(new ArtikelUmsatz("NoName  Hemd", 10.0, 80));
        ald.addUmsatz(new ArtikelUmsatz("Marken Jacke", 230.0, 15));
        ald.addUmsatz(new ArtikelUmsatz("NoName Jacke", 35.0,  40));
        
        // Drucke alle Monatsumsaetze
        System.out.println("Alle Monatsumsaetze");
        ald.druckeMonatsUmsatz();
        
        // Loesche Ladenhueter aus Liste der Monatsumsaetze und
        // sammle sie in eigener Liste
        ald.extrahiereLadenHueter3();

        // Drucke separate Liste der Ladenhueter
        System.out.println();
        System.out.println("Ladenhueter");
        ald.druckeLadenhueter();
        
        // Drucke separate Liste der Ladenhueter sortiert nach
        // Ordnung via Comparable-Implementierung von ArtikelUmsatz
        System.out.println();
        System.out.println("Ladenhueter sortiert (natuerliche Ordnung)");
        Collections.sort(ald.getLadenHueter());
        ald.druckeLadenhueter();
        
//        // Drucke separate Liste der Ladenhueter sortiert nach Stueckzahl
//        System.out.println();
//        System.out.println("Ladenhueter sortiert nach Stueckzahl");
//        Collections.sort(ald.getLadenHueter(), new StueckComparator());
//        ald.druckeLadenhueter();   
//        
//        // Drucke separate Liste der Ladenhueter sortiert nach Umsatz
//        System.out.println();
//        System.out.println("Ladenhueter sortiert nach Umsatz");
//        Collections.sort(ald.getLadenHueter(), new UmsatzComparator());
//        ald.druckeLadenhueter();
        
        
    }
}