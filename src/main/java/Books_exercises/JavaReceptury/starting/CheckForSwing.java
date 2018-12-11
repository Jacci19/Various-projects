package Books_exercises.JavaReceptury.starting;

/** Sprawdzanie dostępności kompnentów Swing w ścieżce klas
 *  podczas działania programu.
 */
// BEGIN main
public class CheckForSwing {
    public static void main(String[] args) {
        try {
            Class.forName("javax.swing.JButton");
        } catch (ClassNotFoundException e) {
            String failure =
                "Przepraszam, ale ta wersja aplikacji wymaga \n" +
                "Javy z nowszymi komponentami JFC/Swing,\n" +
                "należącymi do pakietu javax.swing.*";
            // Lepiej zadbać, by coś się pojawiło na ekranie. Może
            // to być JOptionPane albo myPanel.add(new Label(failure));
            System.err.println(failure);
        }
        // Tu nic nie trzeba wyświetać - GUI powinno działać...
    }
}
// END main