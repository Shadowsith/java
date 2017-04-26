// Vergleiche LinkedListDemo
// Unterschied lediglich im verwendeten Datentyp
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    private List <ArtikelUmsatz> monatsUmsaetze = new ArrayList<ArtikelUmsatz>();
    private List <ArtikelUmsatz> ladenHueter = new ArrayList<ArtikelUmsatz>();

    public void addUmsatz(ArtikelUmsatz au) {
        if (au != null) {
            monatsUmsaetze.add(au);
        }
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
  
    public void extrahiereLadenHueter1() {
        // Verschiebe alle ArtikelUmsaetze mit weniger als
        // 50 verkauften Stueck in Liste ladenHueter
        // Loesche diese aus normaler Liste der Monatsumsaetze

        // Konventionelle for-Schleife
        // Kein Problem mit java.util.ConcurrentModificationException
        // ABER: ??
        for(int i = 0; i < monatsUmsaetze.size(); i++) {
            if (monatsUmsaetze.get(i).getStueck() < 50) {
                // zu Liste ladenHueter hinzufuegen
                ladenHueter.add(monatsUmsaetze.get(i));
                // aus Liste MonatsUmsaetze entfernen
                monatsUmsaetze.remove(i);            
            }
        }      
    }
    
    public void extrahiereLadenHueter2() {
        // Verschiebe alle ArtikelUmsaetze mit weniger als
        // 50 verkauften Stueck in Liste ladenHueter
        // Loesche diese aus normaler Liste der Monatsumsaetze
        
        // Erweiterte for-Schleife
        // Problem mit java.util.ConcurrentModificationException
        for(ArtikelUmsatz au: monatsUmsaetze) {
            if (au.getStueck() < 50) {
                // zu Liste ladenHueter hinzufuegen
                ladenHueter.add(au);
                // aus Liste MonatsUmsaetze entfernen
                monatsUmsaetze.remove(au);              
            }
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
        List <ArtikelUmsatz> topSeller = new ArrayList<ArtikelUmsatz>();
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

    public void extrahiereLadenHueter4() {
        // Verschiebe alle ArtikelUmsaetze mit weniger als
        // 50 verkauften Stueck in Liste ladenHueter
        // Loesche diese aus normaler Liste der Monatsumsaetze

        // Benutzung eines Iterators
        // Kein Problem mit java.util.ConcurrentModificationException
        Iterator<ArtikelUmsatz> iter = monatsUmsaetze.iterator();
        while(iter.hasNext()) {
            ArtikelUmsatz au = iter.next();
            if (au.getStueck() < 50) {
                // zu Liste ladenHueter hinzufuegen
                ladenHueter.add(au);
                // aus Liste MonatsUmsaetze entfernen
                iter.remove();  // kein Problem mehr mit ConcurrentModificationException            
            }
        }      
    }
   
    public static void main(String[] args) {
        // Erzeuge Objekt der Klasse LinkdeListDemo
        LinkedListDemo ald = new LinkedListDemo();

        // Fuege Umsaetze hinzu
        ald.addUmsatz(new ArtikelUmsatz("Marken  Hose", 70.0, 30));
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
        ald.extrahiereLadenHueter1();   // SP
        //ald.extrahiereLadenHueter2(); // LF
        //ald.extrahiereLadenHueter3();
        //ald.extrahiereLadenHueter4();

        // Drucke separate Liste der Ladenhueter
        System.out.println();
        System.out.println("Ladenhueter");
        ald.druckeLadenhueter();

        // Drucke Restliste der Monatsumsaetze; das sind die  TopSeller
        System.out.println();
        System.out.println("TopSeller");
        ald.druckeMonatsUmsatz();
    }
}