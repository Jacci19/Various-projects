package Books_exercises.JavaReceptury.plotter;

import java.awt.Point;

// BEGIN main
/**
 * Klasa abstrakcyjna Plotter. Należy tworzyć jej klasy
 * potomne obsługujące różnego typu plotery:
 * X, DOS, Penman, HP, i tak dalej.
 *
 * Układ współrzędnych: X = 0 z lewej strony, współrzędne rosną
 * ku prawej; Y = 0 u góry, współrzędne rosną ku dołowi 
 * (tak samo jak w AWT).
 *
 * @author    Ian F. Darwin
 */
public abstract class Plotter {
    public final int MAXX = 800;
    public final int MAXY = 600;
    /** Bieżąca współrzędna X (ten sam sposób wykorzystania jak w AWT!) */
    protected int curx;
    /** Bieżąca współrzędna Y (ten sam sposób wykorzystania jak w AWT!) */
    protected int cury;
    /** Bieżący stan: u góry lub na dole */
    protected boolean penIsUp;
    /** Aktualnie używany kolor */
    protected int penColor;

    Plotter() {
        penIsUp = true;
        curx = 0; cury = 0;
    }
    abstract void rmoveTo(int incrx, int incry);
    abstract void moveTo(int absx, int absy);
    abstract void penUp();
    abstract void penDown();
    abstract void penColor(int c);

    abstract void setFont(String fName, int fSize);
    abstract void drawString(String s);

    /* Metody nieabstrakcyjne */

    /** Rysujemy prostokąt o szerokości w i wysokości h */
    public void drawBox(int w, int h) {
        penDown();
        rmoveTo(w, 0);
        rmoveTo(0, h);
        rmoveTo(-w, 0);
        rmoveTo(0, -h);
        penUp();
    }

    /** Rysujemy prostokąt na podstawie obiektu Dimension, 
     * określającego wymiary prostokąta. Klasa ta jest dostępna
     * w bibliotece AWT.
     */
    public void drawBox(java.awt.Dimension d) {
        drawBox(d.width, d.height);
    }

    /** Rysujemy prostokąt na podstawie obiektu Rectangle, 
     * określającego wymiary prostokąta. Klasa ta jest dostępna
     * w bibliotece AWT.
     */
    public void drawBox(java.awt.Rectangle r) {
        moveTo(r.x, r.y);
        drawBox(r.width, r.height);
    }
    
    /** Wyświetlamy bieżące położenie; użyteczne do celów testowych
     * i niczego więcej.
     */
    public Point getLocation() {
        return new Point(curx, cury);
    }
}
// END main
