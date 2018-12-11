package Books_exercises.JavaReceptury.gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// BEGIN main
public class JFrameFlowLayout extends JFrame {
    public JFrameFlowLayout() {
        Container cp = getContentPane();

        // Upewniamy się, że będzie wykorzystywany 
        // menedżer układu FlowLayout.
        cp.setLayout(new FlowLayout());

        // Teraz dodajemy komponenty do "cp"...
        cp.add(new JLabel("Wspaniale?"));
        cp.add(new JButton("O tak!"));
        pack();
    }

    // Potrzebujemy też prostego programu głównego,
    // żeby wyświetlić okno.
    public static void main(String[] args) {
        new JFrameFlowLayout().setVisible(true);
    }
}
// END main
