package Books_exercises.JavaReceptury.lang;

/**
 * Czy można zmienić długość tablicy używając właściwośći .length?
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class ChangeArrayLength {
    public static void main(String[] argv) {
        // BEGIN main
        int[] a = new int[4];
        System.out.println(a.length);
        a.length = 5;    // MOŻNA OCZEKIWAĆ BŁĘDU KOMPILACJI
        // END main
    }
}
