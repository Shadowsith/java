import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
// Author: FABR: 28.05.13
// Implement the getter
//    T get(int i)
// that gets the i-th element (in whatever order)
// based on the API of Collections
//
// Pro: just uses the API of interface Collection
// Contra: has linear (bad) complexity.
//
// Purpose:
// Show that basic algorithms can be implemented using
// the basic API of Collection.
// Enhancements of these algorithms are possible for 
// implementing classes if one uses the properties specific
// to the implementation.
// Here: For ArrayList<T> there exists a fast and efficient
// get() method.

public class BasicAlg<T> {
    Collection<T> col;

    public BasicAlg(Collection<T> col) {
        this.col = col;
    }

    public T get(int i) {
        Iterator<T> iter = col.iterator();
        int c = -1;
        T elem = null;
        while (iter.hasNext() && c < i) {
            elem = iter.next();
            c++;
        }
        if (c < i){
            throw new RuntimeException("Sorry, not enough elements");
        } else {
            return elem;
        }
    }
    
    public static void main(String[] args){
        List<Integer> l = new LinkedList<Integer>();
        l.add(0);
        l.add(10);
        l.add(20);
        l.add(30);
        
        BasicAlg<Integer> alg = new BasicAlg<Integer>(l);
        
        for(int i = l.size()-1; i >= 0; i--){
            System.out.printf("elem(%d)=%d\n", i, alg.get(i));
        }
        
        // Provoke exception
        //alg.get(4);  
    }
}
