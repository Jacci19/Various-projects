package Books_exercises.JavaReceptury.email;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.swing.tree.DefaultMutableTreeNode;

import com.darwinsys.util.StringFormat;

/** Modyfikowalny węzeł drzewa, traktowany jako wiadomość (Message). */
// BEGIN main
public class MessageNode extends DefaultMutableTreeNode {
    Message m;

    StringFormat fromFmt = new StringFormat(20, StringFormat.JUST_LEFT);
    StringFormat subjFmt = new StringFormat(30, StringFormat.JUST_LEFT);

    MessageNode(Message m) {
        this.m = m;
    }

    public String toString() {
        try {
            Address from = m.getFrom()[0];

            String fromAddress;
            if (from instanceof InternetAddress)
                fromAddress = ((InternetAddress)from).getAddress();
            else
                fromAddress = from.toString();

            StringBuffer sb = new StringBuffer();
            fromFmt.format(fromAddress, sb, null);
            sb.    append("  ");
            subjFmt.format(m.getSubject(), sb, null);
            return sb.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }
}
// END main
