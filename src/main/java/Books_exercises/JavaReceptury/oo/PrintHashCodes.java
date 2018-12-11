package Books_exercises.JavaReceptury.oo;

/** Wyświetla kody mieszające (zwracane przez metodę hashCode())
 * kilku obiektów. */
// BEGIN main
public class PrintHashCodes {

    /** Obiekty, dla których wyświetlimy kody mieszające. */
    protected static Object[] data = {
        new PrintHashCodes(),
        new java.awt.Color(0x44, 0x88, 0xcc),
        new SomeClass()
    };

    public static void main(String[] args) {
        System.out.println("Obliczamy kod mieszający " + data.length + " obiektów.");
        for (int i=0; i<data.length; i++) {
            System.out.println(data[i].toString() + " --> " + 
                data[i].hashCode());
        }
        System.out.println("Gotowe.");
    }
}
// END main
