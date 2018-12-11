package Books_exercises.JavaReceptury.reflection.cooklet;

// BEGIN main
public class DemoCooklet extends Cooklet {
    public void work() {
        System.out.println("Jestem zajęty wypiekaniem ciasteczek.");
    }
    public void terminate() {
        System.out.println("Teraz wyłączam piekarnik.");
    }
}
// END main
