package Books_exercises.JavaReceptury.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JFrame;

/** Ten program, który może być zarówn o apletem, jak i zwyczajną
 * aplikacją, przedstawia wersję metody 
 * Toolkit.getImage(), która działa tak samo w apletach 
 * oraz w aplikacjach.
 * <p>
 * W przypadku apletu należy program wywołać w następujący sposób:
    <APPLET CODE="GetImage" WIDTH="100" HEIGHT="100">
    </APPLET>
 * Jeśli program potraktujemy jako aplikację (ma metodę main()),
 * to wystarczy go uruchomić.
 */
// BEGIN main
public class GetImage extends JApplet {

    private static final long serialVersionUID = 4288395022095915666L;
    private Image image;

    public void init() {
        loadImage();
    }

    public void loadImage() {

        // Wersja przenośna: getClass().getResource() działa zarówno
        // w apletach, jak i aplikacjach, w JDK 1.1 lub 1.3; zwraca URL
        // reprezentujący nazwę pliku.
        URL url = getClass().getResource("Duke.gif");
        image = getToolkit().getImage(url);
        // Lub po prostu:
        // image = getToolkit().getImage(getClass().getResource("Duke.gif"));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 20, 20, this);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("GetImage");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GetImage myApplet = new GetImage();
        f.setContentPane(myApplet);
        myApplet.init();
        f.setSize(100, 100);
        f.setVisible(true);
        myApplet.start();
    }
}
// END main
