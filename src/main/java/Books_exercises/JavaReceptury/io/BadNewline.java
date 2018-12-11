package Books_exercises.JavaReceptury.io;

/**
 * NIE RÓB TAK! TEN KOD JEST NIEPRAWIDŁOWY!!
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class BadNewline {
    // BEGIN main
    String myName;
    public static void main(String[] argv) {
        BadNewline jack = new BadNewline("Hermenegilda Kociubińska");
        System.out.println(jack);
    }
    /**
     * NIE RÓB TAK! TEN KOD JEST NIEPRAWIDŁOWY!!
     */
    public String toString() {
        return "BadNewlineDemo@" + hashCode() + "\n" + myName;
    }

    // Postać konstruktora jest oczywista, dlatego jej nie pokazuję,  
    // jest dostępna w przykładach.
    // END main
    
    /* Konstruktor klasy. */
    public BadNewline(String s) {
        myName = s;
    }
}
