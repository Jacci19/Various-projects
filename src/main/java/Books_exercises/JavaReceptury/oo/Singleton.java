package Books_exercises.JavaReceptury.oo;

/** Przykład implementacji wzorca projektowego Singleton w języku Java,
 * korzystający z inicjalizatora. Wzorce ten został opisany w książce
 * Wzorce projektowe. Elementy oprogramowania obiektowego wielokrotnego użytku
 * (wydawnictwo Helion); a idea jego działania polega na zapewnieniu, 
 * że w danej aplikacji będzie istnieć tylko jeden obiekt danej klasy.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Singleton {

    private static Singleton instance;

    /** Prywatny konstruktor uniemożliwia innym klasom tworzenie 
     * obiektów tej klasy. */
    private Singleton() {
        // Tym razem nie ma tu nic do zrobienia.
    }
    
    /** Inicjalizator statyczny tworzy obiekt w momencie wczytywania 
     * klasy; rozwiązanie to symuluje nieco bardzie złożony proces
     * tworzenia obiektu (gdyby to naprawdę było bardzo łatwe, 
     * to zapewne użylibyśmy zwyczajnego inicjalizatora).
     */
    static {
        instance = new Singleton();
    }

    /** Statyczna metoda zwracająca obiekt. */
    public static Singleton getInstance() {
        return instance;
    }

    // Inne metody, do których dostęp jest chroniny przez singleton...  

    /** Prosta metoda demonstracyjna. */
    public String demoMethod() {
        return "demonstracja...";
    }
}
// END main
