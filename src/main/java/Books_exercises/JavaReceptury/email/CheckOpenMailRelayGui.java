package Books_exercises.JavaReceptury.email;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.darwinsys.io.TextAreaOutputStream;
import com.darwinsys.swingui.ErrorUtil;

// BEGIN main
/** 
 * Graficzny interfejs użytkownika dla programu CheckOpenMailRelay 
 * pozwala na jego wielokrotne uruchamianie na jednej wirtualnej
 * maszynie Javy w celu uniknięcia opóźnień podczas inicjalizacji 
 * programu.
 * Każde z zadań jest uruchamiane w osobnym wątku (Thread) 
 * w celu szybszego powrotu do stanu gotowości.
 * Do przechwytywania danych wyjściowych i wyświetlania ich 
 * w oknie stosowane są strumienie potokowe.
 */
public final class CheckOpenMailRelayGui extends JFrame {

    private static final long serialVersionUID = 1L;
    private static CheckOpenMailRelayGui gui;

    public static void main(String unused[]) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler() {
                    public void uncaughtException(Thread t, final Throwable ex) {
                        try {
                            SwingUtilities.invokeAndWait(new Runnable() {
                                public void run() {
                                    ErrorUtil.showExceptions(gui, ex);
                                }
                            });
                        } catch (InvocationTargetException | 
                            InterruptedException e) {

                            // Tu nie możemy nic zrobić...
                            System.err.println("Ech! Klapa: " + e);
                        }
                    }
                });
        gui = new CheckOpenMailRelayGui();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.setVisible(true);   // To możemy zrobić tylko  
                                        // w wątku obsługi zdarzeń (EDT).    
            }
        });
    }

    /** Pole tekstowe służące do podania nazwy hosta lub adresu IP. */
    protected JTextField hostTextField;
    /** Przycisk służący do rozpoczynania testu; użyliśmy pola, aby
     * można było aktywować i dezaktywować przycisk. */
    protected JButton goButton;
    /** Wielowierszowe pole tekstowe służące do prezentacji wyników. */
    protected JTextArea results;
    /** Strumień potokowy używany do zapisu wyników w polu "results". */
    protected PrintStream out;
    /** Strumień potokowy używany do odczytu z "ps" i zapisu 
     * w polu "results". */
    protected BufferedReader iis;

    /** Ta klasa wewnętrzna jest używana do obsługi czynności 
     * związanych z kliknięciem przycisku "Próbuj" w polu tekstowym
     * oraz naciśnięciem klawisza Enter. Jest do niej przekazywany
     * adres IP lub nazwa hosta z pola tekstowego, które to informacje
     * są następnie przekazywane dalej do metody process() klasy 
     * głównej.
     * Jest ona uruchamiana w wątku obsługi zdarzeń interfejsu 
     * użytkownika (EDT) w celu uniknięcia potencjalnych problemów 
     * z wyświetlaniem danych.
     */
    final ActionListener runner;
    /** Tworzymy graficzny interfejs użytkownika oraz 
     * operacje wejścia-wyjścia umożliwiające wyświetlanie 
     * wyników programu "CheckOpenMailRelay" w wielowierszowym
     * polu tekstowym "results".
     */
    public CheckOpenMailRelayGui() throws IOException {
        super("Testy programu CheckOpenMailRelay");

        runner = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                goButton.setEnabled(false);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        String host = hostTextField.getText().trim();
                        out.println("Sprawdzam " + host);
                        CheckOpenMailRelay.process(host, out);
                        goButton.setEnabled(true);
                    }
                });
            }
        };

        JPanel p;
        Container cp = getContentPane();
        cp.add(BorderLayout.NORTH, p = new JPanel());

        // Etykieta i pole tekstowe.
        p.add(new JLabel("Host:"));
        p.add(hostTextField = new JTextField(10));
        hostTextField.addActionListener(runner);

        p.add(goButton = new JButton("Próbuj"));
        goButton.addActionListener(runner);

        JButton cb;
        p.add(cb = new JButton("Czyść dziennik"));
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                results.setText("");
            }
        });
        JButton sb;
        p.add(sb = new JButton("Zapisz dziennik"));
        sb.setEnabled(false);

        results = new JTextArea(20, 60);

        // Dodajemy wielowierszowe pole tekstowe i umieszczamy je 
        // w głównej części okna (CENTER) w 
        // JScrollPane, aby było automatycznie przewijane.
        cp.add(BorderLayout.CENTER, new JScrollPane(results));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();        // Koniec tworzenia interfejsu użytkownika.

        out = new PrintStream(new TextAreaOutputStream(results));
    }
}
// END main
