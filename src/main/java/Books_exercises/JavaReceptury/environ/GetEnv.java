package Books_exercises.JavaReceptury.environ;

/**
 * Prezentacja działania metody System.getenv().
 * Ta bardzo prosta, zależna od systemu operacyjnego, metoda działała
 * w werajch języka 1.1 (i może 1.2), lecz została odrzucona w wersji 
 * 1.3; w wersji 1.4 zgłasza wyjątki, natomiast od wersji 1.5 ponownie
 * działa.
 */
// BEGIN main
public class GetEnv {
    public static void main(String[] argv) {
        System.out.println("System.getenv(\"PATH\") = " + System.getenv("PATH"));
    }
}
// END main
