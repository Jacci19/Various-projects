package Books_exercises.JavaReceptury.reflection;

import java.lang.reflect.Field;
import java.util.Calendar;

/** Program przedstawia sposób wykorzystania możliwości introspekcji
 * w celu pobrania pola innej klasy i wyświetlenia jego wartości. */
// BEGIN main
public class FindField {

    public static void main(String[] unused) 
    throws NoSuchFieldException, IllegalAccessException {

        // Tworzymy obiekt FindField.
        FindField gf = new FindField();

        // Tworzymy obiekt klasy docelowej (w tym przykładzie jest to 
        // zdefiniowana poniżej klasa YearHolder).
        Object o = new YearHolder();

        // Wykorzystujemy obiekt gf, aby pobrać pole z obiektu o.
        System.out.println("Pole 'currentYear' ma wartość: " +
            gf.intFieldValue(o, "currentYear"));
    }

    int intFieldValue(Object o, String name)
    throws NoSuchFieldException, IllegalAccessException {
        Class<?> c = o.getClass();
        Field fld = c.getField(name);
        int value = fld.getInt(o);
        return value;
    }
}

/** To klasa, która posłuży nam do pobrania wartości pola. */
class YearHolder {
    /** Pole używane do zademonstrowania sposobu pobierania wartości pola. */
    public int currentYear = Calendar.getInstance().get(Calendar.YEAR);
}
// END main
