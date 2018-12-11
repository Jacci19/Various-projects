package Books_exercises.JavaReceptury.functional;

// BEGIN main
import java.util.Arrays;
import java.util.Comparator;

public class ReferencesDemo4 {

    static final String[] unsortedNames = { 
        "Gosling", "de Raadt", "Torvalds", "Ritchie", "Hopper"
    };
    
    public static void main(String[] args) {
        String[] names;
        
        // Sortowanie z wykorzystaniem 
        // "metody instancyjnej dowolnego obiektu konkretnego typu"
        names = unsortedNames.clone();
        Arrays.sort(names, String::compareToIgnoreCase);                    // <1>
        dump(names);

        // Analogiczne sortowanie z użyciem wyrażenia lambda:
        names = unsortedNames.clone();
        Arrays.sort(names, (str1, str2) -> str1.compareToIgnoreCase(str2)); // <2>
        dump(names);
        
        // Analogiczne sortowanie wykonane w standardowy sposób:
        names = unsortedNames.clone();
        Arrays.sort(names, new Comparator<String>() {                       // <3>
            @Override
            public int compare(String str1, String str2) {
                return str1.compareToIgnoreCase(str2);
            }
        });
        dump(names);
        
        // Najprostszy sposób sortowania, z użyciem istniejące komparatora.
        names = unsortedNames.clone();
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);                  // <4>
        dump(names);
    }
    // END main

    /** Prosta metoda wyświetlająca zawartość przekazanej tablicy,
     * której używamy by pokazać kolejność przechowywanych łańcuchów. */
    private static void dump(String[] names) {
        for (String s : names) {
            System.out.print(s);
            System.out.print(' ');
        }
        System.out.println();
    }

}
