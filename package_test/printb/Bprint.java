package printb;

import printa.Aprint;
import java.io.*;
 public class Bprint{
 
    Bprint(){};
 
    public static void bPrinting(){
        System.out.println("Das printet B statisch");
    }
 
    public static void main(String[] args){
    
	Aprint ap = new Aprint();
	ap.aPrinting("A Test");
    
	bPrinting();
        
    
    }
 
 }
