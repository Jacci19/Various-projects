package Books_exercises.JavaReceptury.lang;

// BEGIN main
/**
 * AnnotationOverrideDemo - Prosty przykład zastosowania metadanych do
 * sprawdzenia, czy metoda z klasy bazowej jest przesłaniana
 * (a nie przeciążana). Ta klasa udostępnia metodę.
 */
abstract class Top {
    public abstract void myMethod(Object o);
}

/** Prosty przykład zastosowania metadanych do
 * sprawdzenia, czy metoda z klasy bazowej jest przesłaniana
 * (a nie przeciążana). W tej klasie metoda klasy bazowej ma
 * zostać przesłonięta; kod celowo jednak zawiera błąd, aby
 * pokazać działanie nowoczesnych kompilatorów.
 */
class Bottom {

    @Override
    public void myMethod(String s) {    // MOŻNA OCZEKIWAĆ BŁĘDU KOMPILACJI
        // Tu coś robimy...
    }
}
// END main
