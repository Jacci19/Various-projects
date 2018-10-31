package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

import java.util.Scanner;

public class Zad_1_7_DodOdejMnoDziel {

// Napisz program, który oblicza sumę, różnicę, iloczyn i iloraz dla dwóch liczb  x i y wprowadzanych z klawiatury.
// W programie należy założyć, że zmienne  x i  y są typu  float (rzeczywistego).
// Dla zmiennych x, y, suma, roznica, iloczyn i iloraz należy przyjąć format ich wyświetlania na ekranie
// z dokładnością do dwóch miejsc po przecinku.

    Zad_1_7_DodOdejMnoDziel(){
        System.out.println("Zad_1_7_DodOdejMnoDziel\n");

        float x, y, suma, roznica, iloczyn, iloraz;
        Scanner wczytaj = new Scanner(System.in);

        System.out.print("Podaj liczbe x: ");
        x = wczytaj.nextFloat();
        System.out.print("Podaj liczbe y: ");
        y = wczytaj.nextFloat();

        suma = x+y;
        roznica = x-y;
        iloczyn = x*y;
        iloraz = x/y;

        System.out.printf("Dla liczb: x=" + "%2.2f",x);
        System.out.printf(" i y=" + "%2.2f\n",y);
        System.out.printf("Suma wynosi: " + "%2.2f\n" , suma);
        System.out.printf("Roznica wynosi: " + "%2.2f\n" , roznica);
        System.out.printf("Iloczyn wynosi: " + "%2.2f\n" , iloczyn);
        System.out.printf("Iloraz wynosi: " + "%2.2f\n" , iloraz);

    }
}

//printf ("jakiś tekst" + "formatowanie", formatowanaZmienna)