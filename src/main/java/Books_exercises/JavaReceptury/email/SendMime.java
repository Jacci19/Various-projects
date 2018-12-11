package Books_exercises.JavaReceptury.email;

import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.darwinsys.util.FileProperties;

/** SendMime -- wysyłanie wielocześciowej wiadomości MIME.
 * @author Ian F. Darwin
 */
// BEGIN main
public class SendMime {

    /** Odbiorca wiadomości. */
    protected String message_recip = "spam-magnet@somedomainnamehere.com";
    /* Ale o czym my tu w ogóle rozmawiamy, Alfie? */
    protected String message_subject = "Re: Wiadomość do ciebie";
    /** Odbiorcy kopii. */
    protected String message_cc = "nobody@erewhon.com";
    /** Zwyczajna wiadomość tekstowa */
    protected String message_body =
        "Niestety nie mogę odczytać nadesłanej wiadomości, gdyż aktualnie" +
        "jestem zajęty opalaniem się na plażach Maui, gdzie jest "   +
        "ciepło i spokojnie. Być może po powrocie przejrzę korespondencję, " +
        "choć niczego nie obiecuję. ";
    /* Wiadomość w formacie text/html. */
    protected String html_data = 
        "<html><head><title>Mój Boże!</title></head>" +
        "<body><p>Ty <em>naprawdę</em> trochę " +
        "<font color=green>ZZIELENIAŁEŚ</font>" +
        " na tym filmie, czyżby ze strachu..." +
        "</body></html>";

    /** Obiekt sesji JavaMail  */
    protected Session session;
    /** Obiekt wiadomości JavaMail */
    protected Message mesg;

    /** Realizacja zadania: przesłanie wiadomości na serwer SMTP.  */
    public void doSend() throws IOException, MessagingException {

        // Musimy przekazać informacje na serwer w formie obiektu Properties, gdyż 
        // JavaMail (rozsądnie) pozwala na wykorzystywanie wielu właściwości...
        FileProperties props = 
            new FileProperties(MailConstants.PROPS_FILE_NAME);

        // Kopiujemy wartość Mail.send.host i zapisujemy ją w mail.smtp.host
        props.setProperty("mail.smtp.host", 
            props.getProperty(MailConstants.SEND_HOST));

        // Tworzymy obiekt Session
        session = Session.getDefaultInstance(props, null);
        session.setDebug(true);        // komunikaty!
        
        try {
            // Tworzymy wiadomość
            mesg = new MimeMessage(session);

            // Adres nadawcy, należałoby go odczytywać z pliku właściwości
            mesg.setFrom(new InternetAddress("nobody@host.domain"));

            // Adres odbiorcy (To)
            InternetAddress toAddress = new InternetAddress(message_recip);
            mesg.addRecipient(Message.RecipientType.TO, toAddress);

            // Adresy odbiorców kopii wiadomości
            InternetAddress ccAddress = new InternetAddress(message_cc);
            mesg.addRecipient(Message.RecipientType.CC, ccAddress);

            // Temat
            mesg.setSubject(message_subject);

            // Treść wiadomości
            Multipart mp = new MimeMultipart();

            BodyPart textPart = new MimeBodyPart();
            textPart.setText(message_body);    // Określamy typ: "text/plain"

            BodyPart pixPart = new MimeBodyPart();
            pixPart.setContent(html_data, "text/html");

            // Zbieramy wszystkie fragmenty wiadomości
            mp.addBodyPart(textPart);
            mp.addBodyPart(pixPart);

            // Dołączamy wszystkie fragmenty do wiadomości
            mesg.setContent(mp);
            
            // I w końcu wysyłamy wiadomość!
            Transport.send(mesg);

        } catch (MessagingException ex) {
            System.err.println(ex);
            ex.printStackTrace(System.err);
        }
    }
    // END main

    /** Prosty program testowy */
    public static void main(String[] av) throws Exception {
        SendMime sm = new SendMime();
        sm.doSend();
    }
}
