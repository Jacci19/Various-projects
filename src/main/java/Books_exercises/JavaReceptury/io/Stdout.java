package Books_exercises.JavaReceptury.io;

/**
 * Przykład wykorzystania standardowego strumienia wyjściowego.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Stdout {
    public static void main(String[] argv) {
        // BEGIN main
        Object anObject = new Object();
        String myAnswer = "nie";
        int i = 42;
    
        System.out.println("Witaj, w świecie Javy");
        System.out.println("To jest obiekt " + anObject);
        System.out.println("W tym przypadku odpowiedzią jest " + myAnswer + ".");
        System.out.println("Oto odpowiedź " + i + '.');
        System.out.println("Oto odpowiedź " + i + ".");
        System.out.println(i + '=' + " odpowiedź.");
        System.out.println(new StringBuffer("Odpowiedzią jest ").append(i).append('.'));
        // END main
    }
}
