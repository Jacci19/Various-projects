package Books_exercises.JavaReceptury.reflection.cooklet;

/**
 * Ta część aplikacji Cookies odpowiada za pobranie klasy 
 * użytkownika.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Cookies {
    public static void main(String[] argv) {
        System.out.println("Aplikacja \"Ciasteczka\", wersja 0.0");
        Cooklet cooklet = null;
        String cookletClassName = argv[0];
        try {
            Class<Cooklet> cookletClass =
                (Class<Cooklet>) Class.forName(cookletClassName);
            cooklet = cookletClass.newInstance();
        } catch (Exception e) {
            System.err.println("Błąd " + cookletClassName + e);
        }
        cooklet.initialize();
        cooklet.work();
        cooklet.terminate();
    }
}
// END main
