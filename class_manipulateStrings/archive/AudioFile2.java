import java.util.Arrays;
import java.lang.String;
import java.util.*;

public class AudioFile{

    //Variables------------------------------------------------
    private static String operatingSystem = System.getProperty("os.name").toLowerCase();
    public String path = new String();
    private String parsedFilename = new String();
    private String parsedPathname = new String();
    private String author = new String();
    private String title = new String();

    //Constructors---------------------------------------------

    public AudioFile(String input){

    parsePathname(input);
    parseFilename(getFilename());
    }

    public AudioFile() {}


    
    //Own methodes for parsePathname--------------------------
    public String replaceCharAt(String s, int pos, char c) {

	return s.substring(0, pos) + c + s.substring(pos + 1);
    }

    //I only replaces the most symbols with "", but do not shorten the String
    //This method shorten the String and frees them from ""
    public String removeAllEmptyChar(String s){
	StringBuilder sb = new StringBuilder(s);
	int j = 0;

	for(int i = 0; i < sb.length(); i++){
	    if(sb.charAt(i) != '\0'){
		sb.setCharAt(j++, sb.charAt(i));
	    }
	}
	sb.delete(j, sb.length());
	return sb.toString();

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
	
	    path = path.replace("\\","/");
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
		newpath = removeAllEmptyChar(newpath);
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
            setFilename("");
        }
        else if (arr.get(counter) < path.length() -1 && arr.get(counter) >= 0) {
            for(int i = 0; i < arr.get(counter); i++){
                if (path.charAt(i) == ' '){
                    path = replaceCharAt(path, i, '\0');
                }
            }
	    //Save filename
            filename = path.substring(arr.get(counter)+1);
	    filename = removeAllEmptyChar(filename);
            setFilename(filename);
        }
        return path;
    }

    public void parsePathname(String path){
	int spacetabcounter = 0;
	int isfileonlycounter = 0;
        for( int i = 0; i < path.length(); i++){
            if(path.charAt(i) == ' ' || path.charAt(i) == '\t'){
                spacetabcounter++;
            }
	    if(path.charAt(i) != '/' || path.charAt(i) != '\\'){
		isfileonlycounter++;
	    }
        }   
	if(path.isEmpty()){
	    setVoidString(path);
	}
	else if(path.length() == spacetabcounter){
	    tabOrSpace(path);
	}
	else if(path.length() == isfileonlycounter){
	    setPathname("");
	    setFilename(path);
	}
	else{
	    path = replaceSlashOrBackslash(path);
	    path = normWindowsPath(path);
	    path = normaliseSlashes(path);
	    path = deleteUnusedSpace(path);
	    setPathname(path);
	    parseFilename(path);
	}
    }

    public void parseFilename(String file){
        //file = getFilename();
        int counter = 0;
        int counter1 = 0;
	int spacetabcounter = 0;
	int namecounter = 0;
        
	for(int i = 0; i < file.length(); i++){
            if(file.charAt(i) != ' '){
                counter++;
            }
            if(file.charAt(0) != '-' && (file.charAt(i) == ' ' || file.charAt(i) == '-')){
                counter1++;
            }
	    if(file.charAt(i) == ' ' || file.charAt(i) == '\t'){
		spacetabcounter++;
	    }
	    if(file.charAt(i) != ' ' || file.charAt(i) != '.'){
		namecounter++;
	    }
        }
	//System.out.println("File: " + file);
	//System.out.println("Filelength: " + file.length());
	//System.out.println("Counterlength: " + counter);
	//Empty String -> setAllEmpty
	if(file.length() == 0 || spacetabcounter == file.length()){
	    setAuthor("");
	    setTitle("");
	}
	//Begining of file is the file extension, setAllEmpty
        else if(file.indexOf(".") == 0){
            setAuthor("");
            setTitle("");
        }
	//For all filenames with no space and '.' for file extension
	//set Author emptyString and Title is the substring without .fileextension
        else if(file.length() == counter && file.length() > 1){
            setAuthor("");
            setTitle(file.substring(0,file.indexOf(".")));
	    //System.out.println("File: <" + file + ">");
	    //System.out.println("Substring <" + file.substring(0,file.indexOf(".")) + ">");
        }
	//Exist only " " and "-" in filename, setAllEmpty
	else if(file.length() == counter1){
	    setAuthor("");
	    setTitle("");
	}
	//Is the first Character '-', setAuthorEmpty and Title is '-'+'so on'
	else if(file.charAt(0) == '-'){
	    setAuthor("");
	    setTitle(file);
	}
	else if(file.length() == namecounter){
	    setAuthor("");
	    setTitle(file);
	}
	else{
	   
	    //Replace not needed " "
	    for (int i = 0; i < file.length() - 1; i++){
		if (file.charAt(i) == ' ' && file.charAt(i+1) == ' '){
		    file = replaceCharAt(file, i, '\0');
		}
	    }
	    file = removeAllEmptyChar(file);
	    //Replace the first Characters are " "
	    if (file.indexOf(" ") == 0){
		file = file.substring(1,file.length());
		file = removeAllEmptyChar(file);
	    }
	    //Cut of the .extension
	    if (file.lastIndexOf(".") > 0){
		file = file.substring(0,file.lastIndexOf("."));
	    }
	    //Replace the last used! Character if it is " "
	    if (file.lastIndexOf(" ") == file.length()-1){
		file = replaceCharAt(file, file.lastIndexOf(" "), '\0');
		file = removeAllEmptyChar(file);
	    }
	    if (file.indexOf("-") == file.indexOf(" ")+1 ||
	    file.indexOf("-") ==  file.indexOf(" ")-1){
		setAuthor(file.substring(0,file.indexOf(" ")));
		setTitle(file.substring(file.indexOf("-")+2,file.length()));
	    }
	    if (file.indexOf("-") != file.indexOf(" ")+1 ||
		file.indexOf("-") !=  file.indexOf(" ")-1){

		int dashcounter = 0;
		int spacecounter = 0;
		List<Integer> dashpos  = new ArrayList<Integer>();
		//List<String> spacepos  = new ArrayList<String>();
		dashpos.add(dashcounter);
		//spacepos.add(spacecounter);

		for (int i = 0; i < file.length(); i++){

		    if (file.charAt(i) == '-' && file.charAt(i-1) == ' ' || file.charAt(i) == '-' &&
		    file.charAt(i+1) == ' '){
			dashpos.add(i);
			dashcounter++;
		    }
		}

		setAuthor(file.substring(0,dashpos.get(dashcounter)-1));
		setTitle(file.substring(dashpos.get(dashcounter)+2,file.length()));
		//for (int i = 0; i < dashpos.size(); i++){
		
		//    if (

		//}
		//setAuthor(file);
            }
	}
    }

    public String toString(){

	if(getAuthor().isEmpty()){
	    return getTitle();
	}
	else{
	    return getAuthor() + " - " + getTitle();
	}

    }
    //Setter---------------------------------
    public void setFilename(String filename){
	if (!filename.equals("")){
	    filename = removeAllEmptyChar(filename);
	}
	parsedFilename = filename;

    }
    public void setPathname(String pathname){
	if (!pathname.equals("")){
	    pathname = removeAllEmptyChar(pathname);
	}
	parsedPathname = pathname;
    } 

    public void setAuthor(String filename){
	if (filename.indexOf(" ") == 0){
	    for(int i = 0; i < filename.length(); i++){
		filename = filename.substring(1,filename.length());
	    }
	}
	author = filename;
    }

    public void setTitle(String filename){
	if (filename.indexOf(" ") == 0){
	    for(int i = 0; i < filename.length(); i++){
                filename = filename.substring(1,filename.length());
	    }
	}
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
     
    
    }



}
