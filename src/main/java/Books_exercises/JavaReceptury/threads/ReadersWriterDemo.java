package Books_exercises.JavaReceptury.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Program symuluje wielu czytelników pobierających dane korzystając
 * przy tym z blokady ReadWriteLock, oraz jednego pisarza, który
 * może zapisywać dane. 
 */
// BEGIN main
public class ReadersWriterDemo {
    private static final int NUM_READER_THREADS = 3;

    public static void main(String[] args) {
        new ReadersWriterDemo().demo();
    }

    /** Tej fladze należy przypisać wartość true, aby 
     * zakończyć działanie programu. */
    private volatile boolean done = false;

    /** Chronione dane. */
    private BallotBox theData;

    /** Połączenie blokad odczytu i zapisu. */
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * Konstruktor: generujemy pseudolosowe dane początkowe.
     */
    public ReadersWriterDemo() {
        List<String> questionsList = new ArrayList<>();
        questionsList.add("Za");
        questionsList.add("Przeciw");
        questionsList.add("Nie mam zdania");
        theData = new BallotBox(questionsList);
    }

    /**
     * Metoda uruchamia przykład, tworząc więcej wątków odczytujących
     * niż zapisujących.
     */
    private void demo() {

        // Uruchamiamy dwa wątki czytelników.
        for (int i = 0; i < NUM_READER_THREADS; i++) {
            new Thread() {
                public void run() {
                    while (!done) {
                        lock.readLock().lock();
                        try {
                            theData.forEach(p -> 
                                System.out.printf("%s: oddano %d głosów%n", 
                                    p.getName(),
                                    p.getVotes()));
                        } finally {
                            // Odblokowujemy w klauzuli "finally", aby 
                            // mieć pewność, że blokada zostanie zwolniona.
                            lock.readLock().unlock();
                        }
                        
                        try {
                            Thread.sleep(((long)(Math.random()* 1000)));
                        } catch (InterruptedException ex) {
                            // Nie ma nic do zrobienia.
                        }
                    }
                }
            }.start();
        }
        
        // Uruchamiamy jeden wątek pisarza, symulując w ten sposób 
        // sporadyczne operacje przesłania ankiety.
        new Thread() {
            public void run() {
                while (!done) {
                    lock.writeLock().lock();
                    try {
                        theData.voteFor(
                            // Głosujemy na losową odpowiedź.
                            // Poprawa wydajności: należałoby zastosować
                            // po jednym generatorze liczb pseudolosowych
                            // dla każdego wątku.
                            (((int)(Math.random()*
                            theData.getCandidateCount()))));
                    } finally {
                        lock.writeLock().unlock();
                    }
                    try {
                        Thread.sleep(((long)(Math.random()*1000)));
                    } catch (InterruptedException ex) {
                        // Nie ma nic do zrobienia.
                    }
                }
            }
        }.start();

        // W wątku głównym czekamy przez chwilę, a następnie kończymy
        // działanie programu.
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException ex) {
            // Nie ma nic do zrobienia.
        } finally {
            done = true;
        }
    }
}
// END main
