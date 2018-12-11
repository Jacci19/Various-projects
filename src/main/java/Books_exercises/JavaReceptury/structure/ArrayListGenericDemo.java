package Books_exercises.JavaReceptury.structure;

import java.util.*;

// BEGIN main
public class ArrayListGenericDemo {
    public static void main(String[] args) {
        ArrayList<String> data = new ArrayList<>();
        data.add("Witam");
        data.add("Do widzenia");

        // data.add(new Date()); Tego nie uda się skompilować!

        data.forEach(s -> System.out.println(s));
    }
}
// END main
