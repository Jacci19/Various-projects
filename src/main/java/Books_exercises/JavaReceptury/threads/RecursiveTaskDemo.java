package Books_exercises.JavaReceptury.threads;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// BEGIN main
/**
 * Program przedstawia zastosowanie szkieletu "Fork/Join" do
 * wyliczenia średniej dużej tablicy liczb.
 * Wykonanie tego programu na komputerze z wielordzeniowym 
 * procesorem przy użyciu poniższego wywołania:
 * $ time java threads.RecursiveTaskDemo
 * pokazuje, że czas zużyty przez procesor zawsze jest większy 
 * od czasu działania programu, co stanowi dowód na wykorzystywanie 
 * kilku rdzeni procesora. Trzeba jednak pamiętać o tym, że jest
 * to dosyć sztuczny przykład.
 *
 * Jak pokazuje ten przykład, typu RecursiveTask<T> można używać, gdy
 * każde wywołanie zwraca wartość reprezentującą wynik obliczeń 
 * dokonanych na pewnym fragmencie całego zadania.
 * @author Ian Darwin
 */
public class RecursiveTaskDemo extends RecursiveTask<Long> {

    private static final long serialVersionUID = 3742774374013520116L;

    static final int N = 10000000;
    final static int THRESHOLD = 500;

    int[] data;
    int start, length;
    
    public static void main(String[] args) {
        int[] source = new int[N];
        loadData(source);
        RecursiveTaskDemo fb = new RecursiveTaskDemo(source, 0, source.length);
        ForkJoinPool pool = new ForkJoinPool();
        long before = System.currentTimeMillis();
        pool.invoke(fb);
        long after = System.currentTimeMillis();
        long total = fb.getRawResult();
        long avg = total / N;
        System.out.println("Średnia: " + avg);
        System.out.println("Czas :" + (after - before) + " mSec");
    }

    static void loadData(int[] data) {
        Random r = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = r.nextInt();
        }
    }
    
    public RecursiveTaskDemo(int[] data, int start, int length) {
        this.data = data;
        this.start = start;
        this.length = length;
    }

    @Override
    protected Long compute() {
        if (length <= THRESHOLD) { // Obliczamy bezpośrednio.
            long total = 0;
            for (int i = start; i < start + length; i++) {
                total += data[i];
            }
            return total;
        } else {                    // Dziel i rządź.
            int split = length / 2;
            RecursiveTaskDemo t1 =
                new RecursiveTaskDemo(data, start,         split);
            t1.fork();
            RecursiveTaskDemo t2 =
                new RecursiveTaskDemo(data, start + split, length - split);
            return t2.compute() + t1.join();
        }
    }
}
// END main
