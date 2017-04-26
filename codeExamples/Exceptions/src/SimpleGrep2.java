import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Ausfuehren in Shell im Projektverzeichnis
//
// Kompilieren:
//
// mkdir -p classes
// javac -sourcepath src -classpath classes -d classes src/SimpleGrep2.java
//
// Ausfuehren mit Argumenten auf Kommandozeile:
//
// java -cp classes SimpleGrep2 examples/DerWerwolf.txt wolf

public class SimpleGrep2 {
    public void search(String fn, String txt) throws IOException,
            FileNotFoundException, SearchException {

        if (txt == null || txt.isEmpty()) {
            throw new SearchException("Nichtleeren Suchstring eingeben");
        }

        if (fn == null || fn.isEmpty()) {
            throw new SearchException("Nichtleeren Dateinamen eingeben");
        }

        BufferedReader in = new BufferedReader(new FileReader(fn));

        String line;
        int lnr = 0;
        while ((line = in.readLine()) != null) {
            lnr++;
            if (line.contains(txt))
                System.out.println(lnr + ": " + line);
        }
        in.close();
    }

    public static void main(String[] args) throws SearchException {
        String dateiname = "";
        String suchbegriff = "";

        if (args.length == 2) {
            dateiname = args[0];
            suchbegriff = args[1];
        } else {
            throw new SearchException(
                    "Falscher Aufruf:\n"
                            + "   usage: java -cp classes SimpleGrep2 Dateiname Suchtext\n"
                            + "Beispiel: java -cp classes SimpleGrep2 examples/DerWerwolf.txt wolf");
        }

        SimpleGrep2 greper = new SimpleGrep2();
        try {
            greper.search(dateiname, suchbegriff);
        } catch (FileNotFoundException e) {
            System.err.printf("Datei %s kann nicht geoeffnet werden\n",
                    dateiname);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.printf("Fehler beim Lesen aus Datei %s\n", dateiname);
            // e.printStackTrace();
        }
    }
}
