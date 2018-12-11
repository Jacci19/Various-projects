package Books_exercises.JavaReceptury.oo;

/* Demonstracja przesłanianai metody toString(). */
// BEGIN main
public class ToStringWith {
    int x, y;

    /** Prosty konstruktor. */
    public ToStringWith(int anX, int aY) {
        x = anX; y = aY;
    }

    @Override
    public String toString() {
        return "ToStringWith[" + x + "," + y + "]";
    }
    
    /** Metoda main jedynie tworzy i wyświetla obiekt. */
    public static void main(String[] args) { 
        System.out.println(new ToStringWith(42, 86));
    }
}
// END main
