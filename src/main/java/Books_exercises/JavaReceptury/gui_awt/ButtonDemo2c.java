package Books_exercises.JavaReceptury.gui_awt;

import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Prezentacja użycia klasy Button */
// BEGIN main
public class ButtonDemo2c extends Applet {
    Button    b;

    public void init() {
        add(b = new Button("Przycisk"));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showStatus("Dziękuję za kliknięcie pierwszego przycisku!");
            }
        });
        add(b = new Button("Drugi przycisk"));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showStatus("Dziękuję za kliknięcie drugiego przycisku!");
            }
        });
    }
}
// END main
