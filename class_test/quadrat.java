public class quadrat{
    double sidesize;

    quadrat(double a){
	sidesize = a;
    }

    double area(){
	return sidesize*sidesize;
    }

    public static void main(String args[]){
	quadrat q = new quadrat(2.5);
	System.out.println("Size: " + q.area());
    }
}
