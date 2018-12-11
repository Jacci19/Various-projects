package Books_exercises.JavaReceptury.darwinsys.swingui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Klasa pomocnicza od wyświetlania wyjątków w aplikacjach o graficznym
 * interfejsie użytkownika tworzonym przy użyciu pakietu Swing.
 * Wyjątki są wyświetlane w panelu JOptionPane, przy czym prezentowany
 * jest cały łańcuch wyjątków, przy czym wykorzystywana jest w tym 
 * celu zarówno metoda SQLExcption.getNextException(), jak i bardziej
 * ogólna metoda Exception.getCause(). 
 * <p>
 * Użytkownik może kliknąć przycisk Szczegóły..., by wyświetlić w okienku
 * łańcuch wyjątków; przy czym <b>nie</b> jest on prezentowany jeśli 
 * użytkownik o to nie poprosi.
 * @author Ian Darwin
 */
public class ErrorUtil {

    // static { System.out.println("Wczytano klasę ErrorUtil"); }

    /** Opcje przycisku dla ostatniego (bądź jedynego) wyjątku. */
    final static String[] choicesNoMore = { "OK", "Szczegóły..." };

    /** Opcje przycisku dla dowolnego wyjątku za wyjątkie ostatniego. */
    final static String[] choicesMore = { "OK", "Szczegóły...", "Dalej" };

    /** Drugie okno dialogowe dla łańcucha wyjątków wyświetlanego po kliknięciu
     * przycisku "Szczegóły...". */
    static DetailsDialog detailsDialog;

    /** Publiczny konstruktor bezargumentowy, dla tych, którzy lubią proste 
     * sposoby tworzenia obiektów. */
    public ErrorUtil() {
        // Tu nie musimy nic robić.
    }

    /** Metoda pomocnicza przeznaczona do użycia w wątku AWT; to stara,
     * nigdy nie obsługiwana i przeważnie nie działająca metoda, ale i tak 
     * podałem jej kod.
     * Sposób użycia:
     * <pre>
     * System.setProperty("sun.awt.exception.handler", "com.darwinsys.swingui.ErrorUtil");
     * </pre>
     */
    public void handle(Throwable th) {
        //System.out.println("Metoda handle() wywołana z " + th.getClass().getName());
        showExceptions(null, th);
    }
        
    // BEGIN main
    /**
     * Metoda wyświetla przekazany obiekt Exception (oraz ewentualne 
     * wyjątki zagnieżdżone) w oknie JOptionPane.
     */
    public static void showExceptions(Component parent, Throwable theExc) {

        Throwable next = null;

        do {
            String className = theExc.getClass().getName();
            String message = className;

            if (theExc instanceof SQLException) {
                SQLException sexc = (SQLException)theExc;
                message += "; code=" + sexc.getErrorCode();
                next = sexc.getNextException();
            } else {
                next = theExc.getCause();   
                                 // Umieścić w komentarzu, jeśli < JDK 1.4
            }

            String[] choices = next != null ? choicesMore : choicesNoMore;

            /* Wyświetlamy okno dialogowe! */
            int response = JOptionPane.showOptionDialog(
                parent,
                message,
                className,                             // tytuł
                JOptionPane.YES_NO_CANCEL_OPTION,      // typ ikony
                JOptionPane.ERROR_MESSAGE,             // typ komunikatu
                null,                                  // ikona
                choices,                               // opcje
                choices[0]                             // domyślnie
                );

            if (response == 0)          // "OK"
                return;
            if (response == 1) {        // "Szczegóły"
                // Wyświetlamy inne okno JDialog z polem tekstowym JTextArea,
                // w którym wyświetlamy tekst wygenerowany przez metodę
                // printStackTrace().
                if (detailsDialog == null) // Pierwszy raz, tworzenie leniwe.
                    detailsDialog = new DetailsDialog((JFrame)parent);
                detailsDialog.showStackTrace(theExc);
            }
            // W przeciwnym razie resp = 2, "Dalej", działamy dalej...

            theExc = next;

        } while (next != null);
    }

    /** Klasa JDialog używana do wyświetlania szczegółowych informacji
     * o wyjątku Exception. */
    protected static class DetailsDialog extends JDialog {

        private static final long serialVersionUID = -4779441441693785664L;
        JButton ok;
        JTextArea text;
        /** Tworzymy obiekt DetailsDialog, używając podanego komponentu
         * nadrzędnego (Frame/JFrame). */
        DetailsDialog(JFrame parent) {
            super(parent);
            Container cp = getContentPane();
            text = new JTextArea(40, 40);
            cp.add(text, BorderLayout.CENTER);
            ok = new JButton("Zamknij");
            cp.add(ok, BorderLayout.SOUTH);
            ok.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    dispose();
                }
            });
            pack();
        }

        /** Wyświetlamy w tym oknie dialogowym "zrzut stosu" - wyniki 
         * wygenerowane przez metodę printStackTrace(). */
        void showStackTrace(Throwable exc) {
            CharArrayWriter buff = new CharArrayWriter();
            PrintWriter pw = new PrintWriter(buff);
            exc.printStackTrace(pw);
            pw.close();
            text.setText(buff.toString());
            setVisible(true);
        }
    }
    // END main
}
