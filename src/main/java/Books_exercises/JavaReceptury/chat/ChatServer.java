package Books_exercises.JavaReceptury.chat;

import java.io.*;
import java.net.*;
import java.util.*;

/** Prosty serwer pogawędek współpracujący z prostym klientem do 
 * prowadzenia pogawędki.
 *
 * OSTRZEŻENIE -- sądzę, że ten kod jest bezpieczny pod względem 
 * działania wielowątkowego, jednak nie zostało to w 100% potwierdzone
 * przez zespół światowej klasy ekspertów w tej dziedzinie.
 * Warto zatem zajrzeć do książek poświęconych zagadnieniom wielowątkowości
 * w Javie.
 *
 * @author    Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class ChatServer {
    /** Nazwa serwera w komunikatach */
    protected final static String CHATMASTER_ID = "ChatMaster";
    /** Separator pomiędzy nazwą i komunikatem */
    protected final static String SEP = ": ";
    /** Gniazdo serwera */
    protected ServerSocket servSock;
    /** Lista podłączonych klintów */
    protected List<ChatHandler> clients;
    /** Flaga testowania */
    private static boolean DEBUG = false;

    /** Metoda main tworzy obiekt ChatServer i uruchamia go, a działanie 
     * metody runServer nie powinno się nigdy zakończyć. */
    public static void main(String[] argv) throws IOException {
        System.out.println("DarwinSys ChatServer 0.1 uruchamianie...");
        if (argv.length == 1 && argv[0].equals("-debug"))
            DEBUG = true;
        ChatServer w = new ChatServer();
        w.runServer();            // Ta metoda nigdy nie powinna się zakończyć.
        System.out.println("**BŁĄD* Chat Server 0.1 zamykanie serwera...");
    }

    /** Tworzymy i uruchamiamy usługę 
     * @throws IOException
     */
    ChatServer() throws IOException {
        clients = new ArrayList<>();

        servSock = new ServerSocket(ChatProtocol.PORTNUM);
        System.out.println("DarwinSys Chat Server działa na porcie " +
                ChatProtocol.PORTNUM);
    }

    public void runServer() {
        try {
            while (true) {
                Socket userSocket = servSock.accept();
                String hostName = userSocket.getInetAddress().getHostName();
                System.out.println("Odebrano połączenie z " + hostName);
                ChatHandler cl = new ChatHandler(userSocket, hostName);
                String welcomeMessage;
                synchronized (clients) {
                    clients.add(cl);
                    if (clients.size() == 1) {
                        welcomeMessage = "Witam! Jesteś tu pierwszy";
                    } else {
                        welcomeMessage = "Witam! Jesteś ostatnim z " +
                                clients.size() + " użytkowników.";
                    }
                }
                cl.start();
                cl.send(CHATMASTER_ID, welcomeMessage);
            }
        } catch(IOException e) {
            log("Błąd wejścia-wyjścia w metodzie runServer: " + e);
        }
    }

    protected void log(String s) {
        System.out.println(s);
    }

    /** 
     * Dalsza część tego pliku zawiera klasę wewnętrzną, odrębny
     * obiekt tej klasy jest tworzony w ramach obsługi każdego 
     * połączenia.
     */
    protected class ChatHandler extends Thread {
        /** Gniazdo klienta */
        protected Socket clientSock;
        /** Obiekt BufferedReader służący do odczytu danych z gniazda */
        protected BufferedReader is;
        /** Obiekt PrintWriter służący do zapisu komunikatów w gnieździe */
        protected PrintWriter pw;
        /** Komputer, na którym działa klient */
        protected String clientIP;
        /** Nazwa */
        protected String login;

        /* Tworzymy obiekt */
        public ChatHandler(Socket sock, String clnt) throws IOException {
            clientSock = sock;
            clientIP = clnt;
            is = new BufferedReader(
                new InputStreamReader(sock.getInputStream()));
            pw = new PrintWriter(sock.getOutputStream(), true);
        }

        /** Każdy ChatHandler jest wątkiem (Thread), a zatem musi mieć 
         * metodę run(), która obsługuje to połączenie.
         */
        public void run() {
            String line;
            try {
                /* W tej pętli musimy pozostawać tak długo, jak klient 
                 * jest podłączony, a zatem kiedy pętla zostanie 
                 * zakończona, należy także zakończyć połączenie 
                 * z klientem. 
                 */
                while ((line = is.readLine()) != null) {
                    char c = line.charAt(0);
                    line = line.substring(1);
                    switch (c) {
                    case ChatProtocol.CMD_LOGIN:
                        if (!ChatProtocol.isValidLoginName(line)) {
                            send(CHATMASTER_ID, "NAZWA " + line + " jest niewłaściwa");
                            log("Niewłaściwa NAZWA z " + clientIP);
                            continue;
                        }
                        login = line;
                        broadcast(CHATMASTER_ID, login + 
                            " dołączył, aktualnie jest " + 
                            clients.size() + " użytkowników");
                        break;
                    case ChatProtocol.CMD_MESG:
                        if (login == null) {
                            send(CHATMASTER_ID, "proszę się najpierw zalogować");
                            continue;
                        }
                        int where = line.indexOf(ChatProtocol.SEPARATOR);
                        String recip = line.substring(0, where);
                        String mesg = line.substring(where+1);
                        log("WIAD.: " + login + "-->" + recip + ": "+ mesg);
                        ChatHandler cl = lookup(recip);
                        if (cl == null)
                            psend(CHATMASTER_ID, recip + " nie jest zalogowany.");
                        else
                            cl.psend(login, mesg);
                        break;
                    case ChatProtocol.CMD_QUIT:
                        broadcast(CHATMASTER_ID,
                            "Do widzenia " + login + "@" + clientIP);
                        close();
                        return;        // Koniec działania.
                        
                    case ChatProtocol.CMD_BCAST:
                        if (login != null)
                            broadcast(login, line);
                        else
                            log("B<L OD " + clientIP);
                        break;
                    default:
                        log("Nieznane polecenie " + c + " z " + login + "@" + clientIP);
                    }
                }
            } catch (IOException e) {
                log("Błąd wejścia-wyjścia: " + e);
            } finally {
                // Połączenie zamknięte, zatem już kończmy.
                System.out.println(login + SEP + "Wszystko zrobione.");
                String message = "Ten komunikat nie powinien się już pokazać.";
                synchronized(clients) {
                    clients.remove(this);
                    if (clients.size() == 0) {
                        System.out.println(CHATMASTER_ID + SEP +
                            "Jestem taki samotny, że chyba się rozpłaczę...");
                    } else if (clients.size() == 1) {
                        message = 
                            "Hej, znowu rozmawiasz sam ze sobą";
                    } else {
                        message =
                            "Aktualnie jest podłączonych " + 
                            clients.size() + " użytkowników";
                    }
                }
                broadcast(CHATMASTER_ID, message);
            }
        }

        protected void close() {
            if (clientSock == null) {
                log("zamykamy nieotworzone połączenie");
                return;
            }
            try {
                clientSock.close();
                clientSock = null;
            } catch (IOException e) {
                log("Błąd podczas zamykania połączenia z " + clientIP);
            }
        }

        /** Wysłanie jednego komunikatu do użytkownika */
        public void send(String sender, String mesg) {
            pw.println(sender + SEP + mesg);
        }

        /** Wysłanie komunikatu prywatnego */
        protected void psend(String sender, String msg) {
            send("<*" + sender + "*>", msg);
        }
        
        /** Wysłanie jednego komunikatu do wszystkich użytkowników */
        public void broadcast(String sender, String mesg) {
            System.out.println("Transmisja ogólna " + sender + SEP + mesg);
            clients.forEach(sib -> {
                if (DEBUG)
                    System.out.println("Wysłanie do " + sib);
                sib.send(sender, mesg);
            });
            if (DEBUG) System.out.println("Transmisja wykonana");
        }

        protected ChatHandler lookup(String nick) {
            synchronized (clients) {
                for (ChatHandler cl : clients) {
                    if (cl.login.equals(nick))
                        return cl;
                }
            }
            return null;
        }

        /** Zapisanie obiektu w formie łańcucha znaków. */
        public String toString() {
            return "ChatHandler[" + login + "]";
        }
    }
}
// END main
