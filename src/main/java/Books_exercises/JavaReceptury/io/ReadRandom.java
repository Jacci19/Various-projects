package Books_exercises.JavaReceptury.io;

import java.io.*;

/**
 * Program odczytuje plik zawierający przesunięcie oraz łańcuch
 * znaków zapisany w tym położeniu.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class ReadRandom {
    final static String FILENAME = "random.dat";
    protected String fileName;
    protected RandomAccessFile seeker;

    public static void main(String[] argv) throws IOException {
        ReadRandom r = new ReadRandom(FILENAME);

        System.out.println("Przesunięcie ma wartość: " + r.readOffset());
        System.out.println("Treść wiadomości to: \"" + r.readMessage() + "\".");
    }

    /** Konstruktor: zapisuje nazwę pliku i tworzy obiekt RandomAccessFile. */
    public ReadRandom(String fname) throws IOException {
        fileName = fname;
        seeker = new RandomAccessFile(fname, "r");
    }

    /** Odczytuje przesunięcie zapisane na samym początku pliku. */
    public int readOffset() throws IOException {
        seeker.seek(0);             // Przechodzimy na początek pliku.
        return seeker.readInt();    // Odczytujemy przesunięcie.
    }

    /** Odczytuje komunikat zapisany w podanym miejscu pliku. */
    public String readMessage() throws IOException {
        seeker.seek(readOffset());  // Przechodzimy do odpowiedniego miejsca   
        return seeker.readLine();   // i odczytujemy String.
    }
}
// END main
