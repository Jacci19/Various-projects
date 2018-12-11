package Books_exercises.JavaReceptury.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

// BEGIN main
/**
 * Interaktywne sprawdzenie dostępności pakietu "macosui".
 * Klasa nie może rozszerzać JFrame; wartości właściwości muszą
 * zostać określone przed pierwszym wywołaniem konstruktora 
 * jakiegokolwiek komponentu pakietu Swing.
 */
public class MacOsUiHints {

    public static void main(String[] args) throws Exception {
        // Test systemu Mac OS X:
        // zapewniamy, że opcje menu Plik, Edycja, Widok itd. będą się  
        // pojawiały pod górną krawędzią ekranu.
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        // Test systemu Mac OS X Tester: 
        // zapewniamy, że ta nazwa zostanie wyświetlona w menu aplikacji.
        System.setProperty("com.apple.mrj.application.apple.menu.about.name",
            "MacOsUiHints");
        final MacOsUiHints gui = new MacOsUiHints( );
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                gui.getFrame().setVisible(true);
            }
        });
    }

    JFrame jf;

    protected JFrame getFrame() {
        return jf;
    }

    public MacOsUiHints( ) {
        jf = new JFrame("MacOsUiHints");
        JButton button = new JButton("Wyjdź");
        button.addActionListener(new ActionListener( ) {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        jf.getContentPane( ).add(button);
        
        JMenuBar mb = new JMenuBar();
        jf.setJMenuBar(mb);
        
        JMenu fileMenu = new JMenu("Plik");
        mb.add(fileMenu);
        fileMenu.add(new JMenuItem("Zakończ"));
        
        mb.add(new JMenu("Edycja"));
        
        // Test: sprawdźmy, czy wybranie opcji informacji o programie
        // wyświetli nasze okno dialogowe.
        // To samo dotyczy opcji Preferencje oraz Zakończ.
        // MacOSAppAdapter adapter =
        //   new MacOSAppAdapter(jf, abouter, prefser, printer, shutter);
        //adapter.register( );
        jf.setSize(300, 200);

    }
}
// END main
