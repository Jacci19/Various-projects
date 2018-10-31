package Books_exercises.Kubiak.Kubiak_5_obiekty_i_rekurencja;

import java.util.Random;

public class Zad_5_03_OBJ_Tabl2D {

/*  Napisz zgodnie z zasadami programowania obiektowego program, który w tablicy 10×10 umieszcza losowo na przekątnej liczby od 0 do 9, a poza przekątną zera.
    Dodatkowo program oblicza sumę liczb znajdujących się na przekątnej. Klasa powinna zawierać trzy metody z parametrami:
       1. czytaj_dane(double [][]macierz, int rozmiar) — metoda umieszcza dane w tablicy.
       2. przetworz_dane(double [][]macierz, int rozmiar) — metoda oblicza i wyświetla sumę liczb znajdujących się na przekątnej.
       3. wyswietl_wynik(double [][]macierz, int rozmiar) — metoda wyświetla zawartość tablicy na ekranie monitora.                                           */


    private double suma = 0;
    private int i, j;
    Random rand = new Random();


    public void czytaj_dane(double[][] macierz, int rozmiar) {
        for (i = 0; i < rozmiar; i++) {
            for (j = 0; j < rozmiar; j++) {
                if (i == j) {
                   // macierz[i][j] = rand.nextInt(10);
                    macierz[i][j] = 9 * rand.nextDouble();          //rand.nextDouble daje random double z zakresu 0 - 1 (np. 0,3542354)
                } else macierz[i][j] = 0;
            }
        }
    }

    public void przetworz_dane(double [][]macierz, int rozmiar) {
        for (i = 0; i < rozmiar; i++) {
            for (j = 0; j < rozmiar; j++) {
                if (i == j) suma += macierz[i][j];
            }
        }
        System.out.println("Zad 5_3\nSuma cyfr na przekątnej wynosi " + (int)suma + "\n");
    }


    public void wyswietl_wynik(double [][]macierz, int rozmiar) {
        for (i = 0; i < rozmiar; i++) {
            for (j = 0; j < rozmiar; j++) {
                System.out.print((int)macierz[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

