package Books_exercises.JavaReceptury.io;

import java.io.*;

/**
 * Zapis danych w postaci binarnej.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class WriteBinary {
    public static void main(String[] argv) throws IOException {
        int i = 42;
        double d = Math.PI;
        String FILENAME = "binary.dat";
        DataOutputStream os = new DataOutputStream(
            new FileOutputStream(FILENAME));
        os.writeInt(i);
        os.writeDouble(d);
        os.close();
        System.out.println("Zapisujemy " + i + ", " + d + " do pliku " + FILENAME);
    }
}
// END main
