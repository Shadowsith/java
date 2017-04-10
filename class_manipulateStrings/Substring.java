public class Substring{


    public static void main(String[] args){
    
    String s = "hallo.mp3";
    String v = "";
    System.out.println(s.substring(0,s.indexOf("l")));
    System.out.println(s.indexOf(".") + " " + s.lastIndexOf("."));
    System.out.println(s.substring(0,s.indexOf(".")));
    System.out.println(v.length());
    }

}
