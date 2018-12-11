package Books_exercises.JavaReceptury.gui;

import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Przedstawienie użycia dwóch przycisków i jednego obiektu ActionListener, 
 * którym jest ta sama klasa.
 */
// BEGIN main
public class ButtonDemo2a extends Applet implements ActionListener {
    Button b1, b2;

    public void init() {
        add(b1 = new Button("Przycisk"));
        b1.addActionListener(this);

        add(b2 = new Button("Inny przycisk"));
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1)
            showStatus("Dziękuję za kliknięcie pierwszego przycisku!");
        else
            showStatus("Dziękuję za kliknięcie drugiego przycisku!");
    }
}
// END main
