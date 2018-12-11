package Books_exercises.JavaReceptury.structure;

import java.util.Arrays;

/** Przedstawienie zastosowania klasy SubstringComparator.
 */
// BEGIN main
public class SubstrCompDemo {
    public static void main(String[] unused) {
        String[] strings = {
            "bolesny", 
            "głównie",
            "opad",
            "krople"
        };
        Arrays.sort(strings);
        dump(strings, "Używamy domyślnego sposobu sortowania:");
        Arrays.sort(strings, new SubstringComparator());
        dump(strings, "Używamy klasy SubstringComparator:");

    }
    static void dump(String[] args, String title) {
        System.out.println(title);
        for (int i=0; i<args.length; i++)
            System.out.println(args[i]);
    }
}
// END main
