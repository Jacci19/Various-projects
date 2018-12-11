package Books_exercises.JavaReceptury.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.darwinsys.util.Debug;

/** Prosty program rysujący.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Grapher extends JPanel {

    private static final long serialVersionUID = -1813143391310613248L;

    /** Mnożnik zakresu umożliwiający zostawienie miejsca na obramowanie */
    public final static double BORDERFACTOR = 1.1f;

    /** Lista punktów (obiektów Apoint). */
    protected List<Point2D> data;

    /** Minimalna i maksymalna wartość współrzędnej X */
    protected double minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE;
    /** Minimalna i maksymalna wartość współrzędnej Y */
    protected double miny = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE;
    /** Zakres wartości X i Y */
    protected double xrange, yrange;

    public Grapher() {
        data = new ArrayList<Point2D>();
        figure();
    }

    /** Przygotowanie listy danych na podstawie listy łańcuchów znaków,
     * przy czym współrzędna X jest inkrementowana automatycznie, 
     * a wartości współrzędnych Y punktów są wczytywane z przekazanych
     * łańcuchów znaków.
     */
    public void setListDataFromYStrings(List<String> newData) {
        data.clear();
        for (int i=0; i < newData.size(); i++) {
            Point2D p = new Point2D.Double();
            p.setLocation(i, java.lang.Double.parseDouble(newData.get(i)));
            data.add(p);
        }
        figure();
    }

    /** Ustawiamy używaną listę danych na listę przekazaną w wywołaniu,
     * na przykład zwracaną przez wywołanie GraphReader.read(). */
    public void setListData(List<Point2D> newData) {
        data = newData;
        figure();
    }

    /** Metoda oblicza nowe dane po zmianie listy. */
    private void figure() {
        // Odnajdujemy minimum i maksimum.
        for (int i=0 ; i < data.size(); i++) {
            Point2D d = (Point2D)data.get(i);
            if (d.getX() < minx) minx = d.getX();
            if (d.getX() > maxx) maxx = d.getX();
            if (d.getY() < miny) miny = d.getY();
            if (d.getY() > maxy) maxy = d.getY();
        }

        // Obliczamy zakresy.
        xrange = (maxx - minx) * BORDERFACTOR;
        yrange = (maxy - miny) * BORDERFACTOR;
        Debug.println("Zakres", "minx,x,r = " + minx +' '+ maxx +' '+ xrange);
        Debug.println("Zakres", "miny,y,r = " + miny +' '+ maxy +' '+ yrange);
    }

    /** Metoda wywoływana, gdy należy wyświetlić zawartość okna.
     * Oblicza zakres X i Y, po czym odpowiednio skaluje współrzędne.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension s = getSize();
        if (data.size() < 2) {
            g.drawString("Niewystarczające dane: " + data.size(), 10, 40);
            return;
        }

        // Obliczamy współczynniki skali
        double xfact =  s.width  / xrange;
        double yfact =  s.height / yrange;

        // Skalujemy i wyświetlamy dane
        for (int i=0 ; i < data.size(); i++) {
            Point2D d = (Point2D)data.get(i);
            double x = (d.getX() - minx) * xfact;
            double y = (d.getY() - miny) * yfact;
            Debug.println("Punkt", "Dane " + i + " " + d + "; " +
                "x = " + x + "; y = " + y);
            // Rysujemy wycentrowany prostokąt o boku 5 pikseli —
            // stąd od współrzędnych X i Y wierzchołka prostokąta 
            // odejmowana jest liczba 2. W AWT współrzędne Y są 
            // liczone od dołu ku górze, a zatem musimy odjąć Y.
            g.drawRect(((int)x)-2, s.height-2-(int)y, 5, 5);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 150);
    }

    public static void main(String[] args) throws IOException {
        final JFrame f = new JFrame("Grapher");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Grapher grapher = new Grapher();
        f.setContentPane(grapher);
        f.setLocation(100, 100);
        f.pack();
        List<Point2D> data = null;
        if (args.length == 0)
            data = GraphReader.read("Grapher.txt");
        else {
            String fileName = args[0];
            if ("-".equals(fileName)) {
                data = GraphReader.read(new InputStreamReader(System.in),
                    "System.in");
            } else {
                data = GraphReader.read(fileName);
            }
        }
        grapher.setListData(data);
        f.setVisible(true);
    }
}
// END main
