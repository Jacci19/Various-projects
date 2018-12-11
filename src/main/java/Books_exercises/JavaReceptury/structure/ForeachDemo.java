package Books_exercises.JavaReceptury.structure;

import java.util.Arrays;
import java.util.List;

public class ForeachDemo {
    
    public static void main(String args[]) {
        
// BEGIN main
        String[] data = { "Toronto", "Sztokholm" };
        for (String s : data) {
            System.out.println(s);
        }
        
        // Prezentacja pętli "foreach" wprowadzonych w wersji Java 5,
        // nie należy zmieniać kodu i dostosowywać do możliwości 
        // wprowadzonych w wersji Java 8.
        List<String> list = Arrays.asList(data);
        for (String s : list) {
            System.out.println(s);
        }
// END main
    }
}
