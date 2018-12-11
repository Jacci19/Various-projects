package Books_exercises.JavaReceptury.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// BEGIN main
/** Program przedstawia obsługę przycisków JButton przy użyciu 
 * wyrażeń lambda. */
public class ButtonDemo2L extends JFrame {

    private static final long serialVersionUID = 1L;

    public ButtonDemo2L() {
        super("ButtonDemo Lambda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JButton    b;
        add(b = new JButton("Przycisk"));
        // W stylu minimalistycznym.
        b.addActionListener(e -> JOptionPane.showMessageDialog(this,
            "Dziękuję za kliknięcie pierwszego przycisku!"));

        add(b = new JButton("Kolejny przycisk"));
        // Nieco dłuższy zapis z kodem w nawiasach klamrowych.
        b.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Dziękuję za kliknięcie drugiego przycisku!");
            }
        );

        pack();
    }
    
    public static void main(String[] args) {
        new ButtonDemo2L().setVisible(true);
    }
}
// END main
