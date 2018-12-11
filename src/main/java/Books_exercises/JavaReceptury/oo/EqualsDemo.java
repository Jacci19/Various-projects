package Books_exercises.JavaReceptury.oo;

// BEGIN main
public class EqualsDemo {
    private int int1;
    private SomeClass obj1;

    /** Konstruktor */
    public EqualsDemo(int i, SomeClass o) {
        int1 = i;
        if (o == null) {
            throw new IllegalArgumentException("Obiekt danych musi być różny od null.");
        }
        obj1 = o;
    }

    /** Konstruktor domyślny */
    public EqualsDemo() {
        this(0, new SomeClass());
    }

    /** Typowa implementacja metody equals() */
    @Override
    public boolean equals(Object o) {
        if (o == this)                    // <1> Optymalizacja.
            return true;

        if (o == null)                    // <2> Żaden obiekt nie jest równy null.
            return false;
        
        // Czy można rzutować do tej klasy?
        if (o.getClass() != EqualsDemo.class) // <3>
            return false;

        EqualsDemo other = (EqualsDemo)o; // OK, można rzutować.

        // Porównanie poszczególnych pól.       // <4>
        if (int1 != other.int1)           // Typy proste porównujemy bezpośrdnio.
            return false;
        if (!obj1.equals(other.obj1))     // A obiekty przy użyciu metody equals().
            return false;
        return true;
    }
}
// END main
