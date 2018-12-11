package Books_exercises.JavaReceptury.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

/** Program tworzy obiekt JFrame w sposób bezpieczny pod względem
 * wielowątkowym.
 * <br/>
 * Patrz także http://java.sun.com/developer/JDCTechTips/2003/tt1208.html.
 */
// BEGIN main
public class JFrameDemoSafe {
    // Program główny musi zainicjować i wyświetlić graficzny interfejs
    // aplikacji.
    public static void main(String[] args) {

        // Tworzymy graficzny interfejs aplikacji (zmienna jest 
        // sfinalizowana, gdyż używa jej klasa wewnętrzna).
        final JFrame demo = new JFrameDemo();

        // Tworzymy obiekt Runnable, który wyświetli ramkę aplikacji
        // i który będzie mógł być wywołany przez pakiet Swing.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                demo.setVisible(true);
            }
        });
    }
}
// END main
