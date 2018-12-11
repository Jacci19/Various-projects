package Books_exercises.JavaReceptury.threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

// BEGIN main
/** Prosty przykład wykorzystania szkieletu "Fork/Join":
 * podnosimy do kwadratu grupę liczb, używając do tego celu 
 * obiektu RecursiveAction.
 * W tym przykładzie została zastosowana klasa RecursiveAction,
 * gdyż nie jest konieczne, by każde wywołanie metody compute()
 * zwracało wynik. Wyniki cząstkowe są zbierane w tablicy "dest".
 * @author Ian Darwin
 */
public class RecursiveActionDemo extends RecursiveAction {

    private static final long serialVersionUID = 3742774374013520116L;

    static int[] raw = {
        19, 3, 0, -1, 57, 24, 65, Integer.MAX_VALUE, 42, 0, 3, 5
    };
    static int[] sorted = null;
    
    int[] source;
    int[] dest;
    int length;
    int start;
    final static int THRESHOLD = 4;
    
    public static void main(String[] args) {
        sorted = new int[raw.length];
        RecursiveActionDemo fb = 
            new RecursiveActionDemo(raw, 0, raw.length, sorted);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(fb);
        System.out.print('[');
        for (int i : sorted) {
            System.out.print(i + ",");
        }
        System.out.println(']');
    }
    
    public RecursiveActionDemo(int[] src, int start, int length, int[] dest) {
        this.source = src;
        this.start = start;
        this.length = length;
        this.dest = dest;
      }

    @Override
    protected void compute() {
        System.out.println("ForkJoinDemo.compute()");
        if (length <= THRESHOLD) { // Obliczamy bezpośrednio.
            for (int i = start; i < start + length; i++) {
                dest[i] = source[i] * source[i];
            }
        } else {                    // Dziel i rządź.
            int split = length / 2;
            invokeAll(
              new RecursiveActionDemo(source, start,         split,          dest),
              new RecursiveActionDemo(source, start + split, length - split, dest));
        }
    }
}
// END main