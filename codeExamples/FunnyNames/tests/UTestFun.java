import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Gibt gelesene Zeilen aus einer Textdatei mit ihren Zeilennummern wieder aus.
// Hier Spezialanwendung:
// Erzeuge 'lustige' Dateinamen.
//

public class UTestFun {
    @Before
    public void setup() {
        // Achtung: Verzeichnis trash muss existieren fuer die Tests
        new File("trash").mkdir();
    }
    
    @Test
    public void test_names_01() throws IOException {
        // Leerzeichen am Beginn von Dateinamen sind kein Problem!
        do_it("examples/DerWerwolf.txt",
                "trash///////   DerWerwolf.txt.out");
        
        assertTrue(new File("trash/   DerWerwolf.txt.out").exists());
    }

    // ---------------------------------------------------------------
    // Windows tests
    // ---------------------------------------------------------------

    @Test
    public void test_names_win_01() throws IOException {
        if (isWindows()) {
            // Leerzeichen am Beginn von Verzeichnisnamen sind kein Problem
            // Misch von \ und / ist auch kein Problem
            new File("trash/ xxx").mkdirs();
            do_it("examples/DerWerwolf.txt",
                    "trash\\\\//// xxx////\\   DerWerwolf.txt.out");
            
            assertTrue(new File("trash/ xxx/   DerWerwolf.txt.out").exists());
        }
    }
    
    @Test
    public void test_names_win_02() throws IOException {
        if (isWindows()) {
            // Laufwerksbuchstaben muessen nur von : gefolgt werden. Es geht ohne '\' !!  
            File check;
            
            check = new File("c:xxx///\\  yyy");            
            check.mkdirs();
            assertTrue(check.exists());
            
            do_it("examples/DerWerwolf.txt",
            "c:xxx/////////\\\\\\\\  yyy\\\\////   bla  .txt");
            
            check = new File("c:xxx/  yyy/   bla  .txt");
            assertTrue(check.exists() );
        }
    }
    
    @Test
    public void test_names_win_03() throws IOException {
        if (isWindows()) {
            File check;
            
            check = new File("c:xxx///\\  yyy");            
            check.mkdirs();
            assertTrue(check.exists());
            
            do_it("examples/DerWerwolf.txt",
            "c:xxx/////////\\\\\\\\  yyy\\\\//// bla .txt");
            
            check = new File("c:xxx/  yyy/ bla .txt");
            assertTrue(check.exists() );
        }
    }
    
    @Test
    public void test_names_win_04() throws IOException {
        if (isWindows()) {
            File check;
            
            check = new File("c:xxx///\\  yyy");            
            check.mkdirs();
            assertTrue(check.exists());
            
            do_it("examples/DerWerwolf.txt",
            "c:xxx/////////\\\\\\\\  yyy\\\\////bla.txt");
            
            check = new File("c:xxx/  yyy/bla.txt");
            assertTrue(check.exists() );
        }
    }

    @Test
    public void test_names_win_05() throws IOException {
        if (isWindows()) {
            File check;
            
            check = new File("c:xxx///\\  yyy");            
            check.mkdirs();
            assertTrue(check.exists());
            
            do_it("examples/DerWerwolf.txt",
            "c:xxx/////////\\\\\\\\  yyy\\\\////bla");
            
            check = new File("c:xxx/  yyy/bla");
            assertTrue(check.exists() );
        }
    }

    // ---------------------------------------------------------------
    // Tests for OS other than Windows
    // ---------------------------------------------------------------

    @Test
    public void test_names_otherOS_01() throws IOException {
        if (!isWindows()) {
            // Leerzeichen am Beginn und am Ende von Verzeichnis und Dateinamen
            // Kein Problem!
            // \ hingegen ist eine Escape-Sequenz fuer das naechste Zeichen.
            new File("trash/  xxx  ").mkdirs();
            do_it("examples/DerWerwolf.txt",
                    "trash////  xxx  ////   DerWerwolf.txt.out1");
            
            assertTrue(new File("trash/  xxx  /   DerWerwolf.txt.out1").exists());
        }
    }
    
    @Test
    public void test_names_otherOS_02() throws IOException {
        if (!isWindows()) {
            File check;

            check = new File("trash/  xxx  /  yyy");
            check.mkdirs();
            assertTrue(check.exists());
            
            do_it("examples/DerWerwolf.txt",
            ".///trash///  xxx  /////  yyy//////   bla  .txt");
            
            check = new File("trash/  xxx  /  yyy/   bla  .txt");
            assertTrue(check.exists() );
        }
    }    

    @Test
    public void test_names_otherOS_03() throws IOException {
        if (!isWindows()) {
            File check;

            check = new File("trash/  xxx  /  yyy");
            check.mkdirs();
            assertTrue(check.exists());
            
            do_it("examples/DerWerwolf.txt",
            ".///trash///  xxx  /////  yyy//////  bla  .txt");
            
            check = new File("trash/  xxx  /  yyy/  bla  .txt");
            assertTrue(check.exists() );
        }
    }
    
    @Test
    public void test_names_otherOS_04() throws IOException {
        if (!isWindows()) {
            File check;

            check = new File("trash/  xxx  /  yyy");
            check.mkdirs();
            assertTrue(check.exists());
            
            do_it("examples/DerWerwolf.txt",
            ".///trash///  xxx  /////  yyy//////bla  .txt");
            
            check = new File("trash/  xxx  /  yyy/bla  .txt");
            assertTrue(check.exists() );
        }
    }
    
    @Test
    public void test_names_otherOS_05() throws IOException {
        if (!isWindows()) {
            File check;

            check = new File("trash/  xxx  /  yyy");
            check.mkdirs();
            assertTrue(check.exists());
            
            do_it("examples/DerWerwolf.txt",
            ".///trash///  xxx  /////  yyy//////bla");
            
            check = new File("trash/  xxx  /  yyy/bla");
            assertTrue(check.exists() );
        }
    }

    @Test
    public void test_names_otherOS_06() throws IOException {
        if (!isWindows()) {
            // Man kann sogar Dateinamen und Verzeichnisnamen verwenden,
            // die NUR Leerzeichen enthalten!
            File check;

            check = new File("trash/  xxx  /  yyy");
            check.mkdirs();
            assertTrue(check.exists());
            
            do_it("examples/DerWerwolf.txt",
            ".///trash///  xxx  /////  yyy//////   ");
            
            check = new File("trash/  xxx  /  yyy/   ");
            assertTrue(check.exists() );
        }
    }
    
    @Test
    public void test_names_otherOS_07() throws IOException {
        if (!isWindows()) {
            // Man kann sogar Dateinamen und Verzeichnisnamen verwenden,
            // die NUR Leerzeichen enthalten!
            File check;

            check = new File("trash/   /  yyy");
            check.mkdirs();
            assertTrue(check.exists());
            
            do_it("examples/DerWerwolf.txt",
            ".///trash///   /////  yyy//////   ");
            
            check = new File("trash/   /  yyy/   ");
            assertTrue(check.exists() );
        }
    }
    // ---------------------------------------------------------------
    // Auxiliary functions   
    // ---------------------------------------------------------------

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
    }
    
    private void do_it(String inname, String outname) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(inname));
        BufferedWriter out = new BufferedWriter(new FileWriter(outname));

        int lnr = 0;
        String line;
        while ((line = in.readLine()) != null) {
            out.write(++lnr + ": ");
            out.write(line);
            out.newLine();
        }

        in.close();
        out.close();
    }
}
