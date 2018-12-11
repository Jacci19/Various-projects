package Books_exercises.JavaReceptury.structure;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Prezentacja wykorzystania klasy java.util.LinkedList,
 * wykorzystuje to samo rozwiązanie co klasa moja klasa LinkList.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class LinkedListDemo {
    public static void main(String[] argv) {
        System.out.println("Prezentacja klasy LinkedList.");
        LinkedList<String> l = new LinkedList<>();
        l.add(new Object().toString());
        l.add("Witam");
        l.add("koniec listy");

        System.out.println("Oto lista wszystkich elementów");
        ListIterator li = l.listIterator(0);
        while (li.hasNext())
            System.out.println("Kolejny element: " + li.next());

        if (l.indexOf("Witam") < 0)
            System.err.println("Nie udało się odnaleźć");
        else
            System.err.println("Odnaleziono");

        // A teraz, w ramach dodatkowej zabawy, pobierzemy wszystkie
        // elementy listy w odwrotnej kolejności.
        while (li.hasPrevious()) {
            System.out.println("Poprzedni element: " + li.previous());
        }
    }
}
// END main
