package Books_exercises.JavaReceptury.environ;

import com.darwinsys.lang.GetOpt;

/** Bardzo prosty przykład wykorzystania klasy GetOpt. 
 * Jeśli została użyta opcja -h, wyświetlamy pomoc.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */

// BEGIN main
public class GetOptSimple {
    public static void main(String[] args) {
        GetOpt go = new GetOpt("h");
        char c;
        while ((c = go.getopt(args)) != 0) {
            switch(c) {
            case 'h':
                helpAndExit(0);
                break;
            default:
                System.err.println("Nieznana opcja w " +
                    args[go.getOptInd()-1]);
                helpAndExit(1);
            }
        }
        System.out.println();
    }

    /** Miejsce na wyświetlenie pomocy.
     * Oczywiście można wyświetlać znacznie obszerniejsze informacje
     * niż te przedstawione poniżej.
     */
    static void helpAndExit(int returnValue) {
        System.err.println("Tutaj można umieścić informacje, ");
        System.err.println("jak korzystać z tego programu.");
        System.exit(returnValue);
    }
}
// END main
