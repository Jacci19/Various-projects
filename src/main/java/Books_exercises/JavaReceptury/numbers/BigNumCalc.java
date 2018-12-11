package Books_exercises.JavaReceptury.numbers;

import java.math.BigDecimal;
import java.util.Stack;

/** Prosty kalkulator wykorzystujący zasady odwrotnej notacji 
 * polskiej i operujący na bardzo dużych liczbach.
 */
// BEGIN main
public class BigNumCalc {

    /** Tablica obiektów symulująca dane wpisywane przez użytkownika. */
    public static Object[] testInput = {
        new BigDecimal("3419229223372036854775807.23343"),
        new BigDecimal("2.0"),
        "*",
    };

    public static void main(String[] args) {
        BigNumCalc calc = new BigNumCalc();
        System.out.println(calc.calculate(testInput));
    }

    /**
     * Stos liczb używanych w obliczeniach.
     */
    Stack<BigDecimal> stack = new Stack<>();

    /**
     * Obliczanie operandów; do metody przekazywana jest tablica typu 
     * Object zawierająca zarówno obiekty BigDecimal (które 
     * mogą być umieszczane na stosie), jak i operatory (które są 
     * przetwarzane natychmiast).
     * @param input
     * @return
     */
    public BigDecimal calculate(Object[] input) {
        BigDecimal tmp;
        for (int i = 0; i < input.length; i++) {
            Object o = input[i];
            if (o instanceof BigDecimal) {
                stack.push((BigDecimal) o);
            } else if (o instanceof String) {
                switch (((String)o).charAt(0)) {
                // + oraz * są łączne, kolejność nie ma znaczenia
                case '+':
                    stack.push((stack.pop()).add(stack.pop()));
                    break;
                case '*':
                    stack.push((stack.pop()).multiply(stack.pop()));
                    break;
                // - lub /, kolejność *ma* znaczenie
                case '-':
                    tmp = (BigDecimal)stack.pop();
                    stack.push((stack.pop()).subtract(tmp));
                    break;
                case '/':
                    tmp = stack.pop();
                    stack.push((stack.pop()).divide(tmp,
                        BigDecimal.ROUND_HALF_UP));
                    break;
                default:
                    throw new IllegalStateException("Pobrano nieznany operator!");
                }
            } else {
                throw new IllegalArgumentException("Błąd syntaktyczny w danych wejściowych!");
            }
        }
        return stack.pop();
    }
}
// END main
