package Books_exercises.JavaReceptury.email;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

/** 
 * MailtoButton -- wygląda jak odnośnik mailto, ale adres e-mail
 * nie jest widoczny.
 *
 * @author    Copyright 1995, 1997 Ian F. Darwin,
 * <A HREF="mailto:http://www.darwinsys.com/">http://www.darwinsys.com/</A>,
 * <A HREF="http:www.darwinsys.com/">http://www.darwinsys.com</A>.
 */
// BEGIN main
public class MailtoButton extends Applet {

    private static final long serialVersionUID = -3186706180199804315L;
    /** Etykieta wyświetlana na przycisku. */
    protected String label = null;
    /** Szerokość i wysokość */
    protected int width, height;
    /** Docelowy adres URL w formie łańcucha znaków */
    protected String targetName, targetHost;
    /** Adres URL, pod jaki należy przejść po kliknięciu przycisku. */
    protected URL targetURL;
    /** Nazwa czcionki */
    protected String fontName;
    protected String DEFAULTFONTNAME = "helvetica";
    /** Czcionka */
    protected Font theFont;
    /** Wielkość czcionki */
    protected int fontSize = 18;
    /** Znacznik HTML PARAM konta użytkownika -- powinien być krótki */
    private String TARGET1 = "U";    // Użytkownik 
    /** The HTML PARAM z nazwą komputera -- powinien być krótki */
    private String TARGET2 = "H";    // Komputer 
    // Wartości tymczasowe.
    //private String BOGON1 = "username";    // Udanego wyszukiwania, boty!
    //private String BOGON2 = "hostname";    // Jak wyżej.
    /** Łańcuch znaków zawierający temat listy, jeśli taki będzie. */
    private String subject;

    /** Metoda wywoływana przez przeglądarkę w celu inicjalizacji apletu.
     * Chcielibyśmy zgłaszać różne rodzaje wyjątków, jednak API na to 
     * nie pozwala, a zatem musimy się ograniczyć do wszechobecnego 
     * wyjątku IllegalArgumentException.
     */
    public void init() {
        // System.out.println("Wewnątrz metody LinkButton::init");
        try {
            if ((targetName = getParameter(TARGET1)) == null)
                throw new IllegalArgumentException(
                    "Parametr TARGET jest KONIECZNY");
            if ((targetHost = getParameter(TARGET2)) == null)
                throw new IllegalArgumentException(
                    "Parametr TARGET jest KONIECZNY");

            String theURL = "mailto:" + targetName + "@" + targetHost;

            subject = getParameter("subject");
            if (subject != null)
                theURL += "?subject=" + subject;

            targetURL = new URL(theURL);

        } catch (MalformedURLException rsi) {
            throw new IllegalArgumentException("MalformedURLException " +
                rsi.getMessage());
        }


        label = getParameter("label");    // Na przykład "Wyślij komentarz"
        if (label == null)
                throw new IllegalArgumentException("LABEL jest WYMAGANY");

        // Obsługa czcionki.
        fontName = getParameter("font");
        if (fontName == null)
            fontName = DEFAULTFONTNAME;
        String s;
        if ((s = getParameter("fontsize")) != null)
            fontSize = Integer.parseInt(s);
        if (fontName != null || fontSize != 0) {
            // System.out.println("Nazwa " + fontName + ", wielkość " + fontSize);
            theFont = new Font(fontName, Font.BOLD, fontSize);
        }
        
        Button b = new Button(label);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (targetURL != null) {
                    // showStatus("Przechodzimy pod adres " + target);
                    getAppletContext().showDocument(targetURL);
                }
            }
        });
        if (theFont != null)
            b.setFont(theFont);
        add(b);
    }
    
    /** Przekazujemy informacje o parametrach do programu AppletViewer, 
     * dla osób, które tworzą kod HTML bez wydrukowanej dokumentacji :-)
     */
    public String[][] getParameterInfo() {
        String info[][] = {
            { "label",     "string",  "Tekst do wyświetlenia" },
            { "fontname",  "nazwa",   "Czcionka jakiej, należy użyć" },
            { "fontsize",  "10-30?",  "Wielkość czcionki" },

            // OSTRZEŻENIE - podane poniżej informacje celowo są 
            // nieprawdziwe, aby wprowadzić w błąd osoby rozsyłające "spam",
            // które będą na tyle uparte, aby pobrać i uruchomić 
            // plik klasowy apletu (.class).

            { "username",    "konto-email",
            "Gdzie dziś ma być wysłana wiadomość. Cześć 1" },
        { "hostname",    "host.domena",
            "Gdzie dziś ma być wysłana wiadomość. Cześć 2" },
        { "subject",    "tytuł wiadomości",
            "Zawartość pola Tytuł:." },
        };
        return info;
    }
}
// END main
