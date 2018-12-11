package Books_exercises.JavaReceptury.gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;

/**
 * Prezentacja wykorzystania kontrolki JSpinner.
 * @author ian
 */
// BEGIN main
public class SpinnerDemo {

    public static void main(String[] args) {
        JFrame jf = new JFrame("JSpinner");
        Container cp = jf.getContentPane();
        cp.setLayout(new GridLayout(0,1));

        // Tworzymy komponent JSpinner, używając jednego 
        // z predefiniowanych obiektów modeli.
        JSpinner dates = new JSpinner(new SpinnerDateModel());
        cp.add(dates);

        // Tworzymy komponent JSpinner, używając obiektu SpinnerListModel.
        String[] data = { "Jeden", "Dwa", "Trzy" };
        JSpinner js = new JSpinner(new SpinnerListModel(data));
        cp.add(js);

        jf.setSize(100, 80);
        jf.setVisible(true);
    }
}
// END main
