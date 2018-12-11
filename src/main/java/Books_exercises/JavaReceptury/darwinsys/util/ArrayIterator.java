// BEGIN main
package Books_exercises.JavaReceptury.darwinsys.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Klasa demonstruje implementację interfejsów Iterator oraz
 * Iterable, pokazują jednocześnie jak napisać prosty iterator
 * operujący na tablicy obiektów.
 * @author    Ian Darwin, http://www.darwinsys.com/
 */
public class ArrayIterator<T> implements Iterable<T>, Iterator<T> {
    /** Dane, które będą przeglądane. */
    protected T[] data;

    protected int index = 0;

    /** Konstruktor obiektu ArrayIterator.
     * @param d Tablica przeglądanych obiektów.
     */
    public ArrayIterator(final T[] d) {
        setData(d);
    }

    /** (Ponownie) ustawiamy przeglądaną tablicę na tę podaną 
     * w wywołaniu metody i przywracamy początkowy stan iteratora.
     * @param d Tablica przeglądanych obiektów.
     */
    public void setData(final T[] d) {
        this.data = d;
        index = 0;
    }

    // -------------------
    // Metody interfejsu Iterable
    // -------------------

    @Override
    public Iterator<T> iterator() {
        index = 0;
        return this; // Ponieważ klasa główna implementuje oba interfejsy.
    }

    // -------------------
    // Metody interfejsu Iterator
    // -------------------


    /** 
     * Metoda informuje, czy jest dostępny następny element.
     * @return true, jeśli nie jesteśmy na końcu, czyli jeśli wywołanie
     *   next() zakończy się pomyślnie.
     * @return false, jeśli wywołanie next() spowoduje zgłoszenie wyjątku.
     */
    @Override
    public boolean hasNext() {
        return (index < data.length);
    }

    /** Metoda zwraca kolejny obiekt danych. */
    @Override
    public T next() {
        if (hasNext()) {
            return data[index++];
        }
        throw new NoSuchElementException(
              "Dostępnych jest jedynie " + data.length + " elementów.");
    }

    /** Metoda usuwa obiekt zwrócony przez metodę next().
     * Iterator nie jest zobowiązany implementować tej 
     * metody, z czego skorzystamy :-).
     * @throws UnsupportedOperationException Bezwarunkowo.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException(
          "Ten program demonstracyjny nie implementuje metody remove().");
    }
}
// END main
