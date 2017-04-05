import java.lang.String;

public class Space{


  public static void main(String[] argv){
    String s = "";
    System.out.println("vorhin: <" + s + ">");
    while( s.length() > 0 && s.charAt(0) == ' '){
      s = s.substring(1,s.length());
  
    }
    System.out.println("nacher: <" + s + ">");
  }


}
