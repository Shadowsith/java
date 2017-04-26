// Umstieg von C nach Java
// Beispiel fuer prozedurale Programmierung in Java
//
// Achtung: das ist keine objektorientierte Programmierung!
//
// Uebersetzen, direkt in der Shell ohne IDE (Eclipse, ...)
// Dafuer muss Umgebungsvariable JAVA_HOME richtig eingestellt sein!
//
//   javac GGT.java
//
// Ausfuehren in der Shell
//
//   java GGT
//

// Eine Klasse als Huelle definieren, weil man das in Java muss! 
public class GGT {

  // Eine Funktion definieren.
  // Falls man kein Objekt erzeugen will, muss diese Funktion 'static' sein
  static int ggT(int a, int b) {
    if (a < 0) {
      a = -a;
    }
    if (b < 0) {
      b = -b;
    }
    while (a != b) {
      if (a > b) {
        a = a - b;
      } else {
        b = b - a;
      }
    }
    return a;
  }

  // Die Signatur der Funktion main() muss lauten:
  // public static void main(String[] args)
  public static void main(String[] args) {
    int x = 24;
    int y = 9;
    // Ausgabe auf Konsole
    System.out.printf("ggt(%d,%d) = %d\n", x, y, ggT(x, y));
  }

} // Ende der Klassendefinition
