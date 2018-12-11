package Books_exercises.JavaReceptury.strings;


/** Prosta klasa obsługująca znaki tabulacji.
 * N.B. Może obsługiwać wyłącznie punkty tabulacji występujące
 * w równych odległościach.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Tabs {
    /** Punkty tabulacji co podaną liczbę znaków. */
    public final static int DEFTABSPACE =   8;
    /** Bieżące ustawienie punktów tabulacji. */
    protected int tabSpace = DEFTABSPACE;
    /** Maksymalna długość wiersza, dla jakiego obsługujemy tabulacje. */
    public final static int MAXLINE  = 255;

    /** Tworzymy obiekt Tabs z podanym ustawieniem punktów tabulacji. */
    public Tabs(int n) {
        if (n <= 0) {
            n = 1;
        }
        tabSpace = n;
    }

    /** Tworzymy obiekt Tabs z domyślnym ustawieniem punktów tabulacji. */
    public Tabs() {
        this(DEFTABSPACE);
    }
    
    /**
     * @return Zwraca bieżące ustawienia tabSpace.
     */
    public int getTabSpacing() {
        return tabSpace;
    }
    
    /** isTabStop - zwraca true, jeśli kolumna o podanym numerze jest
     * punktem tabulacji.
     * @param col - bieżący numer kolumny.
     */
    public boolean isTabStop(int col) {
        if (col <= 0)
            return false;
        return (col+1) % tabSpace == 0;
    }
}
// END main
