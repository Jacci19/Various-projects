package Books_exercises.JavaReceptury.jaxwsservice;

import javax.xml.ws.Endpoint;

/** Język Java 6 i późniejsze wersje pozwalają na "wdrażanie" prostych
 * usług sieciowych przy nawet w przypadku korzystania ze standardowej
 * wersji języka - Java SE. A zatem, spróbujmy uruchomić ten program!
 * Nie musimy w tym celu tworzyć struktury archiwum WAR, ani plików 
 * konfiguracyjnych, ani pakować, ani wdrażać.
 */
public class ServiceMain {
    
    public static void main(String[] args) {
        // BEGIN main
        // Tworzymy "namiastkę usługi".
        Calc impl = new Calc();
        // Uruchamiamy usługę.
        Endpoint ep =
            Endpoint.publish("http://localhost:9090/calc", impl);
        System.out.println("Usługa uruchomiona: " + ep);
        // END main

    }
}
