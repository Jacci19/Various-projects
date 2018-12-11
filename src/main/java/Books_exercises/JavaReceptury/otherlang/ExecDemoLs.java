package Books_exercises.JavaReceptury.otherlang;

import com.darwinsys.util.Debug;

import java.io.*;

/**
 * ExecDemoLs pokazuje w jaki sposób można wykonać zewnętrzny program
 * (w tym przypadku jest to polecenie systemowe ls) i odczytać wygenerowane
 * przez niego wyniki.
 */
// BEGIN main
public class ExecDemoLs {
    /** Program, który należy uruchomić. */
    public static final String PROGRAM = "ls"; // "dir" w przypadku Windows
    /** True, aby zakończyć pętlę. */
    static volatile boolean done = false;

    public static void main(String argv[]) throws IOException {

        final Process p;      // Obiekt process reprezentuje jeden rodzimy
                              // proces.
        BufferedReader is;    // Obiekt, w którym będą zapisywane wyniki
                              // wykonywanego procesu.
        String line;
        
        p = Runtime.getRuntime().exec(PROGRAM);

        Debug.println("exec", "W metodzie main po wywołaniu exec.");

        // Opcjonalne: uruchamiamy wątek oczekujący na zakończenie 
        // procesu. Nie będziemy czekać w metodzie main() - tutaj 
        // ustawiamy jedynie flagę "done" i używamy jej do kontroli
        // działania głównej pętli odczytującej, umieszczonej poniżej.
        Thread waiter = new Thread() {
            public void run() {
                try {
                    p.waitFor();
                } catch (InterruptedException ex) {
                    // OK, po prosty kończymy.
                    return;
                }
                System.out.println("Program został zakończony!");
                done = true;
            }
        };
        waiter.start();

        // Metoda getInputStream zwraca strumień wejściowy (InputStream)
        // skojarzony ze standardowym wyjściem uruchomionego programu
        // zewnętrznego (i na odwrót). Użyjemy go do utworzenia obiektu
        // BufferedReader, dzięki czemu będziemy mogli odczytywać wiersze
        // tekstu przy użyciu metody readLine().
        
        is = new BufferedReader(new InputStreamReader(p.getInputStream()));

        while (!done && ((line = is.readLine()) != null))
            System.out.println(line);
        
        Debug.println("exec", "W metodzie main po zakończeniu odczytu.");

        return;
    }
}
// END main
