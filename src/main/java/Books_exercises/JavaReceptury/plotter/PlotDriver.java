package Books_exercises.JavaReceptury.plotter;

/**
/** Program główny, "sterownik" dla klasy Plotter.
 * Program symuluje większe aplikacje graficzne, takie jak GnuPlot.
 */
// BEGIN main
public class PlotDriver {

    /** Tworzymy obiekt (sterownik) Plotter i sprawdzamy, jak działa. */
    public static void main(String[] argv) {
        Plotter r ;
        if (argv.length != 1) {
            System.err.println("Sposób użycia: PlotDriver driverclass");
            return;
        }
        try {
            Class<?> c = Class.forName(argv[0]);
            Object o = c.newInstance();
            if (!(o instanceof Plotter))
                throw new ClassNotFoundException("To nie jest obiekt Plotter");
            r = (Plotter)o;
        } catch (ClassNotFoundException e) {
            System.err.println("Przykro mi, " + argv[0] +
                    " nie jest klasą reprezentującą ploter.");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        r.penDown();
        r.penColor(1);
        r.moveTo(200, 200);
        r.penColor(2);
        r.drawBox(123, 200);
        r.rmoveTo(10, 20);
        r.penColor(3);
        r.drawBox(123, 200);
        r.penUp();
        r.moveTo(300, 100);
        r.penDown();
        r.setFont("Helvetica", 14);
        r.drawString("Witaj, świecie");
        r.penColor(4);
        r.drawBox(10, 10);
    }
}
// END main
