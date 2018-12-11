package Books_exercises.JavaReceptury.structure;

import java.util.Arrays;
import java.util.Random;

/** Gra w przeszukiwanie tablicy (niezbyt ciekawa bo komputer
 * gra sam ze sobą).
 * @author Ian Darwin
 */
// BEGIN main
public class ArrayHunt  {
    /** Maksymalna (i aktualna) liczba rezerwowanych losowych
     * wartości typu int. */
    protected final static int MAX    = 4000;
    /** Poszukiwana wartość. */
    protected final static int NEEDLE = 1999;
    int[] haystack;
    Random r;

    public static void main(String[] argv) {
        ArrayHunt h = new ArrayHunt();
        if (argv.length == 0)
            h.play();
        else {
            int won = 0;
            int games = Integer.parseInt(argv[0]);
            for (int i=0; i<games; i++)
                if (h.play())
                    ++won;
            System.out.println("Komputer wygrał " + won + 
                " gier z " + games + ".");
        }
    }

    /** Kontruktor pola poszukiwań */
    public ArrayHunt() {
        haystack = new int[MAX];
        r = new Random();
    }

    /** Gramy jedną grę. */
    public boolean play() {
        int i;

        // Wypełniamy tablicę losowymi liczbami
        for (i=0; i<MAX; i++) {
            haystack[i] = (int)(r.nextFloat() * MAX);
        }

        // Warunkiem wstępnym wyszukiwania binarnego jest 
        // przekazanie posortowanej tabeli - zatem sortujemy ją.
        Arrays.sort(haystack);

        // Szukamy igły w stogu siana
        i = Arrays.binarySearch(haystack, NEEDLE);

        if (i >= 0) {        // Znaleziona - wygraliśmy
            System.out.println("Wartość " + NEEDLE +
                " jest zapisana jako haystack[" + i + "]");
            return true;
        } else {        // Nieznaleziona - przegraliśmy.
            System.out.println("Wartość " + NEEDLE +
                " nie występuje w przeszukiwanej tablicy; najbliższą wartością jest " +
                haystack[-(i+2)] + " (o indeksie " + -(i+2) + ")");
            return false;
        }
    }
}
// END main
