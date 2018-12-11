package Books_exercises.JavaReceptury.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

// BEGIN main
public class TabPaneDemo {
    protected JTabbedPane tabPane;
    public TabPaneDemo() {
        tabPane = new JTabbedPane();
        tabPane.add(new JLabel("Jeden", JLabel.CENTER), "Pierwszy");
        tabPane.add(new JLabel("Dwa", JLabel.CENTER), "Drugi");
    }

    public static void main(String[] a) {
        JFrame f = new JFrame("Demonstracja kart");
        f.getContentPane().add(new TabPaneDemo().tabPane);
        f.setSize(120, 100);
        f.setVisible(true);
    }
}
// END main
