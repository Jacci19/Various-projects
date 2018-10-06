package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

public class Zad_3_1_do_3_12_For_While {

/*  Zadania od 3_1 do 3_12 (z pominięciem mało istotnych)            */

    Zad_3_1_do_3_12_For_While(){
        System.out.println("Zad_3_1_For3x\n");

        //____________________________________________________________________Zad_3_1
        //Napisz program, który za pomocą instrukcji "FOR" dla danych wartości x zmieniających się od 0 do 10 oblicza wartość funkcji  y = 3x.

        System.out.println("\nWykorzystanie petli for do wyswietlenia wartosci funkcji y=3x:");
        int i, wynikSumy;

        for (i=0; i<=10; i++){
            System.out.println("x = " + i +"\t y = " + 3*i);                                //  \t - tabulacja (do wyrównania kolumn)
        }

        //____________________________________________________________________Zad_3_2
        System.out.println("\nTo samo przy uzyciu petli DO...WHILE:________________________________________");
        i=0;
        do{
            System.out.println("x = " + i +"\t y = " + 3*i);
            i++;
        }
        while (i<=10);

        //____________________________________________________________________Zad_3_3
        System.out.println("\nTo samo przy uzyciu petli WHILE:____________________________________________");
        i=0;
        while (i<=10){
            System.out.println("x = " + i +"\t y = " + 3*i);
            i++;
        }

        //____________________________________________________________________Zad_3_4
        //Napisz program, który za pomocą instrukcji "FOR" wyświetla liczby całkowite od 1 do 20

        System.out.println("\n3_4__FOR - Liczby od 1 do 20:____________________________________________________");
        for (i=1; i<=20; i++){
            if (i<20) System.out.print(i + ", ");
            else System.out.println(i + ".");
        }
        //____________________________________________________________________Zad_3_5
        //Napisz program, który za pomocą instrukcji  "DO...WHILE" wyświetla liczby całkowite od 1 do 20.

        System.out.println("\n3_5__DO...WHILE - Liczby od 1 do 20:_____________________________________________");
        i=1;
        do{
            if (i<20) System.out.print(i + ", ");
            else System.out.println(i + ".");
            i++;
        }
        while (i<=20);
        //____________________________________________________________________Zad_3_6
        //Napisz program, który za pomocą instrukcji  "WHILE" wyświetla liczby całkowite od 1 do 20.

        System.out.println("\n3_6__WHILE - Liczby od 1 do 20:__________________________________________________");
        i=1;
        while (i<=20){
            if (i<20) System.out.print(i + ", ");
            else System.out.println(i + ".");
            i++;
        }

        //____________________________________________________________________Zad_3_7
        //Napisz program, który przy użyciu instrukcji "FOR" sumuje liczby całkowite od 1 do 100.

        System.out.println("\n3_7__FOR - sumuje liczby od 1 do 100:____________________________________________");
        wynikSumy=0;
        for (i=1; i<=100; i++){
            wynikSumy +=i;
        }
        System.out.print("Suma liczb calkowitych od 1 do 100 wynosi: " + wynikSumy);

        //____________________________________________________________________Zad_3_10
        //Napisz program, który przy użyciu instrukcji "FOR" sumuje liczby parzyste od 1 do 100.

        System.out.println("\n\n3_10__FOR - sumuje liczby parzyste od 1 do 100:________________________________");
        wynikSumy=0;
        for (i=1; i<=100; i++){
           if (i%2 == 0) wynikSumy +=i;
        }
        System.out.print("Suma liczb parzystych od 1 do 100 wynosi: " + wynikSumy);

        //____________________________________________________________________Zad_3_11
        //Napisz program, który przy użyciu instrukcji "DO...WHILE" sumuje liczby parzyste od 1 do 100.

        System.out.println("\n\n3_11__DO...WHILE - sumuje liczby parzyste od 1 do 100:_________________________");
        i=1;
        wynikSumy=0;
        do{
            if (i%2 == 0) wynikSumy +=i;
            i++;
        }
        while (i<=100);
        System.out.print("Suma liczb parzystych od 1 do 100 wynosi: " + wynikSumy);

        //____________________________________________________________________Zad_3_12
        //Napisz program, który przy użyciu instrukcji "WHILE" sumuje liczby parzyste od 1 do 100.
        System.out.println("\n\n3_12__WHILE - sumuje liczby parzyste od 1 do 100:______________________________");
        i=1;
        wynikSumy=0;
        while (i<=100){
            if (i%2 == 0) wynikSumy +=i;
            i++;
        }
        System.out.print("Suma liczb parzystych od 1 do 100 wynosi: " + wynikSumy);

    }
}


