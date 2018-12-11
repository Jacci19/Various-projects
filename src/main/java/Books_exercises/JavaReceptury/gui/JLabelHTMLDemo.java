package Books_exercises.JavaReceptury.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** Wyświetlanie kodu HTML w komponentach JLabel.
 */
// BEGIN main
public class JLabelHTMLDemo extends JFrame {

    /** Tworzymy obiekt i graficzny interfejs użytkownika. */
    public JLabelHTMLDemo() {
        super("JLabelHTMLDemo");
        Container cp = getContentPane();

        JButton component = new JButton(
            "<html>" +
            "<body bgcolor='white'>" +
            "<h1><font color='red'>Witam!</font></h1>" +
            "<p>Ten przycisk zostanie sformatowany zgodnie ze  " +
            "standardowymi metodami formatowania akapitów tekstu " +
            "w kodzie HTML.</p>" +
            "</body></html>");

        component.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Dziękuję bardzo!");
            }
        });
        cp.add(BorderLayout.CENTER, component);

        setSize(230, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new JLabelHTMLDemo().setVisible(true);
    }
}
// END main
