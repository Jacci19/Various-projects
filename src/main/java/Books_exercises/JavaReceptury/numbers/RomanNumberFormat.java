package Books_exercises.JavaReceptury.numbers;

import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 * Klasa, zapisując wartości przy wykorzystaniu cyfr rzymskich, 
 * nie uwzględnia informacji lokalnych, gdyż łacina jest językiem 
 * martwym i niezależnie od ustawień lokalnych, liczby takie 
 * zawsze są wyświetlane w ten sam sposób.
 * Program wykorzystuje szybkie i niezbyt przejrzyste algorytmy.
 *
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class RomanNumberFormat extends Format {

    private static final long serialVersionUID = -2303809319102357783L;
    
    /** Znaki używane przez metodę format() do konwersji liczby rzymskiej
     * na arabską. */
    final static char A2R[][] = {
            { 0, 'M' },
            { 0, 'C', 'D', 'M' },
            { 0, 'X', 'L', 'C' },
            { 0, 'I', 'V', 'X' },
    };
    
    static class R2A {
        char ch;
        public R2A(char ch, int amount) {
            super();
            this.ch = ch;
            this.amount = amount;
        }
        int amount;
    }
    
    final static R2A[] R2A = {
        new R2A('M', 1000),
        new R2A('D', 500),
        new R2A('C', 100),
        new R2A('L', 50),
        new R2A('X', 10),
        new R2A('V', 5),
        new R2A('I', 1),
    };

    /** Zapisuje podaną liczbę typu double jako rzymską, 
     * w pierwszej kolejności przekształca podaną liczbę na wartość 
     * typu long, a następnie wywołuje metodę format(long).
     */
    public String format(double n) {
        return format((long)n);
    }

    /** Zapisuje podaną liczbę typu long jako rzymską. 
     * Po prostu wywołuje trójargumentową formę metody.
     */
    public String format(long n) {
        if (n <= 0 || n >= 4000)
            throw new NumberFormatException(n + " musi być > 0 i < 4000");
        StringBuffer sb = new StringBuffer();
        format(Integer.valueOf((int)n), sb,
            new FieldPosition(NumberFormat.INTEGER_FIELD));
        return sb.toString();
    }

    /* Zapisuje podaną liczbę (obiekt Number) jako rzymską, zwracając obiekt
     * StringBuffer (zaktualizowany) oraz aktualizując wartość FieldPosition. 
     * Metoda jest faktycznym mechanizmem formatującym.
     * Sygnatura metody jest dość przerażająca, lecz niestety, taką 
     * postać muszą mieć klasy pochodne klasy Format.
     */
    public StringBuffer format(Object on, StringBuffer sb, FieldPosition fp) {
        if (!(on instanceof Number))
            throw new IllegalArgumentException(on + " musi być obiektem Number");
        if (fp.getField() != NumberFormat.INTEGER_FIELD)
            throw new IllegalArgumentException(
            fp + " musi być FieldPosition(NumberFormat.INTEGER_FIELD");
        int n = ((Number)on).intValue();    // TODO: sprawdzać zakres.

        // W pierwszej kolejności umieszczamy liczby na stosie.
        // Muszą być 4 liczby.
        for (int i=0; i<4; i++) {
            int d=n%10;
            push(d);
            // System.out.println("Umieszczono na stosie " + d);
            n=n/10;
        }

        // Teraz pobieramy ze stosu i konwertujemy.
        for (int i=0; i<4; i++) {
            int ch = pop();
            // System.out.println("Pobrano ze stosu " + ch);
            if (ch==0)
                continue;
            else if (ch <= 3) {
                for(int k=1; k<=ch; k++)
                    sb.append(A2R[i][1]); // I
            }
            else if (ch == 4) {
                sb.append(A2R[i][1]);    // I
                sb.append(A2R[i][2]);    // V
            }
            else if (ch == 5) {
                sb.append(A2R[i][2]);    // V
            }
            else if (ch <= 8) {
                sb.append(A2R[i][2]);    // V
                for (int k=6; k<=ch; k++)
                    sb.append(A2R[i][1]);    // I
            }
            else { // 9
                sb.append(A2R[i][1]);
                sb.append(A2R[i][3]);
            }
        }
        // fp.setBeginIndex(0);
        // fp.setEndIndex(3);
        return sb;
    }

    /** Przetwarzamy obiekt ogólny, zwracamy obiekt klasy Object. */
    public Object parseObject(String what, ParsePosition where) {
        int n = 0;
        for (char ch : what.toUpperCase().toCharArray()) {
            for (R2A r : R2A) {
                if (r.ch == ch) {
                    n += r.amount;
                    break;
                }
            }
        }        
        return new Long(n);
    }

    /* Implementacja niewielkiego stosu. */
    protected int stack[] = new int[10];
    protected int depth = 0;

    /* Implementacja niewielkiego stosu. */
    protected void push(int n) {
        stack[depth++] = n;
    }
    /* Implementacja niewielkiego stosu. */
    protected int pop() {
        return stack[--depth];
    }
}
// END main
