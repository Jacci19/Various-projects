package Books_exercises.JavaReceptury.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * UnZip -- wyświetla listę zawartości lub rozpakowuje archiwum
 * JAR lub PKZIP używając w tym celu klas pakietu java.util.zip.
 * Wersja ostateczna wykonywana jest z poziomu wiersza poleceń.
 * @author    Ian Darwin, Ian@DarwinSys.com
 */
// BEGIN main
public class UnZip {
    /** Stałe określające tryb pracy: lista lub pobieranie. */
    public static enum Mode {
        LIST, 
        EXTRACT;
    };
    /** Czy pobieramy czy tylko tworzymy listę zawartości */
    protected Mode mode = Mode.LIST;

    /** Obiekt ZipFile, którego będziemy używać do odczytania
     * zawartości archiwum.
     */
    protected ZipFile zippy;

    /** Bufor wykorzystywany przy odczytywaniu lub zapisywaniu danych */
    protected byte[] b = new byte[8092];;

    /** Prosty program główny, tworzy obiekt UnZip i przy jego 
     * użyciu przetwarza wszystkie pliki .zip, których nazwy 
     * zostały zapisane w tablicy argv[].
     */
    public static void main(String[] argv) {
        UnZip u = new UnZip();

        for (int i=0; i<argv.length; i++) {
            if ("-x".equals(argv[i])) {
                u.setMode(Mode.EXTRACT);
                continue;
            }
            String candidate = argv[i];
            // System.err.println("Próbujemy ścieżki " + candidate);
            if (candidate.endsWith(".zip") ||
                candidate.endsWith(".jar"))
                    u.unZip(candidate);
            else System.err.println("Czy to nie jest plik .zip? " + candidate);
        }
        System.err.println("Wszystko gotowe!");
    }

    /** Określamy tryb działania programu (lista zawartości lub 
     * pobieranie). */
    protected void setMode(Mode m) {
        mode = m;
    }

    /** Pole przechowujące utworzony katalog. */
    protected SortedSet<String> dirsMade;

    /** Dla danego pliku ZIP, przetwarzamy każdy z jego elementów. */
    public void unZip(String fileName) {
        dirsMade = new TreeSet<String>();
        try {
            zippy = new ZipFile(fileName);
            Enumeration all = zippy.entries();
            while (all.hasMoreElements()) {
                getFile((ZipEntry)all.nextElement());
            }
        } catch (IOException err) {
            System.err.println("Błąd wejścia-wyjścia: " + err);
            return;
        }
    }

    protected boolean warnedMkDir = false;

    /** Przetwarzamy jeden plik z archiwum, dysponując jego
     * nazwą. Wyświetlamy jego nazwę lub tworzymy plik na 
     * dysku.
     */
    protected void getFile(ZipEntry e) throws IOException {
        String zipName = e.getName();
        switch (mode) {
        case EXTRACT:
            if (zipName.startsWith("/")) {
                if (!warnedMkDir)
                    System.out.println("Pomijamy ścieżki bezwzględne.");
                warnedMkDir = true;
                zipName = zipName.substring(1);
            }
            // Jeśli to katalog, to wracamy. Tworzymy katalog dla każdego
            // pliku, gdyż niektórzy twórcy programów Zip tego nie robili
            // lub tworzyli katalogi w niewłaściwych miejscach.
            if (zipName.endsWith("/")) {
                return;
            }
            // W przeciwnym razie to musi być plik; otwieramy plik do 
            // zapisu. Pobieramy ścieżkę dostępu.
            int ix = zipName.lastIndexOf('/');
            if (ix > 0) {
                String dirName = zipName.substring(0, ix);
                if (!dirsMade.contains(dirName)) {
                    File d = new File(dirName);
                    // Jeśli nazwa już istnieje i jest katalogiem, to nic 
                    // nie robimy.
                    if (!(d.exists() && d.isDirectory())) {
                        // Próbujemy utworzyć katalog, wyświetlamy 
                        // komunikat jeśli nie uda się tego zrobić.
                        System.out.println("Tworzenie katalogu: " + dirName);
                        if (!d.mkdirs()) {
                            System.err.println(
                            "Ostrzeżenie: nie udało się utworzyć katalogu " 
                            + dirName);
                        }
                        dirsMade.add(dirName);
                    }
                }
            }
            System.err.println("Tworzenie pliku " + zipName);
            FileOutputStream os = new FileOutputStream(zipName);
            InputStream  is = zippy.getInputStream(e);
            int n = 0;
            while ((n = is.read(b)) >0)
                os.write(b, 0, n);
            is.close();
            os.close();
            break;
        case LIST:
            // Nie pobieramy - tworzymy listę zawartości
            if (e.isDirectory()) {
                System.out.println("Katalog " + zipName);
            } else {
                System.out.println("Plik " + zipName);
            }
            break;
        default:
            throw new IllegalStateException("nieprawidłowa wartość trybu (" + mode + ")");
        }
    }
}
// END main
