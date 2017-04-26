// Umstieg von C nach Java
//
// Datentyp String in Java
//
// Uebersetzen, direkt in der Shell ohne IDE (Eclipse, ...)
// Dafuer muss Umgebungsvariable JAVA_HOME richtig eingestellt sein!
//
//   javac StringTest.java
//
// Ausfuehren in der Shell
//
//   java StringTest
//

// Eine Klasse als Huelle definieren, weil man das in Java muss! 
public class StringTest {

  public static void main(String[] args) {
    String s1 = "Don't like AAAAAs!";
    String s2 = "";

    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != 'A') {
        s2 += s1.charAt(i);
      }
    }
    // Ausgabe auf Konsole
    System.out.printf("As you like: %s\n", s2);
  }

} // Ende der Klassendefinition
