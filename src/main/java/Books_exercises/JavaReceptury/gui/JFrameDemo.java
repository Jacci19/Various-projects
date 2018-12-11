package Books_exercises.JavaReceptury.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** Sama ramka.
 */
// BEGIN main
public class JFrameDemo extends JFrame {

    private static final long serialVersionUID = -3089466980388235513L;
    JButton quitButton;

    /** Tworzymy obiekt włącznie z umieszczonymi w nim elementami 
     * graficznego interfejsu użytkownika. */
    public JFrameDemo() {
        super("JFrameDemo");
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(quitButton = new JButton("Koniec"));

        // Te "procedury obsługi czynności" zostaną dokładniej opisane
        // w dalszej części rozdziału.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ta "procedura obsługi czynności" zostanie dokładniej 
        // wyjaśniona w dalszej części rozdziału.
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });
            
        pack();
        setLocation(500, 400);
    }
    public static void main(String[] args) {
        new JFrameDemo().setVisible(true);
    }
}
// END main
