public class Substring{

    public static String replaceCharAt(String s, int pos, char c){

	return s.substring(0, pos) + c + s.substring(pos + 1);
    }

    public static StringBuilder removeBlankSpace(StringBuilder sb) {
	
	int j = 0;
	for(int i = 0; i < sb.length(); i++) {
	    if (!Character.isWhitespace(sb.charAt(i))) {
		sb.setCharAt(j++, sb.charAt(i));
	    }
	}
	sb.delete(j, sb.length());
	return sb;
    }

    public static String removeAllEmptyChar(String s){
	StringBuilder sb = new StringBuilder(s);
	int j = 0;

	for(int i = 0; i < sb.length(); i++){
	    if (sb.charAt(i) != '\0'){
		sb.setCharAt(j++, sb.charAt(i));
	    }
	}
	sb.delete(j, sb.length());
	return sb.toString();
    

    }

    public static void main(String[] args){
    
	StringBuilder dd = new StringBuilder(" ///   Hallo");

	String s = "hallo.mp3";
	String v = "";
	System.out.println(s.substring(0,s.indexOf("l")));
	System.out.println(s.indexOf(".") + " " + s.lastIndexOf("."));
	System.out.println(s.substring(0,s.indexOf(".")));
	System.out.println(v.length());

	String f = " hallo ";
	System.out.println("f.length() <" + f.length());
	System.out.println("f.lastIndexOf() <" + ((f.lastIndexOf(" ")+1)));

	String a = "   Hallo";
	String b = "Hallo";

	for(int i = 0; i < a.length(); i++){

	    if(Character.isWhitespace(a.charAt(i))){

		a = a.replaceAll(" ","");
	    }

	}
	
	StringBuilder df = new StringBuilder(a);

	dd = removeBlankSpace(dd);	
	
	String ss = "Hallo";
	ss = replaceCharAt(ss,0,'\0');
	ss = replaceCharAt(ss,1,'\0');
	System.out.println(ss + " " + ss.length());
	ss = removeAllEmptyChar(ss);
	System.out.println(ss + " " + ss.length());

	//System.out.println("<" + dd + ">");
	//System.out.println(dd.length());
	//System.out.println(a.length());
	//System.out.println(b.length());
	//System.out.println(df);
    }
}
