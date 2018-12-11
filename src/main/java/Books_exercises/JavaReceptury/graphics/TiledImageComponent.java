package Books_exercises.JavaReceptury.graphics;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.darwinsys.util.Debug;

/**
 * Przedstawienie sposobu wielokrotnego wyświetlania obrazu 
 * w komponencie; obraz jest wyświetlany w metodzie paint().
 * @author    Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class TiledImageComponent extends JComponent {

    private static final long serialVersionUID = -8771306833824134974L;
    protected TextField nameTF, passTF, domainTF;
    protected Image im;
    public static final String DEFAULT_IMAGE_NAME =
        "graphics/background.gif";

    /** Rozmieszczamy elementy w oknie. */
    public TiledImageComponent() {

        setLayout(new FlowLayout());
        add(new Label("Nazwa:", Label.CENTER));
        add(nameTF=new TextField(10));

        add(new Label("Hasło:", Label.CENTER));
        add(passTF=new TextField(10));
        passTF.setEchoChar('*');

        add(new Label("Domena:", Label.CENTER));
        add(domainTF=new TextField(10));

        im = getToolkit().getImage(DEFAULT_IMAGE_NAME);
    }

    /** paint() - wielokrotnie wyświetlamy obraz tła. */
    @Override
    public void paintComponent(Graphics g) {
        if (im == null)
            return;
        int iw = im.getWidth(this), ih=im.getHeight(this);
        if (iw < 0 || ih < 0)    // Obraz nie jest gotowy.
            return;              // Kończymy, aby spróbować później.
        int w = getSize().width, h = getSize().height;

        for (int i = 0; i<=w; i+=iw) {
            for (int j = 0; j<=h; j+=ih) {
                Debug.println("draw", "drawImage(im,"+i+","+j+")");
                g.drawImage(im, i, j, this);
            }
        }
    }

    public static void main(String[] av) {
        JFrame f = new JFrame("Program TiledImageComponent");
        f.getContentPane().add(new TiledImageComponent());
        f.setSize(200, 200);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// END main
