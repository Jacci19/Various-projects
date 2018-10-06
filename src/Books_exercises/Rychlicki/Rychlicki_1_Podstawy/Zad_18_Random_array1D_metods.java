package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Zad_18_Random_array1D_metods {
    public Zad_18_Random_array1D_metods() {

        //System.out.println("\n________________________Zad 18_1_______________________________________________________________________________________");

/*      Zadanie 18.1.
            Napisz program, który przeprowadzi symulację 100 rzutów kostkąi wyświetli wyniki w konsoli.                                            */

/*
        System.out.println("Symulacja 100 rzutów kostką:");
        for (int i = 1; i <= 100; i++) {
            int wynikKostki = 1 + (int) (Math.random() * 6);             //losowa liczba całkowita z przedziału 1-6   (wzór działa dobrze dla intów, dla floatów już nie)
            System.out.println("Rzut nr " + i + ": " + wynikKostki);
        }
*/


        //System.out.println("\n________________________Zad 18_2_______________________________________________________________________________________");
/*
        Zadanie 18.2.
            Napisz program, który przeprowadzi symulację 1000 rzutów kostką i sporządzi zestawienie wyników.                                        */

/*
        int w1=0, w2=0, w3=0, w4=0, w5=0, w6=0, inne=0, wynikKostki;
        for (int i = 1; i <= 1000; i++) {
            wynikKostki = 1 + (int) (Math.random() * 6);             //losowa liczba całkowita z przedziału 1-6
            switch (wynikKostki){
                case 1:  w1++; break;
                case 2:  w2++; break;
                case 3:  w3++; break;
                case 4:  w4++; break;
                case 5:  w5++; break;
                case 6:  w6++; break;
                default: inne++; break;
            }
        }
            System.out.println("Wynik 1 wypadł razy: " + w1);
            System.out.println("Wynik 2 wypadł razy: " + w2);
            System.out.println("Wynik 3 wypadł razy: " + w3);
            System.out.println("Wynik 4 wypadł razy: " + w4);
            System.out.println("Wynik 5 wypadł razy: " + w5);
            System.out.println("Wynik 6 wypadł razy: " + w6);
            System.out.println("Wynik inny wypadł razy: " + inne);
            System.out.println("Suma wyników: " + (w1+w2+w3+w4+w5+w6+inne));
*/
        //drugi sposób (z książki)
/*
        int[] wynik = new int[7];
        //Losowanie
        for(int i = 0; i < 1000; ++i) {
            int kostka = 1+(int)(Math.random()*6);
            wynik[0] += 1;        // licznik rzutów
            wynik[kostka] += 1; // licznik wyników
        }
        //Wynik
        System.out.println("Liczba rzutów: "+wynik[0]);
        for(int i = 1; i <= 6; ++i)
            System.out.printf("%d: %d\t - %.1f%%\n", i, wynik[i], wynik[i]*100.0/wynik[0]);
*/


        //System.out.println("\n________________________Zad 18_3_______________________________________________________________________________________");
/*
        Zadanie 18.3.
            Dwukrotnie rzucamy kostką do gry i zapisujemy sumę wyrzuconych oczek.
            Napisz program, który przeprowadzi symulację 3000 powtórzeń doświadczenia i sporządzi zestawienie wyników.                                       */

/*
        int[] wynik = new int[11];
        int ilosc = 3000;
        for (int i =0; i<ilosc; i++){
            int suma = (1+(int)(Math.random()*6)) + (1+(int)(Math.random()*6));

           // int kostka = 1+(int)(Math.random()*6);
           // int suma = kostka + kostka;                   // tak nie mozemy zobić (zamiast linii powyżej) bo wyjdą tylko parzyste sumy

            //System.out.println(suma);
            wynik[suma-2] ++;
        }
        for (int j=2; j<=12; j++){
            System.out.printf("%d:\t %d \t %.2f%%\n", j, wynik[j-2], (wynik[j-2]*100.0)/ilosc);
        }
*/


        //System.out.println("\n________________________Zad 18_4_______________________________________________________________________________________");
/*
        Zadanie 18.4.
            Utwórz funkcję rand() z dwoma parametrami a i b, losującą liczbę rzeczywistą należącą do przedziału a, b,
        gdzie a i b są liczbami całkowitymi i a < b. Wynik losowania powinien być podany z precyzją do 0,1. Napisz program demonstrujący działanie tej funkcji.             */

/*                      //metoda jest na samym dole w dziale "podprogramy", tu jest jej demonstracja działania. Metody nie mogą być w konstruktorze.
        int a, b;
        Scanner wczytaj = new Scanner(System.in);
        System.out.print("Podaj liczbę a: ");
        a = wczytaj.nextInt();
        System.out.print("Podaj liczbę b (>a): ");
        b = wczytaj.nextInt();

        for (int i=0; i<20; i++){
            System.out.printf("Losowa liczba z przedziału (a, b): %.1f\n", rand(a, b));
        }
*/


        //System.out.println("\n________________________Zad 18_5_______________________________________________________________________________________");
/*
        Zadanie 18.5.
            Napisz program losujący 500 liczb rzeczywistych z przedziału (0, 5).
        Przedstaw rozkład wylosowanych liczb w przedziałach o długości 1.                                                                   */

/*
        int[] n = new int[5];
        double x;
        System.out.println("Losowanie liczby z przedziału <0, 5)\n");
        Random rnd = new Random();
        for(int i = 0; i < 500; ++i) {
            x = 5*rnd.nextDouble();
            if (x < 1) n[0] +=1;
            else if (x < 2) n[1] +=1;
            else if (x < 3) n[2] +=1;
            else if (x < 4) n[3] +=1;
            else n[4] +=1;
        }
        for(int i = 0; i < 5; ++i)
            System.out.printf("<%d, %d): %4d - %4.1f%%\n", i, i+1, n[i], n[i]*100.0/500);
*/
        //drugi sposób
/*
        int[] n = new int[5];
        System.out.println("Losowanie liczby z przedziału <0, 5)\n");
        double x;
        for(int i = 0; i < 500; ++i) {
            x = 5*Math.random();
            n[(int)x] += 1;
        }
        for(int i = 0; i < 5; ++i)
            System.out.printf("<%d, %d): %4d - %4.1f%%\n", i, i+1, n[i], n[i]*100.0/500);           //4d - ta liczba zajmuje 4 pozycje
*/


        //System.out.println("\n________________________Zad 18_6_______________________________________________________________________________________");
/*
        Zadanie 18.6.
            Utwórz funkcję(metodęstatyczną) lotto() losującą 6 różnych liczb z 49 i zwracającą wynik w postaci tablicy liczb całkowitych.
        Napisz program demonstrujący działanie tej metody.              */


/*                                                      //moja próba. jest błędna bo w danej szóstce liczby mogą się powtarzać.
        System.out.println("20 Losowań Lotto");
        for (int i=1; i<=20; i++){
            System.out.printf("%2d)",i);
            for (int j=0; j<6; j++){
                System.out.printf("%6d," ,lotto()[j]);                           //6d - ta liczba zajmuje 6 pozycji (stosowane zamiast tabulatorów)
            }
            System.out.println();
        }
*/
        //sposób z książki
/*
        System.out.println("10 Losowań Lotto");
        for (int i=1; i<=10; i++) {
            byte[] wynik = lotto();
            Arrays.sort(wynik);
            for (byte x : wynik) System.out.print(x + "\t ");
            System.out.println();
        }
*/


        System.out.println("\n________________________Zad 18_7_______________________________________________________________________________________");
/*
        Zadanie 18.7.
            Utwórz funkcję (metodę statyczną) lotto2() z dwoma parametrami m i n, losującą m różnych liczb spośród liczb od 1 do n
            i zwracającą wynik w postaci tablicy liczb całkowitych. Napisz program demonstrujący działanie tej metody.

*/
        System.out.println("10 Losowań Lotto2__(m liczb z puli n liczb)");
        for (int i=1; i<=10; i++) {
            int[] wynik = lotto2(3, 100);
            Arrays.sort(wynik);
            for (int x : wynik) System.out.print(x + "\t ");
            System.out.println();
        }


    } //=================================================================podprogramy=============================================================================

    //podprogram do zadania 18.4

    private double rand(int a, int b) {
        //return a + (Math.random() * b);                     //działa nieprawidłowo (wyniki wychodza poza górny zakres)
        int n = (b - a) * 10 + 1;
        return a + ((int) (Math.random() * n)) / 10.0;
    }


    //podprogram do zadania 18.6


    /*
        private int[] lotto(){                                      //moja próba. Jest błędna bo w danej szóstce liczby mogą się powtarzać.
            int[] tabl = new int[6];
            for (int i=0; i<6; i++){
                tabl[i] = 1 + (int)(Math.random()*49);
            }
            return tabl;
        }
    */

    static byte[] lotto() {
        byte[] tmp = new byte[6];
        byte j = 0;
        //tmp[j++] = (byte)(1+49*Math.random());                //ten wers może zastąpić dwa poniższe
        tmp[j] = (byte) (1 + 49 * Math.random());
        j++;
        do {
            byte n = (byte) (1 + 49 * Math.random());
            boolean jest = false;
            for (byte i = 0; i < j; ++i)
                if (n == tmp[i]) jest = true;
            if (!jest) {
                //tmp[j++] = n;                                 //ten wers może zastąpić dwa poniższe
                tmp[j] = n;
                j++;
            }
        } while (j < 6);
        return tmp;
    }


    //podprogram do zadania 18.7                    //metoda ta losuje"m" różnych liczb spośród liczb od 1 do "n"

    static int[] lotto2(int m, int n) {
        int[] tabl = new int[m];
        int j = 0;                                              //index tablicy Tabl
        tabl[j] = (int) (1 + Math.random() * n);                //element tablicy o indeksie j <- losowa liczba całkowitaz zakresu (1, n)
        j++;                                                    //zwiększenie indeksu j
        do {                                                    //rób tą pętlę m razy
            int k = (int) (1 + Math.random() * n);              // k -< losowa liczba całkowitaz zakresu (1, n)
            boolean jest = false;
            for (int i = 1; i < j; i++) {
                if (k == tabl[i]) jest = true;                  //jeśli któryś z elementów tablicy tabl zawiera już element k to nie wpisuj go do tabl
            }
            if (!jest){
                tabl[j] = k;                                    // jeśli nie to wpisz go do tabl
                j++;
            }
        }while (j < m);
    return tabl;
    }


}
