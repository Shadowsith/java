import java.util.Arrays;
import java.lang.String;
import java.util.*;

public class Stringnormalisation{

    //Variables
    private static String operatingSystem = System.getProperty("os.name").toLowerCase();
    public String path = new String();
    public String normedPath = new String();
    private static String parsedFilename = new String();
    private static String parsedPathname = new String();
    private int counter = 0;
    
    //Methodes for parsing in parsePathname
    public static String replaceCharAt(String s, int pos, char c) {

	return s.substring(0, pos) + c + s.substring(pos + 1); 
    }    

    public void setVoidString(String path){

        if(path.equals("")){
            setPathname("");
            setFilename("");

        }
       
    }

    public String taborspace(String path){

	for( int i = 0; i < path.length(); i++){
	    if( path.charAt(i) == ' ' || path.charAt(i) == '\t'){
		counter++;
	    }
	}
	
	if( path.length() == counter ){
	
            setPathname(path);
            setFilename(path);
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
                if (path.charAt(0) == '/' && i == 0) {
                    i++;
                }
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
    
    //Methode ask if second char is ':' than it is a windows path
    public static String normWindowsPath(String path){

        if (path.charAt(1) == ':'){
              
            if( operatingSystem.equals("linux")){
                path = path.replace(":","/"); 
                String newpath = "/" + path;
                return newpath;
            }   
            else if ( operatingSystem.equals("windows")){
                return path;

            }
            else System.out.println("This OS is not supported");
        
        
        }
        return path;
    } 
    public static  String deleteUnusedSpace(String path){
        
        System.out.println("Path: " + path);
        int counter = 0;
        List<Integer> arr = new ArrayList<Integer>(); //Array saves the / Positions
        arr.add(counter);
        String filename = new String();
        //Get slash Position
        for (int i = 0; i < path.length(); i++){

            if (path.charAt(i) == '/'){
                arr.add(i);
                counter++;
            }
        }
        for (int i = 0; i < arr.size(); i++){
            System.out.println("/ Pos " + i + ": " + arr.get(i));

        }
        //Replace not wanted space signs
        if (arr.get(counter) == path.length() - 1 && arr.get(counter) >= 0){

            for(int i = 0; i < arr.get(counter-1); i++){
                if (path.charAt(i) == ' '){
                    path = replaceCharAt(path, i, '\0');
                }
            }
            filename = "";
            setFilename(filename);
        }
        else if (arr.get(counter) < path.length() -1 && arr.get(counter) >= 0) {
            
            for(int i = 0; i < arr.get(counter); i++){
                if (path.charAt(i) == ' '){
                    path = replaceCharAt(path, i, '\0');
                }
            }
            filename = path.substring(arr.get(counter)+1);
            setFilename(filename);

        }
  
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

    //Filenormalisation

    public static void setFilename(String filename){
          parsedFilename = filename;

    }

    public static String getFilename(){
          return parsedFilename;
    }

    public static void setPathname(String pathname){
          parsedPathname = pathname;
    }

    public static String getPathname(){
          return parsedPathname;
    }



    //Main
    public static void main(String[] args){
    String s = "f:\\home\\/philip  /file 3.mp3";
    //String s = "";
    System.out.println("Input Path: <" + s + ">");
    if(s.isEmpty()){
        System.out.println("Leerer String:");
    }
    else{
        s = replaceSlashOrBackslash(s);
        s = normWindowsPath(s);
        s = normaliseSlashes(s);
        s = deleteUnusedSpace(s);
        setPathname(s);
        System.out.println("Normalised Path: <" + s + ">");
        System.out.println("Filename: <" + getFilename() + ">");
        System.out.println("Pathname: <" + getPathname() + ">");
    }

    
    
    }
    


}
