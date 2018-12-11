package Books_exercises.JavaReceptury.structure;

import java.util.Arrays;
import java.util.Collection;

// BEGIN main
public class IterableForEach {

    public static void main(String[] args) {
        Collection<String> c =                          // <1>
                Arrays.asList("jeden", "dwa", "trzy") ; // <2>
        c.forEach(s -> System.out.println(s));          // <3>
    }
}
// END main
