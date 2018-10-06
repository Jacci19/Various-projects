package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

public class Zad_4_01_do_4_02_Tabl_1D {

//    Napisz program, ktory w 10-elementowej tablicy jednowymiarowej o nazwie dane umieszcza liczby od 0 do 9

    Zad_4_01_do_4_02_Tabl_1D() {
        System.out.println("\nZad 4_1__________________________________________________");

        int[] tablica = new int[10];
        int i;
        for (i=0; i<tablica.length; i++){
            tablica[i] = i;
            System.out.println("Tablica[" + i + "] = " + tablica[i]);
        }

        //    Napisz program, który w 10-elementowej tablicy jednowymiarowej o nazwie dane umieszcza liczby od 9 do 0
        System.out.println("\nZad 4_2__________________________________________________");
        for (i=0; i<tablica.length; i++){
            tablica[i] = tablica.length-1-i;
            System.out.println("Tablica[" + i + "] = " + tablica[i]);
        }
    }
}
