package Books_exercises.Kubiak.Kubiak_5_obiekty_i_rekurencja;

import java.util.Scanner;

public class Zad_5_02_OBJ_PierwRownania {

/*  Zad 5_02
    Napisz zgodnie z zasadami programowania obiektowego program, który oblicza pierwiastki równania kwadratowego ax 2 +bx+c = 0
    z wykorzystaniem instrukcji wyboru  switch...case . Klasa powinna zawierać trzy metody:
        1. czytaj_dane() — metoda jest odpowiedzialna za wczytanie danych do programu oraz obsłużenie sytuacji, kiedy  a = 0 .
                           Zmienne  a, b i c to liczby rzeczywiste wprowadzane z klawiatury.
        2. przetworz_dane() — metoda odpowiada za wykonanie niezbędnych obliczeń.
        3. wyswietl_wynik() — metoda jest odpowiedzialna za wyświetlenie wyników na ekranie monitora.
    Dla zmiennych  a ,  b ,  c ,  x1 oraz  x2 należy przyjąć format wyświetlania ich z dwoma miejscami po przecinku.                              */


    private double a,b,c, delta, x1, x2;
    private int ilosc_pierwiastkow;

    public void czytaj_dane(){
        Scanner wczytaj = new Scanner(System.in);
        System.out.println("Program obliczający pierwiastki równania kwadratowego: ax^2 + bx + c = 0");
        System.out.print("Wprowadź współczynnik a: ");
        do{
            a = wczytaj.nextDouble();
            if (a==0) System.out.print("Współczynnik a musi być różny od zera\nWprowadź współczynnik a: ");
        }
        while (a==0);
        System.out.print("Wprowadź współczynnik b: ");
        b = wczytaj.nextDouble();
        System.out.print("Wprowadź współczynnik c: ");
        c = wczytaj.nextDouble();

    }

    public void przetworz_dane(){
        delta = Math.pow(b, 2) - 4 * a * c;

        if (delta > 0) ilosc_pierwiastkow = 2;
        else if (delta == 0) ilosc_pierwiastkow = 1;
        else ilosc_pierwiastkow = 0;

        switch (ilosc_pierwiastkow) {
            case 2:
                x1 = (-b - Math.sqrt(delta)) / (2 * a);
                x2 = (-b + Math.sqrt(delta)) / (2 * a);
                break;
            case 1:
                x1 = -b / (2 * a);
                break;
        }
    }

    public void wyswietl_wynik(){
        switch (ilosc_pierwiastkow) {
            case 2:
                System.out.printf("Równanie ma DWA pierwiastki, x1 = " + "%2.2f", x1);
                System.out.printf(", x2 = " + "%2.2f\n", x2);
                break;
            case 1:
                System.out.printf("Równanie ma JEDEN pierwiastek, x1 = " + "%2.2f", x1);
                break;
            case 0:
                System.out.println("Równanie nie ma pierwiastków.");
                break;

        }
    }
}
