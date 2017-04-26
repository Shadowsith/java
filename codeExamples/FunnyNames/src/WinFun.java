import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WinFun {
    public static void main(String[] args) throws IOException {
        // Fehler fuer Java6 unter win7, OK fuer Java7
        if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
            String path1 = "c:xxx///\\  yyy";
            String pathname1 = "c:xxx////\\\\  yyy\\\\////   bla  .txt";
            String pathname2 = "c:xxx/  yyy/   bla  .txt";
            File thefile;

            // Create a directory
            thefile = new File(path1);
            
            thefile.mkdirs();  
            if (thefile.exists()) {
                System.out.println("Path1 exists");
            } else {
                System.out.println("Path1 does not exist");
            }
            
            // Write to file
            BufferedWriter out = new BufferedWriter(new FileWriter(pathname1));
            out.write("Das ist ein test");
            out.newLine();
            out.close();

            if (new File(pathname2).exists()) {
                System.out.println("Pathname2 exists");
            } else {
                System.out.println("Pathname2 does not exist");
            }
        }
    }
}
