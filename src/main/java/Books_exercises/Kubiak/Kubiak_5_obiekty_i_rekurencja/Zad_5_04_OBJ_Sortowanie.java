package Books_exercises.Kubiak.Kubiak_5_obiekty_i_rekurencja;

public class Zad_5_04_OBJ_Sortowanie {

/*  Napisz zgodnie z zasadami programowania obiektowego program, który sortuje n liczb (w programie jest ich sześć).
    Klasa powinna zawierać trzy metody z parametrami:
        1. czytaj_dane(int [] liczby, int n) — metoda czyta dane i umieszcza je w tablicy o nazwie liczby.
        2. przetworz_dane(int [] liczby, int n) — metoda sortuje dane według wybranego algorytmu sortowania (w programie wykorzystano algorytm sortowania bąbelkowego).
        3. wyswietl_wynik(int [] liczby, int n) — metoda wyświetla zawartość posortowanej tablicy  liczby na ekranie monitora.                         */

    private int i;

    void czytaj_dane(int[] liczby, int n) {

        liczby[0] = 57;
        liczby[1] = 303;
        liczby[2] = 34;
        liczby[3] = 1025;
        liczby[4] = 8;
        liczby[5] = 20;
        System.out.print("Tablica nieposortowana: ");
        for (i = 0; i < n; i++) {
            System.out.print(liczby[i] + "   ");
        }
    }


    void przetworz_dane(int[] liczby, int n) {
        System.out.println("\nSortowanie tablicy metodą bąbelkową.");
        int i, j, temp;
        for (i = 1; i <= n-1; i++) {
            for (j = n-1; j >=i; --j) {
                if (liczby[j-1] > liczby[j]) {
                    temp = liczby[j-1];
                    liczby[j - 1] = liczby[j];
                    liczby[j] = temp;
                }
            }
        }

    }

    void wyswietl_wynik(int[] liczby, int n) {
        System.out.print("Tablica posortowana: ");
        for (i = 0; i < n; i++) {
            System.out.print(liczby[i] + "   ");
        }
    }

}
