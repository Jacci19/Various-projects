package Books_exercises.JavaReceptury.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/** sender -- wysyłanie wiadomości poczty elektronicznej.
 * @author Ian F. Darwin
 */
// BEGIN main
public class Sender {

    /** Odbiorca. */
    protected String message_recip = "spam-magnet@darwinsys.com";
    /* O czym w zasadzie piszemy, Alfie? */
    protected String message_subject = "Re: Twoja wiadomość";
    /** Odbiorca kopii wiadomości. */
    protected String message_cc = "nobody@erewhon.com";
    /** Treść wiadomości */
    protected String message_body =
        "Niestety nie mogę odczytać nadesłanej wiadomości, gdyż aktualnie" +
        "jestem zajęty opalaniem się na plażach Maui, gdzie jest "   +
        "ciepło i spokojnie. Być może po powrocie przejrzę korespondencję, " +
        "choć niczego nie obiecuję. ";

    /** Obiekt sesji JavaMail */
    protected Session session;
    /** Obiekt wiadomości JavaMail */
    protected Message mesg;

    /** Wykonujemy zadanie: wysyłamy wiadomość na serwer SMTP.  */
    public void doSend() {

        // Musimy przekazać informacje na serwer w formie obiektu Properties, gdyż 
        // JavaMail (rozsądnie) pozwala na wykorzystywanie wielu właściwości...
        Properties props = new Properties();

        // Serwer SMTP musi być zdefiniowany w sieci lokalnej jako "mailhost",
        // inaczej nasz prosty program nie będzie w stanie wysyłać wiadomości...
        props.put("mail.smtp.host", "mailhost");

        // Tworzymy obiekt Session
        session = Session.getDefaultInstance(props, null);
        session.setDebug(true);        // Generować komunikaty!
        
        try {
            // Tworzymy wiadomość
            mesg = new MimeMessage(session);

            // Adres Od (From) - powinien być odczytywany z Properties...
            mesg.setFrom(new InternetAddress("nobody@host.domain"));

            // Adres Do (To)
            InternetAddress toAddress = new InternetAddress(message_recip);
            mesg.addRecipient(Message.RecipientType.TO, toAddress);

            // Adres CC (kopie do)
            InternetAddress ccAddress = new InternetAddress(message_cc);
            mesg.addRecipient(Message.RecipientType.CC, ccAddress);

            // Temat 
            mesg.setSubject(message_subject);

            // Treść wiadomości
            mesg.setText(message_body);
            // W programach wielojęzycznych należy użyć metody:
            // setText(msgText.getText(), charset)
            
            // I w końcu wysyłamy wiadomość!
            Transport.send(mesg);

        } catch (MessagingException ex) {
            while ((ex = (MessagingException)ex.getNextException()) != null) {
                ex.printStackTrace();
            }
        }
    }

    /** Prosty test */
    public static void main(String[] av) {
        Sender sm = new Sender();
        sm.doSend();
    }
}
// END main
