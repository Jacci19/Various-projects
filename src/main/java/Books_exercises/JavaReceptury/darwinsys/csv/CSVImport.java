package Books_exercises.JavaReceptury.darwinsys.csv;

import java.util.ArrayList;
import java.util.List;

import com.darwinsys.util.Debug;

/** Przetwarzanie wartości oddzielonych przecinkami popularnego 
 * formatu zapisu danych w systemach MS Windows.
 * Przykładowe dane wejściowe: "LU",86.25,"11/4/1998","2:19PM",+4.0625
 * <p>
 * Logika działania programu oparta na algorytmie napisanym w C++.
 * Prawa do kopiowania (C) 1999 Lucent Technologies.
 * Fragment książki 'The Practice of Programming'
 * autorstwa Briana W. Kernighana i Roba Pika.
 * <p>
 * Dołączone za zgodą witryny http://tpop.awl.com/, 
 * zawierającej następujące informacje:
 * "You may use this code for any purpose, as long as you leave 
 * the copyright notice and book citation attached." 
 * Kod może być używany w dowolnym celu, o ile zostanie do niego 
 * dołączona informacja o prawach autorskich oraz cytaty z książki,
 * co też uczyniłem.
 * @author Brian W. Keringhan and Rob Pike (oryginał w C++)
 * @author Ian F. Darwin (wersja w Javie i usunięcie obsługi I/O)
 * @author Ben Ballard (modyfikacja metody advQuoted w celu obsługi 
 * '""' i zapewnienia większej czytelności kodu).
 */
// BEGIN main

// package com.darwinsys.csv;

public class CSVImport implements CSVParser {

    public static final char DEFAULT_SEP = ',';

    /** Tworzy obiekt CSVImport, w którym domyślnie używanym separatorem
     * będzie  (','). */
    public CSVImport() {
        this(DEFAULT_SEP);
    }

    /** Tworzy obiekt CSVImport, który będzie używał przekazanego
     * separatora.
     * @param sep Znak, który będzie separatorem (a nie lista 
     *        separatorów).
     */
    public CSVImport(char sep) {
        fieldSep = sep;
    }

    /** Pola w aktualnie analizowanym łańcuchu znaków. */
    protected List<String> list = new ArrayList<>();

    /** Używany znak separatora. */
    protected char fieldSep;

    /** Metoda analizująca: dzieli wejściowy łańcuch znaków na pola.
     * @return java.util.Iterator zawierający wszystkie pola z wejściowego
     *    łańcucha znaków, zapisane jako łańcuchy w kolejności w jakiej
     *    wystąpiły w łańcuchu wejściowym.
     */
    public List<String> parse(String line) {
        StringBuffer sb = new StringBuffer();
        list.clear();            // Przywracamy stan początkowy.
        int i = 0;

        if (line.length() == 0) {
            list.add(line);
            return list;
        }

        do {
            sb.setLength(0);
            if (i < line.length() && line.charAt(i) == '"')
                i = advQuoted(line, sb, ++i);    // Pomijamy cudzysłów.
            else
                i = advPlain(line, sb, i);
            list.add(sb.toString());
            Debug.println("csv", sb.toString());
            i++;
        } while (i < line.length());

        return list;
    }

    /** advQuoted: Pole zapisane w cudzysłowach; zwraca indeks 
     * następnego separatora. */
    protected int advQuoted(String s, StringBuffer sb, int i)
    {
        int j;
        int len= s.length();
        for (j=i; j<len; j++) {
            if (s.charAt(j) == '"' && j+1 < len) {
                if (s.charAt(j+1) == '"') {
                    j++; // Pomijamy znak specjalny.
                } else if (s.charAt(j+1) == fieldSep) { // Następny separator.
                    j++; // skip end quotes
                    break;
                }
            } else if (s.charAt(j) == '"' && j+1 == len) { // Koniec cytatu na końcu wiersza.
                break; // Koniec.
            }
            sb.append(s.charAt(j));    // Normalny znak.
        }
        return j;
    }

    /** advPlain: pole zapisane bez cudzysłowów; zwraca indeks następnego
     * separatora. */
    protected int advPlain(String s, StringBuffer sb, int i)
    {
        int j;

        j = s.indexOf(fieldSep, i); // Szukamy separatora.
        Debug.println("csv", "i = " + i + ", j = " + j);
        if (j == -1) {                   // Nie udało się go znaleźć.
            sb.append(s.substring(i));
            return s.length();
        } else {
            sb.append(s.substring(i, j));
            return j;
        }
    }
}
// END main
