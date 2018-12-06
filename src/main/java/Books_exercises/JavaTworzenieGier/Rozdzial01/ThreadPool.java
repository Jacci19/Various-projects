package Books_exercises.JavaTworzenieGier.Rozdzial01;

import java.util.LinkedList;

/**
 Pula wątków stanowi grupę z określoną liczbą wątków,
 które mogą być używane do wykonywania zadań.
 */
public class ThreadPool extends ThreadGroup {

    private boolean isAlive;
    private LinkedList taskQueue;
    private int threadID;
    private static int threadPoolID;

    /**
     Tworzy nowy obiekt ThreadPool.
     @param numThreads Liczba wątków w puli.
     */
    public ThreadPool(int numThreads) {
        super("ThreadPool-" + (threadPoolID++));
        System.out.println("ThreadPool-" + (threadPoolID));
        setDaemon(true);

        isAlive = true;

        taskQueue = new LinkedList();
        for (int i=0; i<numThreads; i++) {
            new PooledThread().start();
        }
    }


    /**
     Powoduje uruchomienie nowego zadania. Metoda ta natychmiast się kończy,
     a zadanie jest wykonywane przez następny wolny wątek z ThreadPool.
     <p>Zadania są wykonywane w kolejności ich uruchomienia.
     @param task Zadanie do wykonania. Jeżeli ma wartość null,
     nie jest podejmowana żadna akcja.
     @throws IllegalStateException - w przypadku, gdy ten obiekt
     ThreadPool jest zamknięty.
     */
    public synchronized void runTask(Runnable task) {
        if (!isAlive) {
            throw new IllegalStateException();
        }
        if (task != null) {
            taskQueue.add(task);
            notify();
        }

    }


    protected synchronized Runnable getTask()
            throws InterruptedException
    {
        while (taskQueue.size() == 0) {
            if (!isAlive) {
                return null;
            }
            wait();
        }
        return (Runnable)taskQueue.removeFirst();
    }


    /**
     Zamyka ten obiekt ThreadPool i kończy pracę. Wszystkie zadania są
     zatrzymywane i nie zostanie uruchomiony żaden wątek oczekujący.
     Po zamknięciu obiektu ThreadPool nie może on już
     uruchamiać żadnych zadań.
     */
    public synchronized void close() {
        if (isAlive) {
            isAlive = false;
            taskQueue.clear();
            interrupt();
        }
    }


    /**
     Zamyka tan obiekt ThreadPool i czeka na zakończenie wszystkich
     działających wątków. Wykonywane są wszystkie oczekujące wątki.
     */
    public void join() {
        // Powiadamia wszystkie oczekujące wątki, że bieżący ThreadPool
        // został wyłączony.
        synchronized (this) {
            isAlive = false;
            notifyAll();
        }

        // Oczekiwanie na zakończenie wszystkich wątków.
        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);
        for (int i=0; i<count; i++) {
            try {
                threads[i].join();
            }
            catch (InterruptedException ex) { }
        }
    }


    /**
     PooledThread jest wątkiem z grupy ThreadPool,
     który może wykonywać zadania (Runnable).
     */
    private class PooledThread extends Thread {


        public PooledThread() {
            super(ThreadPool.this,
                    "PooledThread-" + (threadID++));
        }


        public void run() {
            while (!isInterrupted()) {

                // Pobranie zadania do wykonania.
                Runnable task = null;
                try {
                    task = getTask();
                }
                catch (InterruptedException ex) { }

                // Jeżeli getTask() zwróci null lub zostanie
                // przerwany, wątek zostanie zamknięty.
                if (task == null) {
                    return;
                }

                // Uruchamia wątek i zbiera wszystkie zgłoszone
                // przez niego wyjątki.
                try {
                    task.run();
                }
                catch (Throwable t) {
                    uncaughtException(this, t);
                }
            }
        }
    }
}
