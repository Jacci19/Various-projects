package Books_exercises.JavaReceptury.gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

// BEGIN main
public class ContentPane extends JFrame {
    public ContentPane() {
        Container cp = getContentPane();
        // Teraz można już dodawać komponenty do "cp"...
        cp.add(new JLabel("Naprawdę prosty przykład", JLabel.CENTER));
    }
}
// END main
