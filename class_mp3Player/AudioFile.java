import java.util.Arrays;
import java.lang.String;
import java.util.*;
import java.io.File;

public abstract class AudioFile{

    //Variables------------------------------------------------
    private static String operatingSystem = System.getProperty("os.name").toLowerCase();
    private String path = new String();
    private String parsedFilename = new String();
    private String parsedPathname = new String();
    private String author = new String();
    private String title = new String();
    private File f = null;

    //Constructors---------------------------------------------

    public AudioFile(String pathname){

	parsePathname(pathname);
	
	//try{
	    f = new File(getPathname());
	    if(f.canRead() == true){
		System.out.println("File could be read");
	    }
	    else{
		throw new RuntimeException("File: " + getPathname() + " couldn't read");
	    }

	//}
	//catch {}
    
	parseFilename(getFilename());
    }

    public AudioFile() {}

    //Abstract methodes for subclasses-----------------------

    public abstract void play();
    public abstract void togglePause();
    public abstract void stop();
    public abstract String getFormattedDuration();
    public abstract String getFormattedPosition();
    
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
	//ArrayList saves the position of the "/" or "\" 
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
	int counter = 0;    //path is only tabOrSpace
	int counter1 = 0;   //has not '/' or '\'
        for( int i = 0; i < path.length(); i++){
            if(path.charAt(i) == ' ' || path.charAt(i) == '\t'){
                counter++;
            }
	    if(path.charAt(i) != '/' && path.charAt(i) != '\\'){
		counter1++;
	    }
        }   
	if(path.isEmpty()){
	    setVoidString(path);
	}
	else if(path.length() == counter){
	    tabOrSpace(path);
	}
	else if(path.length() == counter1){
	    setPathname(path);
	    setFilename(path);
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
        int nospacecounter = 0;
        int separatorspacecounter = 0;
	int spacetabcounter = 0;
	int namecounter = 0;
	int signcounter = 0;
        
	for(int i = 0; i < file.length(); i++){
            if(file.charAt(i) != ' '){
                nospacecounter++;
            }
            if(file.charAt(0) != '-' && (file.charAt(i) == ' ' || file.charAt(i) == '-')){
		separatorspacecounter++;
            }
	    if(file.charAt(i) == ' ' || file.charAt(i) == '\t'){
		spacetabcounter++;
	    }
	    if((file.charAt(i) == ' ' || file.charAt(i) != ' ') && file.length() > 1){
		if(file.charAt(i) != '-' || file.charAt(i) == '-' && file.charAt(i+1) != ' '){
		    namecounter++;
		}
	    }
	    if(file.charAt(i) != ' ' && file.charAt(i) != '.'){
		    signcounter++;
	    }
        }
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
	else if(file.length() == signcounter){
	    setAuthor("");
	    setTitle(file);
	}
	//For all filenames with no space and '.' for file extension
	//set Author emptyString and Title is the substring without .fileextension
        else if(file.length() == nospacecounter && file.length() > 1){
            setAuthor("");
            setTitle(file.substring(0,file.indexOf(".")));
	    //System.out.println("File: <" + file + ">");
	    //System.out.println("Substring <" + file.substring(0,file.indexOf(".")) + ">");
        }
	else if(file.length() == namecounter){
	    setAuthor("");
	    int j = 0;
	    for(int i = 0; i < file.length(); i++){
		if(file.charAt(0) == ' '){
		    file = file.substring(1,file.length());
		}
	    }
	    for(int i = 0; i < file.length(); i++){
		if(file.charAt(i) == '.'){
		    j = i;
		}
	    }
	    file = file.substring(0,j);
	    setTitle(file);
	}
	//Exist only " " and "-" in filename, setAllEmpty
	else if(file.length() == separatorspacecounter){
	    setAuthor("");
	    setTitle("");
	}
	//Is the first Character '-', setAuthorEmpty and Title is '-'
	else if(file.charAt(0) == '-' && file.length() == 1){
	    setAuthor("");
	    setTitle("-");
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
	    //Set Author/Title, if it the only seperator "-" is there (space before or after)
	    if (file.indexOf("-") == file.indexOf(" ")+1 ||
	    file.indexOf("-") ==  file.indexOf(" ")-1){
		setAuthor(file.substring(0,file.indexOf(" ")));
		setTitle(file.substring(file.indexOf("-")+2,file.length()));
	    }
	    //Set Author/Title, if more "-" are there
	    if (file.indexOf("-") != file.indexOf(" ")+1 ||
		file.indexOf("-") !=  file.indexOf(" ")-1){

		int dashcounter = 0;
		List<Integer> dashpos  = new ArrayList<Integer>();
		dashpos.add(dashcounter);
		
		//Search the separator char
		for (int i = 0; i < file.length(); i++){

		    if (file.charAt(i) == '-' && file.charAt(i-1) == ' ' || file.charAt(i) == '-' &&
		    file.charAt(i+1) == ' '){
			dashpos.add(i);
			dashcounter++;
		    }
		}

		//Cut of the filename in Author and Title
		setAuthor(file.substring(0,dashpos.get(dashcounter)-1));
		setTitle(file.substring(dashpos.get(dashcounter)+2,file.length()));
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

     
    public static void main(String[] args){
    /* 
        List<String> ss = new ArrayList<String>();
        ss.add("");             //0
        String str = "/home/philip/Projekte/gdp2/mediaplayerv07/audiofiles/Eisbach Deep Snow.ogg";
        ss.add("   \t   \t");
        ss.add("  file.mp3");
        ss.add("/my-tmp/\\       Falco - Rock me Amadeus.mp3");
        ss.add("//my-tmp////\\\\\\part1//\\     Hans-Georg Sonstwas - Blue-eyed boy-friend.mp3");
        ss.add("d:\\\\part1///     A.U.T.O.R. - T.I.T.E.L   .EXTENSION"); //5
	ss.add("p");
	ss.add("home/meier/Musik/Falco - Rock Me Amadeus.mp3");
	ss.add("Z:part1/file.mp3/");
	ss.add("/part1/mymusic/");
	ss.add("/nocheinsong/");
	ss.add("-");
	ss.add("Falco - Rock me Amadeus.mp3");
	ss.add(".mp3");

		
	    AudioFile af = new AudioFile(str);
            System.out.println("Input File:  <" + str + ">");
            System.out.println("getPathname: <" + af.getPathname() + ">");
            System.out.println("getFilename: <" + af.getFilename() + ">");
            System.out.println("getAuthor:   <" + af.getAuthor() + ">"); 
            System.out.println("getTitle:    <" + af.getTitle() + ">");
            System.out.println("<" + af.toString() + ">");
            System.out.println();
	
	/*
	AudioFile af = new AudioFile();

        for(int i = 0; i < ss.size(); i++){
            System.out.println("Input File:  <" + ss.get(i) + ">" + " Length " + ss.get(i).length());
            af.parsePathname(ss.get(i));
	    af.parseFilename(ss.get(i));
            System.out.println("getPathname: <" + af.getPathname() + ">" + " Length " +
	    af.getPathname().length());
            System.out.println("getFilename: <" + af.getFilename() + ">" + " Length ");
	    System.out.println("getAuthor:   <" + af.getAuthor() + ">"); 
            System.out.println("getTitle:    <" + af.getTitle() + ">");
	    System.out.println("<" + af.toString() + ">");
	    System.out.println();
        }
	
	
	
	
	/*
	AudioFile af = new AudioFile();
	
	List<String> fl = new ArrayList<String>();
	fl.add("file.mp3");
	fl.add("-");
	fl.add(" - ");
	fl.add(".mp3");
	fl.add(" Falco - Rock me Amadeus .mp3 ");
	fl.add("Falco - Rock me Amadeus.");
	fl.add("          A.U.T.O.R.  -  T.I.T.E.L.EXTENSION");
	fl.add(" Hans-Georg Sonstwas - Blue-eyed boy-friend.mp3");
	
	for(int i = 0; i < fl.size(); i++){
	    af.setFilename(fl.get(i));	
	    af.parseFilename(af.getFilename());
	    System.out.println("Input Filename: <" + fl.get(i) + ">");
	    System.out.println("getAuthor:   <" + af.getAuthor() + ">");
	    System.out.println("getTitle:    <" + af.getTitle() + ">");
	    af.setAuthor("");
	    af.setTitle("");
	    System.out.println();
	}
	*/

    }

}
