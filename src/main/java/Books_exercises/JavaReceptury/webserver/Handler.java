package Books_exercises.JavaReceptury.webserver;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

/** Klasa wykorzystywana przez Httpd w celu obsługi połączenia.
 * Obiekty tej klasy dysponują jedynie gniazdem (obiektem Socket)
 * z którego odczytywane jest żądanie HTTP. Z żądania jest
 * odczytywana nazwa pliku, który następnie zostaje pobrany
 * (zapisany w obiekcie Hashtable, na wypadek gdyby zostało 
 * odebrane kolejne żądanie dotyczące tego pliku) i zapisany 
 * w strumieniu wyjściowym gniazda.
 * <p>
 * DO ZROBIENIA: podzielić klasę na dwie części - ogólną i "FileServlet",
 * z których pierwsza będzie obsługiwana przez klasę potomną klasy HttpServlet 
 * a druga przez klasę potomną klasy FileServlet.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: Handler.java,v 1.11 2002/03/29 15:00:49 ian Exp $
 */
public class Handler {
    /** Gniazdo (Socket), z którego odczytujemy i w którym zapisujemy dane. */
    protected Socket clntSock;
    /** Strumień wejściowy, z przeglądarki */
    protected BufferedReader is;
    /** Strumień wyjściowy, do przeglądarki */
    protected PrintStream os;
    /** Program główny */
    protected Httpd parent;
    /** Nazwa domyślna w danym katalogu. */
    protected final static String DEF_NAME = "/index.html";

    /** Obiekt Hashtable służący do przechowywania adresów URL i stron 
     * w swoistej pamięci podręcznej. Statyczny, współużytkowany przez 
     * wszystkie obiekty klasy Handler (jeden taki obiekt służy do obsługi
     * jednego żądania; prawdopodobnie rozwiązanie to jest nieefektywne 
     * lecz za to proste. Należałoby wykorzystać pulę wątków - ThreadPool).
     * Warto pamiętać, że metody klasy Hashtable *są* synchronizowane.
     */
    private static Hashtable h = new Hashtable();

    /** Kolejne numery używane do identyfikacji wątków */
    private static int n = 0;

    /** Tworzymy obiekt Handler */
    Handler(Httpd prnt) {
        parent = prnt;
        // Pierwszy raz, dodajemy pusty Handler.
        if (h.size() == 0) {
            h.put("", "<HTML><BODY><B>Nieznany błąd serwera".getBytes());
        }
    }

    protected static final int RQ_INVALID = 0, RQ_GET = 1, RQ_HEAD = 2,
        RQ_POST = 3; 

    public void process(Socket sock) {
        clntSock = sock;
        String request;        // To co przesłała przeglądarka.
        int methodType = RQ_INVALID;
        try {
            System.out.println("Odebrano połączenie z " +
                clntSock.getInetAddress());
            is = new BufferedReader(new InputStreamReader(
                clntSock.getInputStream()));
            // Musimy to zrobić, zanim będzie można wywołać errorResponse!
            os = new PrintStream(clntSock.getOutputStream());

            request = is.readLine();
            if (request == null || request.length() == 0) {
                // Dalsze działanie nie ma sensu: gniazdo nieaktywne, 
                // nawet jeśli będziemy co sił krzyczeć w cyberprzestrzeń, 
                // i tak nikt nas nie usłyszy... Można by to zapisywać 
                // w dzienniku.
                return;
            }

            // Wykorzystujemy obiekt StringTokenizer, aby podzielić 
            // żądania na trzy części: metodę HTTP, nazwę zasobu oraz 
            // wersję protokołu HTTP
            StringTokenizer st = new StringTokenizer(request);
            if (st.countTokens() != 3) {
                errorResponse(444, "Dane wejściowe nie nadają się do przetworzenia " + request);
                return;
            }
            String rqCode = st.nextToken();
            String rqName = st.nextToken();
            String rqHttpVer = st.nextToken();
            System.out.println("Żądanie: Polecenie " + rqCode +
                    ", plik " + rqName + ", wersja " + rqHttpVer);

            // Teraz obsługujemy nagłówki, włącznie z pustym wierszem 
            // poprzedzającym treść żądania; dzięki temu treść będzie 
            // można odczytać bezpośrednio (jeśli obsługujemy żądanie POST).
            HashMap map = new HashMap();
            String hdrLine;
            while ((hdrLine = is.readLine()) != null &&
                    hdrLine.length() != 0) {
                    int ix;
                    if ((ix=hdrLine.indexOf(':')) != -1) {
                        String hdrName = hdrLine.substring(0, ix);
                        String hdrValue = hdrLine.substring(ix+1).trim();
                        System.out.println("hdr("+hdrName+","+hdrValue+")");
                        map.put(hdrName, hdrValue);
                    } else {
                        System.err.println("NIEWŁAŚCIWY NAGŁÓWEK: " + hdrLine);
                    }
            }

            // Sprawdzamy, czy rqCode to GET, HEAD czy też ...
            if ("get".equalsIgnoreCase(rqCode))
                  methodType = RQ_GET;
            else if ("head".equalsIgnoreCase(rqCode))
                  methodType = RQ_HEAD;
            else if ("post".equalsIgnoreCase(rqCode))
                  methodType = RQ_POST;
            else {
                errorResponse(400, "niewłaściwa metoda: " + rqCode);
                return;
            }

            // Czasami paranoiczne rozwiązanie nie jest złe...
            if (rqName.indexOf("..") != -1) {
                errorResponse(404, "nie można odnaleźć: " + rqName);
                return;
            }
                
            // Kiedyś, w przyszłości: new MyRequest(clntSock, rqName, methodType);
            // new MyResponse(clntSock, os);

            // if (isServlet(rqName)) [
            //     doServlet(rqName, methodType, map);
            // else
                doFile(rqName, methodType == RQ_HEAD, os /*, map */);
            os.flush();
            clntSock.close();
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
        System.out.println("KONIEC OBSŁUGI ŻĄDANIA");
    }

    /** Obsługa jednego żądania dotyczącego pliku */
    void doFile(String rqName, boolean headerOnly, PrintStream os) throws IOException {
        File f;
        byte[] content = null;
        Object o = h.get(rqName);
        if (o != null && o instanceof byte[]) {
            content = (byte[])o;
            System.out.println("Pobieramy plik z pamięci podręcznej " + rqName);
            sendFile(rqName, headerOnly, content, os);
        } else if ((f = new File(parent.getRootDir() + rqName)).isDirectory()) {
            // Katalog zawierający plik index.html? Przetwarzamy go.
            File index = new File(f, DEF_NAME);
            if (index.isFile()) {
                doFile(rqName + DEF_NAME, index, headerOnly, os);
                return;
            }
            else {
                // Katalog? Nie zapisujemy w pamięci podręcznej; 
                // zawsze tworzymy listę zawartości.
                System.out.println("ODNALEZIONO KATALOG");
                doDirList(rqName, f, headerOnly, os);
                sendEnd();
            }
        } else if (f.canRead()) {
            // ZWYCZAJNY PLIK
            doFile(rqName, f, headerOnly, os);
        }
        else {
            errorResponse(404, "Nie odnaleziono pliku");
        }
    }

    void doDirList(String rqName, File dir, boolean justAHead, PrintStream os) {
        os.println("HTTP/1.0 200 Nie odnaleziono katalogu");
        os.println("Content-type: text/html");
        os.println("Date: " + new Date().toString());
        os.println("");
        if (justAHead)
            return;
        os.println("<HTML>");
        os.println("<TITLE>Zawartość katalogu " + rqName + "</TITLE>");
        os.println("<H1>Zawartość katalogu " + rqName + "</H1>");
        String fl[] = dir.list();
        Arrays.sort(fl);
        for (int i=0; i<fl.length; i++)
            os.println("<BR><A HREF=\"" + fl[i] + "\">" +
            "<IMG ALIGN=absbottom BORDER=0 SRC=\"internal-gopher-unknown\">" +
            ' ' + fl[i] + "</A>");
    }

    /** Wysyła jeden plik przekazany jako obiekt File. */
    void doFile(String rqName, File f, boolean headerOnly, PrintStream os) throws IOException {
        System.out.println("Odczytuje plik " + rqName);
        InputStream in = new FileInputStream(f);
        byte c_content[] = new byte[(int)f.length()];
        // Jeden odczyt całego pliku powinien być szybszy.
        int n = in.read(c_content);
        h.put(rqName, c_content);
        sendFile(rqName, headerOnly, c_content, os);
        in.close();
    }

    /** Wysyła jeden plik, dysponując jego nazwą i zawartością.
     * @param justHead - jeśli true - metoda wysyła nagłówki i kończy działanie.
     */
    void sendFile(String fname, boolean justHead,
        byte[] content, PrintStream os) throws IOException {
        os.println("HTTP/1.0 200 Oto plik");
        os.println("Content-type: " + guessMime(fname));
        os.println("Content-length: " + content.length);
        os.println();
        if (justHead)
            return;
        os.write(content);
    }

    /** Typ nieodgadnionych plików */
    final static String UNKNOWN = "unknown/unknown";
    String guessMime(String fn) {
        String lcname = fn.toLowerCase();
        int extenStartsAt = lcname.lastIndexOf('.');
        if (extenStartsAt<0) {
            if (fn.equalsIgnoreCase("makefile"))
                return "text/plain";
            return UNKNOWN;
        }
        String exten = lcname.substring(extenStartsAt);
        String guess = parent.getMimeType(exten, UNKNOWN);

        // System.out.println("guessMime: input " + fn + 
        //     ", rozszerzenie " + exten + ", result " + guess);

        return guess;
    }

    /** Zwraca informacje o błędzie według numeru, może z uwzględnieniem
     * lokalizacji. */
    protected void errorResponse(int errNum, String errMsg) {

        // Sprawdzamy zlokalizowaną odpowiedź
        ResourceBundle messages = ResourceBundle.getBundle("errors");

        String response;
        try { response = messages.getString(Integer.toString(errNum)); }
        catch (MissingResourceException e) { response=errMsg; }

        // Generujemy i wysyłamy odpowiedź
        os.println("HTTP/1.0 " + errNum + " " + response);
        os.println("Content-type: text/html");
        os.println();
        os.println("<html>");
        os.println("<head><title>Błąd " + errNum + "--" + response +
            "</title></head>");
        os.println("<H1>" + errNum + " " + response + "</H1>");
        sendEnd();
    }

    /** Dodaje końcówkę do każdej generowanej strony. */
    protected void sendEnd() {
        os.println("<hr>");
        os.println("<address>Java Web Server,");
        String myAddr = "http://www.darwinsys.com/freeware/";
        os.println("<a href=\"" + myAddr + "\">" +
            myAddr + "</a>");
        os.println("</address>");
        os.println("</html>");
        os.println();
    }
}
