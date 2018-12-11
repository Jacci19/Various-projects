package Books_exercises.JavaReceptury.reflection.cooklet;

/** Prosta klasa mająca jedynie udostępniać listę metod, które 
 * należy zaimplementować, by móc jej używać w naszej aplikacji.
 * Należy zwrócić uwagę, że klasa jest abstrakcyjna, zatem trzeba
 * napisać jej klasę potomną; z drugiej strony same metody nie 
 * są abstrakcyjne, zatem jeśli konkretne możliwości funkcjonalne
 * nie będą nam potrzebne, to definiowanie danej metody nie będzie
 * konieczne.
 */
public abstract class Cooklet {

    /** Metoda inicjalizacyjna. Aplikacja Cookie wywoła ją 
     * (po wywołaniu konstruktora bezargumentowego klasy), aby 
     * zapewnić możliwość inicjalizacji kodu.
     */
    public void initialize() {
    }

    /** Metoda robocza. Metoda Cookie wywoła ją, kiedy nadejdzie 
     * czas, na rozpoczęcie wypiekania.
     */
    public void work() {
    }

    /** Metoda kończąca. Aplikacja Cookie wywoła ją, kiedy nadejdzie 
     * czas, by zakończyć wypiekanie i właściwie zakończyć działanie
     * programu.
     */
    public void terminate() {
    }
}