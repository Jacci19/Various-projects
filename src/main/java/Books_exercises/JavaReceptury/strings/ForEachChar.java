package Books_exercises.JavaReceptury.strings;

// BEGIN main
public class ForEachChar {
    public static void main(String[] args) {
        String s = "Witaj, świecie";
        // for (char ch : s) {...} Nie działa w Java 7
        for (char ch : s.toCharArray()) {
            System.out.println(ch);
        }
    }
}
// END main
