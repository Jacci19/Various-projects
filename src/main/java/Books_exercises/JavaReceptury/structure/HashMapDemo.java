package Books_exercises.JavaReceptury.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Program przedstawia wykorzystanie klas HashMap 
 * oraz Iterator.
 * see HashTableDemo, prezentujący starszą klasę Hashtable.
 */
// BEGIN main
public class HashMapDemo {

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

        // Trzy sposoby pobierania danych.
        // Sposób 1.: pobieramy wartość skojarzoną z danym kluczem
        // (w standardowych przypadkach klucz będzie określany
        // przez dane wprowadzone przez użytkownika).
        String queryString = "O'Reilly";
        System.out.println("Zapytałeś o firmę " + queryString + ".");
        String resultString = map.get(queryString);
        System.out.println("Jej siedziba znajduje się w: " + resultString);
        System.out.println();

        // Sposób 2.: pobieramy wszystkie klucze i skojarzone z nimi
        // wartości (może w celu wydrukowania raportu lub zapisania 
        // informacji na dysku).
        for( String key : map.keySet()) {
            System.out.println("Klucz " + key + 
                "; wartość " + map.get(key));
        }
        
        // Sposób 3: jak wyżej, lecz z użyciem lambda Map.Entry.
        map.entrySet().forEach(mE -> 
            System.out.println("Klucz + " + mE.getKey()+ 
                "; wartość " +mE.getValue()));
    }
}
// END main
