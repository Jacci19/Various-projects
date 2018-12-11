package Books_exercises.JavaReceptury.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/* 
 * Colors - prezentacja klasy JColorChooser pakietu Swing.
 * Klasy JColorChooser można używać na trzy sposoby:
 * <UL><LI>Stworzyć obiekt i wyświetlić go w panelu.
 * <LI>Wywołać metodę createDialog() i uzyskać obiekt JDialog.
 * <LI>Wywołać metodę showDialog() i uzyskać wybrany kolor.
 * </UL>
 * <P>Użyjemy trzeciego sposobu, gdyż jest on najprostszy i 
 * prawdopodobnie to właśnie on będzie wykorzystywany przy 
 * tworzeniu aplikacji.
 *
 * Originally appeared in the Linux Journal, 1999.
 */
// BEGIN main
public class JColorChooserDemo extends JFrame {
    /** Etykieta używana do prezentacji wybranego koloru. */
    protected JLabel demo;

    /** Konstruktor - tworzymy cały interfejs graficzny programu. */
    public JColorChooserDemo() {
        super("Wybieranie kolorów (Swing)");
        Container cp = getContentPane();
        JButton jButton;
        cp.add(jButton = new JButton("Zmień kolor..."), BorderLayout.NORTH);
        jButton.setToolTipText("Kliknij, aby wyświetlić okno Color Chooser");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent)
            {
                Color ch = JColorChooser.showDialog(
                    JColorChooserDemo.this,     // Element nadrzędny.
                    "Wybieranie kolorów",       // Tytuł.
                    demo.getForeground());      // Domyślnie.
                System.out.println("Wybrałeś kolor " + ch);
                if (ch != null) {
                    demo.setForeground(ch);
                    demo.repaint();
                }
            }
        });
        cp.add(BorderLayout.CENTER, demo = 
            new JLabel("Twój jedyny prawdziwy kolor", JLabel.CENTER));
        demo.setToolTipText("Oto ostatni wybrany kolor");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /** Główna metoda programu. */
    public static void main(String[] argv) {
        new JColorChooserDemo().setVisible(true);
    }
}
// END main
