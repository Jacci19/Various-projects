package Books_exercises.JavaReceptury.threads;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

// BEGIN main
/** Sprite jest obrazkiem (obiektem Image) samodzielnie poruszającym się 
 * po ekranie.
 */
public class Sprite extends Component implements Runnable {
    protected static int spriteNumber = 0;
    protected Thread t;
    protected int x, y;
    protected Component parent;
    protected Image img;
    protected volatile boolean done = false;
    /** Czas w milisekundach pomiędzy kolejnymi przesunięciami. */
    protected volatile int sleepTime = 250;
    /** Kierunek ruchu danego obrazka. */
    protected int direction;
    /** Kierunek poruszania się - w poziomie */
    public static final int HORIZONTAL = 1;
    /** Kierunek poruszania się - w pionie */
    public static final int VERTICAL = 2;
    /** Kierunek poruszania się - po skosie */
    public static final int DIAGONAL = 3;

    /** Tworzy obiekt Sprite, dysponując komponentem nadrzędnym, obrazkiem
     * oraz kierunkiem ruchu.
     * Tworzy i uruchamia wątek (obiekt Thread) obsługujący danego "duszka".
     */
    public Sprite(Component parent, Image img, int dir) {
        this.parent = parent;
        this.img = img;
        switch(dir) {
            case VERTICAL: case HORIZONTAL: case DIAGONAL:
                direction = dir;
                break;
            default:
                throw new IllegalArgumentException(
                    "Kierunek " + dir + " jest nieprawidłowy");
        }
        setSize(img.getWidth(this), img.getHeight(this));
    }

    /** Tworzy obiekt Sprite, wykorzystując domyślny kierunek ruchu */
    public Sprite(Component parent, Image img) {
        this(parent, img, DIAGONAL);
    }

    /** Uruchamia wątek sterujący poruszaniem obiektu Sprite. */
    public void start() {
        t = new Thread(this);
        t.setName("Sprite nr " + ++spriteNumber);
        t.start();
    }

    /** Zatrzymuje wątek obiektu Sprite. */
    public void stop() {
        if (t == null)
            return;
        System.out.println("Zatrzymujemy " + t.getName());
        done = true;
    }

    /** Zamiana szybkości ruchu */
    protected void setSleepTime(int n) {
        sleepTime = n;
    }

    /**
     * Przesuwamy "duszka" po ekranie.
     * Ta wersja programu umożliwia jedynie ruch w poziomie, pionie 
     * oraz po skosie (pod kątem 45 stopni).
     */
    public void run() {
        int width = parent.getSize().width;
        int height = parent.getSize().height;
        // Określamy położenie początkowe
        x = (int)(Math.random() * width);
        y = (int)(Math.random() * height);
        // Zmieniamy kierunki ruchu w pionie/poziomie
        int xincr = Math.random()>0.5?1:-1;
        int yincr = Math.random()>0.5?1:-1;
        while (!done) {
            width = parent.getSize().width;
            height = parent.getSize().height;
            if ((x+=xincr) >= width)
                x=0;
            if ((y+=yincr) >= height)
                y=0;
            if (x<0)
                x = width;
            if (y<0)
                y = height;
            switch(direction) {
                case VERTICAL: 
                    x = 0;
                    break;
                case HORIZONTAL: 
                    y = 0;
                    break;
                case DIAGONAL: break;
            }
            //System.out.println("Z " + getLocation() + "->" + x + "," + y);
            setLocation(x, y);
            repaint();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    /** paint -- wyświetlamy obrazek w wyznaczonym, bieżącym położeniu */
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }
}
// END main
