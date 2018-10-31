package Books_exercises.Kubiak.Kubiak_5_obiekty_i_rekurencja;

import java.util.Scanner;

public class Zad_5_01_OBJ_PoleProstok {

/*      Zad 5_1
    Napisz zgodnie z zasadami programowania obiektowego program, który oblicza pole prostokąta. Klasa powinna zawierać trzy metody:
      1. czytaj_dane() — metoda umożliwia wprowadzenie do programu długości boków a i b z klawiatury.  a i b oraz  pole mają być typu double (rzeczywistego).
      2. przetworz_dane() — metoda oblicza pole prostokąta według wzoru  pole = a*b.
      3. wyswietl_wynik() — metoda wyświetla długości boków a i b oraz wartość zmiennej pole w określonym formacie.
    Dla zmiennych  a i  b oraz  pole należy przyjąć format wyświetlania ich na ekranie z dwoma miejscami po przecinku.                                           */

    private double a, b, pole;
    public void czytaj_dane(){
        Scanner wczytaj = new Scanner(System.in);
        System.out.print("\nProgram liczący pole prostokąta.\nPodaj długość boku a: ");
        a = wczytaj.nextDouble();
        System.out.print("Podaj długość boku b: ");
        b = wczytaj.nextDouble();
    }

    public void przetworz_dane(){
        pole = a*b;
    }

    public void wyswietl_wynik(){
        System.out.printf("Pole prostokąta wynosi: " + "%2.2f\n", pole);
    }















}

