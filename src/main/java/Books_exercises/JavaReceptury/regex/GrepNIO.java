package Books_exercises.JavaReceptury.regex;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.regex.*;

/** Program przypominający grep, wykonywany z poziomu wiersza poleceń 
 * i operujący na dowolnej ilości plików tekstowych. 
 * Program korzysta z NIO but NOT LINE BASED. !!!!!!!!!!!!!!!!!!!!!


/** A grep-like program using NIO but NOT LINE BASED.
 * Pattern and file name(s) must be on command line.
 */
// BEGIN main
public class GrepNIO {
    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.err.println("Sposób użycia: GrepNIO wzorzec plik [...]");
            System.exit(1);
        }

        Pattern p=Pattern.compile(args[0]);
        for (int i=1; i<args.length; i++)
            process(p, args[i]);
    }

    static void process(Pattern pattern, String fileName) throws IOException {

        // Tworzymy obiekt FileChannel dla danego pliku.
        FileChannel fc = new FileInputStream(fileName).getChannel();

        // Odwzorowujemy zawartość pliku.
        ByteBuffer buf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

        // Dekodujemy ByteBuffer do postaci CharBuffer
        CharBuffer cbuf =
            Charset.forName("ISO-8859-1").newDecoder().decode(buf);

        Matcher m = pattern.matcher(cbuf);
        while (m.find()) {
            System.out.println(m.group(0));
        }
    }
}
// END main
