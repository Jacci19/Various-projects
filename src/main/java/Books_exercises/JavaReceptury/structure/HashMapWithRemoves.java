package Books_exercises.JavaReceptury.structure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Prezentacja zastosowania klasy HashMap, interfejsu Iterator oraz 
 * usuwania współbieżnego.
 */
public class HashMapWithRemoves {

    public static void main(String[] argv) {

        // Tworzymy i wypełniamy tablicę mieszającą. 
        // Symulujemy w ten sposób pobranie danych z bazy
        // lub z pliku albo z dowolnego innego miejsca, 
        // w którym są one zapisane.

        Map<String,String> map = new HashMap<String,String>();

        // Tablica kojarzy nazwy firm z ich adresami.
        // W normalnym programie nazwy firmy mogłyby 
        // zostać skojarzone z jakimiś niestandardowymi 
        // obiektami Address.
        map.put("Adobe", "Mountain View, CA");
        map.put("IBM", "White Plains, NY");
        map.put("Learning Tree", "Los Angeles, CA");
        map.put("Microsoft", "Redmond, WA");
        map.put("Netscape", "Mountain View, CA");
        map.put("O'Reilly", "Sebastopol, CA");
        map.put("Sun", "Mountain View, CA");

        // BEGIN SafeRemoval
        // Sposób 2.: pobieramy wszystkie klucze i skojarzone z nimi
        // wartości (może w celu wydrukowania raportu lub zapisania 
        // informacji na dysku).
        Iterator<String> it = map.keySet( ).iterator( );
        while (it.hasNext( )) {
            String key = it.next( );
            if (key.equals("Sun")) {
                it.remove();
                continue;
            }
            System.out.println("Firma " + key + "; " +
                "adres " + map.get(key));
        }
        // END SafeRemoval
    }
}
