package Books_exercises.JavaReceptury.structure;

import java.util.Arrays;
import java.util.List;

public class ArraysAsListDemo {

    public static void main(String[] args) {
        List<String> firstNames = Arrays.asList("Robert", "Janek", "Jurek");
        for (String fn : firstNames) {
            System.out.println(fn);
        }
        List<String> lastNames = Arrays.asList(new String[]{"Kowalski", "Baranowski", "Kruczyński"});
        System.out.println(lastNames);
    }
}