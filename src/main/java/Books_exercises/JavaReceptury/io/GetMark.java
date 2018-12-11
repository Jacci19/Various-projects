package Books_exercises.JavaReceptury.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;

/** GetMark -- pobieranie oznaczonych wierszy.
 * <p>
 * Klasa GetMark jest narzędziem ogólnego przeznaczenia służącym do
 * dołącznia lub wykluczania fragmentów plików.
 * Można jej używać, na przykład, do pobierania fragmentów polików w celu 
 * umieszczani ich w dokumencji, bądź do usuwania fragmentów plików, na 
 * przykład działającej części rozwiązania.
 * <p>
 * Znaczniki, których klasa poszukuje, mają prostą postać i można je 
 * pozostawić w podstawowej wersji kodu (nigdy nie są wyświetlane).
 * Znacznik //+ (poszukiwany przy użyciu wywołania 
 * line.trim().equals("//+)) rozpoczyna wyświetlanie kodu, natomiast
 * przeciwny znacznik //- kończy wyświetlanie.
 * <p>
 * A zatem, na potrzeby przykładu, możesz napisać działający kod,
 * dodać do niego stosowne komentarze, po czym dodać znaczniki 
 * //- za komentarzami TODO (do zrobienia), lecz przed działającym kodem,
 * a znaczniki //+ za tymi komentarzami. Oto przykład:
 * </p><pre>
 *     public methodA() {
 *         // TODO:
 *         // Poszukać wywoływanego obiektu.
 *         // Szukać nazwy "ex31object".
 * 
 *         //- main
 *         Object o = Naming.lookup("ex31object");
 *         //+ main
 * 
 *         // TODO #2
 *         // Odszukany obiekt rzutować w dół, korzystając z IIOP.
 * 
 *         //- main
 *         Ex31Object obj = (Ex31Object)PortableRemoteObject.narrow(
 *             o, Ex31Object.class);
 *         //+ main
 *     }
 * </pre><p>
 * Po przetworzeniu tego kodu przy użyciu klasy GetMark działającej w trybie 
 * "exclude" (wykluczania), wygenerowane zostaną następujące wyniki:
 * </p><pre>
 *  public methodA() {
 *      // TODO:
 *      // Poszukać wywoływanego obiektu.
 *      // Szukać nazwy "ex31object".
 * 
 * 
 *      // TODO #2
 *      // Odszukany obiekt rzutować w dół, korzystając z IIOP.
 *
 *  }
 * </pre><p>
 * Klasy GetMark można także używać w skryptach:
 * </p><pre>
 * for f in *.java
 * do
 *    echo $f
 *    java GetMark $f &gt; ../solutions/$f
 * done
 * </pre><p>
 * Informacje o użyciu klasy GetMark do pobierania kodu (zastosowałem ją
 * w pierwszym wydaniu książki Java. Receptury) można znaleźć w komentarzach
 * umieszczonych w jej kodzie. 
 * <p>
 * W tej wersji klasy tryb (dołączania lub wykluczania) oraz łańcuchy znaków
 * określające znaczniki umieszczane w kodzie, zostały podane na stałe.
 * Znacznie lepiej byłoby pobierać je z pliku właściwości (Properties) lub 
 * preferencji (Preferences), bądź też z argumentów wiersza poleceń.
 *
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class GetMark {
    /** Domyślny znacznik rozpoczynający. */
    public final String START_MARK = "//+";
    /** Domyślny znacznik zakańczający. */
    public final String END_MARK = "//-";
    /** Przypisz tej właściwości wartość TRUE by pracować w trybie
     * "wykluczania" (na przykład w celu tworzenia ćwiczeń na podstawie 
     * rozwiązania), lub wartość FALSE by pracować w trybie 
     * "wyodrębniania" (na przykład pomijania deklaracji importu lub
     * fragmentów "public class" podczas pisania książki).
     */
    public final static boolean START = true;
    /** TRUE, jeśli aktualnie jesteśmy wewnątrz znaczników. */
    protected boolean printing = START;
    /** TRUE, jeśli chcemy generować numery wierszy. */
    protected final boolean number = false;

    /** Metoda pobiera zaznaczone fragmenty jednego pliku, używa przy 
     * tym obiektu LineNumberReader do określania numerów wierszy.
     * To główna czynność wykonywana przez tę klasę; można jej używać
     * w innych programach, bądź w metodzie main() tej klasy.
     */
    public void process(String fileName,
        LineNumberReader is,
        PrintStream out) {
        int nLines = 0;
        try {
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
                if (inputLine.trim().equals(START_MARK)) {
                    if (printing)
                        // Wyświetlane w standardowym strumieniu błędów   
                        // (stderr), a zatem można przekierować na wyjście.                     
                        System.err.println("BŁĄD: POCZĄTEK W POCZĄTKU, " +
                            fileName + ':' + is.getLineNumber());
                    printing = true;
                } else if (inputLine.trim().equals(END_MARK)) {
                    if (!printing)
                        System.err.println("BŁĄD: KONIEC PO ZAKOŃCZENIU, " +
                            fileName + ':' + is.getLineNumber());
                    printing = false;
                } else if (printing) {
                    if (number) {
                        out.print(nLines);
                        out.print(": ");
                    }
                    out.println(inputLine);
                    ++nLines;
                }
            }
            is.close();
            out.flush(); // Nie możemy zamykać strumienia - może być potrzebny
                         // w kodzie wywołującym tę metodę.
            if (nLines == 0)
                System.err.println("BŁĄD: brak znaczników w pliku " + fileName +
                    "; dane wyjściowe nie zostaną wygenerowane!");
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
// END main

    /** W razie niezależnego użycia klasy GetMark, ten prosty program poszukuje 
     * plików o podanych nazwach i przetwarza je.
     * 
     * XXX TODO opcje związane z analizą, zapewnić możliwość dołączania 
     * i wykluczania itd. z poziomu wiersza poleceń.
     */
    public static void main(String[] av) {
        GetMark o = new GetMark();
        PrintStream pw = new PrintStream(System.out);
    if (av.length == 0) {
        o.process("standard input", new LineNumberReader(
            new InputStreamReader(System.in)), pw);
        } else {
            for (int i=0; i<av.length; i++)
                try {
                    o.process(av[i],
                        new LineNumberReader(new FileReader(av[i])), pw);
                } catch (FileNotFoundException e) {
                    System.err.println(e);
                }
        }
    }
}
