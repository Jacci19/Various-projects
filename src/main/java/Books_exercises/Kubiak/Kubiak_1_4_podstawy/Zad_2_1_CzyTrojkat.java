package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

import java.util.Scanner;

public class Zad_2_1_CzyTrojkat {

//    Napisz program, który sprawdza dla trzech boków trójkąta  a, b, c wprowadzonych z klawiatury,
//    czy tworzą one trójkąt prostokątny (zakładamy, że  a > 0 ,  b > 0 ,  c > 0 ).

    Zad_2_1_CzyTrojkat(){
        System.out.println("Zad_2_1_CzyTrojkat\n");
        int a,b,c;
        Scanner wczytaj = new Scanner(System.in);

        System.out.print("Podaj dlugosc boku a: ");
        a = wczytaj.nextInt();
        System.out.print("Podaj dlugosc boku b: ");
        b = wczytaj.nextInt();
        System.out.print("Podaj dlugosc boku c: ");
        c = wczytaj.nextInt();

        if (a<b+c && b<a+c && c<a+b){
            System.out.println("Te odcinki tworza trojkat");
            if ((a*a == b*b + c*c) || (b*b == a*a + c*c) || (c*c == a*a + b*b)){
                System.out.println("Jest to trojkat prostokatny");
            }
            else{
                System.out.println("To nie jest trojkat prostokatny");
            }
        }
        else{
            System.out.println("Te odcinki NIE tworza trojkata");
        }
    }
}









/*
warunki aby był trójkąt:

a < b+c
b < a+c
c < a+b

warunki aby był trójkąt prostokątny:

a*a = b*b + c*c
b*b = a*a + c*c
c*c = a*a + b*b






*/