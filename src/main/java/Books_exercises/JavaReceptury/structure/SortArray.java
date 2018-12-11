package Books_exercises.JavaReceptury.structure;

import java.util.Arrays;

/** Przedstawienie zastosowania metody Arrays.sort()
 */
// BEGIN main
public class SortArray {
    public static void main(String[] unused) {
        String[] strings = {
            "bolesny", 
            "głównie",
            "opad",
            "krople"
        };
        Arrays.sort(strings);
        for (int i=0; i<strings.length; i++) {
            System.out.println(strings[i]);
        }
    }
}
// END main
