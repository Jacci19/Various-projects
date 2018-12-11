package Books_exercises.JavaReceptury.graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Manager;
import javax.media.Player;
import javax.media.RealizeCompleteEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Program przedstawia prosty kod umożliwiający odtwarzania 
 * klipów wideo przy wykorzystaniu Java Media Framework.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
@SuppressWarnings("serial")
// BEGIN main
public class JMFPlayer extends JPanel implements ControllerListener {

    /** Obiekt odtwarzacza */
    Player thePlayer = null;
    /** Nadrzędna ramka, w której działa program. */
    JFrame parentFrame = null;
    /** Panel zawartości */
    Container cp;
    /** Komponent wizualny (jeśli jakiś jest) */
    Component visualComponent = null;
    /** Domyślny komponent kontrolny (jeśli jest) */
    Component controlComponent = null;
    /** Nazwa używanego pliku multimedialnego. */
    String mediaName;
    /** Adres URL używanego pliku dźwiękowego. */
    URL theURL;

    /** Tworzymy obiekt odtwarzacza oraz graficzny interfejs użytkownika. */
    public JMFPlayer(JFrame pf, String media) {
        parentFrame = pf;
        mediaName = media;
        // cp = getContentPane();
        cp = this;
        cp.setLayout(new BorderLayout());
        try {
            theURL = new URL(getClass().getResource("."), mediaName);
            thePlayer = Manager.createPlayer(theURL);
            thePlayer.addControllerListener(this);
        } catch (MalformedURLException e) {
            System.err.println("JMF - błąd tworzenia adresu URL: " + e);
        } catch (Exception e) {
            System.err.println("Błąd tworzenia odtwarzacza JMF : " + e);
            return;
        }
        System.out.println("URL pliku = " + theURL);

        // Uruchamiamy odtwarzacz: spowoduje to przekazanie informacji
        // do naszego obiektu ControllerListener.
        thePlayer.start();        // Rozpoczynamy odtwarzanie
    }

    /** Metoda wywoływania w celu zatrzymania odtwarzania, na przykład
     * w wyniku kliknięcia przycisku Stop lub wybrania odpowiedniej 
     * opcji z menu programu 
     */
    public void stop() {
        if (thePlayer == null)
            return;
        thePlayer.stop();          // Zatrzymujemy odtwarzanie!
        thePlayer.deallocate();    // Zwalniamy zasoby systemowe
    }

    /** Metoda wywoływana, gdy chcemy zamknąć program (na przykład
     * po kliknięciu przycisku Wyjście) 
     */
    public void destroy() {
        if (thePlayer == null)
            return;
        thePlayer.close();
    }

    /** Metoda wywoływana przez JMF, gdy odtwarzacz chce nas o czymś
     * poinformować 
     */
    public synchronized void controllerUpdate(ControllerEvent event) {
        // System.out.println("controllerUpdate(" + event + ")");
        if (event instanceof RealizeCompleteEvent) {
            if ((visualComponent = thePlayer.getVisualComponent()) != null)
                    cp.add(BorderLayout.CENTER, visualComponent);
            if ((controlComponent = 
                thePlayer.getControlPanelComponent()) != null)
                    cp.add(BorderLayout.SOUTH, controlComponent);
            // Zmieniamy wielkość głównego okna aplikacji
            if (parentFrame != null) {
                parentFrame.pack();
                parentFrame.setTitle(mediaName);
            }
        }
    }

    public static void main(String[] argv) {
        JFrame f = new JFrame("Odtwarzacz JMF - demonstracja");
        Container frameCP = f.getContentPane();
        final String musicURL = argv.length == 0 ?
                "file:/home/ian/Music/Classical/Rachmaninoff Prelude C_ min.mp3" :
                argv[0];
        JMFPlayer p = new JMFPlayer(f, musicURL);
        frameCP.add(BorderLayout.CENTER, p);
        f.setSize(200, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
// END main
