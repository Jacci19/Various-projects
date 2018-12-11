package Books_exercises.JavaReceptury.numbers;

import java.util.BitSet;

/** Operacje na grupach liczb. */
// BEGIN main
public class NumSeries {
    public static void main(String[] args) {

       // Aby stworzyć listę numerów porządkowych,
       // można się posłużyć pętlą for rozpoczynającą działanie 
       // od wartości 1.
        for (int i = 1; i <= months.length; i++)
            System.out.println("Miesiąc nr " + i);
    
        // Aby stworzyć grupę indeksów tablicy, należy użyć 
        // pętli for rozpoczynającej działanie od wartości 0.
        for (int i = 0; i < months.length; i++)
            System.out.println("Miesiąc: " + months[i]);

        // Liczenie od 11 do 27 co 3 przy użyciu pętli for.
        for (int i = 11; i <= 27; i += 3) {
            System.out.println("i = " + i);
        }

        
        // W przypadku operacji na nieciągłych zbiorach liczb
        // można się posłużyć obiektem klasy BitSet.

        // Tworzymy obiekt klasy BitSet i ustawiamy wybrane bity.
        BitSet b = new BitSet();
        b.set(0);    // Styczeń
        b.set(3);    // Kwiecień
        b.set(8);    // Wrzesień

        // Ten fragment znajdzie się w jakimś innym miejscu programu.
        for (int i = 0; i<months.length; i++) {
            if (b.get(i))
                System.out.println("Miesiąc: " + months[i]);
        }

        // Ten sam przykład, tylko krótszy:
        // nieciągły zakres liczb całkowitych zapisany w tablicy
        int[] numbers = {0, 3, 8};

        // Ten fragment znajdzie się w jakimś innym miejscu programu.
        for (int n : numbers) {
            System.out.println("Miesiąc: " + months[n]);
        }
    }
    /** Nazwy miesięcy. Lepsze rozwiązanie można znaleźć w 
     * rozdziale poświęconym datom i godzinom.
     */
    protected static String months[] = {
       "Styczeń", "Luty", "Marzec", "Kwiecień",
       "Maj", "Czerwiec", "Lipiec", "Sierpień",
       "Wrzesień", "Październik", "Listopad", "Grudzień"
    };
}
// END main
