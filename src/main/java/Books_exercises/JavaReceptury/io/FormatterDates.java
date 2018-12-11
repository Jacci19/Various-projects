package Books_exercises.JavaReceptury.io;

import java.util.Calendar;
import java.util.Date;

/** Przedstawia wzorce wykorzystania i przykłady kodów formatujących
 * stosowanych przez klasę Formatter.  
 */
// BEGIN main
public class FormatterDates {
    public static void main(String[] args) {

        // Formatuje daty jako daty, np. 2014-06-28 
        System.out.printf("%4d-%02d-%2d%n", 2014, 6, 28);

        // Formatuje pola bezpośrednio z obiektu Date: wszystkie kody
        // odwołują się do pierwszego parametru ("1$"), zakodowane
        // formaty określające postać dat nie są dostępne, patrz rozdział
        // poświęcony tworzeniu programów wielojęzycznych.
        Date today = Calendar.getInstance().getTime();
        // Wyświetla datę w postaci: 4 września 2015:
        System.out.printf("Dzisiaj jest %1$td %1$tB %1$tY r.%n", today);
    }
}
// END main
