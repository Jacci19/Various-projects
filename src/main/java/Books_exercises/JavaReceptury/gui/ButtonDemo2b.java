package Books_exercises.JavaReceptury.gui;

import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Przedstawienie użycia dwóch przycisków i jednego obiektu ActionListener, 
 * którym jest jednak członkowska klasa wewnętrzna.
 */
// BEGIN main
public class ButtonDemo2b extends Applet {
    Button b1, b2;
    ActionListener handler = new ButtonHandler();

    public void init() {
        add(b1 = new Button("Przycisk"));
        b1.addActionListener(handler);

        add(b2 = new Button("Drugi przycisk"));
        b2.addActionListener(handler);
    }

    class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1)
                showStatus("Dziękuję za kliknięcie pierwszego przycisku!");
            else
                showStatus("Dziękuję za kliknięcie drugiego przycisku!");
        }
    }
}
// END main
