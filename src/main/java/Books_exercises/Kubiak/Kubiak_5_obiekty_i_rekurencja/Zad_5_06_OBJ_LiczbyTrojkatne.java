package Books_exercises.Kubiak.Kubiak_5_obiekty_i_rekurencja;

public class Zad_5_06_OBJ_LiczbyTrojkatne {

    /*    Napisz program, który rekurencyjnie znajduje 10 pierwszych liczb trójkątnych i wyświetla je na ekranie komputera.                                    */


    public int Trojkat(int liczba) {
        int zwrot = 1;
        if (liczba > 1) {
            zwrot = liczba + Trojkat(liczba-1);
        }
        return zwrot;
    }
}
