package Books_exercises.JavaReceptury.structure;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Implementacja listy powiązanej napisana w Javie.
 * Ta klasa <b>nie</b> jest przeznaczona do praktycznego wykorzystania
 * i bez wątpienia nie będzie optymalna jeśli chodzi o efektywność działania.
 * Ma ona jedynie pokazać, jak wiele robią gotowe, istniejące już 
 * implementacje list.
 * <br/>
 * TODO: wywalić wszystko i napisać od początku używając AbstractSequentialList
 * jako klasy bazowej.
 * @deprecated    Uzupełniona przez klasę LinkedList.
 * @author    Ian Darwin
 */
@Deprecated
// BEGIN main
public class LinkList<T> implements List<T> {

    /* Klasa TNode przechowuje jeden element czy też węzeł listy. */
    private static class TNode<T> {
        TNode<T> next;
        T data;
        TNode(T o) {
            data = o;
            next = null;
        }
    }

    private boolean DIAGNOSTIC = true;
    
    /** Korzeń, czyli pierwszy obiekt TNode na liście. */
    protected TNode<T> first;
    /** Ostatni obiekt TNode na liście. */
    protected TNode<T> last;

    /** Konstruktor klasy LinkList: inicjalizuje pierwszy i ostatni 
     * węzeł listy. 
     */
    public LinkList() {
        clear();
    }

    /** Konstruktor, który inicjalizuje listę LinkList na podstawie
     * przekazanej kolekcji (implementacji interfejsu Collection).
     * Stosowanie tej metody jest zalecane przez ogólny kontrakt
     * interfejsu List.
     */
    public LinkList(Collection<T> c) {
        this();
        addAll(c);
    }

    /** Metoda przywraca początkowy stan listy.
     * Wszystkie przechowywane w niej odwołania zostaną usunięte.
     */
    public void clear() {
        first = new TNode<T>(null);
        last = first;
    }

    /** Metoda dodaje jeden element na końcu listy. Powoduje to
     * aktualizację odwołania "next" elementu, który do tej pory 
     * był ostatnim, i zapisanie w nim odwołania do nowego 
     * węzła. Modyfikowane jest także pole "last" - zapisujemy 
     * w nim nowy węzeł. 
     */
    public boolean add(T o) {
        last.next = new TNode<T>(o);
        last = last.next;
        return true;
    }

    public void add(int where, T o) {
        TNode<T> t = first;
        for (int i=0; i<=where; i++) {
            t = t.next;
            if (t == null) {
                throw new IndexOutOfBoundsException(
                    "Wywołanie add(n,T) poza zakresem listy.");
            }
            if (DIAGNOSTIC) {
                System.out.printf("add(int,T): i = %d, t = %s%n", i, t);
            }
        }
        TNode<T> t2 = t;
        t.next = new TNode<T>(o);
        t.next = t2;
    }

    public int size() {
        TNode<T> t = first;
        int i;
        for (i=0; ; i++) {
            if (t == null)
                break;
            t = t.next;
        }
        return i - 1;    // Odejmujemy 1 ze względu na obowiązkowy 
                         // pierwszy węzeł listy.
    }

    public boolean isEmpty() {
        return first == last;
    }

    public T get(int where) {
        TNode<T> t = first;
        int i=0; 
        // Jeśli dotarliśmy do końca listy przed pętlą, zgłaszamy błąd.
        while (i<=where) {
            i++;
            if ((t = t.next) == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return t.data;
    }

    public T set(int i, T o) {
        return null;
    }
    
    public boolean contains(Object o) {
            TNode<T> t = first;
        while ((t = t.next) != null) {
            if (t.data.equals(o)) {
                return true;
            }
        }
        return false;
    }
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(o -> add((T) o));
        return false;
    }

    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("listIterator");
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            TNode<T> t = first.next;
            public boolean hasNext() {
                return t != last;
            }
            public T next() {
                if (t == last)
                    throw new IndexOutOfBoundsException();
                return (T) (t = t.next);
            }
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }
    // END main

    // PONIŻSZE METODY NIE ZOSTAŁY JESZCZE ZAIMPLEMENTOWANE!

    public Object[] toArray() {
        return null;
    }

    public T[] toArray(Object[] data) {
        return null;
    }

    public boolean remove(Object o) {
        return false;
    }

    public T remove(int i) {
        return null;
    }

    public boolean containsAll(Collection c) {
        return false;
    }
    public boolean addAll(int i, Collection c) {
        return false;
    }

    public boolean removeAll(Collection c) {
        return false;
    }

    public boolean retainAll(Collection c) {
        return false;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<T> listIterator(int where) {
        return null;
    }

    public List<T> subList(int sub1, int sub2) {
        return null;
    }
}
