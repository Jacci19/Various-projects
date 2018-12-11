package Books_exercises.JavaReceptury.printjdk14printservice;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.darwinsys.swingui.UtilGUI;

// BEGIN main
/**
 * Program przedstawia najnowsze wcielenie mechanizmów drukowania 
 * PrintService z wykorzystaniem graficznego interfejsu użytkownika;
 * interfejs ten składa się co prawda tylko z jednego przycisku - 
 * Drukuj - a nazwa drukowanego pliku została podana na stałe,
 * jednak ma to być przecież jak najprostszy przykład...
 */
public class PrintServiceDemo extends JFrame {

    private static final long serialVersionUID = 923572304627926023L;
    
    private static final String INPUT_FILE_NAME = "/demo.txt";

    /** Program główny: utworzenie obiektu i wyświetlenie okna. 
     * @throws IOException */
    public static void main(String[] av) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PrintServiceDemo("Przykład drukowania").setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** Konstruktor tworzący graficzny interfejs użytkownika 
     * składający się z jednego przycisku: Drukuj. */
    PrintServiceDemo(String title) {
        super(title);
        System.out.println("PrintServiceDemo.PrintServiceDemo()");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JButton b;
        add(b = new JButton("Drukuj"));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(
                    "PrintServiceDemo.PrintServiceDemo...actionPerformed()");
                try {
                    print(INPUT_FILE_NAME);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(
                        PrintServiceDemo.this, "Błąd: " + e1, "Błąd",
                        JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
        pack();
        UtilGUI.center(this);
    }

    /** Drukujemy plik o podanej nazwie. 
     * @throws IOException
     * @throws PrintException 
     */
    public void print(String fileName) throws IOException, PrintException {
        System.out.println("PrintServiceDemo.print(): " + 
            "Drukowanie pliku: " + fileName);
        DocFlavor flavor = DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_8;
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        //aset.add(MediaSize.NA.LETTER);
        aset.add(MediaSizeName.NA_LETTER);
        //aset.add(new JobName(INPUT_FILE_NAME, null));
        PrintService[] pservices = 
            PrintServiceLookup.lookupPrintServices(flavor, aset);
        int i;
        switch(pservices.length) {
        case 0:
            System.err.println(0);
            JOptionPane.showMessageDialog(PrintServiceDemo.this,
                "Błąd: brak usługi drukowania", "Błąd", 
                JOptionPane.ERROR_MESSAGE);
            return;
        case 1:
            i = 0;    // Dostępna jest tylko jedna drukarka, używamy jej.
            break;
        default:
            i = JOptionPane.showOptionDialog(this, 
                "Wybierz drukarkę", "Wybór", 
                JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, pservices, pservices[0]);
            break;
        }
        DocPrintJob pj = pservices[i].createPrintJob();
        InputStream is = getClass().getResourceAsStream(INPUT_FILE_NAME);
        if (is == null) {
            throw new NullPointerException("Pusty strumień wejściowy: nie znaleziono pliku?");
        }
        Doc doc = new SimpleDoc(is, flavor, null);
        
        pj.print(doc, aset);
    }
}
// END main
