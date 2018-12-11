package Books_exercises.JavaReceptury.email;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import com.darwinsys.util.StringFormat;
import com.darwinsys.util.FileProperties;

/**
* Wyświetla wszystkie dostępne katalogi.
*/
// BEGIN main
public class MailLister {
    static StringFormat fromFmt = 
        new StringFormat(20, StringFormat.JUST_LEFT);
    static StringFormat subjFmt = 
        new StringFormat(40, StringFormat.JUST_LEFT);

    public static void main(String[] argv) throws Exception {
        String fileName = MailConstants.PROPS_FILE_NAME;
        String protocol = null;
        String host = null;
        String user = null;
        String password = null;
        String root = null;

        // Jeśli argc == 1, zakładamy, że jest to plik właściwości 
        // (Properties).
        if (argv.length == 1) {
            fileName = argv[0];
            FileProperties fp = new FileProperties(fileName);
            fp.load();
            protocol = fp.getProperty(MailConstants.RECV_PROTO);
            host = fp.getProperty(MailConstants.RECV_HOST);
            user = fp.getProperty(MailConstants.RECV_USER);
            password = fp.getProperty(MailConstants.RECV_PASS);
            root = fp.getProperty(MailConstants.RECV_ROOT);
        }
        // W przeciwnym przypadku zakładamy, że wszystkie pięć 
        // argumentów zostało podanych w wierszu wywołania
        else if (argv.length == 5) {
            protocol = argv[0];
            host = argv[1];
            user = argv[2];
            password = argv[3];
            root = argv[4];
        }
        // Jeśli brak argumentów - kończymy działanie.
        else {
            System.err.println(
                "Sposób użycia: MailLister protokół komputer użytkownik hasło katalog_główny");
            System.exit(0);
        }


        boolean recursive = false;

        // Zaczynamy jak zwykle od utworzenia obiektu Session
        Session session = Session.getDefaultInstance(
            System.getProperties(), null);
        session.setDebug(false);

        // // Pobieramy obiekt Store dla danego protokołu.
        Store store = session.getStore(protocol);
        if (password.equals("*")) {
            final char[] passBytes = 
                System.console().readPassword("Hasło:", (Object[])null);
            password = new String(passBytes);
        }
        store.connect(host, user, password);

        // Pobieramy obiekt Folder dla katalogu głównego i wyświetlamy 
        // jego zawartość. Jeśli nazwa katalogu głównego = "", 
        // wywołujemy getDefaultFolder(), a w przeciwnym przypadku getFolder(root)
        Folder rf;
        if (root.length() != 0) {
            System.out.println("Pobieram katalog " + root + ".");
            rf = store.getFolder(root);
        } else {
            System.out.println("Pobieram domyślny katalog.");
            rf = store.getDefaultFolder();
        }
        rf.open(Folder.READ_WRITE);

        if (rf.getType() == Folder.HOLDS_FOLDERS) {
            Folder[] fs = rf.list();
            for (Folder f : fs) {
                listFolder(f, "", recursive);
            }
        } else {
            listFolder(rf, "", false);
        }
    }

    static void listFolder(Folder folder, String tab, boolean recurse)
    throws Exception {
        folder.open(Folder.READ_WRITE);
        System.out.println(tab + "Nazwa: " + folder.getName() + '(' +
            folder.getFullName() + ')');
        if (!folder.isSubscribed())
            System.out.println(tab + "Niezaprenumerowany");
        if ((folder.getType() & Folder.HOLDS_MESSAGES) != 0) {
            if (folder.hasNewMessages())
                System.out.println(tab + "Są nowe wiadomości");
            else
                System.out.println(tab + "Brak nowych wiadomości");
            Message[] msgs = folder.getMessages();
            for (Message m : msgs) {
                Address from = m.getFrom()[0];
                String fromAddress;
                if (from instanceof InternetAddress)
                    fromAddress = ((InternetAddress)from).getAddress();
                else
                    fromAddress = from.toString();
                StringBuffer sb = new StringBuffer();
                fromFmt.format(fromAddress, sb, null);
                sb.    append("  ");
                subjFmt.format(m.getSubject(), sb, null);
                System.out.println(sb.toString());
            }
        }
        if ((folder.getType() & Folder.HOLDS_FOLDERS) != 0) {
            System.out.println(tab + "to katalog");
        }
        if (recurse) {
            Folder[] fs = folder.list();
            for (Folder f : fs) {
                listFolder(f, tab + "", recurse);
            }
        }
    }
}
// END main
