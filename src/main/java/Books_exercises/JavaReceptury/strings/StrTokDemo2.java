package Books_exercises.JavaReceptury.strings;

import java.util.StringTokenizer;

/** Prezentacja wykorzystania klasy StringTokenizer. */
// BEGIN main
public class StrTokDemo2 {
   
    public static void main(String[] a) {
       StringTokenizer st = 
             new StringTokenizer("Witamy, świecie|Javy", ", |");

       while (st.hasMoreTokens())
           System.out.println("Leksem: " + st.nextToken());
       
    }
}
// END main
