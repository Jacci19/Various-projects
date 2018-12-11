package Books_exercises.JavaReceptury.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Program przedstawia kilka zastoswan klasy NetworkInterface.
 */
// BEGIN main
public class NetworkInterfaceDemo {
    public static void main(String[] a) throws IOException {
        Enumeration list = NetworkInterface.getNetworkInterfaces();
        while (list.hasMoreElements()) {
            // Pobieramy jeden interfejs - NetworkInterface
            NetworkInterface iface = (NetworkInterface) list.nextElement();
            // Wyświetlamy jego nazwę.
            System.out.println(iface.getDisplayName());
            Enumeration addrs = iface.getInetAddresses();
            // Oraz jego adresy
            while (addrs.hasMoreElements()) {
                InetAddress addr = (InetAddress) addrs.nextElement();
                System.out.println(addr);
            }

        }
        // Próbujemy pobrać interfejs dla adresu lokalnego (tego komputera)
        InetAddress destAddr = InetAddress.getByName("laptop");
        try {
            NetworkInterface dest = NetworkInterface.getByInetAddress(destAddr);
            System.out.println("Komputer " + destAddr + " ma adres " + dest);
        } catch (SocketException ex) {
            System.err.println("Nie można pobrać adresu komputera " + destAddr);
        }
    }
}
// END main
