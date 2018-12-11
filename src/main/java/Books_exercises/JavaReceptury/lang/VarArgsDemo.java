package Books_exercises.JavaReceptury.lang;

import java.io.PrintStream;
import java.util.Date;

/**
 * VarArgsDemo - prezentacja zmiennych list argumentów wprowadzonych 
 * w Java 5.
 * @author ian
 */
public class VarArgsDemo {

    public static void main(String[] args) {
        mySumCalls();
        // BEGIN processCalls
        process(System.out, "Witaj", "Do widzenia");
        process(System.out, (int)42, (int)1066, (int)1776);
        process(System.out, "Foo", new Date(), new Object());
        // END processCalls
        // BEGIN passThroughCalls
        passThrough(System.out, "%s %s %s%n", "Foo", new Date(), new Object());
        // END passThroughCalls
    }

    // BEGIN mySumDefn
    static int mySum(int... args) {
        int total = 0;
        for (int a : args) {
            total += a;
        }
        return total;
    }
    // END mySumDefn

    public static void mySumCalls() {
        // BEGIN mySumCalls
        System.out.println(mySum(5, 7, 9));
        System.out.println(mySum(5));
        System.out.println(mySum());
        int[] nums = {5, 7, 9};
        System.out.println(mySum(nums));
        // END mySumCalls
    }

    /** Prezentacja przekazywania całej listy argumentów do innej metody.
     * @param out
     * @param fmt
     * @param args
     */
    static void passThrough(PrintStream out, String fmt, Object ... args) {
        line();
        out.printf(fmt, args);
    }
    
    /** Prezentacja pobierania zawartości zmiennej listy argumentów.
     * @param out
     * @param args
     */
    static void process(PrintStream out, Object ... args) {
        line();
        int i = 0;
        for (Object o : args){
            out.print("Argumentem " + ++i + " jest " + o + "; ");
        }
        System.out.println();
    }
    
    /** Metoda pomocnicza, rysuje testową linię... */
    private static void line() {
        System.out.println("--------------------------");
    }
}
