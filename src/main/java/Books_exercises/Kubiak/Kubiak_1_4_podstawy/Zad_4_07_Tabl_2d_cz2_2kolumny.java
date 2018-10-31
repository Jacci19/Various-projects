package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

public class Zad_4_07_Tabl_2d_cz2_2kolumny {

/*
    Napisz program, który w zadeklarowanej tablicy dwuwymiarowej 10×10 umieszcza w pierwszej kolumnie liczby od 0 do 9,
    w drugiej kwadraty tych liczb, natomiast w pozostałych kolumnach 0.
    Dodatkowo program powinien obliczać osobno sumę liczb znajdujących się w pierwszej oraz w drugiej kolumnie.                                                    */

    Zad_4_07_Tabl_2d_cz2_2kolumny() {
        System.out.println("\nZad_4_07_________________________________________________");
        int i, j;
        int suma1 = 0, suma2 = 0;
        int sizeX = 10, sizeY = 10;
        int[][] macierz = new int[sizeX][sizeY];

        for (i=0; i<sizeX; i++){
            for (j=0; j<sizeY; j++){
                if (j==0) {
                    macierz[i][j] = i;
                    suma1 += macierz[i][j];
                }
                else if (j==1){
                    macierz[i][j] = i*i;
                    suma2 += macierz[i][j];
                }
                else macierz[i][j] = 0;
                System.out.print(macierz[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("Suma pierwszej kolumny wynosi: " + suma1 + " a suma drugiej: " + suma2);
    }
}
