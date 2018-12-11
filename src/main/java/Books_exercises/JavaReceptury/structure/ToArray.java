package Books_exercises.JavaReceptury.structure;

import java.util.ArrayList;
import java.util.List;

/** Konwersja listy na tablicę. */
public class ToArray {
    public static void main(String[] args) {
        // BEGIN main
        List<String> list = new ArrayList<>();
        list.add("Blobbo");
        list.add("Cracked");
        list.add("Dumbo");

        // Konwertujemy kolekcję na tablicę Object[], która może 
        // zawierać obiekty dowolnego typu.
        Object[] ol = list.toArray();
        System.out.println("Tablica obiektów Object ma długość " + ol.length);

        String[] sl = (String[]) list.toArray(new String[0]);
        System.out.println("Tablica obiektów String ma długość " + sl.length);
        // END main
    }
}
