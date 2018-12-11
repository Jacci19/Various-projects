package Books_exercises.JavaReceptury.structure;

import java.util.Calendar;

/** Przypomnienie sposobów wykorzystania tablic: tworzenie tablicy,
 * przetwarzanie jej zawartości, zapisywanie obiektów w tablicach, 
 * tworzenie tablic dwuwymiarowych, określanie długości tablic.
 *
 * @author Ian Darwin
 */
// BEGIN main
public class Array1  {
    @SuppressWarnings("unused")
    public static void main(String[] argv) {
        int[] monthLen1;            // deklarujemy odwołanie
        monthLen1 = new int[12];        // tworzymy je
        int[] monthLen2 = new int[12];    //zapis skrócony
        // jeszcze prostszy jest poniższy sposób inicjalizacji 
        int[] monthLen3 = {
                31, 28, 31, 30,
                31, 30, 31, 31,
                30, 31, 30, 31,
        };
        
        final int MAX = 10;
        Calendar[] days = new Calendar[MAX];
        for (int i=0; i<MAX; i++) {
            // Warto zauważyć, że poniższa instrukcja zapisuje
            // obiekty GregorianCalendar itp. w tablicach Calendar
            days[i] = Calendar.getInstance();
        }
     
        // Tablice dwuwymiarowe
        // Tworzymy tablicę 10x24
        int[][] me = new int[10][];
        for (int i=0; i<10; i++)
            me[i] = new int[24];

        // Pamiętajmy, że tablice mają właściwość length
        System.out.println(me.length);
        System.out.println(me[0].length);

    }
}
// END main
