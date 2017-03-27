import java.util.Scanner;

 public class Rectangle{

    double length;
    double width;

    public rectangle(){
    }

    public void setSize(){

	Scanner scan = new Scanner(System.in);	    //Eingabestream
	System.out.print("Gebe die Länge ein: ");
	length = scan.nextDouble();
	System.out.print("Gebe die Breite ein: ");
	width = scan.nextDouble();
    }

    public double getLength(){
	
	return length;
    }

    public double getWidth(){

	return width;
    }
    

    public double area(){

	return length*width;
    
    }
}

