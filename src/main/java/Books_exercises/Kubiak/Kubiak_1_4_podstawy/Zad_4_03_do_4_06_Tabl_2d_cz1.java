package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

public class Zad_4_03_do_4_06_Tabl_2d_cz1 {

/*  Zad 4_3_Napisz program, który w zadeklarowanej tablicy dwuwymiarowej 10×10 o nazwie  macierz umieszcza na przekątnej liczbę 1, a poza przekątną 0.
    Dodatkowo program powinien obliczać sumę elementów wyróżnionych w tablicy, tj. tych znajdujących się na jej przekątnej.                                                 */

   Zad_4_03_do_4_06_Tabl_2d_cz1() {
       System.out.println("\nZad 4_3__________________________________________________");
       int sizeX = 10, sizeY = 10, suma = 0;
       int i, j;
       int[][] macierz = new int[sizeX][sizeY];

       for (i=0; i < sizeY; i++){
           for (j=0; j < sizeX; j++){
            if (i==j) {
                macierz[i][j] = 1;
                suma += macierz[i][j];
            }
            else macierz[i][j] = 0;
               System.out.print(macierz[i][j] + "\t");
           }
           System.out.println();
       }
       System.out.println("Suma cyfr na przekatnej wynosi " + suma + "\n");


/*
       Zad 4_4_Napisz program, który w zadeklarowanej tablicy dwuwymiarowej 10×10 o nazwie macierz umieszcza na przekątnej liczby od 0 do 9, a poza przekątną liczbę 0.
       Dodatkowo program powinien obliczać sumę elementów wyróżnionych w tablicy (znajdujących się na przekątnej).                                                       */

       System.out.println("\nZad 4_4__________________________________________________");
       suma = 0;

       for (i=0; i < sizeY; i++){
           for (j=0; j < sizeX; j++){
               if (i==j) {
                   macierz[i][j] = j;                   //tylko ten wers jest inny niż w zad 4_3
                   suma += macierz[i][j];
               }
               else macierz[i][j] = 0;
               System.out.print(macierz[i][j] + "\t");
           }
           System.out.println();
       }
       System.out.println("Suma cyfr na przekatnej wynosi " + suma + "\n");


/*
       Zad 4_5_Napisz program, który w zadeklarowanej tablicy dwuwymiarowej 10×10 o nazwie macierz umieszcza na drugiej przekątnej liczbę 1, a poza przekątną 0.
       Program dodatkowo powinien obliczać sumę wyróżnionych elementów.                                                                                                 */

       System.out.println("\nZad 4_5__________________________________________________");
       suma = 0;

       for (i=0; i < sizeY; i++){
           for (j=0; j < sizeX; j++){
               if (j==sizeY-i-1) {                       //tylko ten wers jest inny niż w zad 4_3
                   macierz[i][j] = 1;
                   suma += macierz[i][j];
               }
               else macierz[i][j] = 0;
               System.out.print(macierz[i][j] + "\t");
           }
           System.out.println();
       }
       System.out.println("Suma cyfr na drugiej przekatnej wynosi " + suma + "\n");


/*
       Zad 4_6_Napisz program, który w zadeklarowanej tablicy dwuwymiarowej 10×10 o nazwie macierz umieszcza na drugiej przekątnej liczby od 0 do 9, a poza przekątną liczbę 0.
       Dodatkowo program powinien obliczać sumę elementów wyróżnionych w tablicy (znajdujących się na przekątnej).                                                       */

       System.out.println("\nZad 4_6__________________________________________________");
       suma = 0;

       for (i=0; i < sizeY; i++){
           for (j=0; j < sizeX; j++){
               if (j==sizeY-i-1) {                      //tylko ten wers jest inny niż w zad 4_3
                   macierz[i][j] = j;                   //tylko ten wers jest inny niż w zad 4_3
                   suma += macierz[i][j];
               }
               else macierz[i][j] = 0;
               System.out.print(macierz[i][j] + "\t");
           }
           System.out.println();
       }
       System.out.println("Suma cyfr na przekatnej wynosi " + suma + "\n");

   }
}
