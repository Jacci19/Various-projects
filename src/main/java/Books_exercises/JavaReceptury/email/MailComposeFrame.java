package Books_exercises.JavaReceptury.email;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Ramka wyświetlająca (być może) wiele okien MailComposeBean.
 */
// BEGIN main
public class MailComposeFrame extends JPanel {
    JDesktopPane dtPane;
    JButton newButton;
    protected int nx, ny;

    /** Aby komponent MailComposeBean był dla nas przydatny,
     * należy go umieścić wewnątrz własnego komponentu JInternalFrame. 
     */
    public MailComposeBean newSend() {

        // Tworzymy komponent JInternalFrame
        JInternalFrame jf = new JInternalFrame();

        // Tworzymy komponent MailComposeBean 
        MailComposeBean newBean = 
            new MailComposeBean(this, "Redagowanie", 400, 250);

        // Rozmieszczamy komponenty po przekątnej.
        jf.setLocation(nx+=10, ny+=10);

        // Umieszczany nasz komponent JavaBean w komponencie JInternalFrame
        jf.setContentPane(newBean);
        jf.pack();
        jf.toFront();

        // Dodajemy JInternalFrame do komponentu JDesktopPane
        dtPane.add(jf);
        return newBean;
    }

    /* Tworzymy obiekt MailComposeFrame wraz z przyciskiem "Redaguj wiadomość". */
    public MailComposeFrame() {

        setLayout(new BorderLayout());

        dtPane = new JDesktopPane();
        add(dtPane, BorderLayout.CENTER);

        newButton = new JButton("Redaguj wiadomość");
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newSend();
            }
        });
        add(newButton, BorderLayout.SOUTH);
    }
}
// END main
