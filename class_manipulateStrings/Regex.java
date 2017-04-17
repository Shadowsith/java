public class Regex{

    public static void main(String[] args){

	String str = "\\hallo\\test.mp3";
	String a = "   Hallo";
	str = str.replace("\\", "/");
	System.out.println(str);

	for(int i = 0; i < a.length(); i++){
	    
	    if(a.charAt(0) == ' '){
		a = a.substring(1,a.length());
	    }
	}
	System.out.println(a);

    }


}
