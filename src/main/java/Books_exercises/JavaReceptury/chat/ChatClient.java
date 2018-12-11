package Books_exercises.JavaReceptury.chat;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;

/** 
 * <p>
 * Proty klient pogawędek z graficznym interfejsem użytkownika.
 * Można sądzić, że pisanie aplikacji obsługujących internetowe pogawędki 
 * staje się w dzisiejszym czasie obowiązkowym ćwiczeniem, jakie 
 * należy zdać by zostać ekspertem do spraw języka Java.
 * <p>
 * Przedstawiony tu program jest bardzo prostym przykładem, gdyż 
 * nie wykorzystuje nawet żadnego protokołu poleceń, co oznacza, że 
 * nie możemy poprosić serwera o sporządzenie listy osób, które aktualnie
 * są zalogowane, ani o wykonanie jakichkolwiek innych bardziej 
 * wyszukanych operacji. Niemniej jednak w przypadku niewielkich grup 
 * osób, serwer działa dobrze.
 * <p>
 * Program używa gniazd klienckich obsługiwanych przez dwa wątki 
 * (obiekty Thread) (główny i jeden utworzony), z których jeden 
 * służy do odczytu danych a drugi do ich transmisji.
 * <p>
 * Serwer rozsyła odpowiedzi do wszystkich klientów.
 * <p>
 * @author Ian Darwin
 */
// BEGIN main
public class ChatClient extends JFrame {

    private static final long serialVersionUID = -3686334002367908392L;
    private static final String userName = 
        System.getProperty("user.name", "Bezimienny użytkownik");
    /** Czy użytkownik jest zalogowany? */
    protected boolean loggedIn;
    /** Główna ramka aplikacji. */
    protected JFrame cp;
    /** Domyślny numer portu. */
    protected static final int PORTNUM = ChatProtocol.PORTNUM;
    /** Używany numer portu. */
    protected int port;
    /** Gniazdo */
    protected Socket sock;
    /** Obiekt BufferedReader służący do odczytu danych z gniazda */
    protected BufferedReader is;
    /** Obiekt PrintWriter służący do wysyłania wierszy tekstu */
    protected PrintWriter pw;
    /** TextField - pole wejściowe */
    protected TextField tf;
    /** TextArea - pole do wyświetlania pogawędki */
    protected TextArea ta;
    /** Przycisk Login */
    protected Button loginButton;
    /** Przycisk Logout */
    protected Button logoutButton;
    /** Tytuł wyświetlany na pasku nagłówka */
    final static String TITLE = "ChatClient: Prosty klient pogawędek Iana Darwina";

    /** Przygotowanie graficznego interfejsu użytkownika. */
    public ChatClient() {
        cp = this;
        cp.setTitle(TITLE);
        cp.setLayout(new BorderLayout());
        port = PORTNUM;
        
        // Interfejs użytkownika
        ta = new TextArea(14, 80);
        ta.setEditable(false);        // tylko do odczytu
        ta.setFont(new Font("Monospaced", Font.PLAIN, 11));
        cp.add(BorderLayout.NORTH, ta);

        Panel p = new Panel();

        // Przycisk do logowania.
        p.add(loginButton = new Button("Logowanie"));
        loginButton.setEnabled(true);
        loginButton.requestFocus();
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
                loginButton.setEnabled(false);
                logoutButton.setEnabled(true);
                tf.requestFocus();    // Określenie miejsca wprowadzania!
            }
        });

        // Przycisk do wylogowania
        p.add(logoutButton = new Button("Wyloguj się"));
        logoutButton.setEnabled(false);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
                loginButton.setEnabled(true);
                logoutButton.setEnabled(false);
                loginButton.requestFocus();
            }
        });

        p.add(new Label("Treść wiadomości:"));
        tf = new TextField(40);
        tf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loggedIn) {
                    pw.println(ChatProtocol.CMD_BCAST+tf.getText());
                    tf.setText(""); 
                }
            }
        });
        p.add(tf);

        cp.add(BorderLayout.SOUTH, p);

        cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cp.pack();
    }

    protected String serverHost = "localhost";

    /** Podłączamy się do pogawędki */
    public void login() {
        showStatus("Logujemy się!");
        if (loggedIn)
            return;
        try {
            sock = new Socket(serverHost, port);
            is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            pw = new PrintWriter(sock.getOutputStream(), true);
            showStatus("Mamy połączenie");

            // Udajemy logowanie - na razie nie potrzeba hasła.
            pw.println(ChatProtocol.CMD_LOGIN + userName);

            loggedIn = true;

        } catch(IOException e) {
            showStatus("Nie można nawiązać połączenia z " + 
                serverHost + "/" + port + ": " + e);
            cp.add(new Label("Nie można uzyskać gniazda: " + e));
            return;
        }

        // Tworzymy i uruchamiamy czytelnika: z serwera do wielowierszowego
        // pola tekstowego. Aby uniknąć blokowania używamy wątku.
        new Thread(new Runnable() {
            public void run() {
                String line;
                try {
                    while (loggedIn && ((line = is.readLine()) != null))
                        ta.append(line + "\n");
                } catch(IOException e) {
                    showStatus("POŁĄCZENIE ZOSTAŁO UTRACONE!!");
                    return;
                }
            }
        }).start();
    }

    /** Wychodzimy stąd, Scotty, nie ma tu żadnych inteligentnych form życia! */
    public void logout() {
        if (!loggedIn)
            return;
        loggedIn = false;
        try {
            if (sock != null)
                sock.close();
        } catch (IOException ign) {
            // No i co z tego?
        }
    }

    public void showStatus(String message) {
        System.out.println(message);
    }

    /** Metoda główna umożliwiająca uruchomienie programu jako 
     * normalnej aplikacji. 
     */
    public static void main(String[] args) {
        ChatClient room101 = new ChatClient();
        room101.pack();
        room101.setVisible(true);
    }
}
// END main
