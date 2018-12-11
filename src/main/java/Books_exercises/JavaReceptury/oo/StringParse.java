package Books_exercises.JavaReceptury.oo;

import com.darwinsys.lang.MutableInteger;

/** Prezentacja wykorzystania klasy MutableInteger 
 * w celu przekazywania dodatkowej wartości, oprócz 
 * właściwej wartości wynikowej zwracanej przez metodę.
 */
// BEGIN main
public class StringParse {
    /** Ta metoda zwraca wartość logiczną, a jednocześnie
     * przekazuje wartość całkowitą określającą położenie 
     * w łańcuchu znaków, gdzie odnaleziono poszukiwaną liczbę.
     */
    public static boolean parse(String in, char lookFor, 
        MutableInteger whereFound) {

        int i = in.indexOf(lookFor);
        if (i == -1)
            return false;    // Nie znaleziono.
        whereFound.setValue(i);    // Zwróć miejsce, gdzie znaleziono.
        return true;        // Poinformuj, że znaleziono.
    }

    public static void main(String[] args) {
        MutableInteger mi = new MutableInteger();
        String text = "Witaj, świecie";
        char c = 'W';
        if (parse(text, c, mi)) {
            System.out.println("Litera " + c + 
                " odnaleziona na pozycji " + mi + " w łańcuchu " + text);
        } else {
            System.out.println("Nie odnaleziono litery.");
        }
    }
}
// END main
