package Books_exercises.JavaReceptury.strings;

/**
 * Kontrola wielkości liter.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Case {
    public static void main(String[] argv) {
        // BEGIN main
       String name = "Java - Receptury";
       System.out.println("Naturalna wielkość:\t" + name);
       System.out.println("Duże litery:\t" + name.toUpperCase());
       System.out.println("Małe litery:\t" + name.toLowerCase());
       String javaName = "java - recePtury"; // Gdyby to były identyfikatory Javy :-)
        if (!name.equals(javaName))
            System.err.println("equals() poprawnie zwraca wartość false");
        else
            System.err.println("equals() niepoprawnie zwraca wartość true");
        if (name.equalsIgnoreCase(javaName))
            System.err.println("equalsIgnoreCase() poprawnie zwraca wartość true");
        else
            System.err.println("equalsIgnoreCase() niepoprawnie zwraca wartość false");
        // END main
    }
}
