package Books_exercises.JavaReceptury.strings;

import java.util.StringTokenizer;

public class StringBuilderCommaList {
    public static final String SAMPLE_STRING = "Alpha Bravo Charlie";
    public static void main(String[] args) {

        // BEGIN main
        // Zastosowanie metody split z wyrażeniem regularnym 
        StringBuilder sb1 = new StringBuilder();
        for (String word : SAMPLE_STRING.split(" ")) {
            if (sb1.length() > 0) {
                sb1.append(", ");
            } 
            sb1.append(word);
        }
        System.out.println(sb1);

        // Zastosowanie klasy StringTokenizer
        StringTokenizer st = new StringTokenizer(SAMPLE_STRING);
        StringBuilder sb2 = new StringBuilder();
        while (st.hasMoreElements()) {
            sb2.append(st.nextToken());
            if (st.hasMoreElements()) {
                sb2.append(", ");
            }
        }
        System.out.println(sb2);
        // END main
    }
}