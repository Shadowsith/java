public class OS{

    public static boolean isWindows(){

	String isOS = "Windows";
	return isOS.equals(System.getProperty("os.name"));

    }

    public static boolean isLinux(){

	String isOS = "Linux";
	return isOS.equals(System.getProperty("os.name"));

    }

    public static void main(String[] args){
	

	String s = "Hallo/";

	if(isLinux()){

	    s = s.replace("/","\\");

	}

	System.out.println(s);
	System.out.println(System.getProperty("os.name"));
    }

}
