// BEGIN main
package Books_exercises.JavaReceptury.darwinsys.lang;

/** Klasa MutableInteger jest podobna do klasy Integer, lecz daje 
 * możliwość zmieniania przechowywanych liczb całkowitych w celu 
 * uniknięcia zbyt częstego tworzenia obiektów związanych 
 * z wykonywaniem operacji typu: 
 * c = new Integer(c.getInt()+1),
 * które mogą być bardzo kosztowne, jeśli będą wykonywane zbyt często.
 * Nie jest to klasa potomna klasy Integer, gdyż Integer jest klasą 
 * sfinalizowaną (dla uzyskania wysokiej efektywności działania:-))
 */
public class MutableInteger {
    private int value = 0;

    public MutableInteger(int i) {
        value = i;
    }
    
    public MutableInteger() {
        this(0);
    }

    public int incr() {
        value++;
        return value;
    }

    public int incr(int amt) {
        value += amt;
        return value;
    }

    public int decr() {
        value--;
        return value;
    }

    public int setValue(int i) {
        value = i;
        return value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return Integer.toString(value);
    }

    public static String toString(int val) {
        return Integer.toString(val);
    }

    public static int parseInt(String str) {
        return Integer.parseInt(str);
    }
}
// END main
