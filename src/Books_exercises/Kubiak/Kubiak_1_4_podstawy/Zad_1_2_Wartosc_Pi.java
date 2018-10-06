package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

public class Zad_1_2_Wartosc_Pi {

//Napisz program, który wyświetla na ekranie komputera wartość predefiniowanej stałej π = 3,14…
//Należy przyjąć format wyświetlania tej stałej z dokładnością do pięciu miejsc po przecinku.

    Zad_1_2_Wartosc_Pi(){
        System.out.println("Zad_1_2_Wartosc_Pi\n");

        System.out.println("Pi = " + Math.PI);                  //PI standardowe
        System.out.printf("Pi = " + "%6.5f\n", Math.PI);        //PI ograniczone do 5 miejsc po przecinku
    }

}






//printf - drukuj tekst sformatowany

//  %6.5f - oznacza przyznanie sześciu pól na liczbę typu  float, w tym pięciu pól na jej część ułamkową;
//  %d —  integer
//  %e —  double
//  %f —  float
//  %4d — oznacza przyznanie czterech pól na liczbę typu całkowitego
