package Books_exercises.JavaReceptury.io;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.Stack;

/**
 * SimpleCalc -- prosty kalkulator przedstawiający sposób wykorzystania
 * klasy StreamTokenizer.
 *
 * @author    Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class SimpleCalcStreamTok {
    /** Obiekt StreamTokenizer  */
    protected  StreamTokenizer tf;
    /** Plik wynikowy */
    protected PrintWriter out = new PrintWriter(System.out, true);
    /** Nazwa zmiennej (nieużywana w tej wersji programu) */
    protected String variable;
    /** Stos operandów */
    protected Stack s;

    /* Program główny */
    public static void main(String[] av) throws IOException {
        if (av.length == 0)
            new SimpleCalcStreamTok(
                new InputStreamReader(System.in)).doCalc();
        else 
            for (int i=0; i<av.length; i++)
                new SimpleCalcStreamTok(av[i]).doCalc();
    }

    /** Tworzy obiekt SimpleCalcStreamTok operujący na pliku o podanej nazwie */
    public SimpleCalcStreamTok(String fileName) throws IOException {
        this(new FileReader(fileName));
    }

    /** Tworzy obiekt SimpleCalcStreamTok operujący na istniejącym obiekcie Reader */
    public SimpleCalcStreamTok(Reader rdr) throws IOException {
        tf = new StreamTokenizer(rdr);
        // Obsługa "wejściowego" zbioru znaków:
        tf.slashSlashComments(true);    // "//" traktowane jako komentarze
        tf.ordinaryChar('-');        // operator odejmowania
        tf.ordinaryChar('/');    // operator dzielenia

        s = new Stack();
    }

    /** Tworzy obiekt SimpleCalcStreamTok operujący na obiektach 
     * Reader oraz PrintWriter.
     */
    public SimpleCalcStreamTok(Reader in, PrintWriter out) throws IOException {
        this(in);
        setOutput(out);
    }
    
    /**
     * Zmiana miejsca docelowego, gdzie będą kierowane wyniki.
     */
    public void setOutput(PrintWriter out) {
        this.out = out;
    }

    protected void doCalc() throws IOException {
        int iType;
        double tmp;

        while ((iType = tf.nextToken()) != StreamTokenizer.TT_EOF) {
            switch(iType) {
            case StreamTokenizer.TT_NUMBER: // Znaleźliśmy liczbę, zapisujemy
                push(tf.nval);              // ją na stosie.
                break;
            case StreamTokenizer.TT_WORD:
                /* Znaleźliśmy zmienną, zapisujemy jej nazwę. 
                 * Możliwość nie jest używana w tej wersji programu. 
                 */
                variable = tf.sval;
                break;
            case '+':
                // Odnaleźliśmy operator +, od razu wykonujemy działanie.
                push(pop() + pop());
                break;
            case '-':
                // Odnaleźliśmy operator +, wykonujemy działanie (z uwzględnieniem kolejności).
                tmp = pop();
                push(pop() - tmp);
                break;
            case '*':
                // Mnożenie dzieła OK.
                push(pop() * pop());
                break;
            case '/':
                // Uważnie obsługujemy dzielenie: kolejność ma znaczenie!
                tmp = pop();
                push(pop() / tmp);
                break;
            case '=':
                out.println(peek());
                break;
            default:
                out.println("Co to jest? iType = " + iType);
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
