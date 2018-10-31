package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

import java.util.Scanner;

public class Zad_1_4_Objetosc_kuli {

//    Napisz program, który oblicza objętość kuli o promieniu  r. Wartość promienia wprowadzamy z klawiatury.
//    W programie należy przyjąć, że zmienne: promień r i objetosc, są typu double (rzeczywistego).
//    Dla tych zmiennych należy przyjąć format wyświetlania na ekranie z dokładnością do dwóch miejsc po przecinku.

    Zad_1_4_Objetosc_kuli(){
        System.out.println("Zad_1_4_Objetosc_kuli\n");

        Scanner skanuj = new Scanner(System.in);
        System.out.print("Podaj promien kuli: ");
        double r = skanuj.nextDouble();
        double objetosc = 4/3 * Math.PI * Math.pow(r, 3);

        System.out.print("Objetosc kuli o promieniu: ");
        System.out.printf("%3.2f" , r);
        System.out.print(" wynosi: ");
        System.out.printf("%3.2f\n", objetosc);
    }
}


// Objetość kuli = 4/3 * pi * R^3
