package Books_exercises.JavaReceptury.environ;

import com.darwinsys.lang.GetOpt;
import com.darwinsys.lang.GetOptDesc;
import java.util.*;

/** Program demonstruje nowoczesny sposób korzystania z klasy GetOpt. 
 * Program akceptuje podzbiór <pre>opcji polecenia sort systemów
 * UNIX: sort -n -o outfile infile1 infile2</pre>.
 * Opcja '-n' oznacza sortowanie numeryczne, opcja '-o' pozwala na 
 * podanie pliku wyjściowego, natomiast infile1 i infile2 to dwa
 * pliki wejściowe.
*/

// BEGIN main
public class GetOptDemoNew {
    public static void main(String[] argv) {
        boolean numeric_option = false;
        boolean errs = false;
        String outputFileName = null;

        GetOptDesc[] options = {
            new GetOptDesc('n', "numeric", false),
            new GetOptDesc('o', "output-file", true),
        };
        GetOpt parser = new GetOpt(options);
        Map<String,String> optionsFound = parser.parseArguments(argv);
        for (String key : optionsFound.keySet()) {
            char c = key.charAt(0);
            switch (c) {
                case 'n':
                    numeric_option = true;
                    break;
                case 'o':
                    outputFileName = (String)optionsFound.get(key);
                    break;
                case '?':
                    errs = true;
                    break;
                default:
                    throw new IllegalStateException(
                    "Nieznany znak opcji: " + c);
            }
        }
        if (errs) {
            System.err.println("Użycie: GetOptDemoNew [-n][-o plik][plik...]");
        }
        System.out.print("Opcje: ");
        System.out.print("Numeryczna: " + numeric_option + ' ');
        System.out.print("Plik wyjściowy: " + outputFileName + "; ");
        System.out.print("Pliki wejściowe: ");
        for (String fileName : parser.getFilenameList()) {
            System.out.print(fileName);
            System.out.print(' ');
        }
        System.out.println();
    }
}
// END main
