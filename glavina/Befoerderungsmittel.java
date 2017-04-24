public class Befoerderungsmittel{

    //Attr.
    protected int gewicht;
    private int maxGeschw;
    private int anzSitze;

    //Const.
    public Befoerderungsmittel(){
        gewicht = -1;
        maxGeschw = -2;
        anzSitze = -3;
    }
    public void print(){
    System.out.println("Gewicht: " + gewicht);
    System.out.println("Hoechstgesch: " + maxGeschw);
    System.out.println("Sitzplaetze: " + anzSitze);
    }

    public static void main(String [] args){

        System.out.println("Ein Befoerderungsmittel: ");
        Befoerderungsmittel b = new Befoerderungsmittel();
        b.print();
        System.out.println("Ein Fahrzeug (mit 4 Reader):");
        Fahrzeug f = new Fahrzeug(4);
        f.print();
        System.out.println("... ein Sportwagen mit 850kg:");
        Sportwagen s = new Sportwagen(850);
        s.print();
    }
} //end

class Fahrzeug extends Befoerderungsmittel{

    //Attr.

    private int anzRaeder;

    //Const.
    public Fahrzeug(int r){
        anzRaeder = r;
    }

    public void print(){
        super.print();
        System.out.println("Raederzahl: " + anzRaeder);
    }
}

class Sportwagen extends Fahrzeug{

    //Attr.
    private boolean hatBreitreifen;

    //Const.

    public Sportwagen(int g){
    
    super(4); //explizieter Aufruf des Oberklassenconst
              //an erster Stelle im Code
    gewicht = g;
    hatBreitreifen = false;

    }

    public void print(){

        super.print(); 
        System.out.println("Breitreifen:" + hatBreitreifen);
    }
}



