public class quadrat{
   private double sidesize;

    public quadrat(double a){
	sidesize = a;
    }

    public double getSidesize(){
        return sidesize;
    }

    public double area(){
	return sidesize*sidesize;
    }

    public static void main(String[] args){
	quadrat q = new quadrat(2.5);
        System.out.println("Seitenlängen: " + q.getSidesize());
	System.out.println("Fläche: " + q.area());
    }
}
