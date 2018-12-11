package Books_exercises.JavaReceptury.otherlang;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.darwinsys.util.Debug;

/**
 * ExecDemoNS - pokazuje jak, z poziomu kodu Java, uruchomić 
 * zewnętrzny program.
 */
// BEGIN main
public class ExecDemoNS extends JFrame {
    private static final String NETSCAPE = "netscape";

    /** Nazwa pliku pomocy. */
    protected final static String HELPFILE = "./help/index.html";

    /** Stos obiektów procesów; każdy element tego stosu śledzi jeden
     *  wykonywany proces zewnętrzny. */
    Stack<Process> pStack = new Stack<>();

    /** main - inicjalizacja i uruchomienie */
    public static void main(String av[]) throws Exception {
        String program = av.length == 0 ? NETSCAPE : av[0];
        new ExecDemoNS(program).setVisible(true);
    }

    /** Ścieżka do pliku wykonywalnego, który chcemy uruchomić */
    protected static String program;

    /** Konstruktor - konfigurujemy wszystko... */
    public ExecDemoNS(String prog) {
        super("ExecDemo: " + prog);
        String osname = System.getProperty("os.name");
        if (osname == null)
            throw new IllegalArgumentException("no os.name");
        if (prog.equals(NETSCAPE))
            program = // Na razie tylko Windows lub UNIX, 
                      // użytkownicy Maców - przepraszam
                (osname.toLowerCase().indexOf("windows")!=-1) ?
                "c:/program files/netscape/navigator 9/navigator.exe" :
                "/usr/local/netscape/navigator";
        else
            program = prog;

        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        JButton b;
        cp.add(b=new JButton("Uruchom"));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                runProg();
            }
        });
        cp.add(b=new JButton("Czekaj"));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                doWait();
            }
        });
        cp.add(b=new JButton("Zakończ"));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        pack();
    }

    /** Uruchamiamy "system pomocy" we własnym wątku. */
    public void runProg() {

        new Thread() {
            public void run() {

                try {
                    // Pobieramy URL do pliku pomocy
                    URL helpURL = this.getClass().getClassLoader().
                        getResource(HELPFILE);

                    // Uruchamiamy przeglądarkę Netscape Navigator.

                    pStack.push(Runtime.getRuntime().exec(program + " " + helpURL));

                    Debug.println("trace", 
                             "Metoda main po uruchomieniu przeglądarki " + 
                             pStack.size());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ExecDemoNS.this,
                        "Błąd" + ex, "Błąd",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }.start();

    }

    public void doWait() {
        if (pStack.size() == 0) return;
        Debug.println("trace", "Czekamy na proces " + pStack.size());
        try {
            pStack.peek().waitFor();
            // Czekamy na zakończenie procesu.
            // (W przypadku pewnych starych programów dla systemu Windows
            // rozwiązanie to może działać nieprawidłowo).
            Debug.println("trace", "Proces " + pStack.size() + " został zakończony");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Błąd" + ex, "Błąd",
                JOptionPane.ERROR_MESSAGE);
        }
        pStack.pop();
    }

}
// END main
