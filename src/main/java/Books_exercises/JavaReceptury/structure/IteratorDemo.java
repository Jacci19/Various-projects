package Books_exercises.JavaReceptury.structure;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/** Iterator używany do przeglądnięcia zawartości listy (List).
 */
// BEGIN main
public class IteratorDemo {

    public static void main(String[] argv) {

        List<Date> l = new ArrayList<>();
        StructureDemo source = new StructureDemo(15);

        // Dodajemy do listy wiele elementów...
        l.add(source.getDate());
        l.add(source.getDate());
        l.add(source.getDate());

        int i = 0;

        Iterator it = l.iterator();

        // Przetwarzamy strukturę danych, używając obiektu Iterator.
        // Ta część kodu nie wie ani nawet jej nie interesuje,
        // jakiego typu obiekty są zapisane w tablicy, na liście,
        // w obiekcie klasy Vector czy w jakimkolwiek innym obiekcie.
        while (it.hasNext()) {
            Object o = it.next();
            System.out.println("Element " + i++ + " = " + o);
        }
    }
}
// END main
