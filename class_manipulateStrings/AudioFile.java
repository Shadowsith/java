import java.util.Arrays;
import java.lang.String;
import java.util.*;

public class AudioFile{

    //Variables------------------------------------------------
    private static String operatingSystem = System.getProperty("os.name").toLowerCase();
    public String path = new String();
    private String normedPath = new String();
    private String parsedFilename = new String();
    private String parsedPathname = new String();
    private String author = new String();
    private String title = new String();
    private int counter = 0;
    
    //Own methodes for parsePathname--------------------------
    public String replaceCharAt(String s, int pos, char c) {

	return s.substring(0, pos) + c + s.substring(pos + 1); 
    }    
    //Set all to ""-String if origin path is ""
    public void setVoidString(String path){

        if(path.equals("")){
            setPathname("");
            setFilename("");
        }
    }
    //Set all to origin path, if it contains only space or/and tab
    public void tabOrSpace(String path){

        setPathname(path);
        setFilename(path);
    }

    //Replace slashes or backslashes OS specificly
    public String replaceSlashOrBackslash(String path){

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

    //Delete not needed / or \ (e.g. //,\\)
    public String normaliseSlashes(String path){

	if( operatingSystem.equals("linux")){

	    //Danger: You need string.length() - 1 or it gives a Stringoverflow
	    for ( int i = 0; i < path.length() - 1; i++){
                if (path.charAt(i) == '/' && path.charAt(i+1) == '/'){
                    path = replaceCharAt(path, i, '\0');
                }
		
	    }
            return path;
        }   
        else if ( operatingSystem.equals("windows")){

            for ( int i = 0; i < path.length()-1; i++){
                if (path.charAt(i) == '\\' && path.charAt(i+1) == '\\'){
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
    public String normWindowsPath(String path){

        if (path.charAt(1) == ':' && path.charAt(0) != '/'){
              
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

    //Deletes not needed space signs in front of filename
    public String deleteUnusedSpace(String path){
	
	int counter = 0;
	for(int i = 0; i < path.length(); i++){
	    if(path.charAt(i) == '/' || path.charAt(i) == '\\'){
		counter++;
	    }
	}
	if(counter == 0){
	    setFilename(path);
	    return path;
	}
        counter = 0;
	//ArrayList saves the position of the 
        List<Integer> arr = new ArrayList<Integer>();
        arr.add(counter);
        String filename = new String();
        //Get slash Position (linux)
	if(operatingSystem.equals("linux")){
	    for (int i = 0; i < path.length(); i++){

		if (path.charAt(i) == '/'){
		    arr.add(i);
		    counter++;
		}
	    }
	}
	//Get slash Position (windows)
	else if (operatingSystem.equals("windows")){
            for (int i = 0; i < path.length(); i++){

                if (path.charAt(i) == '\\'){
                    arr.add(i);
                    counter++;
                }   
            }   
	}
        //Replace not wanted space signs
        if (arr.get(counter) == path.length() - 1 && arr.get(counter) >= 0){
            for(int i = 0; i < arr.get(counter-1); i++){
                if (path.charAt(i) == ' '){
                    path = replaceCharAt(path, i, '\0');
                }
            }
	    //Save filename
            filename = "";
            setFilename(filename);
        }
        else if (arr.get(counter) < path.length() -1 && arr.get(counter) >= 0) {
            for(int i = 0; i < arr.get(counter); i++){
                if (path.charAt(i) == ' '){
                    path = replaceCharAt(path, i, '\0');
                }
            }
	    //Save filename
            filename = path.substring(arr.get(counter)+1);
            setFilename(filename);
        }
        return path;
    }

    public void parsePathname(String path){
	int counter = 0;
        for( int i = 0; i < path.length(); i++){
            if( path.charAt(i) == ' ' || path.charAt(i) == '\t'){
                counter++;
            }   
        }   
	if(path.isEmpty()){
	    setVoidString(path);
	}
	else if(path.length() == counter){
	    tabOrSpace(path);
	}
	else{
	    path = replaceSlashOrBackslash(path);
	    path = normWindowsPath(path);
	    path = normaliseSlashes(path);
	    path = deleteUnusedSpace(path);
	    setPathname(path);
	}
    }

    public void parseFilename(String file){
        file = getFilename();
        int counter = 0;
        int counter1 = 0;
        for(int i = 0; i < file.length(); i++){
            if(file.charAt(i) == ' '){
                counter++;
            }
            if(file.charAt(i) == ' ' || file.charAt(i) == '-'){
                counter1++;
            }
        }
        if(file.indexOf(".") == 0){
            setAuthor("");
            setTitle("");
        }
        else if(file.length() == counter){
            setAuthor("");
            setTitle(file.substring(0,file.indexOf(".")));
        }


    }

    //Setter---------------------------------
    public void setFilename(String filename){
	parsedFilename = filename;

    }
    public void setPathname(String pathname){
	parsedPathname = pathname;
    } 

    public void setAuthor(String filename){
	author = filename;
    }

    public void setTitle(String filename){
	title = filename;
    }

    //Getter--------------------------------
    public String getFilename(){
	return parsedFilename;
    }   

    public String getPathname(){
	return parsedPathname;
    }

    public String getAuthor(){
	return author;
    }

    public String getTitle(){
	return title;
    }

    //Main----------------------------------
    public static void main(String[] args){

        List<String> ss = new ArrayList<String>();
        ss.add("");             //0
        ss.add("   \t   \t");
        ss.add("file.mp3");
        ss.add("/my-tmp/file.mp3");
        ss.add("//my-tmp////part1//file.mp3///");
        ss.add("d:\\\\\\\\part1///file.mp3"); //5

        AudioFile af = new AudioFile();
        for(int i = 0; i < ss.size(); i++){
            System.out.println("Input File:  <" + ss.get(i) + ">");
            af.parsePathname(ss.get(i));
            System.out.println("getPathname: <" + af.getPathname() + ">");
            System.out.println("getFilename: <" + af.getFilename() + ">");
            System.out.println();
        }
    }
    


}
