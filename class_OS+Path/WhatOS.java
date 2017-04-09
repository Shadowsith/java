import java.io.*;
import java.lang.Object;
import java.lang.String;

public class WhatOS{

    private static String operatingSystem = System.getProperty("os.name").toLowerCase();

    public static String replaceCharAt(String s, int pos, char c) {

        return s.substring(0, pos) + c + s.substring(pos + 1); 
    }   




    public static void main(String[] args){

	int count;
	int counter = 0;
	String d = "Hallo";
	String s = "/file.mp3/";
	String f = "file.mp3\\";
	String n = "/ \t hallo / ffg /  f";
	String g = "      ";
	System.out.println(new File(s).getAbsolutePath());
	System.out.println(operatingSystem);
	System.out.println(java.io.File.separatorChar);
	System.out.println("last character: " + f.substring(f.length() - 1));
	count = s.length() - s.replace("/", "").length();	
	System.out.println("Numbers of / in String s: " + count);
	n = n.replace(" ", "");
	n = n.replace("\t", "");
	System.out.println(n);
	System.out.println(g.length());
	
	for( int i = 0; i < g.length(); i++){
	    if( g.charAt(i) == ' '){
		counter++;
	    }
	}

	for (int i = 0; i < d.length(); i++){

	  if ( d.charAt(i) == 'l' && d.charAt(i-1) == 'l'){
	       d = replaceCharAt(d, i, '\0');
	  }

	}
	System.out.println(d);


	System.out.println(g.length() - counter);
        
        if(g.length() == counter){
            System.out.println("Besteht nur aus Leerzeichen");
        
        }

	if(f.substring(f.length() - 1).equals("\\")){
	    System.out.println("Es funktioniert :D");
	}
	else System.out.println("Irgendwas ist schief gelaufen");
	
	if(operatingSystem.equals("linux")){

	    System.out.println("This is Unix/Linux");
	}
//	else if(operatingSystem.equals("windows");
	String m = "Hallo/";
	System.out.println(n.length());
	System.out.println(m.indexOf('/'));
    }



}
