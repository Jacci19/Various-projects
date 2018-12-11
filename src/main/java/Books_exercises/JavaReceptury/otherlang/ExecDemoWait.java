package Books_exercises.JavaReceptury.otherlang;

import java.io.*;

/**
 * ExecDemo pokazuje jak wykonać zewnętrzny program, odczytać wygenerowane
 * przez niego wyniki i wyświetlić jego kod wynikowy.
 */
public class ExecDemoWait {

    public static void main(String argv[]) throws IOException {

        // BEGIN main
        // Obiekt Runtime udostępnia metody pozwalające na wymianę 
        // informacji z systemem operacyjnym.
        Runtime r = Runtime.getRuntime();
        Process p;         // Obiekt Process "śledzi" jeden zewnętrzny proces.
        BufferedReader is; // Czytelnik przechwytujący wyniki działania procesu.
        String line;
        
        // argv[0] zawiera nazwę programu, jaki należy wykonać; pozostałe
        // elementy argv zawierają argumenty, jakie należy przekazać w 
        // wywołaniu procesu zewnętrznego. To wystarczy do wywołania 
        // metody exec wymagającej podania tablicy łańcuchów znaków.
        p = r.exec(argv);

        System.out.println("Metoda main po wywołaniu programu zewnętrznego");

        // Metoda getInputStream zwraca InputStream (strumień wejściowy) 
        // skojarzony ze standardowym strumieniem wyjściowym procesu zewnętrznego.
        // Można go wykorzystać do stworzenia obiektu BufferedReader i odczytania
        // wierszy wyników generowanych przez program zewnętrzny przy użyciu 
        // metody readLine().
        is = new BufferedReader(new InputStreamReader(p.getInputStream()));

        while ((line = is.readLine()) != null)
            System.out.println(line);
        
        System.out.println("Metoda main po odczytaniu wyników");
        System.out.flush();
        try {
            p.waitFor();    // Czekamy na zakończenie procesu.
        } catch (InterruptedException e) {
            System.err.println(e);    // "Niemożliwe".
            return;
        }
        System.err.println("Proces zakończony, kod wynikowy: " + p.exitValue());
        // END main
    }
}
