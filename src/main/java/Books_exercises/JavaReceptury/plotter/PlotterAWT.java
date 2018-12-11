package Books_exercises.JavaReceptury.plotter;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

/**
 * Klasa potomna klasy Plotter umożliwiająca wyświetlenie rysunku 
 * w oknie AWT. Odwołanie się od AWT daje nam do dyspozycji 
 * "znany i działający" ploter, który można wykorzystać do celów
 * testowych. Program można także wykorzystać jako bazę do 
 * stworzenia własnego sterownika plotera.
 * @author    Ian Darwin
 */
// BEGIN main
public class PlotterAWT extends Plotter {

    private JFrame f;
    private PCanvas p;
    private Graphics g;
    private Font font;
    private FontMetrics fontMetrics;

    PlotterAWT() {
        f = new JFrame("Ploter");
        Container cp = f.getContentPane();
        p = new PCanvas(MAXX, MAXY);
        cp.add(p, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g = p.getOsGraphics();
    }

    public void drawBox(int w, int h) {
        g.drawRect(curx, cury, w, h);
        p.repaint();
    }

    public void rmoveTo(int incrx, int incry){
        moveTo(curx += incrx, cury += incry);
    }

    public void moveTo(int absx, int absy){
        if (!penIsUp)
            g.drawLine(curx, cury, absx, absy);
        curx = absx;
        cury = absy;
    }

    public void setdir(float deg){}
    void penUp(){ penIsUp = true; }
    void penDown(){ penIsUp = false; }
    void penColor(int c){
        switch(c) {
        case 0: g.setColor(Color.white); break;
        case 1: g.setColor(Color.black); break;
        case 2: g.setColor(Color.red); break;
        case 3: g.setColor(Color.green); break;
        case 4: g.setColor(Color.blue); break;
        default: g.setColor(new Color(c)); break;
        }
    }
    void setFont(String fName, int fSize) {
        font = new Font(fName, Font.BOLD, fSize);
        fontMetrics = p.getFontMetrics(font);
    }
    void drawString(String s) {
        g.drawString(s, curx, cury);
        curx += fontMetrics.stringWidth(s);
    }

    /** Klasa wewnętrzna zawierająca "pozaekranowy" obiekt 
     * Image, w którym rysujemy. Metoda paint() tego komponentu
     * kopiuje rysunek z obrazu na ekran. Dzięki temu nie trzeba
     * sporządzać listy elementów rysunku.
     */
    class PCanvas extends Canvas {
        private static final long serialVersionUID = 6827371843858633606L;
        Image offScreenImage;
        int width;
        int height;
        Graphics pg;

        PCanvas(int w, int h) {
            width = w;
            height = h;
            setBackground(Color.white);
            setForeground(Color.red);
        }

        public Graphics getOsGraphics() {
            return pg;
        }

        /** Ta metoda jest wywoływana przez AWT po utworzeniu obiektu
         * odpowiednika rodzimego okna, lecz przed pierwszym wywołaniem 
         * metody paint(), zatem doskonale nadaje się do tworzenia
         * obrazów itp.
         */
        public void addNotify() {
            super.addNotify();
            offScreenImage = createImage(width, height);
            // assert (offScreenImage != null);
            pg = offScreenImage.getGraphics();
        }

        public void paint(Graphics pg) {
            pg.drawImage(offScreenImage, 0, 0, null);
        }
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }
    }
}
// END main
