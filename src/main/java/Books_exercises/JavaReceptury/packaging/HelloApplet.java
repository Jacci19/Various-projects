package Books_exercises.JavaReceptury.packaging;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;

/**
 * HelloApplet to prosty aplet, zmieniający wyświetlany komunikat
 * po kliknięciu przycisku.
 */
// BEGIN main
public class HelloApplet extends JApplet {

    /** Flaga zarządzająca wyświetlaniem komunikatu. */
    protected boolean requested;

    /** init() jest metodą wywoływaną przez przeglądarkę w celu 
     * inicjalizacji apletu. */
    public void init() {
        JButton b;
        requested = false;
        Container cp = (Container)getContentPane();
        cp.setLayout(new FlowLayout());
        String buttonLabel = getParameter("buttonlabel");
        if (buttonLabel == null) {
            buttonLabel = "Rysuj/nie rysuj";
        }
        cp.add(b = new JButton(buttonLabel));
        b.addActionListener(new ActionListener() {
            /* Button - zmieniający stan flagi określającej, czy 
             * mamy rysować, czy też nie.
             */
            public void actionPerformed(ActionEvent e) {
                String arg = e.getActionCommand();
                // Zmieniamy stan flagi na przeciwny.
                requested = !requested;
                do_the_work();
            }
        });
    }

    /** paint() to metoda obiektu Component pakietu AWT wywoływana, gdy
     *  powierzchnia komponentu musi zostać przerysowana.
     */
    public void do_the_work() {
        /* Jeśli przycisk jest wybrany, to wyświetlamy coś. */
        if (requested) {
            showStatus("Witamy w Javie!");
        } else {
            showStatus("");    // Usuwamy komunikat? :-)
        }
    }
}
// END main
