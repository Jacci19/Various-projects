package Books_exercises.JavaReceptury.otherlang;

/**
 * ExecDemo - program pokazuje, jak wykonać zewnętrzny program i przechwycić 
 * generowane przez niego wyniki.
 */
// BEGIN main
public class ExecDemoSimple {
    public static void main(String av[]) throws Exception {
        
        // Uruchamiamy program "notepad" lub inny podobny edytor.
        Process p = Runtime.getRuntime().exec("kwrite");

        p.waitFor();
    }
}
// END main
