package Books_exercises.JavaReceptury.structure;

import java.util.Comparator;

// BEGIN main
/** Klasa porównuje łańcuchy znaków bez uwzględniania pierwszego
 * znaku.
 */
public class SubstringComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        s1 = s1.substring(1);
        s2 = s2.substring(1);
        return s1.compareTo(s2);
        // Lub bardziej jawnie:
        // return o1.substring(1).equals(o2.substring(1));
    }
}
// END main
