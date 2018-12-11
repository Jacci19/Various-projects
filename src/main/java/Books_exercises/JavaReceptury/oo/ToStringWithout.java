package Books_exercises.JavaReceptury.oo;

/* Prezentacja metody toString() (bez przesłaniania). */
// BEGIN main
public class ToStringWithout {
    int x, y;

    /** Prosty konstruktor. */
    public ToStringWithout(int anX, int aY) {
        x = anX; y = aY;
    }

    /** Metoda main - tworzy i wyświetla obiekt. */
    public static void main(String[] args) {
        System.out.println(new ToStringWithout(42, 86));
    }
}
// END main
