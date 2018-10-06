package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

public class Zad_4_08_Tabl_2d_cz3_kolumNaWiersze {

/*
    Dane są dwie tablice dwuwymiarowe 10×10 o nazwach  A i B. Tablica "A" w pierwszej kolumnie ma same 0, w drugiej same 1, itd.
    Tablica B zawiera same zera. Napisz program, który przepisuje zawartość tablicy "A" do tablicy "B" , zamieniając kolumny na wiersze.
*/

    Zad_4_08_Tabl_2d_cz3_kolumNaWiersze() {
        System.out.println("Zad_4_8______________________________________________");
        int i, j;
        int sizeX = 10, sizeY = 10;
        int[][] TablA = new int[sizeX][sizeY];
        int[][] TablB = new int[sizeX][sizeY];

        System.out.println("\n Tablica A: ");
        for (i=0; i<sizeX; i++){
            for (j=0; j<sizeY; j++){
                TablA[i][j] = j;
                System.out.print(TablA[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n Tablica B: ");
        for (i=0; i<sizeX; i++){
            for (j=0; j<sizeY; j++){
                TablB[i][j] = 0;
                System.out.print(TablB[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n Tablica B po przepisaniu: ");
        for (i=0; i<sizeX; i++){
            for (j=0; j<sizeY; j++){
                TablB[i][j] = TablA[j][i];                  //zamiana kolumn na wiersze
                System.out.print(TablB[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
