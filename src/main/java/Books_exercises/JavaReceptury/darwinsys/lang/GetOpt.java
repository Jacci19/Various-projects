package Books_exercises.JavaReceptury.darwinsys.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.darwinsys.util.Debug;

/** Klasa GetOpt implementuje mechanizm przetwarzania prostych, uniksowych
 * (jednoznakowych) argumentów wiersza poleceń. Początkowo bazowała ona
 * (chodź nie pod względem używanego kodu) na programie getopt(3) systemu 
 * UNIX, jednak później zmodyfikowałem ją bardziej pod kontem zastosowań 
 * w programach pisanych w Javie. W efekcie klasy można używać na dwa 
 * sposoby, które określam jako "sposób uniksowy" oraz "sposób Javy".
 * <ol><li>Początkowy (uniksowy) sposób użycia:
 * <pre>
 *      GetOpt go = new GetOpt("hno:");
 *      boolean numeric_option = false;
 *      String outFileName = "(standard output)";
 *      char c;
 *      while ((c = go.getopt(args)) != GetOpt.DONE) {
 *          switch(c) {
 *          case 'h':
 *              doHelp(0);
 *              break;
 *          case 'n':
 *              numeric_option = true;
 *              break;
 *          case 'o':
 *              outFileName = go.optarg();
 *              break;
 *          default:
 *              System.err.println("Nieznany znak opcji " + c);
 *              doHelp(1);
 *          }
 *      }
 *      System.out.print("Opcje: ");
 *      System.out.print("Liczbowe: " + numeric_option + ' ');
 *      System.out.print("Wyniki: " + outFileName + "; ");
 *      System.out.print("Wejściowe: ");
 *      if (go.getOptInd() == args.length) {
 *          doFile("(standardowy strumień wejściowy)");
 *      } else for (int i = go.getOptInd(); i &lt; args.length; i++) {
 *          doFile(args[i]);
 *      }
 * </pre></li>
 * <li>Nowszy sposób zastosowania, dostosowany do programów pisanych w Javie,
 *  który pozwala na stosowanie opcji o długich nazwach (poprzedzonych 
 *  pojedynczym znakiem minusa, np. -outputfile /tmp/j":
 * <pre>
 *      boolean numeric_option = false;
 *      boolean errs = false;
 *      String outputFileName = null;
 *
 *      GetOptDesc options[] = {
 *          new GetOptDesc('n', "numeric", false),
 *          new GetOptDesc('o', "output-file", true),
 *      };
 *      GetOpt parser = new GetOpt(options);
 *      Map&lt;String,String&gt; optionsFound = parser.parseArguments(argv);
 *      Iterator&lt;String&gt; it = optionsFound.keySet().iterator();
 *      while (it.hasNext()) {
 *          String key = (String)it.next();
 *          switch (key.charAt(0)) {
 *              case 'n':
 *                  numeric_option = true;
 *                  break;
 *              case 'o':
 *                  outputFileName = optionsFound.get(key);
 *                  break;
 *              case '?':
 *                  errs = true;
 *                  break;
 *              default:
 *                  throw new IllegalStateException(
 *                  "Nieoczekiwany znak opcji: " + key);
 *          }
 *      }
 *      if (errs) {
 *          System.err.println("Usage: GetOptDemo [-n][-o file][file...]");
 *      }
 *      System.out.print("Opcje: ");
 *      System.out.print("Liczbowe: " + numeric_option + ' ');
 *      System.out.print("Wyniki: " + outputFileName + "; ");
 *      System.out.print("Pliki wejściowe: ");
 *      List&lt;Files&gt; files = parser.getFilenameList();
 *      for (String file : files) {
 *          System.out.print(file);
 *          System.out.print(' ');
 *      }
 *      System.out.println();
 * }
 * </pre></li>
 * </ol>
 * <p>
 * Ta klasa <em>nie jest</em> bezpieczna pod względem wielowątkowym; ma być
 * używana wyłącznie wewnątrz metody main().
 * <p>
 * Informacje na temat innych sposobów obsługi argumentów podawanych w wierszu 
 * poleceń, można znaleźć na stronie WWW projektu 
 * <a href="http://jakarta.apache.org/commons/cli/">Jakarta Commons
 * Command Line Interface</a>.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
// package com.darwinsys.lang;
public class GetOpt {
    /** Lista plików podanych po argumentach. */
    protected List<String> fileNameArguments;
    /** Zestaw poszukiwanych znaków. */
    protected final GetOptDesc[] options;
    /** Położenie w opcjach. */
    protected int optind = 0;
    /** Publiczna zmienna ustalona reprezentująca "brak 
     * dalszych opcji" */
    public static final int DONE = 0;
    /** Flaga wewnętrzna - czy zostały przetworzone wszystkie opcje. */
    protected boolean done = false;
    /** Bieżący argument opcji. */
    protected String optarg;

    /** Pobiera argument bieżącej opcji; zapis typowy dla 
     * systemów UNIX. */
    public String optarg() {
        return optarg;
    }
    /** Pobiera argument bieżącej opcji; zapis typowy dla Javy. */
    public String optArg() {
        return optarg;
    }

    /** Tworzy obiekt GetOpt na podstawie specyfikacji opcji podanych
     * w tablicy obiektów GetOptDesc. To preferowany konstruktor.
     */
    public GetOpt(final GetOptDesc[] opt) {
        this.options = opt.clone();
    }

    /** Tworzy obiekt GetOpt, zapisując w nim zestaw znaków opcji.
     * To stary konstruktor, zapewniający zgodność wstecz.
     * Jednak łatwiej go stosować jeśli nie musimy używać opcji o długich
     * nazwach, dlatego też nie zamierzam go "odrzucać".
     */
    public GetOpt(final String patt) {
        if (patt == null) {
            throw new IllegalArgumentException("Wzorzec nie może być pusty.");
        }
        if (patt.charAt(0) == ':') {
            throw new IllegalArgumentException(
                "Niewłaściwy wzorzec, nie może się zaczynać od znaku ':'");
        }

        // Pierwszy przebieg: zliczamy wszystkie litery opcji we wzorcu.
        int n = 0;
        for (char ch : patt.toCharArray()) {
            if (ch != ':')
                ++n;
        }
        if (n == 0) {
            throw new IllegalArgumentException(
                "Nie znaleziono żadnych opcji w łańcuchu " + patt);
        }

        // Drugi przebieg: tworzymy tablicę obiektów GetOptDesc.
        options = new GetOptDesc[n];
        for (int i = 0, ix = 0; i<patt.length(); i++) {
            final char c = patt.charAt(i);
            boolean argTakesValue = false;
            if (i < patt.length() - 1 && patt.charAt(i+1) == ':') {
                argTakesValue = true;
                ++i;
            }
            Debug.println("getopt",
                "KONSTRUKTOR: opcje[" + ix + "] = " + c + ", " + argTakesValue);
            options[ix++] = new GetOptDesc(c, null, argTakesValue);
        }
    }

    /** Przywrócenie wartości początkowych. */
    public void rewind() {
        fileNameArguments = null;
        done = false;
        optind = 0;
        optarg = null;
    }

    /** 
     * Nowoczesny sposób stosowania klasy GetOpt: wywołujemy ją raz
     * i pobieramy wszystkie opcje.
     * <p>
     * Ta metoda analizuje opcje i zwraca obiekt Map, którego kluczami
     * są znalezione opcje.
     * Zazwyczaj po niej należy wywołać metodę getFilenameList().
     * <br>Efekt uboczny: w polu "fileNameArguments" zapisuje nową listę.
     * @return Obiekt Map, którego kluczami są łańcuchy składające się
     *    z jednego znaku (zawierają one dopasowane opcje) a wartościami
     *    są łańcuchy znaków zawierające wartości opcji lub wartość null
     *    jeśli dana opcja była bezargumentowa.
     */
    public Map<String,String> parseArguments(String[] argv) {
        Map<String, String> optionsValueMap = new HashMap<String, String>();
        fileNameArguments = new ArrayList<String>();
        for (int i = 0; i < argv.length; i++) {    // Nie możemy użyć pętli
                                        // foreach, potrzebujemy zmiennej i
            Debug.println("getopt", "parseArg: i=" + i + ": arg " + argv[i]);
            char c = getopt(argv);    // ustawia globalne pole "optarg"
            if (c == DONE) {
                fileNameArguments.add(argv[i]);
            } else {
                optionsValueMap.put(Character.toString(c), optarg);
                // Jeśli to argument to przygotowujemy się, by go pominąć. 
                if (optarg != null) {
                    i++;
                }
            }
        }
        return optionsValueMap;
    }

    /** Pobiera listę argumentów o postaci przypominającej nazwy plików,
     * umieszczoną za opcjami; używana wyłącznie po wcześniejszym 
     * wywołaniu metody parseArguments.
     */
    public List<String> getFilenameList() {
        if (fileNameArguments == null) {
            throw new IllegalArgumentException(
                "Nie można wywoływać metody getFilenameList() przed wywołaniem parseOptions()");
        }
        return fileNameArguments;
    }

    /** To najważniejsza metoda tej klasy, niezależnie do tego, jak jej 
     * używamy. Zwraca jeden argument; jest wywoływana w pętli, aż zwróci
     * DONE.
     * Efekty uboczne: zmienia pola globalne: optarg i optind.
     */
    public char getopt(String argv[]) {
        Debug.println("getopt",
            "optind=" + optind + ", argv.length="+argv.length);

        if (optind >= (argv.length) || !argv[optind].startsWith("-")) {
            done = true;
        }

        // Jeśli skończyliśmy (teraz lub wcześniej), to spadamy stąd.
        // Nie należy łączyć tej instrukcji z poprzednią.
        if (done) {
            return DONE;
        }
        
        optarg = null;

        // XXX TODO - drugi przebieg, pierwszy sprawdza długie nazwy opcji,
        // a drugi obsługuje zastosowania zaawansowane, takie jak 
        // możliwość zapisu opcji "-n -o plikWyjściowy" w formie 
        // "-no plikWyjściowy".

        // Pobieramy następny argument wiersza wywołania; jeśli zaczyna 
        // się od "-", to szukamy go na liście prawidłowych opcji.
        String thisArg = argv[optind];

        if (thisArg.startsWith("-")) {
            for (GetOptDesc option : options) {
                if ((thisArg.length() == 2 &&
                        option.getArgLetter() == thisArg.charAt(1)) ||
                   (option.getArgName() != null &&
                    option.getArgName().equals(thisArg.substring(1)))) { // znaleziony
                    // Jeśli potrzebuje argumentu, to go pobieramy.
                    if (option.takesArgument()) {
                        if (optind < argv.length-1) {
                            optarg = argv[++optind];                             
                        } else {
                            throw new IllegalArgumentException(
                                "Opcja " + option.getArgLetter() +
                                " wymaga wartości, a znaleziono koniec listy opcji.");
                        }
                    }
                    ++optind;
                    return option.getArgLetter();
                }
            }
            // Zaczyna się od "-" lecz nie jest prawidłową opcją, 
            // a zatem uznajemy to za błąd.
            ++optind;
            return '?';
        } else {
            // Znaleziono słowo, które nie jest ani opcją, ani argumentem,
            // traktujemy je jako koniec listy opcji.
            ++optind;
            done = true;
            return DONE;
        }
        

        
    }

    /** Zwraca wartość pola optind, indeks ostatniej sprawdzanej opcji 
     * w tablicy args. */
    public int getOptInd() {
        return optind;
    }

}
// END main
