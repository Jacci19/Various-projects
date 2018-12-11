package Books_exercises.JavaReceptury.reflection;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * AppletAdaptor: Częściowa implementacja interfejsów AppletStub 
 * oraz AppletContext.
 *
 * Poniższy kod w żadnym wypadku nie jest zakończony, jak zresztą 
 * łatwo można zauważyć.
 *
 * @author    Ian Darwin, http://www.darwinsys.com/, for Learning Tree Course 478
 */
// BEGIN main
public class AppletAdapter extends Panel implements AppletStub, AppletContext {

    private static final long serialVersionUID = 1L;
    /** Okno statusu u dołu okna aplikacji */
    Label status = null;

    /** Tworzymy interfejs użytkownika dla okna statusu apletu */
    AppletAdapter() {
        super();

        // Należy to zrobić jak najwcześniej, gdyż konstruktor
        // klasy Applet oraz jej metoda init() mogą korzystać 
        // z metody showStatus().
        add(status = new Label());

        // Nadajemy okienku statusu pełną szerokość
        status.setSize(getSize().width, status.getSize().height);

        showStatus("AppletAdapter utworzony");    // Teraz można to wyświetlić
    }

    /****************** AppletStub ***********************/
    /** Metoda wywoływana, gdy aplet chce zmienić wielkość.  */
    public void appletResize(int w, int h) {
        // applet.setSize(w, h);
    }

    /** Zwraca odwołanie do kontekstu apletu.  */
    public AppletContext getAppletContext() {
        return this;
    }

    /** Zwraca bazowy adres URL.  */
    public URL getCodeBase() {
        return getClass().getResource(".");
    }

    /** Zwraca adres URL dokumentu.  */
    public URL getDocumentBase() {
        return getClass().getResource(".");
    }

    /** Zwraca wartość atrybutu (znacznika HTML) o podanej nazwie */
    public String getParameter(String name) {
        String value = null;
        return value;
    }
    /** Określa, czy aplet jest aktywny.  */
    public boolean isActive() {
        return true;
    }

    /************************ AppletContext ************************/

    /** Odnajduje i zwraca aplet o podanej nazwie. */
    public Applet getApplet(String an) {
        return null;
    }

    /** Odnajduje wszystkie aplety w dokumencie
     * XXX WŁAŚCIWIE TA METODA NIE JEST ZAIMPLEMENTOWANA
     */
    public Enumeration<Applet> getApplets()  {
        class AppletLister implements Enumeration<Applet> {
            public boolean hasMoreElements() {
                return false;
            }
            public Applet nextElement() {
                return null;
            }
        }
        return new AppletLister();
    }

    /** Tworzy klip dźwiękowy dla pliku .au o podanym adresie URL */
    public AudioClip getAudioClip(URL u) {
        return null;
    }

    /** Odnajduje i tworzy obiekt Image, który następnie będzie 
     * można wyświetlić przy użyciu metody paint(). */
    public Image getImage(URL u)  {
        return null;
    }

    /** Prośba zastąpienia bieżącej strony nową - ignorujemy ją */
    public void showDocument(URL u) {
    }

    /** Jak wyżej, z tym że dotyczy wskazanej ramki */
    public void showDocument(URL u, String frame)  {
    }

    /** Metoda wywoływana przez aplety, aby wyświetlić komunikat 
     * u dołu okna */
    public void showStatus(String msg) {
        if (msg == null)
            msg = "";
        status.setText(msg);
    }

    /* Metody związane ze strumieniami - nowe w JDK1.4 */
    Map<String,InputStream> streamMap = new HashMap<>();

    /** Metoda kojarzy strumień z kluczem. */
    public void setStream(String key, InputStream stream) throws IOException {
        streamMap.put(key, stream);
    }

    public InputStream getStream(String key) {
        return (InputStream)streamMap.get(key);
    }

    public Iterator<String> getStreamKeys() {
        return streamMap.keySet().iterator();
    }
}
// END main
