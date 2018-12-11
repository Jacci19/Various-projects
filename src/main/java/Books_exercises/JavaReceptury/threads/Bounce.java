package Books_exercises.JavaReceptury.threads;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/** Oto klasa Bounce; odpowiedzialan za stworzenie i uruchamienie 
 * obiektów Sprite i wykorzystująca wątki (Thread). 
 */
// BEGIN main
public class Bounce extends Applet implements ActionListener {

    private static final long serialVersionUID = -5359162621719520213L;
    /** Panel główny. */
    protected Panel p;
    /** Obrazek wykorzystywany wspólnie przez wszystkie obiekty Sprite */
    protected Image img;
    /** Vector zawierajacy obiekty Sprite. */
    protected List<Sprite> v;

    public void init() {
        Button b = new Button("Start");
        b.addActionListener(this);
        setLayout(new BorderLayout());
        add(b, BorderLayout.NORTH);
        add(p = new Panel(), BorderLayout.CENTER);
        p.setLayout(null);
        String imgName = getParameter("imagefile");
        if (imgName == null) imgName = "duke.gif";
        img = getImage(getCodeBase(), imgName);
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(img, 0);
        try {
            mt.waitForID(0);
        } catch(InterruptedException e) {
            throw new IllegalArgumentException(
                "InterruptedException podczas pobierania obrazu " + imgName);
        }
        if (mt.isErrorID(0)) {
            throw new IllegalArgumentException(
                "Nie można pobrać obrazu " + imgName);
        }
        v = new Vector<Sprite>(); // Działa wielowątkowo, 
                                  // używamy klasy Vector.
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Tworzymy następny!");
        Sprite s = new Sprite(this, img);
        s.start();
        p.add(s);
        v.add(s);
    }

    public void stop() {
        for (int i=0; i<v.size(); i++) {
            v.get(i).stop();
        }
        v.clear();
    }
}

// END main
