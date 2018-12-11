package Books_exercises.JavaReceptury.structure;

import java.util.ArrayList;
import java.util.Date;

/**
 * Prezentacja klasy ArrayList.
 */
// BEGIN main
public class ArrayListDemo {
    public static void main(String[] argv) {
        ArrayList<Date> al = new ArrayList<>();

        // Tworzymy źródło obiektów.
        StructureDemo source = new StructureDemo(15);

        // Dodajemy do obiektu ArrayList wiele innych obiektów.
        al.add(source.getDate());
        al.add(source.getDate());
        al.add(source.getDate());

        // Wyświetlamy je przy użyciu tradycyjnej postaci pętli for.
        System.out.println("Pobieramy według indeksów:");
        for (int i = 0; i<al.size(); i++) {
            System.out.println("Element " + i + " = " + al.get(i));
        }
    }
}
// END main
