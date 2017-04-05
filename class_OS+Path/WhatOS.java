import java.io.*;
import java.lang.Object;
import java.lang.String;

public class WhatOS{

    private static String operatingSystem = System.getProperty("os.name").toLowerCase();

    public static void main(String[] args){
	
	String s = "Hallo.txt";
	System.out.println(new File(s).getAbsolutePath());
	System.out.println(operatingSystem);
	System.out.println(java.io.File.separatorChar);
	
	if(operatingSystem.equals("linux")){

	    System.out.println("This is Unix/Linux");
	}
//	else if(operatingSystem.equals("windows");
    }



}
