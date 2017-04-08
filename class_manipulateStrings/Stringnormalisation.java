public class Stringnormalisation{

    //Variables
    private static String operatingSystem = System.getProperty("os.name").toLowerCase();
    public String path = new String();
    public String normedPath = new String ();
    private int counter = 0;
    
    //Methodes
    public static String replaceCharAt(String s, int pos, char c) {

	return s.substring(0, pos) + c + s.substring(pos + 1); 
    }    

    public String taborspace(String path){

	for( int i = 0; i < path.length(); i++){
	    if( path.charAt(i) == ' ' || path.charAt(i) == '\t'){
		counter++;
	    }
	}
	
	if( path.length() == counter ){
	
	    return path;	    
	}
	else return path;
    }

    public static String replaceSlashOrBackslash(String path){

	if( operatingSystem.equals("linux")){
	
	    for ( int i = 0; i < path.length(); i++){
		if (path.charAt(i) == '\\'){
		    path = replaceCharAt(path, i, '/');
		}
	    }
	    return path;
	}
	else if ( operatingSystem.equals("windows")){

            for ( int i = 0; i < path.length(); i++){
                if (path.charAt(i) == '/'){
                    path = replaceCharAt(path, i, '\\');
                }   
            }   
	    return path;

	}
	else{
	    System.out.println("The OS is not supported!");
	    return path;
	}

    }

    public static String normaliseSlashes(String path){

           if( operatingSystem.equals("linux")){
    
            for ( int i = 0; i < path.length(); i++){
                if (path.charAt(i) == '/' && path.charAt(i-1) == '/'){
                    path = replaceCharAt(path, i, '\0');
                }   
            }   
            return path;
        }   
        else if ( operatingSystem.equals("windows")){

            for ( int i = 0; i < path.length(); i++){
                if (path.charAt(i) == '\\' && path.charAt(i-1) == '\\'){
                    path = replaceCharAt(path, i, '\0');
                }   
            }   
            return path;

        }   
        else{
            System.out.println("The OS is not supported!");
            return path;
        }   

 

    }

    public String replaceWindowspath(String path){
	return "";

    }
    
    public static String deleteUnusedSpace(String path){

	path = path.replace(" ", "");
	return path;
    }

    public static  String test(String path){

	int counter = 0;
	for (int i = -1; (i = path.indexOf("/", i + 1)) != -1; ) {
	    counter++;
	    System.out.println(i);
	}
    
	int arr[] = new int[counter];	
	
	return path;
    }

    public String endingSlash(String path){

	if( operatingSystem.equals("linux")){
         
	    if(path.substring(path.length() - 1).equals("//")){
		normedPath = "";
		return path;
	    }
	    else return path;
	
	}
	else if ( operatingSystem.equals("windows")){

            if(path.substring(path.length() - 1).equals("\\")){
		normedPath = "";
                return path; 
            }   
            else return path;

          
	}
	else{
	    System.out.println("The OS is not supported!");
	    return path;
	}

    }
    
    //Main
    public static void main(String[] args){
    String s = "home/\\philip  /Schreibtisch";
    System.out.println(s);
    s = replaceSlashOrBackslash(s);
    s = normaliseSlashes(s);
    s = deleteUnusedSpace(s);
    s = test(s);
    System.out.println(s);
    
    
    }
    


}
