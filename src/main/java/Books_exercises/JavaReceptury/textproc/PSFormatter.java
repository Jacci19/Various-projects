package Books_exercises.JavaReceptury.textproc;

import java.io.*;

/**
 * Tekst do formatu PostScript 
 */
// BEGIN main
public class PSFormatter {
    /** Bieżące źródło tekstu */
    protected BufferedReader br;
    /** Bieżący numer strony */
    protected int pageNum;
    /** Bieżące współrzędne na stronie */
    protected int curX, curY;
    /** Bieżący numer wiersza na stronie */
    protected int lineNum;
    /** Bieżące ustawienie tabulacji */
    protected int tabPos = 0;
    public static final int INCH = 72;    // stała PostScript: 72 pts/inch

    // Parametry strony
    /** Przesunięcie lewego marginesu */
    protected int leftMargin = 50;
    /** Przesunięcie prawego marginesu */
    protected int topMargin = 750;
    /** Przesunięcie dolnego marginesu */
    protected int botMargin = 50;

    // Parametry formatujące
    protected int points = 12;
    protected int leading = 14;

    public static void main(String[] av) throws IOException {
        if (av.length == 0) 
            new PSFormatter(
                new InputStreamReader(System.in)).process();
        else for (int i = 0; i < av.length; i++) {
            new PSFormatter(av[i]).process();
        }
    }

    public PSFormatter(String fileName) throws IOException {
        br = new BufferedReader(new FileReader(fileName));
    }

    public PSFormatter(Reader in) throws IOException {
        if (in instanceof BufferedReader)
            br = (BufferedReader)in;
        else
            br = new BufferedReader(in);
    }

    /** Główna metoda przetwarzająca zawartość źródła danych. */
    protected void process() throws IOException {

        String line;

        prologue();            // generujemy prolog PostScript, jeden raz.

        startPage();        // generujemy początek strony 

        while ((line = br.readLine()) != null) {
            if (line.startsWith("\f") || line.trim().equals(".bp")) {
                startPage();
                continue;
            }
            doLine(line);
        }

        // Kończymy ostatnią stronę, jeśli jeszcze tego nie zrobiliśmy.
        if (lineNum != 0)
            System.out.println("showpage");
    }

    /** Obsługa początku strony. */
    protected void startPage() {
        if (pageNum++ > 0)
            System.out.println("showpage");
        lineNum = 0;
        moveTo(leftMargin, topMargin);
    }

    /** Przetwarzamy jeden wiersz z wejścia */
    protected void doLine(String line) {
        tabPos = 0;
        // Liczymy początkowe (nieuwzględniane) tabulacje.
        for (int i=0; i<line.length(); i++) {
            if (line.charAt(i)=='\t')
                tabPos++;
            else
                break;
        }
        String l = line.trim(); // Usuwamy tabulacje i odstępy
        if (l.length() == 0) {
            ++lineNum;
            return;
        }
        moveTo(leftMargin + (tabPos * INCH),
            topMargin-(lineNum++ * leading));
        System.out.println('(' + toPSString(l)+ ") show");

        // Dotarliśmy do końca, zaczynamy nową stronę 
        if (curY <= botMargin)
            startPage();
    }

    /** Wyjątkowo uproszczona konwersja na format PS, 
     * np. nie da sobie rady z łańcuchem "foo\)bar". */
    protected String toPSString(String o) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<o.length(); i++) {
            char c = o.charAt(i);
            switch(c) {
                case '(':    sb.append("\\("); break;
                case ')':    sb.append("\\)"); break;
                default:    sb.append(c); break;
            }
        }
        return sb.toString();
    }

    protected void moveTo(int x, int y) {
        curX = x;
        curY = y;
        System.out.println(x + " " + y + " " + "moveto");
    }

    void prologue() {
        System.out.println("%!PS-Adobe");
        System.out.println("/Courier findfont " + points + " scalefont setfont ");
    }
}
// END main
