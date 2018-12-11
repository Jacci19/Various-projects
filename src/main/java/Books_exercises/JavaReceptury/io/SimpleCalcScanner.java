package Books_exercises.JavaReceptury.io;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;
import java.util.Stack;

/**
 * SimpleCalc -- prosty kalkulator korzystający z klasy java.util.Scanner.
 */
// BEGIN main
public class SimpleCalcScanner {
    /** Obiekt Scanner. */
    protected  Scanner scan;

    /** Docelowe miejsce, gdzie będą zapisywane wyniki. */
    protected PrintWriter out = new PrintWriter(System.out);

    /** Nazwa zmiennej (nieużywana w tej wersji programu). */
    protected String variable;

    /** Stos operandów; nie są na nim umieszczane żadne operatory,
     * a zatem mógłby to być stos liczb typu Double. */
    protected Stack<Double> s = new Stack<>();

    /* Program główny. */
    public static void main(String[] args) throws IOException {
        if (args.length == 0)
            new SimpleCalcScanner(
                new InputStreamReader(System.in)).doCalc();
        else 
            for (String arg : args) {
                new SimpleCalcScanner(arg).doCalc();
            }
    }

    /** Tworzy obiekt SimpleCalcScanner używając nazwy pliku. */
    public SimpleCalcScanner(String fileName) throws IOException {
        this(new FileReader(fileName));
    }

    /** Tworzy obiekt SimpleCalcScanner na podstawie obiektu Reader. */
    public SimpleCalcScanner(Reader rdr) throws IOException {
        scan = new Scanner(rdr);
    }

    /** Tworzy obiekt SimpleCalcScanner na podstawie obiektów 
     * Reader oraz PrintWriter. */
    public SimpleCalcScanner(Reader rdr, PrintWriter pw) throws IOException {
        this(rdr);
        setWriter(pw);
    }

    /** Zmiana miejsca gdzie będą zapisywane wyniki na przekazany 
     * obiekt PrintWriter. */
    public void setWriter(PrintWriter pw) {
        out = pw;
    }

    protected void doCalc() throws IOException {
        double tmp;

        while (scan.hasNext()) {
            if (scan.hasNextDouble()) {
                push(scan.nextDouble());
            } else {
                String token = scan.next();
                if (token.equals("+")) {
                    // Znaleziono operator +, wykonujemy go natychmiast.
                    push(pop() + pop());
                } else if (token.equals("-")) {
                    // Znaleziono operator -, wykonujemy go 
                    // (kolejność ma znaczenie).
                    tmp = pop();
                    push(pop() - tmp);
                } else if (token.equals("*")) {
                    // W przypadku mnożenia kolejność nie ma znaczenia.
                    push(pop() * pop());
                } else if (token.equals("/")) {
                    // Dzielenie należy wykonywać ostrożnie: kolejność 
                    // ma znaczenie!
                    tmp = pop();
                    push(pop() / tmp);
                } else if (token.equals("=")) {
                    out.println(peek());
                } else {
                    out.println("To co to jest? " + token);
                }
            }
        }
    }

    void push(double val) {
        s.push(new Double(val));
    }

    double pop() {
        return ((Double)s.pop()).doubleValue();
    }

    double peek() {
        return ((Double)s.peek()).doubleValue();
    }

    void clearStack() {
        s.removeAllElements();
    }
}
// END main
