package Books_exercises.JavaReceptury.threads;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/** Producent i konsument w Javie, dla wersji języka J2SE 1.5+; używa 
 * kolekcji współbieżnych.
 */
// BEGIN main
public class ProdCons15 {

    protected volatile boolean done = false;

    /** Klasa wewnętrzna reprezentująca producenta. */
    class Producer implements Runnable {

        protected BlockingQueue<Object> queue;

        Producer(BlockingQueue<Object> theQueue) { this.queue = theQueue; }

        public void run() {
            try {
                while (true) {
                    Object justProduced = getRequestFromNetwork();
                    queue.put(justProduced);
                    System.out.println(
                        "Wyprodukowano 1 obiekt; obecnie lista zawiera " + 
                        queue.size() + " elementów.");
                    if (done) {
                        return;
                    }
                }
            } catch (InterruptedException ex) {
                System.out.println(
                        "DZIAŁANIE PRODUCENTA ZOSTAŁO PRZERWANE");
            }
        }

        Object getRequestFromNetwork() {    // Symulujemy odczyt.
            try {
                    Thread.sleep(10); // Symulujemy upływ czasu podczas
                                      // operacji odczytu.
            } catch (InterruptedException ex) {
                 System.out.println(
                         "Odczyt przez producenta został PRZERWANY");
            }
            return new Object();
        }
    }

    /** Klasa wewnętrzna reprezentująca konsumenta. */
    class Consumer implements Runnable {
        protected BlockingQueue<Object> queue;

        Consumer(BlockingQueue<Object> theQueue) { this.queue = theQueue; }

        public void run() {
            try {
                while (true) {
                    Object obj = queue.take();
                    int len = queue.size();
                    System.out.println("Lista zawiera obecnie " + len + 
                            " elementów." );
                    process(obj);
                    if (done) {
                        return;
                    }
                }
            } catch (InterruptedException ex) {
                    System.out.println(
                            "DZIAŁANIE KONSUMENTA ZOSTAŁO PRZERWANE");
            }
        }

        void process(Object obj) {
            // Thread.sleep(123)       // Symulujemy upływ czasu.
            System.out.println("Wykorzystujemy obiekt " + obj);
        }
    }

    ProdCons15(int nP, int nC) {
        BlockingQueue<Object> myQueue = new LinkedBlockingQueue<>();
        for (int i=0; i<nP; i++)
            new Thread(new Producer(myQueue)).start();
        for (int i=0; i<nC; i++)
            new Thread(new Consumer(myQueue)).start();
    }

    public static void main(String[] args)
    throws IOException, InterruptedException {

        // Uruchamiamy wątki producentów i konsumentów. 
        int numProducers = 4;
        int numConsumers = 3;
        ProdCons15 pc = new ProdCons15(numProducers, numConsumers);

        // Niech wątki działają przez, powiedzmy, 10 sekund.
        Thread.sleep(10*1000);

        // Koniec symulacji - kończymy ją w "delikatny" sposób.
        pc.done = true;
    }
}
// END main
