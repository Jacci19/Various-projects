package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

public class Zad_3_19_do_3_21_TablMnoz {

/*  Napisz program wyświetlający tabliczkę mnożenia dla liczb od 1 do 100 z wykorzystaniem podwójnej pętli  for                                        */

    Zad_3_19_do_3_21_TablMnoz() {
        System.out.println("Zad_3_19_do_3_21_TablMnoz");
        int n = 11;

        //Zad 3_19 Rozwiązanie pętlą FOR________________________________________________________________________________

        System.out.println("Zad_3_19__________FOR__________Tabliczka Mnozenia");
        int i,j;
        for (i=1; i<n; i++) {
            for (j = 1; j < n; j++) {
                System.out.print(i*j + "\t");
            }
            System.out.println();
        }
        //Zad 3_20 Rozwiązanie pętlą DO...WHILE_________________________________________________________________________

        System.out.println("\nZad_3_20__________DO...WHILE__________Tabliczka Mnozenia");
        i=1; j=1;
        do{
            do{
                System.out.print(i*j + "\t");
                j++;
            }
            while (j<n);
            System.out.println();
            i++; j=1;
        }
        while (i<n);


        //Zad 3_21 Rozwiązanie pętlą WHILE______________________________________________________________________________

        System.out.println("\nZad_3_21__________WHILE__________Tabliczka Mnozenia");
        i=1; j=1;
        while (i < n){
            while (j<n){
                System.out.print(i*j + "\t");
                j++;
            }
            System.out.println();
            i++; j=1;
        }
    }
}
