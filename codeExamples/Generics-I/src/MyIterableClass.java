// From
// http://stackoverflow.com/questions/5836174/java-iterator-and-iterable
import java.util.Iterator;

class MyIterableClass implements Iterable<String>{
    public String[] a=null; //make this final if you can
    public MyIterableClass(String[] arr){
        a=arr; //maybe you should copy this array, for fear of external modification
    }

    //the interface is sufficient here, the outside world doesn't need to know
    //about your concrete implementation.
    public Iterator<String> iterator(){
        //no point implementing a whole class for something only used once
        return new Iterator<String>() {
            private int count=0;
            //no need to have constructor which takes MyClass, (non-static) inner class has access to instance members
            public boolean hasNext(){
                //simplify
                return count < a.length;
            }
            public String next(){
                return a[count++]; //getting clever
            }

            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }
    
    public static void main(String[] args){
        MyIterableClass mc =
                new MyIterableClass(new String[]{"alpha","beta","gamma"});
        for (String s: mc){
            System.out.println(s);
        }
    }
}