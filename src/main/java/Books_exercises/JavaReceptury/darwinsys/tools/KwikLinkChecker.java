package Books_exercises.JavaReceptury.darwinsys.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Bardzdo prosty program do sprawdzania odnośników. 
 * Typowy sposób użycia: 
 *     java -cp darwinsys-api.jar com.darwinsys.net.KwikLinkChecker plik...
 * @author Ian Darwin
 */
public class KwikLinkChecker {
    
    static boolean verbose;

    public static void main(String[] args) {
        KwikLinkChecker checker = new KwikLinkChecker();
        for (String arg : args) {
            if (arg.equals("-v")) {
                verbose = true;
                continue;
            }
            LinkStatus stat = checker.check(arg);
            if (verbose || !stat.ok)
                System.out.println(stat.message);
        }
    }
    
    // BEGIN main
    /**
     * Metoda sprawdza jeden odnośnik HTTP. Nie działa rekurencyjnie.
     * Zwraca obiekt LinkStatus z logicznym polem określającym status
     * wykonanego sprawdzenia i nazwą pliku lub komentrzem o błędzie 
     * w polu message. Końcówka tej metody jest jednym z niewielu 
     * miejsc, w których konieczna jest cała sekwencja różnych 
     * klauzul catch, niezbędnych do prawidłowego działania programu.
     */
    public LinkStatus check(String urlString) {
        URL url;
        HttpURLConnection conn = null;
        HttpURLConnection.setFollowRedirects(false);
        try {
            url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            switch (conn.getResponseCode()) {
            case 200:
                return new LinkStatus(true, urlString);
            case 403:
                return new LinkStatus(false,"403: " + urlString );
            case 404:
                return new LinkStatus(false,"404: " + urlString );
            }
            conn.getInputStream();
            return new LinkStatus(true, urlString);
        } catch (IllegalArgumentException | MalformedURLException e) {
            // JDK firmy Oracle zgłasza wyjątek IllegalArgumentException,
            // jeśli na podstawie adresu URL nie uda się określić 
            // komputera docelowego 
            return new LinkStatus(false, "Nieprawidłowy adres URL: " + urlString);
        } catch (UnknownHostException e) {
            return new LinkStatus(false, "Nieprawidłowy lub nieaktywny komputer: " + urlString);
        } catch (FileNotFoundException e) {
            return new LinkStatus(false,"NIE ODNALEZIONO (404) " + urlString);
        } catch (ConnectException e) {
            return new LinkStatus(false, "Serwer nie działa: " + urlString);
        } catch (SocketException e) {
            return new LinkStatus(false, e + ": " + urlString);
        } catch (IOException e) {
            return new LinkStatus(false, e.toString()); // Dołączamy URL.
        } catch (Exception e) {
            return new LinkStatus(false, "Nieoczekiwany wyjątek! " + e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
    // END main
}
