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
        

	for (int i = 0; i < file.length() - 1; i++){
	    if (file.charAt(i) == ' ' && file.charAt(i+1) == ' '){
                    file = replaceCharAt(file, i, '\0');
            }   
	}
	if (file.indexOf(" ") == 0){
                file = replaceCharAt(file, 0, '\0');
                if ( (file.lastIndexOf(" ")+1) == file.length()){
                    file = replaceCharAt(file, file.lastIndexOf(" "), '\0');
                }
        }
   
	for(int i = 0; i < file.length(); i++){
            if(file.charAt(i) != ' '){
                counter++;
            }
            if(file.charAt(0) != '-' && (file.charAt(i) == ' ' || file.charAt(i) == '-')){
                counter1++;
            }
        }
	//System.out.println("File: " + file);
	System.out.println("Filelength: " + file.length());
	System.out.println("Counterlength: " + counter);
	if(file.length() == 0){
	    setAuthor("");
	    setTitle("");
	}
        else if(file.indexOf(".") == 0){
            setAuthor("");
            setTitle("");
        }
        else if(file.length() == counter && file.length() > 1){
            setAuthor("");
            setTitle(file.substring(0,file.indexOf(".")));
	    //System.out.println("File: <" + file + ">");
	    //System.out.println("Substring <" + file.substring(0,file.indexOf(".")) + ">");
        }
	else if(file.length() == counter1){
	    setAuthor("");
	    setTitle("");
	}
	else if(file.charAt(0) == '-'){
	    setAuthor("");
	    setTitle(file);
	}
	else if(file.indexOf("-") > 0){
	    
	    for (int i = 0; i < file.length() - 1; i++){
		if (file.charAt(i) == ' ' && file.charAt(i+1) == ' '){
		    file = replaceCharAt(file, i, '\0');
		}
	    }
	    if (file.indexOf(" ") == 0){
		file = replaceCharAt(file, 0, '\0');
		if ( (file.lastIndexOf(" ")+1) == file.length()){
		    file = replaceCharAt(file, file.lastIndexOf(" "), '\0');
		}
	    }
	    if (file.lastIndexOf(".") > 0){
		file = file.substring(0,file.lastIndexOf("."));

	    }
	    if (file.lastIndexOf(" ") == file.length()-1){
		file = file.substring(0,file.lastIndexOf(" "));
	    }
	    if (file.indexOf(" ") != 0 && (file.indexOf("-") == file.indexOf(" ")+1 ||
	    file.indexOf("-") ==  file.indexOf(" ")-1)){


	    setAuthor(file.substring(0,file.indexOf(" ")));
	    setTitle(file.substring(file.indexOf("-")+2,file.length()));
	    }

	    //setAuthor(file);
	    //setTitle(file);
	}
	else if(0){
	    setAuthor(file);
	    setTitle(file);if (file.indexOf(" ") == 0){
                file = replaceCharAt(file, 0, '\0');
                if ( (file.lastIndexOf(" ")+1) == file.length()){
                    file = replaceCharAt(file, file.lastIndexOf(" "), '\0');
                }
            }

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
    /*
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
	    af.parseFilename(ss.get(i));
            System.out.println("getPathname: <" + af.getPathname() + ">");
            System.out.println("getFilename: <" + af.getFilename() + ">");
	    System.out.println("getAuthor:   <" + af.getAuthor() + ">"); 
            System.out.println("getTitle:    <" + af.getTitle() + ">");
	    System.out.println();
        }
	*/

	AudioFile af = new AudioFile();
	
	List<String> fl = new ArrayList<String>();
	//fl.add("file.mp3");
	//fl.add("-");
	//fl.add(" - ");
	//fl.add(".mp3");
	//fl.add(" Falco - Rock me Amadeus .mp3 ");
	fl.add("Falco - Rock me Amadeus.");
	fl.add("   A.U.T.O.R.  -  T.I.T.E.L.EXTENSION");
	fl.add("Hans-Georg Sonstwas - Blue-eyed boy-friend.mp3");
	
	for(int i = 0; i < fl.size(); i++){
	    af.setFilename(fl.get(i));	
	    af.parseFilename(af.getFilename());
	    System.out.println("Input Filename: <" + fl.get(i) + ">");
	    System.out.println("getAuthor:   <" + af.getAuthor() + ">");
	    System.out.println("getTitle:    <" + af.getTitle() + ">");
	    System.out.println();
	}
    }
    


}
