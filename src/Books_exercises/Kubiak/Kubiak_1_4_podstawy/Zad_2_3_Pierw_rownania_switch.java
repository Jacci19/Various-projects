package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

import java.util.Scanner;

public class Zad_2_3_Pierw_rownania_switch {

/*
   Napisz program, który oblicza pierwiastki równania kwadratowego  ax^2+bx+c = 0 z wykorzystaniem instrukcji wyboru switch,
   gdzie zmienne  a, b, c to liczby rzeczywiste wprowadzane z klawiatury.
   Dla zmiennych  a ,  b ,  c ,  x1 oraz  x2 należy przyjąć format wyświetlania ich na ekranie z dokładnością do dwóch miejsc po przecinku.
   ---czyli to samo co w 2_2 tylko ze switch */

    Zad_2_3_Pierw_rownania_switch(){

        System.out.println("Zad_2_3_Pierw_rownania_switch\n");
        System.out.println("Program wylicza pierwiastki rownania: ax^2 + bx + c = 0");
        double a, b, c, x1, x2, delta;
        char liczbaPierwiastkow = 0;
        Scanner wczytaj = new Scanner(System.in);

        do {
            System.out.print("Podaj wspołczynnik a: ");
            a = wczytaj.nextFloat();
            if (a==0) System.out.println("Wspolczynnik a musi być rozny od zera");
        }
        while (a == 0);

        System.out.print("Podaj wspolczynnik b: ");
        b = wczytaj.nextFloat();
        System.out.print("Podaj wspolczynnik c: ");
        c = wczytaj.nextFloat();

        delta = b*b - 4*a*c;

        if (delta > 0) liczbaPierwiastkow = 2;
        if (delta == 0) liczbaPierwiastkow = 1;
        if (delta < 0) liczbaPierwiastkow = 0;

        switch (liczbaPierwiastkow) {
            case 0:
                System.out.println("Rownanie NIE MA pierwiastkow:");
                break;
            case 1:
                x1 = (-b )/(2*a);
                System.out.println("Rownanie ma jeden (podwojny) pierwiastek:");
                System.out.printf("x1 = " + "%2.2f\n", x1);
                break;
            case 2:
                x1 = (-b - Math.sqrt(delta))/(2*a);
                x2 = (-b + Math.sqrt(delta))/(2*a);
                System.out.println("Rownanie ma dwa pierwiastki:");
                System.out.printf("x1 = " + "%2.2f\n", x1);
                System.out.printf("x2 = " + "%2.2f\n", x2);
                break;
        }
    }
}