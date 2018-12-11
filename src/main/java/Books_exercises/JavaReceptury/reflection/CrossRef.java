package Books_exercises.JavaReceptury.reflection;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Program CrossRef wyświetla listę z informacjami na temat wszystkich
 * klas podanych w wierszu wywołania programu (przekazany w argv).
 * Dla każdej z klas wyświetlane są wszystkie publiczne metody i pola.
 * Do pobrania informacji wykorzystywane są mechanizmy introspekcji.
 *
 * Oczekuje się, że wyniki zwracane przez ten program zostaną 
 * poddane dalszemu przetwarzaniu, na przykład przy użyciu poleceń
 * sort oraz skryptów awt/perl. Spróbuj:
    java CrossRef | 
        uniq | # kompresja polimorficznych form
        sort | awk '$2=="method" { ... }' > crossref-methods.txt
 * To, co należy zapisać w nawiasach "{ ... }" pozostawiam jako 
 * ćwiczenie dla czytelnika. :-(
 *
 * @author    Ian Darwin, Ian@DarwinSys.com
 */
// BEGIN main
public class CrossRef extends APIFormatter {

    /** Prosty program główny; tworzymy obiekt CrossRef i przetwarzamy każdy 
     * plik .ZIP znaleziony w katalogach podanych w zmiennej środowiskowej 
     * CLASSPATH lub w argv.
     */
    public static void main(String[] argv) throws IOException {
        CrossRef xref = new CrossRef();
        xref.doArgs(argv);
    }

    /**
     * Metoda wyświetla pola i metody jednej klasy.
     */
    protected void doClass(Class<?> c) {
        startClass(c);
        try {
            Field[] fields = c.getDeclaredFields();
            Arrays.sort(fields, new Comparator<Field>() {
                public int compare(Field o1, Field o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            for (int i = 0; i < fields.length; i++) {
                Field field = (Field)fields[i];
                if (!Modifier.isPrivate(field.getModifiers()))
                    putField(field, c);
                // else System.err.println("Zignorowano pole prywatne: " + field);
            }

            Method methods[] = c.getDeclaredMethods();
            Arrays.sort(methods, new Comparator<Method>() {
                public int compare(Method o1, Method o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            for (int i = 0; i < methods.length; i++) {
                if (!Modifier.isPrivate(methods[i].getModifiers()))
                    putMethod(methods[i], c);
                // else System.err.println("Metoda prywatna: " + methods[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        endClass();
    }

    /** Zapisujemy informacje pobrane z obiektów Field w 
     * standardowym strumieniu wyjściowym.  */
    protected void putField(Field fld, Class<?> c) {
        println(fld.getName() + " field " + c.getName() + " ");
    }

    /** Zapisujemy informacje pobrane z obiektów Method w 
     * standardowym strumieniu wyjściowym.   */
    protected void putMethod(Method method, Class<?> c) {
        String methName = method.getName();
        println(methName + " method " + c.getName() + " ");
    }

    /** Wyświetlamy początek klasy. Metoda nie jest używana w tej 
     * wersji programu; została zaprojektowana, aby przesłonić ją 
     * w przyszłości. */
    protected void startClass(Class<?> c) {
    }

    /** Wyświetlamy koniec klasy. Metoda nie jest używana w tej 
     * wersji programu; została zaprojektowana, aby przesłonić ją 
     * w przyszłości. */
    protected void endClass() {
    }

    /** Metoda pomocnicza, skrócona wersja wywołania System.out.println */
    protected final void println(String s) {
        System.out.println(s);
    }
}
// END main
