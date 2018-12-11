package Books_exercises.JavaReceptury.oo;

// BEGIN main
/** A ChessMoveException jest zgłaszany, gdy użytkownik
 * wykona nieprawidłowy ruch. */
public class ChessMoveException extends Exception {

    private static final long serialVersionUID = 802911736988179079L;

    public ChessMoveException () {
        super();
    }
    
    public ChessMoveException (String msg) {
        super(msg);
    }
    
    public ChessMoveException(String msg, Exception cause) {
        super(msg, cause);
    }
}
// END main
