package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

public class Zad_3_22_do_3_24_literyAZ {

/*  Napisz program, który wyświetla duże litery alfabetu od A do Z i od Z do A z wykorzystaniem pętli  for                                             */

    Zad_3_22_do_3_24_literyAZ() {
        System.out.println("Zad_3_22_do_3_24_literyAZ");
        char i;
        //Zad 3_22 Rozwiązanie pętlą FOR________________________________________________________________________________
        System.out.println("\nZad_3_22__________FOR__________Litery AZ-ZA: \n");

        for (i='A'; i<='Z'; i++){
            if (i < 'Z') System.out.print(i + ", ");
            else System.out.println(i + ".");
        }
        for (i='Z'; i>='A'; i--){
            if (i > 'A') System.out.print(i + ", ");
            else System.out.println(i + ".");
        }


        //Zad 3_23 Rozwiązanie pętlą DO...WHILE_________________________________________________________________________
        System.out.println("\nZad_3_23__________DO...WHILE__________Litery AZ-ZA: ");
        i = 'A';
        do{
            if (i < 'Z') System.out.print(i + ", ");
            else System.out.println(i + ".");
            i++;
        }
        while (i <= 'Z');

        i = 'Z';
        do{
            if (i > 'A') System.out.print(i + ", ");
            else System.out.println(i + ".");
            i--;
        }
        while (i >= 'A');


        //Zad 3_24 Rozwiązanie pętlą WHILE______________________________________________________________________________
        System.out.println("\nZad_3_24__________WHILE__________Litery AZ-ZA: ");
        i = 'A';
        while (i <= 'Z'){
            if (i < 'Z') System.out.print(i + ", ");
            else System.out.println(i + ".");
            i++;
        }
        i = 'Z';
        while (i >= 'A'){
            if (i > 'A') System.out.print(i + ", ");
            else System.out.println(i + ".");
            i--;
        }
    }
}
