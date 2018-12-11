package Books_exercises.JavaReceptury.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

// BEGIN main
public class PaintDemo extends Component {

    private static final long serialVersionUID = -5595189404659801913L;
    int rectX = 20, rectY = 30;
    int rectWidth = 50, rectHeight = 50;

    /**
     * Klasy potomne klasy Component mogą przesłaniać metodę 
     * paint(), jednak klasy potomne klasy JComponent   
     * powinny raczej używać metody paintComponent(), gdyż w ten 
     * sposób mogą uniknąć niepotrzebnego rysowania krawędzi itd.
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(rectX, rectY, rectWidth, rectHeight);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }
}
// END main
