package Books_exercises.JavaReceptury.network;

import java.io.*;
import java.net.*;

/**
 * EchoServer - program tworzy gniazdo serwera i zapisuje 
 * w nim dane.
 *
 * @author  Ian Darwin
 * Copyright (c) 1995, 1996, 1997, 2000 Ian F. Darwin
 */
// BEGIN main
public class EchoServer {
    /** Gniazdo używane przez serwer do komunikacji z klientem. */
    protected ServerSocket sock;
    /** Domyślny numer portu. */
    public final static int ECHOPORT = 7;
    /** Flaga kontrolująca testowanie. */
    protected boolean debug = true;

    /** main: stworzenie i uruchomienie. */
    public static void main(String[] args) {
        int p = ECHOPORT;
        if (args.length == 1) {
            try {
                p = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Sposób użycia: EchoServer [port#]");
                System.exit(1);
            }
        }
        new EchoServer(p).handle();
    }

    /** Konstruktor, tworzy serwer EchoServer używający podanego portu. */
    public EchoServer(int port) {
        try {
            sock = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Błąd wejścia-wyjścia podczas konfiguracji.");
            System.err.println(e);
            System.exit(1);
        }
    }

    /** Metoda obsługująca połączenie. */
    protected void handle() {
        Socket ios = null;
        BufferedReader is = null;
        PrintWriter os = null;
        while (true) {
            try {
                System.out.println("Czekamy na klienta...");
                ios = sock.accept();
                System.err.println("Odebrano połączenie od " +
                    ios.getInetAddress().getHostName());
                is = new BufferedReader(
                    new InputStreamReader(ios.getInputStream(), "8859_1"));
                os = new PrintWriter(
                        new OutputStreamWriter(
                            ios.getOutputStream(), "8859_1"), true);
                String echoLine;
                while ((echoLine = is.readLine()) != null) {
                    System.err.println("Odczytano " + echoLine);
                    os.print(echoLine + "\r\n");
                    os.flush();
                    System.err.println("Zapisano " + echoLine);
                }
                System.err.println("Gotowe!");
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                try {
                    if (is != null)
                        is.close();
                    if (os != null)
                        os.close();
                    if (ios != null)
                        ios.close();
                } catch (IOException e) {
                    // Te przypadki są rzadkie, lecz mogą wskazywać, 
                    // że klient został przedwcześnie zamknięty, 
                    // dysk został wypełniony, co nie zostało wykryte 
                    // aż do chwili zamknięcia programu, i tak dalej.
                    System.err.println("Błąd wejścia-wyjścia podczas zamykania");
                }
            }
        }
        /*NIEOSIĄGANE*/
    }
}
// END main
