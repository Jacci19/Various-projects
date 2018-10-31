package Books_exercises.Kubiak.Kubiak_5_obiekty_i_rekurencja;

public class Zad_5_05_OBJ_Rekur_silnia {

    /*   Napisz program, który rekurencyjnie oblicza kolejne wartości n! dla  n = 10 i wynik wyświetla na ekranie komputera.                                        */

    public long silnia(int liczba){
        long zwrot = 1;
        if (liczba >= 2) {
            zwrot = liczba * silnia(liczba - 1);
        }
        return zwrot;
    }
}
