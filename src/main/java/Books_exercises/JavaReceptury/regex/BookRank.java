package Books_exercises.JavaReceptury.regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Graficzna prezentacja wyników sprzedarzy książki w wybranej
 * księgarni internetowej.
 * @author Ian F. Darwin, ian@darwinsys.com, autor książki Java Cookbook 
 *    wydanej przez Wydawnictwo HELION pod tytułem (Java. Receptury),
 *    program został w stosunkowo bezpośredni sposób przetłumaczony z 
 *    języka Perl.
 * @author Patrick Killelea <p@patrick.net>: oryginalna wersja w Perl-u,
 *    pochodzi z 2-go wydania książki "Web Performance Tuning".
 */
// BEGIN main
public class BookRank {
    public final static String DATA_FILE = "book.sales";
    public final static String GRAPH_FILE = "book.png";
    public final static String PLOTTER_PROG = "/usr/local/bin/gnuplot";

    final static String isbn = "0596007019"; 
    final static String title = "Java Cookbook";
    
    /** Pobranie informacji o sprzedaży ze strony i zapisanie w pliku */
    public static void main(String[] args) throws Exception {

        Properties p = new Properties();
        p.load(new FileInputStream(
            args.length == 0 ? "bookrank.properties" : args[1]));
        String title = p.getProperty("title", "NO TITLE IN PROPERTIES");
        // Adres URL musi zawierać na końcu "isbn=", w przeciwnym razie
        // przygotuj się na to, że dane będą przycięte.
        String url = p.getProperty("url", "http://test.ing/test.cgi?isbn=");
        // Dzisięcioznakowy symbol ISBN książki.
        String isbn  = p.getProperty("isbn", "0000000000");
        // Wzorzec wyrażenia regularnego (MUSI zawierać jedną grupę 
        // przechwytującą pobierającą symbol książki).
        String pattern = p.getProperty("pattern", "Rank: (\\d+)");

        int rank = getBookRank(isbn);

        System.out.println("Ocena to: " + rank);

        // Niezależnie od tego, czy odnaleźliśmy bieżące dane, czy też nie,
        // używamy zewnętrznego programu do wyświetlenia wszystkich 
        // danych historycznych. Można wykorzystać program gnuplot, R
        // lub dowolny inny program matematyczno-graficzny.
        // Lepsze rozwiązanie polega na wykorzystaniu graficznego API 
        // języka Java.

        PrintWriter pw = new PrintWriter(
            new FileWriter(DATA_FILE, true));
        String date = new SimpleDateFormat("MM dd hh mm ss yyyy ").
            format(new Date());
        pw.println(date + " " + rank);
        pw.close();

        String gnuplot_cmd = 
            "set term png\n" + 
            "set output \"" + GRAPH_FILE + "\"\n" +
            "set xdata time\n" +
            "set ylabel \"Book sales rank\"\n" +
            "set bmargin 3\n" +
            "set logscale y\n" +
            "set yrange [1:60000] reverse\n" +
            "set timefmt \"%m %d %H %M %S %Y\"\n" +
            "plot \"" + DATA_FILE + 
                "\" using 1:7 title \"" + title + "\" with lines\n" 
        ;

        if (!new File(PLOTTER_PROG).exists()) {
            System.out.println(
                 "Oprogramowanie do rysowania nie zostało zainstalowane");
            return;
        }
        Process proc = Runtime.getRuntime().exec(PLOTTER_PROG);
        PrintWriter gp = new PrintWriter(proc.getOutputStream());
        gp.print(gnuplot_cmd);
        gp.close();
    }

    /**
     * Szukamy czegoś takiego jak poniższy kod HTML:
     *     <b>Sales Rank:</b> 
     *     #26,252
     *      </font><br>
     * @throws IOException 
     * @throws IOException 
     */
    public static int getBookRank(String isbn) throws IOException {

        // Wyrażenie regularne - dozwolone są cyfry i przecinki.
        final String pattern = "Rank:</b> #([\\d,]+)";
        final Pattern r = Pattern.compile(pattern);

        // Adres URL -- musi zawierać na końcu "isbn=", w przeciwnym razie
        // taki fragment zostanie dodany.
        final String url = "http://www.amazon.com/exec/obidos/ASIN/" + isbn;

        // Odwołujemy się do adresu i tworzymy obiekt Reader.
        final BufferedReader is = new BufferedReader(new InputStreamReader(
            new URL(url).openStream()));

        // Odczytujemy zawartość strony, poszukując informacji o ocenie,
        // zapisanych jako jeden, długi łańcuch znaków. Zatem każde 
        // wyrażenie może obejmować wiele wierszy.
        final String input = readerToString(is);

        // Jeśli uda się znaleźć dopasowanie, dodajemy je do pliku wyników.
        Matcher m = r.matcher(input);
        if (m.find()) {
            // Grupa 1 to dopasowane cyfry (ewentualnie z przecinkiem, który
            // jest usuwany).
            return Integer.parseInt(m.group(1).replace(",",""));
        } else {
            throw new RuntimeException(
                "Nie udało się dopasować wzorca na stronie: `" + url + "'!");
        }
    }

    private static String readerToString(BufferedReader is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = is.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
// END main
