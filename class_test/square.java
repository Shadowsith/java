import java.util.Scanner;

public class square{
   private double sidelength;
    
    public square(){
    }

    public void setSidelength(){
	Scanner scan = new Scanner(System.in);	    //Eingabestream
	System.out.print("Gebe eine Zahl ein: ");
	sidelength = scan.nextDouble();
    }

    public double getSidelength(){
        return sidelength;
    }

    public double area(){
	return sidelength*sidelength;
    }

    public static void main(String[] args){
	square q = new square();
	q.setSidelength();
        System.out.println("Seitenlängen: " + q.getSidelength());
	System.out.println("Fläche: " + q.area());
	rectangle r = new rectangle();
	r.setRectangle();
	System.out.println("Länge: " + r.getLength());
	System.out.println("Breite: " + r.getWidth());
	System.out.println("Fläche: " + r.area());
	
    }
}
