package Books_exercises.JavaReceptury.regex;

import java.io.*;
import java.util.regex.*;
import com.darwinsys.lang.GetOpt;

// BEGIN main


/** Program grep wykonywany z poziomu wiersza poleceń.
 * Program akceptuje kilka opcji i wymaga podania wzorca oraz 
 * dowolnej liczby nazw plików tekstowych. 
 * Uwaga. Aktualna implementacja klasy GetOpt nie pozwala łączyć krótkich
 * argumentów, zatem rozdzielenie ich odstępami, jak w przykładzie: 
 * "JGrep -l -r -i wzorzec plik..." jest OK, jednak zapis 
 * "JGrep -lri wzorzec plik..." doprowadzi do wystąpienia błędów. 
 * Miejmy nadzieję, że klasa GetOpt zostanie niebawem poprawiona.
 */

public class JGrep {
    private static final String USAGE =
        "Sposób użycia: JGrep wzorzec [-chilrsnv][-f plikwzorca][nazwapliku...]";
    /** Poszukiwany wzorzec. */
    protected Pattern pattern;
    /** Obiekt Matcher dla danego wzorca. */
    protected Matcher matcher;
    private boolean debug;
    /** Czy mamy tylko zliczać wiersze i nie wyświetlać ich? */
    protected static boolean countOnly = false;
    /** Czy nie należy uwzględniać wielkości liter? */
    protected static boolean ignoreCase = false;
    /** Czy nie należy wyświetlać nazw plików? */
    protected static boolean dontPrintFileName = false;
    /** Czy mają być wyświetlane wyłącznie nazwy pasujących plików? */
    protected static boolean listOnly = false;
    /** Czy mamy wyświetlać numery wierszy? */
    protected static boolean numbered = false;
    /** Czy nie należy wyświetlać komunikatów o błędach? */
    protected static boolean silent = false;
    /** Czy mamy wyświetlać wiersze, które NIE pasują do podanego wzorca? */
    protected static boolean inVert = false;
    /** Czy jeśli argumenty są katalogami, to należy je 
     * przeszukiwać rekurencyjnie? */
    protected static boolean recursive = false;

    /** Tworzymy obiekt JGrep dla każdego wzorca i przy jego pomocy 
     * przetwarzamy wszystkie pliki, których nazwy zostały podane 
     * w tablicy argv.
     * Należy pamiętać, że niektóre z opcji nie są obsługiwane przez 
     * poniższą wersję programu. Implementacja obsługi tych opcji stanowi 
     * ćwiczenie, które Czytelnik może wykonać samodzielnie.
     */
    public static void main(String[] argv) {

        if (argv.length < 1) {
            System.err.println(USAGE);
            System.exit(1);
        }
        String patt = null;

        GetOpt go = new GetOpt("cf:hilnrRsv");

        char c;
        while ((c = go.getopt(argv)) != 0) {
            switch(c) {
                case 'c':
                    countOnly = true;
                    break;
                case 'f':    /* Wzorzec wyrażenia w pliku zewnętrznym. */
                    try (BufferedReader b = 
                        new BufferedReader(new FileReader(go.optarg()))) {
                        patt = b.readLine();
                    } catch (IOException e) {
                        System.err.println(
                            "Nie można odczytać pliku wzorca " + go.optarg());
                        System.exit(1);
                    }
                    break;
                case 'h':
                    dontPrintFileName = true;
                    break;
                case 'i':
                    ignoreCase = true;
                    break;
                case 'l':
                    listOnly = true;
                    break;
                case 'n':
                    numbered = true;
                    break;
                case 'r':
                case 'R':
                    recursive = true;
                    break;
                case 's':
                    silent = true;
                    break;
                case 'v':
                    inVert = true;
                    break;
                case '?':
                    System.err.println("Błąd klasy Getopts!");
                    System.err.println(USAGE);
                    break;
            }
        }

        int ix = go.getOptInd();

        if (patt == null)
            patt = argv[ix++];

        JGrep prog = null;
        try {
            prog = new JGrep(patt);
        } catch (PatternSyntaxException ex) {
            System.err.println("Błąd składni wyrażenia w " + patt);
            return;
        }

        if (argv.length == ix) {
            dontPrintFileName = true; // Jeśli to standardowy strumień, 
                                      // to nie wyświetlamy nazw plików.
            if (recursive) {
                System.err.println("Ostrzeżenie: rekurencyjne przeszukiwanie strumienia wejściowego!");
            }
            prog.process(new InputStreamReader(System.in), null);
        } else {
            if (!dontPrintFileName)
                dontPrintFileName = ix == argv.length - 1; // Podobnie, jeśli
                                          // operujemy tylko na jednym pliku.
            if (recursive)
                dontPrintFileName = false;           // Chyba że to katalog!

            for (int i=ix; i<argv.length; i++) { 
                                         // Rejestrujemy indeks początkowy
                try {
                    prog.process(new File(argv[i]));
                } catch(Exception e) {
                    System.err.println(e);
                }
            }
        }
    }

    /** Konstruktor obiektu JGrep.
     * @param patt Poszukiwany wzorzec.
     * param args Opcje wiersza poleceń.
     */
    public JGrep(String patt) throws PatternSyntaxException {
        if (debug) {
            System.err.printf("JGrep.JGrep(%s)%n", patt);
        }
        // Kompilujemy wyrażenie regularne.
        int caseMode = ignoreCase ?
            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE :
            0;
        pattern = Pattern.compile(patt, caseMode);
        matcher = pattern.matcher("");
    }

    /** Metoda przetwarza jeden argument wiersza poleceń (plik lub katalog).
     * @throws FileNotFoundException 
     */
    public void process(File file) throws FileNotFoundException {
        if (!file.exists() || !file.canRead()) {
            System.err.println("BŁĄD: nie można odczytać pliku " + file.getAbsolutePath());
            return;
        }
        if (file.isFile()) {
            process(new BufferedReader(new FileReader(file)), 
                file.getAbsolutePath());
            return;
        }
        if (file.isDirectory()) {
            if (!recursive) {
                System.err.println(
                    "BŁĄD: użyto opcji -r, lecz nie podano katalogu " + 
                    file.getAbsolutePath());
                return;
            }
            for (File nf : file.listFiles()) {
                process(nf);   // "Rekurencja, patrz rozdział o rekurencji."
            }
            return;
        }
        System.err.println(
            "DZIWNE: nie podano ani pliku, ani katalogu: " +
                                  file.getAbsolutePath());
    }

    /** Analiza jednego pliku.
     * @param    ifile    Reader    Otworzony obiekt Reader.
     * @param    fileName String    Nazwa pliku wejściowego.
     */
    public void process(Reader ifile, String fileName) {

        String inputLine;
        int matches = 0;

        try (BufferedReader reader = new BufferedReader(ifile)) {

            while ((inputLine = reader.readLine()) != null) {
                matcher.reset(inputLine);
                if (matcher.find()) {
                    if (listOnly) {
                        // -l, Wyświetlamy nazwę pliku przy pierwszym 
                        // dopasowaniu i sprawa załatwiona.
                        System.out.println(fileName);
                        return;
                    }
                    if (countOnly) {
                        matches++;
                    } else {
                        if (!dontPrintFileName) {
                            System.out.print(fileName + ": ");
                        }
                        System.out.println(inputLine);
                    }
                } else if (inVert) {
                    System.out.println(inputLine);
                }
            }
            if (countOnly)
                System.out.println(matches + " dopasowań w pliku " + fileName);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}


// END main
