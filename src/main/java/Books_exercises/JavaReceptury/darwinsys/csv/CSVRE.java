package Books_exercises.JavaReceptury.darwinsys.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Prosty przykład analizy danych zapisanych w formacie CSV przy 
 * wykorzystaniu wyrażeń regularnych.
 * NIE korzysta ona z klasy "CSV" prezentowanej w książce 
 * Java Cookbook, używa natomiast uproszczonego wzorca wyrażenia 
 * regularnego przedstawionego w rozdziale 7. książki 
 * <em>Mastering Regular Expressions</em> (str. 205, pierwsze wyd.)
 * Przykładowe zastosowanie:
 * <pre>
 * public static void main(String[] argv) throws IOException {
 *     System.out.println(CSV_PATTERN);
 *     new CSVRE().process(new BufferedReader(new InputStreamReader(System.in)));
 * }
 * </pre>
 */
// BEGIN main
// package com.darwinsys.csv;
public class CSVRE implements CSVParser {
   /** Raczej złożony wzorzec, rozpoznający trzy warianty pól:
    * zapisane w cudzysłowach (pierwszy), bez cudzysłowów (drugi)
    * oraz pola puste (trzeci).
    */
    public static final String CSV_PATTERN =
        "\"([^\"]+?)\",?|([^,]+),?|,";

    private final static Pattern csvRE = Pattern.compile(CSV_PATTERN);

    public static void main(final String[] argv) throws IOException {
        System.out.println(CSV_PATTERN);
        new CSVRE().process(
            new BufferedReader(new InputStreamReader(System.in)));
    }

    /** Przetwarzamy jeden plik. Do metody parse() przekazywane są
     * kolejne wiersze.
     */
    public void process(final BufferedReader input) throws IOException {
        String line;

        // Dla każdego wiersza...
        while ((line = input.readLine()) != null) {
            System.out.println("line = `" + line + "'");
            final List<String> list = parse(line);
            System.out.println("Znaleziono " + list.size() + " elementów.");
            for (String str : list) {
                System.out.print(str + ",");
            }
            System.out.println();
        }
    }

    /** Przetwarzamy jeden wiersz.
     * @return Lista łańcuchów z pominięciem znaków cudzysłowu.
     */
    public List<String> parse(final String line) {
        final List<String> list = new ArrayList<>();
        final Matcher m = csvRE.matcher(line);
        // Dla każdego pola.
        while (m.find()) {
            String match = m.group();
            if (match == null) {
                break;
            }
            if (match.endsWith(",")) {    // pomijamy kończące ,
                match = match.substring(0, match.length() - 1);
            }
            if (match.startsWith("\"")) { // musi się także kończyć \"
                if (!match.endsWith("\"")) {
                    throw new IllegalArgumentException(
                        "Brak kończącego cudzysłowu w kolumnie: " + line);
                }
                match = match.substring(1, match.length() - 1);
            }
            if (match.length() == 0) {
                match = "";
            }
            list.add(match);
        }
        return list;
    }
}
// END main
