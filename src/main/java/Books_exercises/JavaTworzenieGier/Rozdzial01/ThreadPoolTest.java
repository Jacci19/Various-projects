package Books_exercises.JavaTworzenieGier.Rozdzial01;

public class ThreadPoolTest {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Test zadan ThreadPool.");
            System.out.println(
                    "Uzycie: java ThreadPoolTest liczbaZadan liczbaWatkow");
            System.out.println(
                    "  iloscZadan - liczba: liczba zadan do wykonania.");
            System.out.println(
                    "  iloscWatkow - liczba: liczba watkow " +
                            "w puli.");
            //        return;
        }
        int numTasks = 9;//  Integer.parseInt(args[0]);
        int numThreads = 4;//Integer.parseInt(args[1]);

        // Utworzenie puli wątków:
        ThreadPool threadPool = new ThreadPool(numThreads);
        ThreadPool threadPool1 = new ThreadPool(numThreads);

        // Uruchomienie przykładowych wątków:
        for (int i=0; i<numTasks; i++) {
            threadPool.runTask(createTask(i));
        }

        // Zamknięcie puli i oczekiwanie na zakończenie wszystkich zadań.
        threadPool.join();
        threadPool1.join();
    }


    /**
     Tworzy prosty obiekt Runnable drukujący ID i oczekujący 500
     milisekund; następnie ponownie drukowany jest identyfikator zadania.
     */
    private static Runnable createTask(final int taskID) {
        return new Runnable() {
            public void run() {
                System.out.println("Zadanie " + taskID + ": uruchomione");

                // Symulowanie długo działającego zadania:
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex) { }

                System.out.println("Zadanie " + taskID + ": koniec");
            }
        };
    }
}

