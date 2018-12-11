package Books_exercises.JavaReceptury.threads;

// BEGIN main
public class RunnableLambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("Witam w wątku")).start();
    }
}
// END main
