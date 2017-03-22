public class square{
   private double sidesize;
    
    public square(){
    }

    public void setSidesize(double a){
	sidesize = a;
    }

    public double getSidesize(){
        return sidesize;
    }

    public double area(){
	return sidesize*sidesize;
    }

    public static void main(String[] args){
	square q = new square();
	q.setSidesize(2.5);
        System.out.println("Seitenlängen: " + q.getSidesize());
	System.out.println("Fläche: " + q.area());
    }
}
