package Books_exercises.JavaReceptury.reflection;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/*
 * AppletViewer - prosty program do uruchamiania i wyświetlania apletów.
 * @author    Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class AppletViewer {
    /** Główna ramka (Frame) programu */
    JFrame f;
    /** Obiekt AppletAdapter (udostępnia AppletStub, AppletContext, 
     *  showStatus) */
    static AppletAdapter aa = null;
    /** Nazwa klasy apletu */
    String appName = null;
    /** Obiekt Class reprezentujący klasę apletu */
    Class<?> ac = null;
    /** Obiekt klasy apletu, który będzie uruchamiany, lub null. Nie może to 
     * być klasa potomna JApplet aż do momentu, gdy cały świat zacznie tę  
     * klasę (JApplet) stosować. */
    Applet ai = null;
    /** Szerokość apletu */
    final int WIDTH = 250;
    /** Wysokość apletu */
    final int HEIGHT = 200;

    /** Metoda main, w której wszystko się rozpoczyna. 
     * Tworzymy graficzny interfejs użytkownika. Ładujemy aplet, a następnie
     * uruchamiamy go.
     */
    public static void main(String[] av) {
        new AppletViewer(av.length==0?"HelloApplet":av[0]);
    }

    /** Tworzymy graficzny interfejs użytkownika programu AppletViewer */
    AppletViewer(String appName) {
        super();

        this.appName = appName;

        f = new JFrame("AppletViewer");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                f.setVisible(false);
                f.dispose();
                System.exit(0);
            }
        });
        Container cp = f.getContentPane();
        cp.setLayout(new BorderLayout());

        // Obiekt klasy AppletAdapter dający nam dostęp do
        // AppletStub oraz AppletContext.
        if (aa == null)
            aa = new AppletAdapter();

        // Obiekt AppletAdapter daje nam także dostęp do metody showStatus.
        // Z tego względu należy go dodać (przy użyciu metody add())
        // jak najwcześniej, gdyż konstruktor klasy Applet oraz jej 
        // metoda init() korzystają z showStatus().
        cp.add(BorderLayout.SOUTH, aa);

        showStatus("Wczytujemy aplet " + appName);

        loadApplet(appName , WIDTH, HEIGHT);    // Określamy ac oraz ai.
        if (ai == null)
            return;

        // Informujemy aplet, jak odszukać metodę showStatus itd.
        ai.setStub(aa);

        // Kojarzymy aplet z ramką (Frame).
        cp.add(BorderLayout.CENTER, ai);

        Dimension d = ai.getSize();
        d.height += aa.getSize().height;
        f.setSize(d);
        f.setVisible(true);        // Wyświetlamy ramkę oraz jej zawartość.

        showStatus("Aplet " + appName + " został wczytany");

        // I udajemy, że program jest przeglądarką.
        ai.init();
        ai.start();
    }

    /*
     * Wczytujemy aplet do pamięci. Należałoby dodać możliwość 
     * przechowywania go w pamięci podręcznej.
     */
    void loadApplet(String appletName, int w, int h) {
        // appletName = ... pobierana w jakiś sposób z atrybutu code= ...;
        // width =      jak wyżej
        // height =     jak wyżej
        try {
            // Pobieramy obiekt Class dla klasy apletu (klasy potomnej 
            // Applet).
            ac = Class.forName(appletName);
            // Tworzymy kopię tej klasy (jak gdyby posługując się 
            // konstruktorem bezargumentowym).
            ai = (Applet) ac.newInstance();
        } catch(ClassNotFoundException e) {
            showStatus("Klasa apletu " + appletName + " nie została wczytana");
            return;
        } catch (Exception e ){
            showStatus("Obiekt apletu " + appletName + " nie został utworzony");
            return;
        }
        ai.setSize(w, h);
    }

    public void showStatus(String s) {
        aa.getAppletContext().showStatus(s);
    }
}
// END main
