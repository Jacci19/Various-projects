package Books_exercises.JavaReceptury.network;

import java.net.*;
import java.io.*;

/**
 * Telnet - nawiązuje połączenie z podanym komputerem i usługą.
 * Programu nie można porównać z normalnym klientem usługi 
 * Telnet, jednak przedstawia on ogólną ideę jak takie 
 * programy należy tworzyć.

 */
// BEGIN main
public class Telnet {
    String host;
    int portNum;
    public static void main(String[] argv) {
        new Telnet().talkTo(argv);
    }
    private void talkTo(String av[]) {
        if (av.length >= 1)
            host = av[0];
        else
            host = "localhost";
        if (av.length >= 2)
            portNum = Integer.parseInt(av[1]);
        else portNum = 23;
        System.out.println("Komputer " + host + "; port " + portNum);
        try {
            Socket s = new Socket(host, portNum);

            // Kojarzymy wyniki zdalnego programu ze strumieniem stdout
            new Pipe(s.getInputStream(), System.out).start();

            // Kojrzymy stdin z wejściem zdalnego programu
            new Pipe(System.in, s.getOutputStream()).start();

        } catch(IOException e) {
            System.out.println(e);
            return;
        }
        System.out.println("Połączenie nawiązano.");
    }

    /* Ta klasa obsługuje jeden kierunek transmisji dupleksowej 
     * (dwukierunkowej). Pracuje w trybie obsługi wierszy.
     * Wykorzystywane są strumienie, a nie pisarze.
     */
    class Pipe extends Thread {
        BufferedReader is;
        PrintStream os;

        /** Tworzymy obiekt Pipe odczytujący ze strumienia os 
         * i zapisujący w strumieniu os. */
        Pipe(InputStream is, OutputStream os) {
            this.is = new BufferedReader(new InputStreamReader(is));
            this.os = new PrintStream(os);
        }

        /** Metoda obsługująca transmisję. */
        public void run() {
            String line;
            try {
                while ((line = is.readLine()) != null) {
                    os.print(line);
                    os.print("\r\n");
                    os.flush();
                }
            } catch(IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
// END main
