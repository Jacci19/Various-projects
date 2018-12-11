package Books_exercises.JavaReceptury.numbers;

/**
 * Ręczne formatownie liczby mnogiej.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class FormatPlurals {
    public static void main(String[] argv) {
        report(0);
        report(1);
        report(2);
    }

    /** report - wykorzystuje operator warunkowy. */
    public static void report(int n) {
       int t = n < 20 ? n : n % 10; 
       if (n==1)
           System.out.println("Zużyto " + n + " element");                  
       else                                                                 
           System.out.println("Zużyto " + n + " element" + 
                   ((t <= 1 || t >= 5)?"ów":"y"));
   }
}
// END main
