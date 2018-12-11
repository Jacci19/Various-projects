package Books_exercises.JavaReceptury.javacomm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Enumeration;

import javax.comm.CommPort;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;

// BEGIN main
/**
 * Program otwiera port szeregowy korzystając z Java Communications API.
 * @author    Ian F. Darwin, http://www.darwinsys.com/
 */
public class CommPortSimple {
    private static final String HELLO = "Witaj?";
    /** Jak długo należy czekać na zakończenie operacji otwierania. */
    public static final int TIMEOUTSECONDS = 30;
    /** Szybkość transmisji (w bodach). */
    public static final int BAUD = 19200;
    /** Strumień wejściowy. */
    protected BufferedReader is;
    /** Strumień wyjściowy. */
    protected PrintStream os;
    /** Wybrany obiekt CommPortIdentifier. */
    CommPortIdentifier thePortID;
    /** Obiekt wybranego portu komunikacyjnego. */
    CommPort thePort;

    public static void main(String[] argv) throws Exception {
        
        if (argv.length != 1) {
            System.err.println("Sposób użycia: CommPortSimple nazwa");
            System.exit(1);
        }

        new CommPortSimple(argv[0]).holdConversation();

        System.exit(0);
    }

    /* Konstruktor. */
    public CommPortSimple(String devName) throws Exception {

        @SuppressWarnings("unchecked")
        Enumeration<CommPortIdentifier> pList = 
                CommPortIdentifier.getPortIdentifiers();

        // Przeglądamy listę, poszukując podanej nazwy.
        CommPortIdentifier cpi = null;
        boolean atLeastOneSerialPresent = false;
        while (pList.hasMoreElements()) {
            CommPortIdentifier c = pList.nextElement();
            if (c.getPortType() !=CommPortIdentifier.PORT_SERIAL) {
                System.err.println("Nie znaleziono portów szeregowych: " + c.getName());
                continue;
            }
            if (devName.equals(c.getName())) {
                cpi = c;
                break; // Znaleźliśmy!
            }
            atLeastOneSerialPresent = true;
            System.out.println("Nie znaleziono portu: " + c.getName());
        }
        if (cpi == null) {
            System.err.println("Nie znaleziono portu szeregowego '" + devName + "'.");
            if (atLeastOneSerialPresent)
                System.err.println("Spróbuj jeszcze raz, wybierając jedną z wyświetlonych nazw.");
            else
                System.err.println("Nie znaleziono ŻADNYCH portów szeregowych!");
            System.exit(1);
        }

        thePort = cpi.open("JavaCook DataComm",
                TIMEOUTSECONDS * 1000);
        SerialPort myPort = (SerialPort) thePort;

        // Konfigurujemy port szeregowy.
        myPort.setSerialPortParams(BAUD, SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

        // Pobieramy strumienie wejściowy i wyjściowy.
        is = new BufferedReader(new InputStreamReader(thePort.getInputStream()));
        os = new PrintStream(thePort.getOutputStream(), true);
    }

    /** Metoda odpowiada za przeprowadzenie konwersacji - w tym przypadku
     * jest ona naprawdę *bardzo* prosta. */
    protected void holdConversation() throws IOException {

        System.out.println("Jestem gotowy do zapisu i odczytu z użyciem portu.");

        os.println(HELLO);
        String response = is.readLine();
        
        System.out.printf("Przesłałem %s i otrzymałem odpowiedź %s%n", 
                HELLO, response);

        // Porządki przed zakończeniem.
        if (is != null)
            is.close();
        if (os != null)
            os.close();
    }
}
// END main
