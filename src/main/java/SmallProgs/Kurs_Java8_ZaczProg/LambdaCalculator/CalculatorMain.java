package SmallProgs.Kurs_Java8_ZaczProg.LambdaCalculator;

import java.util.HashMap;
import java.util.Map;

/**http://zacznijprogramowac.net/praktyczny-przyklad-kalkulator-lambda-i-interfejs-funkcjonalny/
 *
 * Lekcja opisuje zastosowanie wyrażeń lambda i interfejsu funkcjonalnego w prostym kalkulatorze
 */

public class CalculatorMain {

    /** Tworzymy mapę która przechowywać będzie lambdy i symbol kalkulacji. Parametrami jest String oraz nasz interfejs funkcjonalny     */

    private static final Map<String, CalculationInterface> calculationMap = new HashMap<>();


    public static void main(String[] args) {
        fillMap();
        result("+", 1, 2);
        result("-", 2, 2);
        result("/", 4, 4);
        result("*", 5, 2);
    }

    /** Wypełniamy mapę symbolem kalkulacji i lambdami           */

    private static void fillMap() {
        calculationMap.put("+", (a, b) -> a + b);
        calculationMap.put("-", (a, b) -> a - b);
        calculationMap.put("/", (a, b) -> a / b);
        calculationMap.put("*", (a, b) -> a * b);
    }

    //CalculationInterface add = (a, b) -> a + b;                   //linię 29 możemy rozpisać na takie dwa wersy
    //calculationMap.put("+", add);

    /** Ta metoda zwraca nam gotowy wynik po wybraniu operatorem odpowiedniej lambdy i wykonaniu metody calculate
     * @param operator
     * @param a
     * @param b      */

    private static void result(String operator, double a, double b) {
        double result = calculationMap.get(operator).calculate(a, b);
        System.out.println(result);
    }
}