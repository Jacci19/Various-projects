package Books_exercises.JavaReceptury.numbers;

/** Obliczenie palindromu liczby poprzez dodanie do niej drugiej liczby 
 * utworzonej z cyfr oryginalnej liczby zapisanych w odwrotnej kolejności.
 * Operacja powtarzana aż do uzyskania palindromu.
 * Na przykład: 42->66 (42+24); 1951->5995 (1951+1591=3542; 3542+2453=5995).
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Palindrome {

    public static boolean verbose = true;

    public static void main(String[] argv) {
        for (int i=0; i<argv.length; i++)
            try {
                long l = Long.parseLong(argv[i]);
                if (l < 0) {
                    System.err.println(argv[i] + " -> ZBYT MAŁA");
                    continue;
                }
                System.out.println(argv[i] + "->" + findPalindrome(l));
            } catch (NumberFormatException e) {
                System.err.println(argv[i] + "-> NIEWŁAŚCIWA");
            } catch (IllegalStateException e) {
                System.err.println(argv[i] + "-> " + e);
            } 
    }

    /** Odnajdywanie palindromu na podstawie liczby początkowej.
     * Metoda rekurencyjnie wywołuje samą siebie aż do momentu 
     * odnalezienia palindromu.
     */
    static long findPalindrome(long num) {
        if (num < 0)
            throw new IllegalStateException("ujemna");
        if (isPalindrome(num))
            return num;
        if (verbose)
             System.out.println("Sprawdzam liczbę " + num);
        return findPalindrome(num + reverseNumber(num));
    }

    /** Ilość cyfr w liczbie Long.MAX_VALUE. */
    protected static final int MAX_DIGITS = 19;

    // Tablica cyfr jest wykorzystywana przez metody
    // isPalindrome oraz reverseNumber, które nie mogą
    // być wykonywane w tym samym czasie.

    /* Tablica statyczna, aby nie trzeba jej było tworzyć
     * za każdym razem.
     */
    static long[] digits = new long[MAX_DIGITS];

    /** Sprawdzenie, czy liczba jest palindromem. */
    static boolean isPalindrome(long num) {
        // Pojedynczą cyfrę uważamy za palindrom.
        if (num >= 0 && num <= 9)
            return true;

        int nDigits = 0;
        while (num > 0) {
            digits[nDigits++] = num % 10;
            num /= 10;
        }
        for (int i=0; i<nDigits/2; i++)
            if (digits[i] != digits[nDigits - i - 1])
                return false;
        return true;
    }

    static long reverseNumber(long num) {
        int nDigits = 0;
        while (num > 0) {
            digits[nDigits++] = num % 10;
            num /= 10;
        }
        long ret = 0;
        for (int i=0; i<nDigits; i++) {
            ret *= 10;
            ret += digits[i];
        }
        return ret;
    }
}
// END main
