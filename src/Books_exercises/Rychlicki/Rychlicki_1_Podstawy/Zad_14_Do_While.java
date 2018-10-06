package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

import java.util.Scanner;

public class Zad_14_Do_While {
    public Zad_14_Do_While() {

/*
        Zadanie 14.1.
            Użytkownik wprowadza z klawiatury serię liczb różnych od zera, zero natomiast jest sygnałem zakończenia wprowadzania danych.
            Napisz program, który obliczy sumę tych liczb.                                                                                                          */

/*
        System.out.println("\n________________________Zad 14_1_______________________________________________________________________________________");

        int liczba, suma = 0;
        Scanner wczytaj = new Scanner(System.in);
        do{
            System.out.print("Wprowadź liczbę: ");
            liczba = wczytaj.nextInt();
            suma += liczba;
        }
        while (liczba != 0);
        System.out.println("Suma wprowadzonych liczb wynosi: " + suma);
*/


/*
        Zadanie 14.3.
            Napisz program, który wyświetli w konsoli wielokrotności liczby x mniejsze od liczby max.                                                                      */

/*
        System.out.println("\n________________________Zad 14_3_______________________________________________________________________________________");

        int x = 0, max, wynik;
        Scanner wczytaj = new Scanner(System.in);
        System.out.print("Podaj liczbę x: ");
        x = wczytaj.nextInt();
        System.out.print("Podaj liczbę max: ");
        max = wczytaj.nextInt();
        System.out.println("Wielokrotności liczby " + x + " (max: " + max + "):");
        wynik = x;
        do {
            wynik += x;
            System.out.println(wynik);
        }
        while (wynik < max);
*/



/*
        Zadanie 14.8.
        Napisz program obliczający pole powierzchni trójkąta równobocznego i umożliwiający wielokrotne powtarzanie obliczeń.
        Jako znak zakończenia obliczeń przyjmij podanie długości boku równej 0. Dla wartości ujemnych wyświetl stosowny komunikat i ponów pytanie o długość boku.    */

/*
        double bok, pole;

        System.out.println("\n________________________Zad 14_8_______________________________________________________________________________________");
        do{
            do {
                System.out.print("Podaj długość boku trójkąta równobocznego (0 aby zakończyć): ");
                Scanner wczytaj = new Scanner(System.in);
                bok = wczytaj.nextDouble();
                if (bok < 0) System.out.println("Długość boku musi być większa od zera");
            }
            while (bok < 0);

            if (bok > 0){
                pole = (bok*bok*Math.sqrt(3))/4;
                System.out.printf("Pole wynosi: %.3f\n", pole);
            }
            else {
                System.out.println("Koniec programu");
            }
        }
        while (bok > 0);
*/




/*
        Zadanie 14.9.
            Napisz program umożliwiający wielokrotne wykonywanie obliczeń pola koła. Po wykonaniu obliczeń program powinien wyświetlić pytanie Czy obliczamy dalej (t/n)?.
            W pytaniu zawarta jest sugestia sposobu udzielenia odpowiedzi: t — tak, n — nie (inne odpowiedzi powinny być ignorowane).                           */

        System.out.println("\n________________________Zad 14_9_______________________________________________________________________________________");

        double r, pole;
        String czyDalej;
        Scanner wczytaj = new Scanner(System.in);
        do {
            do {
                System.out.print("Podaj promien koła: ");
                r = wczytaj.nextDouble();
                if (r <= 0) System.out.println("Promień musi być większy od zera");
            }
            while (r <= 0);
            System.out.printf("Pole tego koła wynosi: %.3f\n", Math.PI * r * r);
            do{
                System.out.println("Czy obliczamy dalej (t/n): ");
                czyDalej = wczytaj.next();
            }
            while (!czyDalej.equals("t") && !czyDalej.equals("n"));
            if (czyDalej.equals("n")) System.out.println("Koniec programu");
        }
        while (czyDalej.equals("t"));

    }
}
