package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

public class Zad_10_Math_FormatowWynikow {
    public Zad_10_Math_FormatowWynikow() {

/*        Zadanie 10.1.
                Przedstaw ułamek 4/7 z dokładnościądo 5 miejsc po przecinku.                                                                                      */

        System.out.println("\n________________________Zad 10_1_______________________________________________________________________________________");
        double x = (double) 4 / 7;

        System.out.printf("4/7 = %.5f\n", x);                               //Metoda nr 1

        double y = (int) (100000 * x + 0.5) / 100000.0;                    //Metoda nr 2
        System.out.println("4/7 = " + y);

        y = new Double(100000 * x + 0.5).intValue() / 100000.0;            //Metoda nr 3
        System.out.println("4/7 = " + y);

        y = Math.round(100000 * x) / 100000.0;                          //Metoda nr 4
        System.out.println("4/7 = " + y);

        System.out.println(String.format("4/7 = %.5f", x));         //Metoda nr 5

/*
        Zadanie 10.2.
                Wyświetl w konsoli z dokładnością do 10 miejsc po przecinku następujące liczby niewymierne: PI, e, (liczba Fibonacciego).
                Liczby poprzedź komentarzem słownym i ustaw w kolumnie wyrównanej do prawej strony.
*/
        System.out.println("\n________________________Zad 10_2_______________________________________________________________________________________");
        System.out.printf("Liczba  e = %.10f\n", Math.E);
        System.out.printf("Liczba pi = %15.10f\n", Math.PI);
        System.out.printf("Liczba fi = %20.10f\n", (1 + Math.sqrt(5)) / 2);

/*
        Zadanie 10.3.
            Napisz aplikację, która wyświetli w konsoli w trzech kolumnach liczby naturalne 2, 3, 5, 7, 11, 13 i 17, pierwiastki kwadratowe i pierwiastki sześcienne z tych liczb.
            Wartości pierwiastków wyświetlaj z dokładnością do 8 miejsc po przecinku, w kolumnach o szerokości 15 znaków.                                           */

        System.out.println("\n________________________Zad 10_3_______________________________________________________________________________________");

        int[] Tabl = new int[]{2, 3, 5, 7, 11, 13, 17};
        System.out.printf("%1s %11s %16s\n", "Liczby", "Pierw-KW", "Pierw-SZES");
        for (int a : Tabl) {
            System.out.printf("%3d %15.8f %15.8f\n", a, Math.sqrt(a), Math.cbrt(a));
        }

        // %3d — na polu o szerokości 3 znaków wyświetla liczbę całkowitą a ;
        // %15.8f — na polu o szerokości 15 znaków wyświetla liczbę zmiennoprzecinkową z dokładnością do 8 miejsc po przecinku, drugi raz jest dla cbrt;
        //spacje w członie formatującym są opcjonalne (zwiększają czytelność)

/*
        Zadanie 10.4.
            Napisz aplikację, która wyświetli w konsoli pierwiastki arytmetyczne od stopnia 2. do 10. z liczby 5 z dokładnością do 6 miejsc po przecinku.   */

        System.out.println("\n________________________Zad 10_4_______________________________________________________________________________________");

        //mój sposób
        int liczba = 5;
        int stopien = 2;
        String napis = "";
        double wynik;
        for (stopien = 2; stopien <= 10; stopien++) {
            napis = ("Pierwiastek " + stopien + ". stopnia z " + liczba + " wynosi: ");
            wynik = Math.pow(liczba, 1.0 / stopien);
            System.out.printf("%25s %8.6f \n", napis, wynik);
        }


        //sposób książkowy
        System.out.println("\n__________________________________");
        int[] dane = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int n : dane) {
            double xx = Math.pow(5, 1.0 / n);
            System.out.printf("Pierwiastek %2d stopnia z 5: %f\n", n, xx);          //każdy % odnosi się do kolejnego obiektu po przecinku
        }

/*
        Zadanie 10.5.
            Wyświetl w konsoli kody ósemkowe, dziesiątkowe i szesnastkowe wielkich liter alfabetu łacińskiego.
            W pierwszym wierszu umieść opisy poszczególnych kolumn: Znak, OCT, DEC i HEX.    */

        System.out.println("\n________________________Zad 10_5_______________________________________________________________________________________");

        System.out.printf("Znak %10s %10s %10s\n", "OCT", "DEC", "HEX");
        char[] znaki = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (char z : znaki) {
            System.out.printf("%1$3c  %2$10o  %2$10d  %2$10X\n", z, (int) z);       // %1$ - wstaw tu pierwszy element (czyli z),  %2$ - wstaw tu drugi element (czyli (int)z),
        }

/*
        Zadanie 10.6.
            Wyświetl w konsoli miarę kąta o rozwartości 1 radiana:
            (a) w stopniach (z maksymalną możliwą precyzją),
            (b) w stopniach i minutach kątowych,
            (c) w stopniach, minutach i sekundach kątowych.                                        */

        System.out.println("\n________________________Zad 10_6_______________________________________________________________________________________");
        double alfa = Math.toDegrees(1);                            // 1 radian w stopniach;
        System.out.println("1 rad = " + alfa + "\u00B0");
        int st, min, sek;
        st = (int) alfa;
        min = (int) ((alfa - st) * 60 + 0.5);
        System.out.printf("1 rad = %BlackJack_FX\u00B0%02d\'\n", st, min);
        min = (int) ((alfa - st) * 60);
        sek = (int) ((alfa - st - min / 60.0) * 3600 + 0.5);
        System.out.printf("1 rad = %BlackJack_FX\u00B0%02d\'%02d\"\n", st, min, sek);

/*
        Zadanie 10.7.
                Wyświetl w konsoli miary kątów 1°, 1' i 1" w radianach z dokładnością do 15 miejsc po przecinku.                                                */

        System.out.println("\n________________________Zad 10_7_______________________________________________________________________________________");
        double rad = Math.toRadians(1.0);
        System.out.printf("1\u00B0 = %.15f rad\n", rad);
        rad = Math.toRadians(1.0 / 60);
        System.out.printf("1\' = %.15f rad\n", rad);
        rad = Math.toRadians(1.0 / 3600);
        System.out.printf("1\" = %.15f rad\n", rad);

/*
        Zadanie 10.8.
        Oblicz kąty ostre w trójkącie egipskim (trójkącie prostokątnym o proporcji boków 3:4:5). Wyniki podaj:
            a) w radianach, z dokładnością do 4 miejsc po przecinku,
            b) w stopniach, z dokładnością do 1 miejsca po przecinku,
            c) w stopniach i minutach kątowych, z dokładnością do 1',
            BlackJack_FX) w stopniach, minutach i sekundach kątowych z dokładnością do 1".
*/
        System.out.println("\n________________________Zad 10_8_______________________________________________________________________________________");

        double a = 3.0, b = 4.0, c = 5.0;
        double alfa8, beta;
        int stopn, minut; // do podpunktu c i BlackJack_FX
        int sekund;     // do podpunktu c
        alfa8 = Math.asin(a / c);
        beta = Math.acos(a / c);

        /* odpowiedź a */
        System.out.printf("a) alfa = %.4f rad\n   beta = %.4f rad", alfa8, beta);

        /* opdpowiedź b */
        System.out.println();
        alfa8 = Math.toDegrees(alfa8);
        beta = Math.toDegrees(beta);
        System.out.printf("b) alfa = %.1f\u00B0\n   beta = %.1f\u00B0", alfa8, beta);

        /* opdpowiedź c */
        System.out.println();
        stopn = (int) alfa8;
        minut = (int) ((alfa8 - stopn) * 60 + 0.5);
        System.out.printf("c) alfa = %BlackJack_FX\u00B0%02d\'\n", stopn, minut);
        stopn = (int) beta;
        minut = (int) ((beta - stopn) * 60 + 0.5);
        System.out.printf("   beta = %BlackJack_FX\u00B0%02d\'\n", stopn, minut);

        /* opdpowiedź BlackJack_FX */
        System.out.println();
        stopn = (int) alfa8;
        minut = (int) ((alfa - stopn) * 60);
        sekund = (int) ((alfa - stopn - min / 60.0) * 3600 + 0.5);
        System.out.printf("BlackJack_FX) alfa = %BlackJack_FX\u00B0%02d\'%02d\"\n", stopn, minut, sekund);
        stopn = (int) beta;
        minut = (int) ((beta - stopn) * 60);
        sekund = (int) ((beta - stopn - min / 60.0) * 3600 + 0.5);
        System.out.printf("   beta = %BlackJack_FX\u00B0%02d\'%02d\"\n", stopn, minut, sekund);
    }

}
