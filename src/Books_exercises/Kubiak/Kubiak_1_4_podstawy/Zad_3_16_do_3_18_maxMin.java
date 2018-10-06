package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

import java.util.Random;

public class Zad_3_16_do_3_18_maxMin {

/*  Napisz program, który za pomocą instrukcji for znajduje największą i najmniejszą liczbę ze zbioru n wylosowanych liczb
    całkowitych od 0 do 99 (w zadaniu  n = 5 ) oraz oblicza średnią ze wszystkich wylosowanych liczb.                                        */

    Zad_3_16_do_3_18_maxMin(){
        System.out.println("Zad_3_16_do_3_18_maxMin");
        Random r = new Random();
        int n = 5;
        int i, liczba, suma =0, min = 99, max = 0;

        //Zad 3_16 Rozwiązanie pętlą FOR________________________________________________________________________________

        System.out.print("\nZad_3_16__________FOR__________Wylosowane liczby: ");
        for (i=0; i<n; i++){
            liczba = r.nextInt(100);
            suma += liczba;
            System.out.print(liczba +", ");
            if (liczba < min) min = liczba;
            if (liczba > max) max = liczba;
        }
        System.out.println("\nMin = " + min + ", Max = " + max);
        System.out.println("Suma = " + suma + ", srednia = " + suma/n );

        //Zad 3_17 Rozwiązanie pętlą DO...WHILE_________________________________________________________________________

        System.out.print("\nZad_3_17__________DO...WHILE__________Wylosowane liczby: ");
        i=0; suma=0; min = 99; max = 0;
        do{
            liczba = r.nextInt(100);
            suma += liczba;
            System.out.print(liczba +", ");
            if (liczba < min) min = liczba;
            if (liczba > max) max = liczba;
            i++;
        }
        while (i<n);
        System.out.println("\nMin = " + min + ", Max = " + max);
        System.out.println("Suma = " + suma + ", srednia = " + suma/n );


        //Zad 3_18 Rozwiązanie pętlą WHILE______________________________________________________________________________

        System.out.print("\nZad_3_18__________WHILE__________Wylosowane liczby: ");
        i=0; suma=0; min = 99; max = 0;
        while (i<n)  {
            liczba = r.nextInt(100);
            suma += liczba;
            System.out.print(liczba +", ");
            if (liczba < min) min = liczba;
            if (liczba > max) max = liczba;
            i++;
        }
        System.out.println("\nMin = " + min + ", Max = " + max);
        System.out.println("Suma = " + suma + ", srednia = " + suma/n );
    }
}
