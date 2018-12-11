package Books_exercises.JavaReceptury.email;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.darwinsys.mail.Mailer;
import com.darwinsys.util.FileProperties;

/** MailComposeBean - komponent JavaBean służący do redagowania i wysyłania
 * wiadomości poczty elektronicznej.
 *
 * Może być stosowany jako "widoczny" (Visible) komponent lub jako 
 * komponent niewidoczny. Jesli zostanie wywołana metoda setVisible(true), 
 * wyświetlane jest okno do tworzenia wiadomości zawierające przycisk Wyślij.
 * Jeśli użytkownik klinie ten przycisk, komponent spróbuje przesłać 
 * wiadomość na serwer.
 *
 * Jeśli komponent jest niewidoczny, należy podać wszystkie informacje 
 * przy użyciu metod addXXX(), setXXX(), oraz wysłać wiadomośc wysołując
 * metodę doSend() .

 *
 * @author Ian F. Darwin
 */
// BEGIN main
public class MailComposeBean extends JPanel {

    /** Ramka główna do wyświetlenia lub ukrycia; może to być obiekt JFrame, 
     * JInternalFrame lub JPanel, w zależności od potrzeb */
    private Container parent;

    private JButton sendButton, cancelButton;
    private JTextArea msgText;        // Wiadomość!

    // Pola To (Do), Subject (Temat), oraz CC (adresaci kopii) są 
    // traktowane w specjalny sposób, wszelkie nagłówki określone
    // przez użytkownika są umieszczane w tablicy tfs.
    private JTextField tfs[], toTF, ccTF, subjectTF;
    // tfsMax MUSI == ilości bieżących nagłówków, jest to konieczne 
    // do poprawnego przekazywania fokusu.
    private int tfsMax = 3;
    private final int TO = 0, SUBJ = 1, CC = 2, BCC = 3, MAXTF = 8;

    /** Obiekt Session */
    private Session session = null;
    /** Obiekt Message */
    private Message mesg = null;

    private int mywidth;
    private int myheight;

    /** Tworzymy obiekt MailComposeBean bez domyślnego adresata */
    MailComposeBean(Container parent, String title, int height, int width) {
        this(parent, title, null, height, width);
    }

    /** Tworzymy obiekt MailComposeBean bez argumentów (konieczne 
     * w komponencie JavaBean) */
    MailComposeBean() {
        this(null, "Compose", null, 300, 200);
    }

    /** Konstruktor obiektu MailComposeBean.
     *
     * @param parent    Nadrzędny kontener. Jeśli jest nim obiekt JFrame lub 
                        JInternalFrame, to po wysłaniu wiadomości zostaną 
                        wywołane metody setvisible(false) oraz dispose(). 
                        Czynności te nie są wykonywane, gdy parent == "null" 
                        lub gdy jest to obiekt JPanel.
     * @param title        Tytuł wyświetlany na pasku tytułu
     * @param recipient    Adres poczty elektronicznej odbiorcy wiadomości
     * @param height    Wysokość okna
     * @param width        Szerokość okna
     */
    MailComposeBean(Container parent, String title, String recipient,
            int width, int height) {
        super();

        this.parent = parent;

        mywidth = width;
        myheight = height;

        // Graficzny interfejs użytkownika
        Container cp = this;
        cp.setLayout(new BorderLayout());


        // To jest JPanel do podawania adresu, tytułu itd.
        // W środku jest umieszczany obiekt TextArea, a u dołu 
        // przyciski Wyślij i Anuluj.
        JPanel tp = new JPanel();
        tp.setLayout(new GridLayout(3,2));
        cp.add(BorderLayout.NORTH, tp);

        tfs = new JTextField[MAXTF];

        tp.add(new JLabel("Do: ", JLabel.RIGHT));
        tp.add(tfs[TO] = toTF = new JTextField(35));
        if (recipient != null)
            toTF.setText(recipient);
        toTF.requestFocus();

        tp.add(new JLabel("Temat: ", JLabel.RIGHT));
        tp.add(tfs[SUBJ] = subjectTF = new JTextField(35));
        subjectTF.requestFocus();

        tp.add(new JLabel("DW: ", JLabel.RIGHT));
        tp.add(tfs[CC] = ccTF = new JTextField(35));

        // Wyśrodkowanie elementu TextArea
        cp.add(BorderLayout.CENTER, msgText = new JTextArea(70, 10));
        msgText.setBorder(BorderFactory.createTitledBorder("Treść wiadomości"));

        // U dołu będzie przycisk Wyślij.
        JPanel bp = new JPanel();
        bp.setLayout(new FlowLayout());
        bp.add(sendButton = new JButton("Wyślij"));
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    doSend();
                } catch(Exception err) {
                    System.err.println("Błąd: " + err);
                    JOptionPane.showMessageDialog(null,
                        "Błąd wysyłania:\n" + err.toString(),
                        "Nie udało się wysłać wiadomości", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        bp.add(cancelButton = new JButton("Anuluj"));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                maybeKillParent();
            }
        });
        cp.add(BorderLayout.SOUTH, bp);
    }

    public Dimension getPreferredSize() {
        return new Dimension(mywidth, myheight);
    }
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /** Wykonanie zadania: przesłanie wiadomości na serwer SMTP.
     *
     * ASERCJA: musi być określony przynajmniej jeden adresat.
     */
    public void doSend() {

        try {
            Mailer m = new Mailer();

            FileProperties props =
                new FileProperties(MailConstants.PROPS_FILE_NAME);
            String serverHost = props.getProperty(MailConstants.SEND_HOST);
            if (serverHost == null) {
                JOptionPane.showMessageDialog(parent,
                    "\"" + MailConstants.SEND_HOST +
                        "\" musi być określony we właściwościach",
                    "Nie określono serwera!",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            m.setServer(serverHost);

            String tmp = props.getProperty(MailConstants.SEND_DEBUG);
            m.setVerbose(tmp != null && tmp.equals("true"));

            String myAddress = props.getProperty("Mail.address");
            if (myAddress == null) {
                JOptionPane.showMessageDialog(parent,
                    "Klucz \"Mail.address\" musi być określony we właściwościach",
                    "Brak adresu nadawcy (Od)!",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            m.setFrom(myAddress);

            m.setToList(toTF.getText());
            m.setCcList(ccTF.getText());
            // m.setBccList(bccTF.getText());

            if (subjectTF.getText().length() != 0) {
                m.setSubject(subjectTF.getText());
            }

            // Kopiujemy tekst z pola TextArea.
            m.setBody(msgText.getText());
            // W programach wielojęzycznych należałoby użyć
            // wywołania setBody(msgText.getText(), charset)
                
            // Wysyłamy wiadomość
            m.doSend();

            // Chowamy okno
            maybeKillParent();

        } catch (MessagingException me) {
            me.printStackTrace();
            while ((me = (MessagingException)me.getNextException()) != null) {
                me.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,
                "Błąd wysyłania poczty:\n" + me.toString(),
                "Błąd", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Błąd wysyłania poczty:\n" + e.toString(),
                "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void maybeKillParent() {
        if (parent == null)
            return;
        if (parent instanceof Frame) {
            ((Frame)parent).setVisible(true);
            ((Frame)parent).dispose();
        }
        if (parent instanceof JInternalFrame) {
            ((JInternalFrame)parent).setVisible(true);
            ((JInternalFrame)parent).dispose();
        }
    }


    /** Prosty program testowy */
    public static void main(String[] av) {
        final JFrame jf = new JFrame("Testowy program pocztowy");
        System.getProperties().setProperty("Mail.server", "mailhost");
        System.getProperties().setProperty("Mail.address", "nobody@home");
        MailComposeBean sm =
            new MailComposeBean(jf, 
            "Testowy program pocztowy", "spam-magnet@darwinsys.com", 500, 400);
        sm.setSize(500, 400);
        jf.getContentPane().add(sm);
        jf.setLocation(100, 100);
        jf.setVisible(true);
        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            jf.setVisible(false);
            jf.dispose();
            System.exit(0);
            }
        });
        jf.pack();
    }
}
// END main
