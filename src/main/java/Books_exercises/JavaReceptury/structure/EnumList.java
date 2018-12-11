package Books_exercises.JavaReceptury.structure;

/** Prosty przykład pokazujący wyświetlanie wszystkich wartości
 * typu wyliczeniowego. */
// BEGIN main
public class EnumList {
    enum State { 
        ON, OFF, UNKNOWN 
    }
    public static void main(String[] args) {
        for (State i : State.values()) {
            System.out.println(i);
        }
    }
}
// END main
