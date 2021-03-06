package Books_exercises.JavaReceptury.threads;

/** 
 * Demonstracyjna aplikacja wielowątkowa, wykorzystująca klasę wewnętrzną
 * jako obiekt Runnable.
 * @author    Ian Darwin
 */
// BEGIN main
public class ThreadsDemo3 {
    private Thread t;
    private int count;

    /**
     * Program główny testujący klasę ThreadsDemo3.
     */
    public static void main(String[] argv) {
        new ThreadsDemo3("Witamy z wątku X", 10);
        new ThreadsDemo3("Witamy z wątku Y", 15);
    }

    /**
     * Tworzymy obiekt ThreadsDemo3.
     * param m Wyświetlany komunikat.
     * @param n Ile razy komunikat należy wyświetlić.
     */
    public ThreadsDemo3(final String mesg, int n) {
        count = n;
        t = new Thread(new Runnable() {
            public void run() {
                while (count-- > 0) {
                    System.out.println(mesg);
                    try {
                        Thread.sleep(100);    // 100 ms
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                System.out.println(mesg + " wszystko gotowe.");
            }
        });
        t.setName("Wątek roboczy nr " + mesg);
        t.start();
    }
}
// END main
