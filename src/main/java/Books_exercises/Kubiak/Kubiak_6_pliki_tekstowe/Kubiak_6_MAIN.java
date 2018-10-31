package Books_exercises.Kubiak.Kubiak_6_pliki_tekstowe;

import java.io.IOException;

public class Kubiak_6_MAIN {
    public static void main(String[] args) throws IOException{


/*
        Zad_6_1_OBJ_ReadWriteFile Zad6_1 = new Zad_6_1_OBJ_ReadWriteFile();
        Zad6_1.czytaj_dane();
        Zad6_1.zapisz_dane_do_pliku();
        Zad6_1.czytaj_dane_z_pliku();
*/





        Zad_6_2_OBJ_FileMacierz Zad6_2 = new Zad_6_2_OBJ_FileMacierz();
        int rozm = 10;
        int[][] tabl = new int[rozm][rozm];
        int[][] tabl2 = new int[rozm][rozm];

        Zad6_2.czytaj_dane(tabl, rozm);
        Zad6_2.zapisz_dane_do_pliku(tabl, rozm);
        Zad6_2.czytaj_dane_z_pliku(tabl2, rozm);





/*
        Test T = new Test();
        T.czytaj_dane();
        T.zapisz_dane_do_pliku();
        T.czytaj_dane_z_pliku();
*/



    }

}
