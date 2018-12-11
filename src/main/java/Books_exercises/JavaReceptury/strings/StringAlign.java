package Books_exercises.JavaReceptury.strings;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

/**
 * Minimalna klasa formatująca łańcuchy znaków (określająca wyrówannie).
 */
// BEGIN main
public class StringAlign extends Format {

    private static final long serialVersionUID = 1L;

    public enum Justify {
        /* Stała do wyrównania w lewo. */
        LEFT,
        /* Stała do wyśrodkowania. */
        CENTER,
        /* Stała do wyrównania w prawo. */
        RIGHT,
    }

    /** Bieżący sposób wyrównywania. */
    private Justify just;
    /** Bieżąca długość maksymalna. */
    private int maxChars;

    /** Tworzy obiekt StringAlign; informacje o długości i wyrównaniu są
     * przekazywand do konstruktora a nie do wywołania metody format(),
     * gdyż zazwyczaj obiekt będzie używany do wielokrotnego formatowania
     * na przykład numerów wielu kolejnych stron.
     * @param maxChars - maksymalna długość wiersza
     * @param just - jedna z wartości wyliczenia: LEFT, CENTER lub RIGHT
     */
    public StringAlign(int maxChars, Justify just) {
        switch(just) {
        case LEFT:
        case CENTER:
        case RIGHT:
            this.just = just;
            break;
        default:
            throw new IllegalArgumentException("nieprawidłowy argument.");
        }
        if (maxChars < 0) {
            throw new IllegalArgumentException("arg. maxChars musi być większy od zera.");
        }
        this.maxChars = maxChars;
    }

    /** Formatuj łańcuch znaków.
     * @param input - wyrównywany łańcuch.
     * @parm where - StringBuffer do którego łańcuch zostanie dołączony.
     * @param ignore - obiekt FieldPosition (może być równy null, parametr nie
     *    jest używany lecz wymaga go ogólny kontrakt metody format).
     */
    public StringBuffer format(
        Object input, StringBuffer where, FieldPosition ignore)  {

        String s = input.toString();
        String wanted = s.substring(0, Math.min(s.length(), maxChars));

        // Wstawiamy odstępy w odpowiednie miejsca.
        switch (just) {
            case RIGHT:
                pad(where, maxChars - wanted.length());
                where.append(wanted);
                break;
            case CENTER:
                int toAdd = maxChars - wanted.length();
                pad(where, toAdd/2);
                where.append(wanted);
                pad(where, toAdd - toAdd/2);
                break;
            case LEFT:
                where.append(wanted);
                pad(where, maxChars - wanted.length());
                break;
            }
        return where;
    }

    protected final void pad(StringBuffer to, int howMany) {
        for (int i=0; i<howMany; i++)
            to.append(' ');
    }

    /** Metoda pomocnicza. */
    String format(String s) {
        return format(s, new StringBuffer(), null).toString();
    }

    /** ParseObject jest konieczna, lecz w naszym przypadku nieprzydatna. */
    public Object parseObject(String source, ParsePosition pos)  {
        return source;
    }
}
// END main
