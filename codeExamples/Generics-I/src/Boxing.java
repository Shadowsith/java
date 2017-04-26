import java.util.LinkedList;

public class Boxing {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        LinkedList<Integer> lint = new LinkedList<Integer>();
        lint.add(2);  // Boxing von 2
        lint.add(3);  // Boxing von 3
        lint.add(4);  // Boxing von 4

        // Auto(un)boxing
        int x = new Integer(5);     // Unboxing von Integer(5)
        Integer y = 5;              // Boxing von 5
        int z = new Integer(3) +2;  // Unboxing von Integer(3)
        
        for (int i = 0; i < lint.size(); ++i) {
            System.out.println("Integer " + lint.get(i));    //Unboxing von Integer-Element in Liste)
        }
    }
}
