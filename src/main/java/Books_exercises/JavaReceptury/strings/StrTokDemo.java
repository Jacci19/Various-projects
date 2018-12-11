package Books_exercises.JavaReceptury.strings;

import java.util.StringTokenizer;

/** Prezentacja wykorzystania klasy StringTokenizer. */
// BEGIN main
public class StrTokDemo {
   
    public static void main(String[] a) {
       StringTokenizer st = new StringTokenizer("Witam w świecie Javy");

       while (st.hasMoreTokens())
           System.out.println("Leksem: " + st.nextToken());
       
    }
}
// END main
