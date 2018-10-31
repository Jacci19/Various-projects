package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

import java.util.Scanner;

public class Zad_15_While {
    public Zad_15_While() {

/*
        Zadanie 15.7.
            Napisz program wyznaczający najmniejszą wspólną wielokrotność (NWW) dla pary liczb całkowitych dodatnich.
            Wskazówka: Obliczaj wielokrotności jednej liczby i sprawdzaj, czy obliczona wielokrotność jest wielokrotnością drugiej liczby (czy dzieli się bez reszty przez drugąliczbę).*/

/*
        System.out.println("\n________________________Zad 15_7_______________________________________________________________________________________");

        int a, b, nnw, mniejsza, x;
        Scanner wczytaj = new Scanner(System.in);
        System.out.print("Podaj dwie liczby całkowite dodatnie.\npierwsza liczba: ");
        a = wczytaj.nextInt();
        System.out.print("druga liczba: ");
        b = wczytaj.nextInt();

        if (a > b) {
            nnw = a;
            x = a;
            mniejsza = b;
        } else {
            nnw = b;
            x = b;
            mniejsza = a;
        }
        while (nnw % mniejsza != 0) {
            nnw += x;
            System.out.println("nnw: " + nnw + " mn: " + mniejsza);
        }

        System.out.println("Najmniejsza wspólna wielokrotność tych liczb wynosi: " + nnw);
*/




/*
        Zadanie 15.8.
            Korzystając z algorytmu Euklidesa, napisz program wyznaczający największy wspólny dzielnik (NWD) pary liczb całkowitych dodatnich.

           ( Algorytm Euklidesa opiera sięna spostrzeżeniu, że dla dowolnych dwóch liczb naturalnych mniejsza spośród nich i ich różnica
            mają taki sam największy wspólny dzielnik (NWD) jak te liczby.)                                                                                      */


        System.out.println("\n________________________Zad 15_8_______________________________________________________________________________________");

        int a, b, mniejsza, roznica, nwd;
        Scanner wczytaj = new Scanner(System.in);
        System.out.print("Program obliczy największy wspólny dzielnik. Podaj dwie liczby.\npierwsza liczba: ");
        do{
            a = wczytaj.nextInt();
        }
        while (a<=0);
        System.out.print("druga liczba: ");
        do{
            b = wczytaj.nextInt();
        }
        while (b<=0);
        wczytaj.close();

        while (a != b)
            if (a > b) {
                System.out.print("a = a - b        " + a + " - " + b);
                a = a - b;
                System.out.println(" = " + a + "          a = " + a + "    b = " + b);
            } else {
                System.out.print("b = b - a        " + b + " - " + a);
                b = b - a;
                System.out.println(" = " + b + "          a = " + a + "    b = " + b);

            }
        System.out.printf("NWD(m, n) = %BlackJack_FX\n", a);











    }
}
