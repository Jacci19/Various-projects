package Books_exercises.JavaReceptury.i18n;

import java.text.*;
import java.util.*;

/** Używa niektórych, domyślnie wybranych ustawień lokalnych; 
 * można także wywołać -Duser.lang= lub -Duser.region=.
 */
// BEGIN main
public class UseLocales {
    public static void main(String[] args) {

        Locale frLocale = Locale.FRANCE;           // Predefiniowane.
        Locale ukLocale = new Locale("en", "UK");  // Angielski,
                                                   // wersja Wielka Brytania.

        DateFormat defaultDateFormatter = DateFormat.getDateInstance(
            DateFormat.MEDIUM);
        DateFormat frDateFormatter = DateFormat.getDateInstance(
            DateFormat.MEDIUM, frLocale);
        DateFormat ukDateFormatter = DateFormat.getDateInstance(
            DateFormat.MEDIUM, ukLocale);

        Date now = new Date();
        System.out.println("Domyślnie: " + ' ' +
            defaultDateFormatter.format(now));
        System.out.println(frLocale.getDisplayName() + ' ' +
            frDateFormatter.format(now));
        System.out.println(ukLocale.getDisplayName() + ' ' +
            ukDateFormatter.format(now));
    }
}
// END main
