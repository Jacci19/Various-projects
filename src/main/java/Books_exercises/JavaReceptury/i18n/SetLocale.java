package Books_exercises.JavaReceptury.i18n;

import java.text.*;
import java.util.*;

/** Zmiana domyślnych ustawień lokalnych. */
// BEGIN main
public class SetLocale {
    public static void main(String[] args) {

        switch (args.length) {
        case 0:
            Locale.setDefault(Locale.FRANCE);
            break;
        case 1:
            throw new IllegalArgumentException();
        case 2:
            Locale.setDefault(new Locale(args[0], args[1]));
            break;
        default:
            System.out.println("Sposób użycia: SetLocale [język [kraj]]");
            // Błąd wywołania.
        }

        DateFormat df = DateFormat.getInstance();
        NumberFormat nf = NumberFormat.getInstance();

        System.out.println(df.format(new Date()));
        System.out.println(nf.format(123.4567));
    }
}
// END main
