package Books_exercises.JavaReceptury.darwinsys.csv;

import java.util.List;

/* Prosta demonstracja klasy analizatora CSV. */
public class CSVSimple {

    public static void main(String[] args) {
        CSVImport parser = new CSVImport();
        List<String> list = parser.parse(
             "\"LU\",86.25,\"11/4/1998\",\"2:19PM\",+4.0625");
        
        for (String word : list) {
            System.out.println(word); 
        }
        
        // A teraz testujemy przy użyciu separatora innego niż 
        // separator domyślny.
        parser = new CSVImport('|');
        list = parser.parse(
              "\"LU\"|86.25|\"11/4/1998\"|\"2:19PM\"|+4.0625");
        for (String word : list) {
            System.out.println(word);
        }
    }
}