import java.util.Scanner;


public class Weapons {

  private double w_length;
  private double w_weigth;
  private double w_caliber;
  private String w_name = new String();
  Scanner input = new Scanner(System.in);

  public void setLength(double a){
    
    w_length = a;  
  }

  public void setWeigth(double a){
    
    w_weigth = a;
  }
  public void setCaliber(double a){
  
    w_caliber = a;
  }
  
  public void setName(){

    w_name = input.next();
  }

  public double getLength(){
    
    return w_length;
  }

  public double getWeigth(){
    
    return w_weigth;
  }

  public double getCaliber(){

    return w_caliber;
  }

  public String getName(){

    return w_name;
  }

  public static void main(String[] args){
    double length, weigth, caliber;
    Weapons w = new Weapons(); 
    System.out.println("In diesem Programm können Waffen hinzugefügt werden");
    Scanner input = new Scanner(System.in);
    System.out.print("Waffenlänge: ");
    length = input.nextDouble();
    w.setLength(length);
    System.out.println("Länge: " + w.getLength());
    System.out.print("Gewicht der Waffe: ");
    weigth = input.nextDouble();
    w.setWeigth(weigth);
    System.out.println("Gewicht: " + w.getWeigth());
    System.out.print("Kalibergröße: ");
    caliber = input.nextDouble();
    w.setLength(caliber);
    System.out.println("Kaliber: " + w.getCaliber());


  }

}
