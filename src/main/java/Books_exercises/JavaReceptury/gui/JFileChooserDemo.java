package Books_exercises.JavaReceptury.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** Prosty przykład wykorzystania klasy JFileChooser. */
// BEGIN main
public class JFileChooserDemo extends JPanel {

    private static final long serialVersionUID = 2615629432967419176L;

    /** Konstruktor */
    public JFileChooserDemo(JFrame f) {
        final JFrame frame = f;
        final JFileChooser chooser = new JFileChooser();

        /* Aby użytkownik mógł wybierać wyłącznie katalogi, należy
         * usunąć komentarz z poniższego wiersza. Domyślnie okno 
         * dialogowe pozwala jedynie na wybieranie plików.
         * Należy pamiętać, że w przypadku wybrania trybu pracy 
         * DIRECTORIES_ONLY w oknie dialogowym nie będą wyświetlane
         * żadne pliki, nawet jeśli będzie ono pracować w widoku plików.
         */

        // chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        /* Jeśli w oknie mają być wyświetlane wyłącznie pliki konkretnych 
         * typów, to należy użyć klasy FileFilter. Swoją drogą, użyta poniżej 
         * klasa JFileFilter nie należy do pakietu javax.swing; jest to 
         * stworzona przeze mnie implementacja interfejsu 
         * javax.swing.filechooser.FileFilter, przypominająca nieco
         * ExtensionFilter - przykład JFC dostarczany wraz z J2SE SKD.
         */
        JFileFilter filter = new JFileFilter();
        filter.addType("java");
        filter.addType("class");
        filter.addType("jar");
        filter.setDescription("Pliki Javy");
        chooser.addChoosableFileFilter(filter);
        JButton b = new JButton("Wybierz plik...");
        add(b);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            int returnVal = chooser.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                System.out.println("Wybrałeś " + 
                    (file.isFile() ? "plik" : "katalog") +
                    " o nazwie: " + file.getPath());
            } else {
                System.out.println("Nie został wybrany żaden obiekt systemu plików.");
            }
            }
        });
    }


    public static void main(String[] args) {
        JFrame f = new JFrame("Demo klasy JFileChooser");
        f.getContentPane().add(new JFileChooserDemo(f));
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
// END main
