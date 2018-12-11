package Books_exercises.JavaReceptury.structure;

import java.util.ArrayList;
import java.util.List;

/** Fragment kodu pokazujący w jaki sposób można dodawać elementy
 * do list (ArrayList AWT List) w miejscu określonym porządkiem sortowania,
 * wykorzystując przy tym proste wyszukiwanie sekwencyjne (w obiekcie 
 * ArrayList), w celu odnalezienia obiektu (lub końca listy), przed którym
 * należy wstawić dodawny obiekt.
 */
// BEGIN main
public class CallTrack {

    /** Lista obiektów Person. */
    protected List<Person> usrList = new ArrayList<>();

    /** Lista z możliwością przewijania. */
    protected java.awt.List visList = new java.awt.List();

    /** Dodajemy do listy jeden (nowy) obiekt Person, przy czym 
     * cały czas jej zawartość jest posortowana. */
    protected void add(Person p) {
        String lastName = p.getLastName();
        int i;
        // Odnajdujemy i-tą pozycję na liście, gdzie należy wstawić
        // nową osobę.
        for (i=0; i<usrList.size(); i++)
            if (lastName.compareTo((usrList.get(i)).getLastName()) <= 0)
                break; // Bez tej instrukcji, obiekt zostałby dodany
                       // na końcu listy.
        usrList.add(i, p);

        // Teraz dodajemy nową osobę do listy, którą można przewijać,
        // umieszczając ją w tym samym miejscu.
        visList.add(p.getName(), i);
        visList.select(i);      // A teraz zaznaczamy nową osobę.
    }

}
// END main
