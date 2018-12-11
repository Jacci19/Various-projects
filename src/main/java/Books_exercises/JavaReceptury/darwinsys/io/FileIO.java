// BEGIN main
package Books_exercises.JavaReceptury.darwinsys.io;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.darwinsys.lang.StringUtil;
import com.darwinsys.util.Debug;

/**
 * Niektóre proste operacje wejścia-wyjścia operujące  
 * na plikach i zaimplementowane w Javie.
 * Wszystkie metody są statyczne, gdyż nie ma konieczności
 * zachowywania stanu.
 */
public class FileIO {
    
    /** Wielkość używanego bloku danych. */
    protected static final int BLKSIZ = 16384;

    /** Łańcuch znaków do kodowania w UTF-8; skopiowany bezpośrednio 
     * z klasy StringUtil. */
    public static final String ENCODING_UTF_8 = StringUtil.ENCODING_UTF_8;
    
    /** Nikt nie musi tworzyć obiektów tej klasy, wszystkie jej metody 
     * są statyczne.
     */
    private FileIO() {
        // Tu nic nie robimy.
    }

    /** Kopiuje zawartość pliku o podanej nazwie do drugiego pliku 
     * o podanej nazwie.
     */
    public static void copyFile(String inName, String outName)
    throws FileNotFoundException, IOException {
        BufferedInputStream is = null;
        BufferedOutputStream os = null;
        try {
            is = new BufferedInputStream(new FileInputStream(inName));
            os = new BufferedOutputStream(new FileOutputStream(outName));
            copyFile(is, os, false);
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    /** Kopiujemy plik z jednego otworzonego obiektu InputStream do 
     * drugiego obiektu InputStream.
     */
    public static void copyFile(InputStream is, OutputStream os, boolean close) 
    throws IOException {
        byte[] b = new byte[BLKSIZ];            // Bajt odczytany z pliku.
        int i;
        while ((i = is.read(b)) != -1) {
            os.write(b, 0, i);
        }
        is.close();
        if (close)
            os.close();
    }

    /** Kopiujemy plik z otwartego obiektu Reader do obiektu Writer. */
    public static void copyFile(Reader is, Writer os, boolean close) 
    throws IOException {
        int b;                // Bajt odczytany z pliku.

        while ((b = is.read()) != -1) {
            os.write(b);
        }
        is.close();
        if (close)
            os.close();
    }

    /** Kopiujemy plik o podanej nazwie do obiektu PrintWriter. */
    public static void copyFile(String inName, PrintWriter pw, boolean close) 
    throws FileNotFoundException, IOException {
        BufferedReader ir = new BufferedReader(new FileReader(inName));
        copyFile(ir, pw, close);
    }
    
    /**
     * Kopiuje plik do podanego katalogu. Zarówno plik, jak i katalog 
     * są reprezentowane przez obiekty File określające ich nazwy.
     * @param file Obiekt File reprezentujący plik źródłowy, to musi 
     *    być jeden plik.
     * @param target Obiekt File reprezentujący katalog docelowy, musi 
     *    reprezentować katalog.
     * @throws IOException
     */
    public static void copyFile(File file, File target) throws IOException {
        if (!file.exists() || !file.isFile() || !(file.canRead())) {
            throw new IOException(file + " nie jest plikiem, który można odczytać!");
        }
        File dest = target;
        if (target.isDirectory()) {
            dest = new File(dest, file.getName());
        }
        InputStream is = null;
        OutputStream os  = null;
        try {
            is = new FileInputStream(file);
            os = new FileOutputStream(dest);
            int count = 0;        // Ilość bajtów.
            byte[] b = new byte[BLKSIZ];    // Bajty odczytane z pliku.
            while ((count = is.read(b)) != -1) {
                os.write(b, 0, count);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    /** Kopiujemy dane z pliku o podanej nazwie do drugiego pliku
     * o podanej nazwie inną metodą. Zgodnie z tym, co sugeruje 
     * nazwa, wykorzystujemy własny bufor, zamiast posługiwać 
     * się buforem rezerwowanym przez klasę BufferedReader.
     */
    public void copyFileBuffered(String inName, String outName) throws
            FileNotFoundException, IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(inName);
            os = new FileOutputStream(outName);
            int count = 0;        // Ilość bajtów.
            byte[] b = new byte[BLKSIZ];    // Bajty odczytane z pliku.
            while ((count = is.read(b)) != -1) {
                os.write(b, 0, count);
            }
        } finally {
            if (is != null) {
                is.close();                
            }
            if (os != null) {
                os.close();
            }
        }
    }
    
    /**
     * Kopiujemy wszystkie obiekty znalezione w drzewie katalogu "fromDir"
     * w odpowiednie miejsca drzewa katalogu "toDir".
     * @param fromDir Katalog źródłowy.
     * @param toDir Katalog docelowy.
     * @throws IOException
     */
    public static void copyRecursively(File fromDir, File toDir, boolean create)
        throws IOException {

        Debug.printf("fileio", "copyRecursively(%s, %s%n", fromDir, toDir);
        if (!fromDir.exists()) {
            throw new IOException(
                String.format("Katalog źródłowy %s nie istnieje.", fromDir));
        }
        if (create) {
            toDir.mkdirs();
        } else if (!toDir.exists()) {
            throw new IOException(
                String.format("Katalog docelowy %s musi istnieć.", toDir));
        }
        for (File src : fromDir.listFiles()) {
            if (src.isDirectory()) {
                File destSubDir = new File(toDir, src.getName());
                copyRecursively(src, destSubDir, true);
            } else if (src.isFile()) {
                copyFile(src, toDir);
            } else {
                System.err.println(
                    String.format("Ostrzeżenie: %s nie jest ani plikiem, ani katalogiem.", src));
            }
        }
    }
    
    public static void copyRecursively(File fromDir, File toDir) throws IOException {
        copyRecursively(fromDir, toDir, false);
    }
    
    public static void deleteRecursively(File startDir) throws IOException {
        
        String startDirPath = startDir.getCanonicalPath();
        
        // Przebieg pierwszy - rekurencyjne usuwanie.
        for (File f : startDir.listFiles()) {
            if (!f.getCanonicalPath().startsWith(startDirPath)) {
                throw new IOException("Próba wyjścia z katalogu " + startDir);
            }
            if (f.isDirectory()) {
                deleteRecursively(f);
            }
        }
        // Przebieg drugi - usuwamy to, co zostało: pliki i (puste) 
        // katalogi.
        for (File f : startDir.listFiles()) {
            f.delete();
            if (f.exists()) {
                System.err.println(f + " nie zosał usunięty!");
            }
        }
        
        // Przebieg trzeci - usuwamy (teraz już pusty) katalog początkowy
        startDir.delete();
    }
    
    /**
     * Kopiujemy całe drzewo plików do katalogu na podstawie przekazanych 
     * obiektów File reprezentujących pliki. 
     * @param base Plik źródłowy, to musi być pojedynczy plik.
     * @param startingDir Katalog początkowy.
     * @param toDir Obiekt File określający miejsce docelowe, może 
     *     reprezentować katalog lub plik.
     * @throws IOException 
     */
    public static void copyRecursively(JarFile base, JarEntry startingDir,
            File toDir) throws IOException {
        if (!startingDir.isDirectory()) {
            throw new IOException(String.format(
                    "Punkt początkowy %s nie jest katalogiem.", startingDir));
        }
        if (!toDir.exists()) {
            throw new IOException(String.format(
                    "Katalog docelowy %s musi istnieć.", toDir));
        }
        Enumeration<JarEntry> all = base.entries();
        while (all.hasMoreElements()) {
            JarEntry file = all.nextElement();
            // XXX upewnić się, że plik odpowiada początkowemu 
            // katalogowi.
            if (file.isDirectory()) {
                copyRecursively(base, file, new File(toDir, file.getName()));
            } else {
                InputStream is = null;
                OutputStream os = null;
                try {
                    is = base.getInputStream(file);
                    os = new FileOutputStream(new File(toDir, file
                            .getName()));
                    copyFile(is, os, false);
                } finally {
                    if (os != null)
                        os.close();
                    if (is != null)
                        is.close();
                }
            }
        }
    }

    // Metody do odczytu zawartści plików.
    /** Otwieramy plik i odczytujemy jego pierwszy wiersz. */
    public static String readLine(String inName)
    throws FileNotFoundException, IOException {
        BufferedReader is = null;
        try {
        is = new BufferedReader(new FileReader(inName));
        String line = null;
        line = is.readLine();
        is.close();
        return line;
        } finally {
            if (is != null) 
                is.close();
        }
    }
    
    /** Odczytujemy całą zawartość pliku używając klasy Reader 
     * i zapisujemy ją w obiekcie String; oczywiście
     * obiektu Reader należy używać wyłącznie w przypadku odczytu 
     * danych z plików tekstowych - proszę nie używać tej metody do 
     * odczytu na przykład plików JPEG.
     */
    public static String readerToString(Reader is) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] b = new char[BLKSIZ];
        int n;

        // Odczytujemy blok. Jeśli są w nim jakieś znaki, dodajemy je.
        while ((n = is.read(b)) > 0) {
            sb.append(b, 0, n);
        }

        // Tworzymy tylko jeden obiekt String.
        return sb.toString();
    }

    /** Zapisujemy zawartość obiektu Stream w obiekcie String. */
    public static String inputStreamToString(InputStream is)
    throws IOException {
        return readerToString(new InputStreamReader(is));
    }

    public static String readAsString(String filename) throws IOException {
        return readerToString(new FileReader(filename));
    }
    
    /** Zapisujemy String jako zawartość pliku o podanej nazwie. */
    public static void stringToFile(String text, String fileName)
    throws IOException {
        BufferedWriter os = new BufferedWriter(new FileWriter(fileName));
        os.write(text);
        os.flush();
        os.close();
    }

    /** Tworzymy obiekt BufferedReader na podstawie nazwy pliku. */
    public static BufferedReader openFile(String fileName)
    throws IOException {
        return new BufferedReader(new FileReader(fileName));
    }
}
// END main
