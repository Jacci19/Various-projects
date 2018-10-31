package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

import static java.lang.System.*;               //teraz zamiast "System.out.print" można pisać: "out.print"

public class Zad_03 {

    /*
    Zadanie 3.5.
        Utwórz tablice zawierajaca znaki slowa programowanie. Napisz program odwracajcy kolejnosc znaków w tablicy
        Dodalem jak wyswietlają się tablice różnych typów
*/

    Zad_03() {
        System.out.println("\n________________________Zad 3_5_______________________________________________________________________________________");

        char TabChar[] = {'p', 'r', 'o', 'g', 'r', 'a', 'm', 'o', 'w', 'a', 'n', 'i', 'e'};
        System.out.print("Normalna kolejność znaków: ");
        System.out.println(TabChar);

        for (int i = 0, j = TabChar.length - 1; i < j; i++, j--) {                //połączenie dwóch petli for w jedną
            char tmp = TabChar[i];
            TabChar[i] = TabChar[j];
            TabChar[j] = tmp;
        }
        System.out.print("Odwrotna kolejność znaków: ");
        System.out.println(TabChar);

        /*
        //Zadanie 3.6.Przeanalizuj kod__________________________________________________________________________________*/
        out.println("\n________________________Zad 3_6_______________________________________________________________________________________");
        // Informacje o metodzie
        out.println("\n\nKlasa: java.lang.Character");
        out.println("Metoda statyczna: digit\n");
        out.println("static int digit(int ch, int radix)");
        out.println("Returns the numeric value of the character ch in the specified radix.");
        out.println();

        // Przykładowa tablica znaków
        char znak[] = {'E', 'u', 'r', 'o', ' ', '2', '0', '1', '2'};

        // Demonstracja działania metody
        out.println("Wartość znaku jako cyfry w układzie dziesiątkowym (radix = 10)");
        for(char z : znak) out.println("Znak: "+z+"  Cyfra: "+Character.digit(z, 10));
        out.println("Uwaga: -1 oznacza, że znak nie jest cyfrą w tym układzie liczbowym.");
        out.println();

        out.println("Wartość znaku jako cyfry w układzie szesnastkowym (radix = 16)");
        for(char z : znak) out.println("Znak: "+z+"  Cyfra: "+Character.digit(z, 16));
        out.println("Uwaga: -1 oznacza, że znak nie jest cyfrą w tym układzie liczbowym.");

        /*
        //Zadanie 3.7.Tabela z cyframi 0-9 z wykorzystaniem ASCII__________________________________________________________________________________*/
        out.println("\n________________________Zad 3_7_______________________________________________________________________________________");
        char[] cyfry = new char[10];
        for(int i = 0; i < 10; i++)
            cyfry[i] = (char)(i+48);
        System.out.print("Cyfry układu dziesiątkowego: ");
        System.out.println(cyfry);

        /*
        //Zadanie 3.8.Tabela z cyframi układu szesnastkowego__________________________________________________________________________________*/
        out.println("\n________________________Zad 3_8_______________________________________________________________________________________");
        char[] cyfry16 = new char[16];
        for(int i = 0; i < 10; ++i)
            cyfry16[i] = (char)(i+48);
        for(int i = 10; i < 16; ++i)
            cyfry16[i] = (char)(i+55);
        System.out.print("Cyfry układu szesnastkowego: ");
        System.out.println(cyfry16);

        System.out.println("Druga metoda, z użyciem Character.forDigit: ");
        for(int i = 0; i < 16; i++){
            System.out.print(Character.forDigit(i, 16));        //fordigit zamienia cyfry układu dziesiętnego na znaki układu szesnastkowego
        }
        System.out.println("\n...a żeby były wielkie litery to tak:");
        for(int i = 0; i < 16; i++){
            System.out.print(Character.toUpperCase(Character.forDigit(i, 16)));                }


    }
}