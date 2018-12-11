package Books_exercises.JavaReceptury.structure;

import java.util.HashSet;
import java.util.Set;

/**
 * Prezentacja zastosowania interfejsu Set.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class SetDemo {
    public static void main(String[] argv) {
        // BEGIN main
        Set<String> hashSet = new HashSet<>();
        hashSet.add("jeden");
        hashSet.add("dwa");
        hashSet.add("jeden"); // POWTÓRZENIE  
        hashSet.add("trzy");
        hashSet.forEach(s -> System.out.println(s));
        // END main
    }
}
