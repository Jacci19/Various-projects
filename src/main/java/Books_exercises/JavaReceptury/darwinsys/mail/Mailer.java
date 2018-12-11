// BEGIN package
package Books_exercises.JavaReceptury.darwinsys.mail;
// END package

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/** Prosty program do wysyłania e-maili. Nie ma żadnego powiązania 
 * z Normanem Mailerem.
 * Sposób użycia:
 * <pre>
 * Mailer mb = new Mailer();
 * mb.setFrom("orders@YourDomain.com");
 * mb.addTo("orders@YourDomain.com");
 * mb.setSubject("Zamówienie!!");
 * mb.setBody(order.toString());
 * mb.setServer(application.getInitParameter("mail.server.smtp"));
 * try {
 *     mb.doSend();
 * } catch (MessagingException ex) {
 *       ...
 * }
 * </pre>
 * @author Ian F. Darwin
 */
// BEGIN main
public class Mailer {
    /** Obiekt sesji JavaMail. */
    protected Session session;
    /** Adres nadawcy */
    protected String from;
    /** Temat wiadomości. */
    protected String subject;
    /** Adresat ("To:"), jako Strings. */
    protected List<String> toList = new ArrayList<>();
    /** Lista adresów, na jakie należy przesłać kopie (CC), 
     * poszczególne adresy zapisane jako String. */
    protected List<String> ccList = new ArrayList<String>();
    /** Lista adresów, na jakie należy przesłać kopie niewidoczne
     * dla innych (BCC), poszczególne adresy zapisane jako String. */
    protected List<String> bccList = new ArrayList<String>();
    /** Tekst wiadomości. */
    protected String body;
    /** Serwer SMTP  */
    protected String mailHost;
    /** Flaga określająca generowanie komunikatów */
    protected boolean verbose;

    /** Pobranie pola from */
    public String getFrom() {
        return from;
    }

    /** Określenie pola from */
    public void setFrom(String fm) {
        from = fm;
    }

    /** Pobranie pola subject */
    public String getSubject() {
        return subject;
    }

    /** Określenie pola subject */
    public void setSubject(String subj) {
        subject = subj;
    }

    // Metody określające i pobierające listę adresatów

    /** Pobiera listę adresatów jako tablicę obiektów String */
    public List<String> getToList() {
        return toList;
    }

    /** Określa listę adresatów na podstawie obiektu ArrayList 
     * zawierającego obiekty String */
    public void setToList(ArrayList<String> to) {
        toList = to;
    }

    /** Określa listę adresatów zapisaną w formie "tom, mary, robin@host". 
     * Usuwa wszystkich adresatów podanych wcześniej */
    public void setToList(String s) {
        toList = Arrays.asList(s.split(",\\s+"));
    }

    /** Dodaje jednego adresata */
    public void addTo(String to) {
        toList.add(to);
    }

    // Metody określające i odczytujące listę adresatów, do których zostaną
    // przesłane kopie wiadomości (CC)

    /** Pobiera listę w formie tablicy obiektów String */
    public List<String> getCcList() {
        return ccList;
    }

    /** Określa listę adresatów na podstawie obiektu ArrayList 
     * zawierającego obiekty String */
    public void setCcList(ArrayList<String> cc) {
        ccList = cc;
    }

    /** Określa listę adresatów zapisaną w formie "tom, mary, robin@host". 
     * Usuwa wszystkiech adresatów podanych wcześniej */
    public void setCcList(String s) {
        ccList = Arrays.asList(s.split(",\\s+"));
    }

    /** Dodaje jednego adresata */
    public void addCc(String cc) {
        ccList.add(cc);
    }

    // Metody określające i odczytujące listę adresatów, do których zostaną
    // przesłane kopie wiadomości niewidoczne dla innych 
    // odbiorców (BCC)

    /** Pobiera listę w formie tablicy obiektów String */
    public List<String> getBccList() {
        return bccList;
    }

    /** Określa listę adresatów na podstawie obiektu ArrayList 
     * zawierającego obiekty String */
    public void setBccList(List<String> bcc) {
        bccList = bcc;
    }

    /** Określa listę adresatów zapisaną w formie "tom, mary, robin@host". 
     * Usuwa wszystkich adresatów podanych wcześniej */
    public void setBccList(String s) {
        bccList = Arrays.asList(s.split(",\\s+"));
    }

    /** Dodaje jednego adresata */
    public void addBcc(String bcc) {
        bccList.add(bcc);
    }

    // Metody odczytujące i określające treść wiadomości

    /** Odczytanie treści */
    public String getBody() {
        return body;
    }

    /** Określenie treści*/
    public void setBody(String text) {
        body = text;
    }

    // Odczytanie i ustawienie flagi komunikatów

    /** Odczytanie flagi */
    public boolean isVerbose() {
        return verbose;
    }

    /** Ustawienie flagi */
    public void setVerbose(boolean v) {
        verbose = v;
    }

    /** Sprawdzenie, czy zostały podane wartości wszystkich 
     * wymaganych pól. Metoda jest zazwyczaj wywoływana przed
     * wywołaniem doSend; tu jest wywoływana przez metodę
     * doSend w celu sprawdzenia poprawności danych.
     */
    public boolean isComplete() {
        if (from == null    || from.length()==0) {
            System.err.println("doSend: brak pola FROM");
            return false;
        }
        if (subject == null || subject.length()==0) {
            System.err.println("doSend: brak pola SUBJECT");
            return false;
        }
        if (toList.size()==0) {
            System.err.println("doSend: nie określono adresatów");
            return false;
        }
        if (body == null || body.length()==0) {
            System.err.println("doSend: nie podano treści wiadomości");
            return false;
        }
        if (mailHost == null || mailHost.length()==0) {
            System.err.println("doSend: nie określono serwera poczty elektronicznej");
            return false;
        }
        return true;
    }

    public void setServer(String s) {
        mailHost = s;
    }

    /** Przesłanie wiadomości.
     */
    public synchronized void doSend() throws MessagingException {

        if (!isComplete())
            throw new IllegalArgumentException(
                "Metoda doSend wywołana przez dokończeniem wiadomości");

        /** Obiekt Properties używany w celu przekazania ustawień do JavaMail API */
        Properties props = new Properties();
        props.put("mail.smtp.host", mailHost);

        // Tworzymy obiekt Session 
        if (session == null) {
            session = Session.getDefaultInstance(props, null);
            if (verbose)
                session.setDebug(true);        // Komunikaty!
        }
        
        // Tworzymy wiadomość
        final Message mesg = new MimeMessage(session);

        InternetAddress[] addresses;

        // Obsługa listy adresatów
        addresses = new InternetAddress[toList.size()];
        for (int i=0; i<addresses.length; i++)
            addresses[i] = new InternetAddress((String)toList.get(i));
        mesg.setRecipients(Message.RecipientType.TO, addresses);

        // Określenie nadawcy
        mesg.setFrom(new InternetAddress(from));

        // Lista adresatów CC
        addresses = new InternetAddress[ccList.size()];
        for (int i=0; i<addresses.length; i++)
            addresses[i] = new InternetAddress((String)ccList.get(i));
        mesg.setRecipients(Message.RecipientType.CC, addresses);

        // Lista adresatów BCC
        addresses = new InternetAddress[bccList.size()];
        for (int i=0; i<addresses.length; i++)
            addresses[i] = new InternetAddress((String)bccList.get(i));
        mesg.setRecipients(Message.RecipientType.BCC, addresses);

        // Temat wiadomości
        mesg.setSubject(subject);

        // Treść wiadomości.
        mesg.setText(body);

        Transport.send(mesg);
    }

    /** Metoda pomocnicza załatwiająca wszystko w jednym wywołaniu.
     * @param mailhost - serwer SMTP 
     * @param recipient - adres odbiorcy (user@host.domain)
     * @param sender - adres nadawcy
     * @param subject - temat
     * @param message - cała treść jako String z dołączonymi znakami '\n'

     */
    public static void send(String mailhost,
        String recipient, String sender,
        String subject, String message)
        throws MessagingException {

        Mailer m = new Mailer();
        m.setServer(mailhost);
        m.addTo(recipient);
        m.setFrom(sender);
        m.setSubject(subject);
        m.setBody(message);
        m.doSend();
    }
}
// END main
