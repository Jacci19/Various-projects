package Books_exercises.JavaReceptury.threads;


/** 
 * Demonstracyjna aplikacja wielowątkowa, wykorzystująca interfejs Runnable.
 *
 * @author    Ian Darwin
 */
// BEGIN main
public class ThreadsDemo2 implements Runnable {
    private String mesg;
    private Thread t;
    private int count;

    /**
     * Program główny testujący działanie klasy ThreadsDemo2.
     */
    public static void main(String[] argv) {
        new ThreadsDemo2("Witamy z wątku X", 10);
        new ThreadsDemo2("Witamy z wątku Y", 15);
    }

    /**
     * Tworzymy obiekt DemoThread2.
     * @param m Wyświetlany komunikat.
     * @param n Ile razy komunikat należy wyświetlić.
     */
    public ThreadsDemo2(String m, int n) {
        count = n;
        mesg  = m;
        t = new Thread(this);
        t.setName("Wątek roboczy nr " + m);
        t.start();
    }

    /** Metoda run() wykonuje całą robotę. Przesłaniamy metodę run() 
     * interfejsu Runnable. 
     */ 
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
}
// END main
