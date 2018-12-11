package Books_exercises.JavaReceptury.strings;

/**
 * Konwersja znaków Unicode na łańcuchy znaków.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class UnicodeChars {
    public static void main(String[] argv) {
        // BEGIN main
        StringBuffer b = new StringBuffer();
        for (char c = 'a'; c<'d'; c++) {
            b.append(c);
        }
        b.append('\u00a5');    // Japoński symbole jena.
        b.append('\u01FC');    // Litera AE alfabetu łacińskiego z ostrym akcentem.
        b.append('\u0391');    // Grecka litera alfa (duża).
        b.append('\u03A9');    // Grecka litera omega (duża).

        for (int i=0; i<b.length(); i++) {
            System.out.printf(
                "Znak #%d (%04x) to %c%n",
                i, (int)b.charAt(i), b.charAt(i));
        }
        System.out.println("Uzyskany łańcuch znaków " + b);
        // END main
    }
}
