package Books_exercises.JavaReceptury.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

// BEGIN main
public class InetAddrDemo {
    public static void main(String[] args) throws IOException {
        String hostName = "www.darwinsys.com";
        String ipNumber = "8.8.8.8"; // powszechnie znany serwer DNS 
                                     

        // Odnajdujemy komputer na podstawie nazwy.
        System.out.println("Komputer " + hostName + " ma adres " +
            InetAddress.getByName(hostName).getHostAddress());

        // Odnajdujemy komputer na podstawie adresu.
        System.out.println("Komputer o adresie " + ipNumber + 
            " nosi nazwę " + InetAddress.getByName(ipNumber).getHostName());

        // Odnajdujemy adres skojarzony z nazwą localhost.
        final InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("Adres localhost to: " + localHost);

        // Sposób uzyskiwania obiektu InetAddress przy użyciu
        // otworzonego gniazda (Socket).
        String someServerName = "www.google.com";
        // assuming there's a web server on the named server:
        // Zakładamy, że na komputerze o podanej nazwie działa 
        // serwer WWW:
        Socket theSocket = new Socket(someServerName, 80);    
        InetAddress remote = theSocket.getInetAddress();
        System.out.printf("Adres InetAddress komputera %s to %s%n",
            someServerName, remote);
    }
}
// END main
