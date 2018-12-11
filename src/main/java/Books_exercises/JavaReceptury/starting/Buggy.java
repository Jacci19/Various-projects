package Books_exercises.JavaReceptury.starting;

// BEGIN main
/** W tym programie celowo wprowadzono kilka błędów, dzięki czemu 
 * można go sprawdzić przy użyciu programu uruchomieniowego. */
public class Buggy {
    static String name;

    public static void main(String[] args) {
        int n = name.length();    // błąd # 1
        System.out.println(n);

        name += "; Koniec.";    // błąd #2
        System.out.println(name); // #3
    }
}
// END main
