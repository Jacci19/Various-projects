// BEGIN main
package Books_exercises.JavaReceptury.structure;

import com.darwinsys.util.ArrayIterator;

public class ArrayIteratorDemo {
    
    private final static String[] names = {
        "róża", "petunia", "tulipan"
    };
    
    public static void main(String[] args) {
        ArrayIterator<String> arrayIterator = new ArrayIterator<>(names);

        // Sposób dostępny w wersjach języka Java 5 i 6 
        for (String s : arrayIterator) {
            System.out.println(s);
        }
        
        // Sposób dostępny w wersji języka Java 8 
        arrayIterator.forEach(s->System.out.println(s));
    }
}
// END main
