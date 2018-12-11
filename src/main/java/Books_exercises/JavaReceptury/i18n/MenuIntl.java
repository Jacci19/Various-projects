package Books_exercises.JavaReceptury.i18n;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/** Oto program MenuDemo z niepełnymi możliwościami programu
 * wielojęzycznego.
 * Aby go uruchomić można użyć poleceń
 *   java MenuIntl
 *   java -Duser.language=es MenuIntl
 */
@SuppressWarnings("serial")
// BEGIN main
public class MenuIntl extends JFrame {

    /** Metoda główna - stworzenie i wyświetlenie menu */
    public static void main(String[] av) {
        // tworzymy obiekt MenuIntl i wyświetlamy go
        new MenuIntl().setVisible(true);
    }

    /** Tworzymy obiekt i interfejs użytkownika */
    public MenuIntl() {
        super("MenuIntlTest");
        JMenuItem mi;        // używana w wielu miejscach

        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        JLabel lab;
        cp.add(lab = new JLabel());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);

        ResourceBundle b = ResourceBundle.getBundle("i18n.Widgets");

        String titlebar;
        try { titlebar = b.getString("program"+".title"); }
        catch (MissingResourceException e) { titlebar="Menu wielojęzyczne"; }
        setTitle(titlebar);

        String message;
        try { message = b.getString("program"+".message"); }
        catch (MissingResourceException e) { 
            message="Witamy w świecie Javy";
        }
        lab.setText(message);

        JMenu fm = mkMenu(b, "file");
        // W normalnym kodzie po *każdym* wywołaniu mkMenuItem
        // byłoby umieszczone wywołanie
        // mi.addActionListener(...) 
        fm.add(mi = mkMenuItem(b, "file", "open"));
        fm.add(mi = mkMenuItem(b, "file", "new"));
        fm.add(mi = mkMenuItem(b, "file", "save"));
        fm.add(mi = mkMenuItem(b, "file", "exit"));
        mi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuIntl.this.setVisible(false);
                MenuIntl.this.dispose();
                System.exit(0);
            }
        });
        mb.add(fm);

        JMenu vm = mkMenu(b,  "view");
        vm.add(mi = mkMenuItem(b, "view", "tree"));
        vm.add(mi = mkMenuItem(b, "view", "list"));
        vm.add(mi = mkMenuItem(b, "view", "longlist"));
        mb.add(vm);

        JMenu hm = mkMenu(b,  "help");
        hm.add(mi = mkMenuItem(b, "help", "about"));
        // mb.setHelpMenu(hm);    // potrzebne dla zachowania 
                                  // przenaszalności kodu(Motif itp.).

        // główne okno
        JLabel jl = new JLabel("Demonstracja menu");
        jl.setSize(200, 150);
        cp.add(jl);
        pack();
    }

    // Kopie metod dostępnych w pliku darwinsys.jar,
    // umieściłem je tutaj tylko po to, by ułatwić kompilację przykładu.

    /** Metoda pomocnicza służąca do tworzenia obiektu JMenu */
    public JMenu mkMenu(ResourceBundle b, String name) {
        String menuLabel;
        try { menuLabel = b.getString(name+".label"); }
        catch (MissingResourceException e) { menuLabel=name; }
        return new JMenu(menuLabel);
    }

    /** Metoda pomocnicza służąca do tworzenia obiektu JMenuItem */
    public JMenuItem mkMenuItem(ResourceBundle b, String menu, String name) {
        String miLabel;
        try { miLabel = b.getString(menu + "." + name + ".label"); }
        catch (MissingResourceException e) { miLabel=name; }
        String key = null;
        try { key = b.getString(menu + "." + name + ".key"); }
        catch (MissingResourceException e) { key=null; }

        if (key == null)
            return new JMenuItem(miLabel);
        else
            return new JMenuItem(miLabel, key.charAt(0));
    }

    private String lookupWithDefault(ResourceBundle rb, String key, String dflt)
    {
        try {
            return rb.getString(key);
        } catch (MissingResourceException e) {
            return dflt;
        }
    }
}
// END main
