package Books_exercises.JavaReceptury.structure;

// BEGIN main
public class AutoboxDemo {
    
    /** Przykład przedstawia automatyczną konwersję liczby na obiekt 
     * (w wywołaniu metody foo(i)) oraz automatyczną konwersję obiektu 
     * na liczbę (konwertowany jest obiekt zwracany z metody foo).
     */
    public static void main(String[] args) {
        int i = 42;
        int result = foo(i);            // <1>
        System.out.println(result);
    }

    public static Integer foo(Integer i) {
        System.out.println("Obiekt = " + i);
        return Integer.valueOf(123);    // <2>
    }
}
// END main
