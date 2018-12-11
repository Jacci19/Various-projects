package Books_exercises.JavaReceptury.dir_file;

import java.io.File;

import com.darwinsys.lang.GetOpt;
import com.darwinsys.util.Debug;

// BEGIN main
/**
 * Find - odnajduje pliki na podstawie nazwy, wielkości oraz innych kryteriów. 
 * Wersja obsługiwana z poziomu wiersza poleceń.
 */
public class Find {
    /** Program główny */
    public static void main(String[] args) {
        Find finder = new Find();
        GetOpt argHandler = new GetOpt("n:s:");
        int c;
        while ((c = argHandler.getopt(args)) != GetOpt.DONE) {
            switch(c) {
            case 'n': finder.filter.setNameFilter(argHandler.optarg()); break;
            case 's': finder.filter.setSizeFilter(argHandler.optarg()); break;
            default:    
                System.out.println("Przekazana opcja: " + c);
                usage();
            }
        }
        if (args.length == 0 || argHandler.getOptInd()-1 == args.length) {
            finder.doName(".");
        } else {
            for (int i = argHandler.getOptInd()-1; i<args.length; i++)
                finder.doName(args[i]);
        }
    }

    protected FindFilter filter = new FindFilter();

    public static void usage() {
        System.err.println(
            "Sposób użycia: Find [-n filtr_nazw][-s filtr_wielkości][katalog...]");
        System.exit(1);
    }

    /** doName - obsługuje poszukiwanie elementu systemu plików
     * na podstawie podanej nazwy.
     */
    private void doName(String s) {
        Debug.println("działamy", "doName(" + s + ")");
        File f = new File(s);
        if (!f.exists()) {
            System.out.println(s + " nie istnieje");
            return;
        }
        if (f.isFile())
            doFile(f);
        else if (f.isDirectory()) {
            // System.out.println("d " + f.getPath());
            String objects[] = f.list(filter);

            for (String o : objects)
                doName(s + File.separator + o);
        } else
            System.err.println("Nieznany typ: " + s);
    }

    /** doFile - obsługuje jeden zwyczajny plik. */
    private static void doFile(File f) {
        System.out.println("f " + f.getPath());
    }
}
// END main
