package Books_exercises.JavaReceptury.printjdk14printservice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.SimpleDoc;
import javax.print.StreamPrintService;
import javax.print.StreamPrintServiceFactory;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

// BEGIN main
/** Program demonstrujący odnajdywanie usługi PrintService 
 * i drukowanie pliku przy jej użyciu. */
public class PrintPostScript {
    
    private static final String INPUT_FILE_NAME = "/demo.txt";

    public static void main(String[] args) throws IOException, PrintException {
        new PrintPostScript().print();
    }
    
    public void print() throws IOException, PrintException {
        
        DocFlavor inputFlavor = DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_8;
        
        // Odnajdujemy obiekt StreamPrintServiceFactory, który będzie
        // w stanie skonwertować właściwe dane wejściowe na 
        // odpowiedni format wyjściowy.
        StreamPrintServiceFactory[] psfactories =
            StreamPrintServiceFactory.lookupStreamPrintServiceFactories(
                inputFlavor, DocFlavor.BYTE_ARRAY.POSTSCRIPT.getMimeType());
        if (psfactories.length == 0) {
            System.err.println("Nie udało się znaleźć obiektu " +
                  "StreamPrintFactory do wykonania tego zadania!");
        }
        StreamPrintService printService = 
            psfactories[0].getPrintService(new FileOutputStream("demo.ps"));
        PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
        attrs.add(OrientationRequested.LANDSCAPE);
        attrs.add(MediaSizeName.NA_LETTER);
        attrs.add(new Copies(1));
        attrs.add(new JobName(INPUT_FILE_NAME, null));

        InputStream is = getClass().getResourceAsStream(INPUT_FILE_NAME);
        if (is == null) {
            throw new NullPointerException(
                "Pusty strumień wejściowy: nie znaleziono pliku?");
        }
        Doc doc = new SimpleDoc(is, inputFlavor, null);
        
        DocPrintJob printJob = printService.createPrintJob();
        printJob.print(doc, attrs);
    }
}
// END main
