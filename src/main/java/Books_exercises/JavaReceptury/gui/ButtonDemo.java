package Books_exercises.JavaReceptury.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

// BEGIN main
/** Prezentacja wykorzystania przycisku. */
public class ButtonDemo extends JFrame implements ActionListener {
    JButton    b1;

    public ButtonDemo() {
        setLayout(new FlowLayout());
        add(b1 = new JButton("Przycisk"));
        b1.addActionListener(this);
        setSize(300, 200);
    }

    public void actionPerformed(ActionEvent event) {
        System.out.println("Dziękuję za kliknięcie przycisku!");
    }
    
    public static void main(String[] unuxed) {
        new ButtonDemo().setVisible(true);
    }
}
// END main
