package Rekurencja_static;

public class SumaLiczb {                                                 //program liczy sumę liczb od 1 do danej liczby (rekurencyjnie)
    public static void main(String[] args) {

        SumaLiczb sumaLiczb = new SumaLiczb();                                     //przykład rekurencji oraz różnice między metodą styczną i niestatyczną
        int rekurencja = sumaLiczb.policzSume(6);
        System.out.println(rekurencja);

        System.out.println(policzSumeStatic(7));
    }

    private int policzSume(int liczba) {                            //metoda niestatyczna
        if (liczba <= 1) {
            return 1;
        } else {
            return liczba + policzSume(liczba - 1);
        }
    }

    private static int policzSumeStatic(int liczba) {               //metoda statyczna
        if (liczba <= 1) {
            return 1;
        } else {
            return liczba + policzSumeStatic(liczba - 1);
        }
    }
}
