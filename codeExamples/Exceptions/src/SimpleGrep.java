import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SimpleGrep {
    public void search(String fn, String txt) throws IOException,
            FileNotFoundException {

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

    public static void main(String[] args) {
        String dateiname = "examples/DerWerwolf.txt";
        String suchbegriff = "wolf";
        SimpleGrep greper = new SimpleGrep();
        try {
            greper.search(dateiname, suchbegriff);
        } catch (FileNotFoundException e) {
            System.err.printf("Datei %s existiert nicht\n", dateiname);
            // e.printStackTrace();
        } catch (IOException e) {
            System.err.printf("Fehler beim Lesen aus Datei %s\n", dateiname);
            // e.printStackTrace();
        }
    }
}
