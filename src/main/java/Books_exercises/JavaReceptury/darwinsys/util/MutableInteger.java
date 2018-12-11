package Books_exercises.JavaReceptury.darwinsys.util;

/** Klasa MutableInteger jest podobna do klasy Integer 
 * lecz daje możliwość zmieniania przechowywanych liczb 
 * całkowitych, w celu uniknięcia zbyt częstego 
 * tworzenia obiektów związanych z wykonywaniem operacji
 * typu: 
 * c = new Integer(c.getInt()+1)
 * które mogą być bardzo kosztowe, jeżli będą wykonywane 
 * zbyt często.
 * Nie jest to klasa potomna klasy Integer, gdyż Integer 
 * jest klasą sfinalizowaną (dla uzyskania wysokiej 
 * efektywności działania:-))
 * @version $Id: MutableInteger.java,v 1.3 2001/10/12 22:47:06 ian Exp $
 */
public class MutableInteger {
    private int value = 0;

    public MutableInteger() {
    }

    public MutableInteger(int i) {
        value = i;
    }

    public void incr() {
        value++;
    }

    public void incr(int amt) {
        value += amt;
    }

    public void decr() {
        value--;
    }

    public void setValue(int i) {
        value = i;
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
