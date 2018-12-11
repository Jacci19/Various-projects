package Books_exercises.JavaReceptury.structure;

// BEGIN main
public class MyStackDemo {
    public static void main(String[] args) {
        MyStack<String> ms1 = new MyStack<>();
        ms1.push("wilhelm");
        ms1.push("szymon");

        while (ms1.hasNext()) {
            String name = ms1.pop();
            System.out.println(name);
        }

        // Stary sposób korzystania z klas kolekcji: mechanizmy 
        // bezpieczeństwa typów nie są używane.
        // TU NIE NALEŻY UŻYWAĆ TYPÓW OGÓLNYCH.
        MyStack ms2 = new MyStack();
        ms2.push("wilhelm");             // Można oczekiwać ostrzeżeń.
        ms2.push("szymon");              // Można oczekiwać ostrzeżeń.
        ms2.push(new java.util.Date());  // Można oczekiwać ostrzeżeń.
        
        // Demonstracja problemów.
        try {
            String bad = (String)ms2.pop();
            System.err.println("Brak oczekiwanego wyjątku, pobrano " + bad);
        } catch (ClassCastException ex) {
            System.out.println("Zgłoszono oczekiwany wyjątek.");
        }

        // Sposób usunięcia problemów, wyświetlenie pozostałej 
        // zawartości stosu.
        while (ms2.hasNext()) {
            String name = (String)ms2.pop();
            System.out.println(name);
        }
    }
}
// END main
