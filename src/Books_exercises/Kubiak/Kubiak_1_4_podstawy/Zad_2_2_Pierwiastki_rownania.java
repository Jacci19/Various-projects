package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

import java.util.Scanner;

public class Zad_2_2_Pierwiastki_rownania {

/*  Napisz program, który oblicza pierwiastki równania kwadratowego  ax^2 + bx + c = 0 z wykorzystaniem instrukcji warunkowej if,
gdzie zmienne  a, b i c to liczby rzeczywiste wprowadzane z klawiatury.
Dla zmiennych  a, b, c, x1 oraz x2 należy przyjąć format wyświetlania ich na ekranie z dokładnością do dwóch miejsc po przecinku.
*/
    Zad_2_2_Pierwiastki_rownania(){
        System.out.println("Zad_2_2_Pierwiastki_rownania\n");
        System.out.println("Program wylicza pierwiastki rownania: ax^2 + bx + c = 0");
        double a, b, c, x1, x2, delta;
        Scanner wczytaj = new Scanner(System.in);

        do {
            System.out.print("Podaj wspolczynnik a: ");
            a = wczytaj.nextFloat();
            if (a==0) System.out.println("Wspolczynnik a musi byc rozny od zera");
        }
        while (a == 0);

        System.out.print("Podaj wspolczynnik b: ");
        b = wczytaj.nextFloat();
        System.out.print("Podaj wspolczynnik c: ");
        c = wczytaj.nextFloat();

        delta = b*b - 4*a*c;

        if (delta > 0){
            x1 = (-b - Math.sqrt(delta))/(2*a);
            x2 = (-b + Math.sqrt(delta))/(2*a);
            System.out.println("Rownanie ma dwa pierwiastki:");
            System.out.printf("x1 = " + "%2.2f\n", x1);
            System.out.printf("x2 = " + "%2.2f\n", x2);
        }
        else if (delta == 0){
            x1 = (-b )/(2*a);
            System.out.println("Rownanie ma jeden (podwojny) pierwiastek:");
            System.out.printf("x1 = " + "%2.2f\n", x1);

        }
        else if (delta < 0){
            System.out.println("Rownanie NIE MA pierwiastkow:");
        }
    }
}



/*
Pierwiastki równania kwadratowego  ax^2 + bx + c = 0

delta = b^2 - 4*a*c

Gdy: delta > 0:
x1 = ( -b - sqrt(delta) ) / (2*a)
x2 = ( -b + sqrt(delta) ) / (2*a)

Gdy: delta = 0:
x1 =  -b  / (2*a)

Gdy: delta < 0:
nie ma pierwiastków


*/