package Books_exercises.JavaReceptury.email;

// BEGIN main
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 * Wyświetla zawartość jednej lub kilku skrzynek pocztowych.
 * To ogólny komponent graficznego interfejsu użytkownika, 
 * służący do wyświetlania wiadomości e-mail.
 */
public class MailReaderBean extends JSplitPane {

    private static final long serialVersionUID = 1L;
    
    private JTextArea bodyText;

    /* Tworzymy obiekt, wykorzystując domyślne ustawienia.
     */
    public MailReaderBean() throws Exception {
        this("imap", "mailhost", "user", "*", "/");
    }

    /* Tworzymy obiket, wykorzystując podane wartości. */
    public MailReaderBean(
        String protocol,
        String host,
        String user,
        String password,
        String rootName)
    throws Exception {

        super(VERTICAL_SPLIT);
        
        boolean recursive = false;
        
        // Zaczynamy od utworzenia obiektu Session
        Session session = Session.getDefaultInstance(
            System.getProperties(), null);
        session.setDebug(false);

        // Pobieramy obiekt Store dla danego protokołu
        Store store = session.getStore(protocol);
        store.connect(host, user, password);

        // Pobieramy obiekt Folder dla katalogu głównego i wyświetlamy 
        // jego zawartość. Jeśli nazwa katalogu głównego = "", 
        // wywołujemy getDefaultFolder(), a w przeciwnym przypadku getFolder(root)
        FolderNode top;
        if (rootName.length() != 0) {
            // System.out.println("Pobieram katalog " + rootName + ".");
            top = new FolderNode(store.getFolder(rootName));
        } else {
            // System.out.println("Pobieram domyślny katalog.");
            top = new FolderNode(store.getDefaultFolder());
        }
        if (top == null || !top.f.exists()) {
            System.out.println("Niewłaściwy katalog " + rootName);
            return;
        }

        if (top.f.getType() == Folder.HOLDS_FOLDERS) {
            Folder[] fs = top.f.list();
            for (Folder f : fs)
                listFolder(top, new FolderNode(f), recursive);
        } else
                listFolder(top, top, false);

        // Teraz, gdy wszystkie węzły reprezentujące foldery i 
        // wiadomości są gotowe, tworzymy obiekt JTree, wypełniając 
        // go od folderu głównego aż do końca listy; zapewniamy, że 
        // będzie można przewijać jego zawartość (umieszczając w nim
        // obiekt JScrollPane), i dodajemy do menedżera układu  
        // w położeniu "North" (północnym).
        JTree tree = new JTree(top);
        JScrollPane treeScroller = new JScrollPane(tree);
        treeScroller.setBackground(tree.getBackground());
        this.setTopComponent(treeScroller);

        // W położeniu "South" (południowym, czyli dolnym) umieszczamy 
        // wielowierszowe pole tekstowe służące do wyświetlania treści 
        // wiadomości.
        bodyText = new JTextArea(15, 80);
        this.setBottomComponent(new JScrollPane(bodyText));

        // Dodajemy odbiorcę zdarzeń generowanych przez komponent JTree; 
        // będzie on wyświetlać treść klikniętej wiadomości.
        TreeSelectionListener tsl = new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent evt) {
                Object[] po = evt.getPath().getPath();    // Tak, powtarzamy to.
                Object o = po[po.length - 1];    // Ostatni węzeł w tej ścieżce
                if (o instanceof FolderNode) {
                    // System.out.println("Wybierz katalog " + o.toString());
                    return;
                }
                if (o instanceof MessageNode) {
                    bodyText.setText("");
                    try {
                        Message m = ((MessageNode)o).m;

                        bodyText.append("Do: ");
                        Object[] tos = m.getAllRecipients();
                        for (Object to : tos) {
                            bodyText.append(to.toString());
                            bodyText.append(" ");
                        }
                        bodyText.append("\n");

                        bodyText.append("Temat: " + m.getSubject() + "\n");
                        bodyText.append("Od: ");
                        Object[] froms = m.getFrom();
                        for (Object from : froms) {
                            bodyText.append(from.toString());
                            bodyText.append(" ");
                        }
                        bodyText.append("\n");

                        bodyText.append("Data: " + m.getSentDate() + "\n");
                        bodyText.append("\n");

                        bodyText.append(m.getContent().toString());

                        // Zaczynamy odczyt od początku wiadomości(!)
                        bodyText.setCaretPosition(0);
                    } catch (Exception e) {
                        bodyText.append(e.toString());
                    }
                } else 
                    System.err.println("NIEOCZEKIWANY WYBÓR: " + o.getClass());
            }
        };
        tree.addTreeSelectionListener(tsl);
    }

    /** Przetworzenie jednego katalogu. */
    static void listFolder(FolderNode top, FolderNode folder, boolean recurse)
        throws Exception {

        if ((folder.f.getType() & Folder.HOLDS_MESSAGES) != 0) {
            Message[] msgs = folder.f.getMessages();
            for (Message ms : msgs) {
                MessageNode m = new MessageNode(ms);
                Address from = m.m.getFrom()[0];
                String fromAddress;
                if (from instanceof InternetAddress)
                    fromAddress = ((InternetAddress)from).getAddress();
                else
                    fromAddress = from.toString();
                top.add(new MessageNode(ms));
            }
        }
        if ((folder.f.getType() & Folder.HOLDS_FOLDERS) != 0) {
            if (recurse) {
                Folder[] fs = folder.f.list();
                for (Folder f : fs) {
                    listFolder(new FolderNode(f), top, recurse);
                }
            }
        }
    }

    /* Testowy program główny */
    public static void main(String[] args) throws Exception {
        final JFrame jf = new JFrame("MailReaderBean");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String mbox = "INBOX";
        if (args.length > 0)
            mbox = args[0];
        MailReaderBean mb = new MailReaderBean("imap", "localhost",
            System.getProperty("user.name"), "*", mbox);
        jf.getContentPane().add(mb);
        jf.setSize(640,480);
        jf.setVisible(true);
    }
}
// END main
