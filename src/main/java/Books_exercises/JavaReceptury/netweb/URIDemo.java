package Books_exercises.JavaReceptury.netweb;

import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import java.net.MalformedURLException;

// BEGIN main
public class URIDemo {
    public static void main(String[] args)
    throws URISyntaxException, MalformedURLException {

        URI u = new URI("http://www.darwinsys.com/java/../openbsd/../index.jsp");
        System.out.println("Forma początkowa: " + u);
        URI normalized = u.normalize();
        System.out.println("Po normalizacji: " + normalized);
        final URI BASE = new URI("http://www.darwinsys.com");
        System.out.println("Po relatywizacji względem adresu " + BASE + 
                ": " + BASE.relativize(u));

        // Obiekt URL jest rodzajem URI
        URL url = new URL(normalized.toString());
        System.out.println("URL: " + url);

        // Nic ważnego.
        URI uri = new URI("bean:WonderBean");
        System.out.println(uri);
    }
}
// END main
