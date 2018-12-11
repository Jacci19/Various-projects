package Books_exercises.JavaReceptury.numbers;

import java.util.*;

/** Wyświetla bieżący rok w formie liczby zapisanej przy użyciu 
 * cyfr rzymskich. 
 */

// BEGIN main
public class RomanYear {

    public static void main(String[] argv) {

        RomanNumberFormat rf = new RomanNumberFormat();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        // Jeśli nie zostały podane żadne argumenty wywołania,
        // wyświetlamy sam rok.
        if (argv.length == 0) {
            System.out.println(rf.format(year));
            return;
        }
        
        // W przeciwnym przypadku "mikronarzędzie formatujące" zastępuje
        // "-" liczbą rzymską reprezentującą bieżący rok
        for (int i=0; i<argv.length; i++) {
            if (argv[i].equals("-"))
                System.out.print(rf.format(year));
            else
                System.out.print(argv[i]);    // Na przykład "Copyright"
            System.out.print(' ');
        }
        System.out.println();
    }
}
// END main
