package Books_exercises.JavaReceptury.structure;

import java.util.TreeSet;

/**
 * Przedtawienie wykorzystania klasy TreeSet.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class TreeSetDemo {
    public static void main(String[] argv) {
        // BEGIN main
        /* Obiekt klasy TreeSet przechowuje obiekty w uporządkowanej          
         * kolejności. W przykładzie używamy sortowania, w którym              
         * wielkość liter nie ma znaczenia, posługując się               
         * przy tym obiektem Comparator dostępnym w klasie                   
         * String.                                                            
         */
        TreeSet<String> theSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        theSet.add("Gosling");
        theSet.add("da Vinci");
        theSet.add("van Gogh");
        theSet.add("Java To Go");
        theSet.add("Vanguard");
        theSet.add("Darwin");
        theSet.add("Darwin");    // TreeSet to Set (zbiór), wartości              
                                 // powtarzające się są pomijane.                  

        System.out.printf("Nasz zbiór zawiera %d elementów.", theSet.size());
        
        // Ponieważ dane są posortowane, można pobierać ich różne              
        // podzbiory.                                                         
        System.out.println("Najmniejszym (alfabetycznie) elementem jest " + theSet.first());
        
        // Wyświetlamy wszystkie elementy większe od "k".
        // Powinny być 2 takie elementy: "van Gogh" oraz "Vanguard"
        System.out.println("Dostępne są " + 
            theSet.tailSet("k").toArray().length + 
            " elementy większe od \"k\"");                                  

        // Wyświetlamy całą posortowaną listę.
        System.out.println("Posortowana lista:");
        theSet.forEach(name -> System.out.println(name));
        // END main
    }
}
