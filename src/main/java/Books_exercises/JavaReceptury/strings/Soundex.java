package Books_exercises.JavaReceptury.strings;

/**
 * Soundex - algorytm Soundex, opisany przez Knutha.
 * <p>
 * Ta klasa implementuje algorytm soundex opisany przez Donalda 
 * Knutha w trzecim tomie książki <I>The Art of Computer Programming</I>.
 * Algorytm ten został opracowany w celu klasyfikacji nazw i łączenia 
 * ich w niewielkie grupy przy wykorzystaniu prostego modelu szacującego 
 * brzmienie słowa wymawianego w języku angielskim. Każde słowo jest 
 * zamieniane na łańcuch składający się z czterech znaków, z których 
 * pierwszy jest wielką literą, a pozostałe trzy - cyframi. Podwójne 
 * litery są łączone i zastępowane jedną cyfrą.
 * <h2>PRZYKŁADY</h2>
 * Przykłady różnych nazwisk oraz odpowiadające im kody soundex, podane 
 * przez Knutha:
 * <b>Euler, Ellery -> E460
 * <b>Gauss, Ghosh -> G200
 * <b>Hilbert, Heilbronn -> H416
 * <b>Knuth, Kant -> K530
 * <b>Lloyd, Ladd -> L300
 * <b>Lukasiewicz, Lissajous -> L222
 * 
 * <h2>OGRANICZENIA</h2>
 * Ponieważ algorytm Soundex był używany <B>dawno</B> temu w Stanach 
 * Zjednoczonych, obsługuje on wyłącznie angielski alfabet 
 * i wymowę. 
 * <p>
 * Ponieważ algorytm przekształca długie łańcuchy (o dowolnej długości) 
 * na łańcuchy krótkie (składające się z litery oraz trzech znaków),
 * na podstawie uzyskania tego samego kodu nie można 
 * wnioskować o podobieństwie oryginalnych łańcuchów znaków. Na przykład
 * "Hilbert" oraz "Heilbronn" dają te same kody soundex o wartości
 * "H416".
 * <p>
 * Metoda soundex() jest metodą statyczną, gdyż nie zawiera żadnych 
 * informacji dotyczących konkretnego egzemplarza obiektu.
 *
 * @author Implementacja w języku Perl - Mike Stok 
 * (<stok@cybercom.net>) na podstawie opisu podanego przez Knutha.  
 * Ian Phillips (<ian@pipex.net>) oraz Rich Pinder (<rpinder@hsc.usc.edu>) 
 * dostarczyli dodatkowych pomysłów oraz szukali błędów.
 * @author Ian Darwin, ian@darwinsys.com (Java Version)
 */
// BEGIN main
public class Soundex {
    
    static boolean debug = false;

    /* Implementacja kojarzenia
     * z:  AEHIOUWYBFPVCGJKQSXZDTLMNR
     * na: 00000000111122222222334556
     */
    public static final char[] MAP = {
        //A  B   C   D   E   F   G   H   I   J   K   L   M
        '0','1','2','3','0','1','2','0','0','2','2','4','5',
        //N  O   P   Q   R   S   T   U   V   W   X   Y   Z
        '5','0','1','2','6','2','3','0','1','0','2','0','2'
    };

    /** Konwersja podanego łańcucha znaków na kod Soundex.
     * @return null - jeśli łańcucha nie można zamienić na kod Soundex.
     */
    public static String soundex(String s) {

        // Algorytm operuje wyłącznie na wielkich literach (era 
        // komputerów mainframe).
        String t = s.toUpperCase();

        StringBuffer res = new StringBuffer();
        char c, prev = '?', prevOutput = '?';

        // Pętla główna: odszukaj do 4 znaków i zamień je na cyfry.
        for (int i=0; i<t.length() && res.length() < 4 &&
            (c = t.charAt(i)) != ','; i++) {
 
            // Sprawdzamy, czy dany znak należy do alfabetu.
            // Tekst został już wcześniej zapisany wielkimi literami. 
            // Algorytm obsługuje wyłącznie litery należące do kodu 
            // ASCII. Nie należy używać metody Character.isLetter()!!
            // Algorytm pomija podwójne litery.
            if (c>='A' && c<='Z' && c != prev) {
                prev = c;

                // Pierwszy znak jest kopiowany w oryginalnej postaci,
                // aby umożliwić sortowanie.
                if (i==0) {
                    res.append(c);
                } else {
                    char m = MAP[c-'A'];
                    if (debug) {
                        System.out.println(c + " --> " + m);
                    }
                    if (m != '0' && m != prevOutput) {
                        res.append(m);
                        prevOutput = m;
                    }
                }
            }
        }
        if (res.length() == 0)
            return null;
        for (int i=res.length(); i<4; i++)
            res.append('0');
        return res.toString();
    }
}

// END main
