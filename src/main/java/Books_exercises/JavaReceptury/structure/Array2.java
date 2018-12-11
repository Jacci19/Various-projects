package Books_exercises.JavaReceptury.structure;

import java.util.Date;

/** Tworzymy nową większą tablicę...
 * @author Ian Darwin
 */
// BEGIN main
public class Array2  {
    public final static int INITIAL = 10,   // <1>
        GROW_FACTOR = 2;                    // <2>

    public static void main(String[] argv) {
        int nDates = 0;
        Date[] dates = new Date[INITIAL];
        StructureDemo source = new StructureDemo(21);
        Date c;
        while ((c=(Date)(source.getDate())) != null) {

            // if (nDates >= dates.length) {
            //     System.err.println("Zbyt wiele dat! Uprość swoje życie!!");
            //     System.exit(1);  // Kończymy.
            // }

            // Lepsze: ponowne przydzielenie pamięci, zapewniające 
            // dynamiczność struktury.
            if (nDates >= dates.length) {
                Date[] tmp = new Date[dates.length * GROW_FACTOR];
                System.arraycopy(dates, 0, tmp, 0, dates.length);
                dates = tmp;       // Kopiujemy odwołanie do tablicy.
                // Oryginalna tablica zostanie wkrótce usunięta 
                // przez narzędzia oczyszczania pamięci...
            }
            dates[nDates++] = c;
        }
        System.out.println("Końcowa wielkość tablicy = " + dates.length);
    }
}
// END main
