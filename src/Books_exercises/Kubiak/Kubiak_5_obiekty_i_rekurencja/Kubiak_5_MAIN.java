package Books_exercises.Kubiak.Kubiak_5_obiekty_i_rekurencja;

import java.util.Scanner;

public class Kubiak_5_MAIN {
    public static void main(String[] args) {

        /*Zad 5_1 Pole Prostokąta (OBJ)______________________________________________________________________                                         */
/*
        Zad_5_01_OBJ_PoleProstok Pole = new Zad_5_01_OBJ_PoleProstok();
        Pole.czytaj_dane();
        Pole.przetworz_dane();
        Pole.wyswietl_wynik();
*/

        /*Zad 5_2 Pierwiastki równania kwadratowego (OBJ)_____________________________________________________                                        */
/*
        Zad_5_02_OBJ_PierwRownania Pierw = new Zad_5_02_OBJ_PierwRownania();
        Pierw.czytaj_dane();
        Pierw.przetworz_dane();
        Pierw.wyswietl_wynik();
*/

        /*Zad 5_3 Tablica 2D (OBJ)___________________________________________________________________________                                         */
/*

        int rozmiar = 10;
        double[][] macierz = new double[rozmiar][rozmiar];

        Zad_5_03_OBJ_Tabl2D Tabl = new Zad_5_03_OBJ_Tabl2D();
        Tabl.czytaj_dane(macierz, rozmiar);
        Tabl.przetworz_dane(macierz, rozmiar);
        Tabl.wyswietl_wynik(macierz, rozmiar);
*/

        /*Zad 5_4 Sortowanie (OBJ)___________________________________________________________________________                                         */
/*
        int n = 6;
        int[] liczby = new int[n];

        Zad_5_04_OBJ_Sortowanie Sorter = new Zad_5_04_OBJ_Sortowanie();
        Sorter.czytaj_dane(liczby, n);
        Sorter.przetworz_dane(liczby, n);
        Sorter.wyswietl_wynik(liczby, n);
*/

        /*Zad 5_5 Silnia Rekurencja (OBJ)___________________________________________________________________________                                         */
/*
        int a, i;
        int n = 15;
        Zad_5_05_OBJ_Rekur_silnia s = new Zad_5_05_OBJ_Rekur_silnia();
        System.out.print("\nProgram liczący SILNIĘ danej liczby. Podaj liczbę: ");

        Scanner wczytaj = new Scanner(System.in);
        a = wczytaj.nextInt();

        System.out.println("Silnia liczby " +a+ " wynosi: " + s.silnia(a));
        System.out.println("Silnie liczb od 0 do " + n +": ");
        for (i=0; i<n; i++){
            System.out.print(i + "! = " + s.silnia(i) + "\n");
        }
*/

        /*Zad 5_6 Liczby trójkątne (OBJ)___________________________________________________________________________                                         */

        int a,i;
        int n = 10;

        Zad_5_06_OBJ_LiczbyTrojkatne T = new Zad_5_06_OBJ_LiczbyTrojkatne();

        System.out.print("\nProgram liczący TROJKAT danej liczby. Podaj liczbę: ");
        Scanner wczytaj = new Scanner(System.in);
        a = wczytaj.nextInt();
        System.out.println("Trojkat liczby " + a + " wynosi: " + T.Trojkat(a));

        System.out.println(n + " pierwszych liczb trójkątnych: ");
        for (i=0; i<n; i++){
            System.out.println(T.Trojkat(i));
        }



    }
}
