package Books_exercises.JavaReceptury.email;

import static email.MailConstants.PROPS_FILE_NAME;
import static email.MailConstants.RECV_HOST;
import static email.MailConstants.RECV_PASS;
import static email.MailConstants.RECV_PROTO;
import static email.MailConstants.RECV_USER;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

import com.darwinsys.util.FileProperties;

/** Niezależny program MailClient dysponujący graficznym interfejsem 
 * użytkownika.
 * @author    Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class MailClient extends JComponent {

    private static final long serialVersionUID = 1L;
    /** Przycisk zamknięcia programu */
    JButton quitButton;
    /** Tryb odczytu */
    MailReaderBean mrb;
    /** Tryb redagowania i wysyłania  */
    MailComposeFrame mcb;
    /** Panel Pseudonimy */
    AliasBean alb;

    /** Tworzymy komponent MailClient, używając domyślnej nazwy 
     * pliku Properties */
    public MailClient() throws Exception {
        this(PROPS_FILE_NAME);
    }

    /** Tworzymy komponent MailClient bez używania pliku Properties */
    public MailClient(String propsFileName) throws Exception {
        super();

        // Pobieramy właściwości konieczne do odczytu i wysyłania 
        // wiadomości.
        Properties mailProps = new FileProperties(propsFileName);

        // Pobieramy wartości wybranych kluczy
        String proto = mailProps.getProperty(RECV_PROTO);
        String user  = mailProps.getProperty(RECV_USER);
        String pass  = mailProps.getProperty(RECV_PASS);
        String host  = mailProps.getProperty(RECV_HOST);

        if (proto==null)
            throw new IllegalArgumentException(RECV_PROTO + "==null");

        // Protokoły inne niż "mbox" wymagają podania hasła.
        if (!proto.equals("mbox") && (pass == null || pass.equals("ASK"))) {
            String np;
            do {
                // Sztuczka, aby obiekt JOptionPane poprosił o podanie hasła bez
                // jego wyświetlania. Tworzymy "wiadomość", używając w tym celu 
                // komponentów JPanel, JLabel oraz JPasswordField
                // Kod wykorzystany dzięki uprzejmości Marc'a Loy'a.

                JPanel p = new JPanel();
                p.add(new JLabel("Hasło użytkownika " + user + 
                    " łączącego się z komputerem " + host + 
                    " przy użyciu protokołu " + proto));
                JPasswordField jpf = new JPasswordField(20);
                p.add(jpf);
                JOptionPane.showMessageDialog(null, p,
                    "Prośba o podanie hasła", JOptionPane.QUESTION_MESSAGE);
                np = new String(jpf.getPassword());
            } while (np == null || (np != null && np.length() == 0));
            mailProps.setProperty(RECV_PASS, np);
        }

        // Zapisanie wszystkich właściwości w obiekcie System.properties 
        // tak aby można je odczytać w innych fragmentach kodu.
        System.getProperties().putAll(mailProps);

        // Tworzymy graficzny interfejs użytkownika
        // System.out.println("Tworzymy GUI");
        setLayout(new BorderLayout());
        JTabbedPane tbp = new JTabbedPane();
        add(BorderLayout.CENTER, tbp);
        tbp.addTab("Odczyt", mrb = new MailReaderBean());
        tbp.addTab("Wysyłanie", mcb = new MailComposeFrame());
        tbp.addTab("Pseudonimy", alb = new AliasBean());
        tbp.addTab("Listy korespondencyjne", new JLabel("W trakcie tworzenia",
            JLabel.CENTER));
        add(BorderLayout.SOUTH, quitButton = new JButton("Koniec")); 
        // System.out.println("Koniec konstruktora");
    }

    /** Główna metoda programu - wykonanie programu */
    public static void main(String[] av) throws Exception {

        final JFrame f = new JFrame("MailClient");

        // Zaczynamy od sprawdzenia, czy jest zainstalowany pakiet javax.mail!
        try {
            Class.forName("javax.mail.Session");
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(f, 
                "Przykro mi, nie odnaleziono pakietu javax.mail\n(" + cnfe + ")",
                "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tworzymy obiekt
        MailClient comp;
        if (av.length == 0)
            comp = new MailClient();
        else
            comp = new MailClient(av[0]);
        f.getContentPane().add(comp);

        // Tworzymy kod realizujący czynności związane z obsługą 
        // graficznego interfejsu użytkownika
        comp.quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                f.dispose();
                System.exit(0);
            }
        });
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                f.setVisible(false);
                f.dispose();
                System.exit(0);
            }
        });

        f.pack();

        f.setVisible(true);
    }
}
// END main
