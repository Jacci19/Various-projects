package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

import java.util.Scanner;

public class Zad_1_1__Pole_prostokata {

//Napisz program, który oblicza pole prostokąta. Wartości boków a i  b wprowadzamy z klawiatury.
//W programie należy przyjąć, że zmienne  a, b oraz  pole są typu  double (rzeczywistego).


    Zad_1_1__Pole_prostokata(){                                     //konstruktor

        System.out.println("Zad_1_1__Pole_prostokata\n");

        Scanner skanuj = new Scanner(System.in);
        System.out.print("Podaj dlugosc boku a: ");
        double a = skanuj.nextDouble();
        System.out.print("Podaj dlugosc boku b: ");
        double b = skanuj.nextDouble();
        System.out.println("Pole prostokata o bokach " +a+ " i " +b+ " wynosi: " + a*b);
    }
}

//w konsoli liczby niecałkowite podajemy z przecinkiem a nie z kropką