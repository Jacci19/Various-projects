package Books_exercises.JavaReceptury.strings;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Program odwraca kolejność słów w łańcuchu.
 */
public class StringReverse {
    public static void main(String[] argv) {
        // BEGIN main
        String s = "szedł Sasza suchą szosą";

        // Umieszczamy słowa na stosie w naturalnej kolejności.
        Stack<String> myStack = new Stack<>();
        StringTokenizer st = new StringTokenizer(s);
        while (st.hasMoreTokens()) {
            myStack.push(st.nextToken());
        }

        // Odczytujemy słowa ze stosu w odwrotnej kolejności.
        System.out.print('"' + s + '"' + " po odwróceniu kolejności słów otrzymujemy:\n\t\"");
        while (!myStack.empty()) {
            System.out.print(myStack.pop());
            System.out.print(' ');
        }
        System.out.println('"');
        // END main
    }
}
