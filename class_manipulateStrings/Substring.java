public class Substring{


    public static void main(String[] args){
    
	String s = "hallo.mp3";
	String v = "";
	System.out.println(s.substring(0,s.indexOf("l")));
	System.out.println(s.indexOf(".") + " " + s.lastIndexOf("."));
	System.out.println(s.substring(0,s.indexOf(".")));
	System.out.println(v.length());
	 
	file = "file.mp3";
        int counter = 0;
        int counter1 = 0;
        for(int i = 0; i < file.length(); i++){
            if(file.charAt(i) != ' '){
                counter++;
            }
            if(file.charAt(i) == ' ' || file.charAt(i) == '-'){
                counter1++;
            }
        }
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
        else if(file.length() == counter){
            setAuthor("");
            setTitle(file.substring(0,file.indexOf(".")));
            System.out.println("File: <" + file + ">");
            System.out.println("Substring <" + file.substring(0,file.indexOf(".")) + ">");

	}

}
