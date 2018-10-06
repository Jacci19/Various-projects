package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

import java.util.Scanner;

public class MyArray {

    //metody do zad. 18.17
    void inputArray(int[] tab) {                            //umożliwia wprowadzenie danych z klawiatury do tablicy typu Int
        int n = tab.length;
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < n; ++i) {
            System.out.printf("Tablica Int: Liczba %d z %d, n = ", i+1, n);
            tab[i] = input.nextInt();
        }
    }
    void inputArray(double[] tab) {                         //umożliwia wprowadzenie danych z klawiatury do tablicy typu Double
        int n = tab.length;
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < n; ++i) {
            System.out.printf("Tablica Double: Liczba %d z %d, x = ", i+1, n);
            tab[i] = input.nextDouble();
        }
    }

    //metody wyswietlające
    void arrayPrint(int[] tab) {                     //wyświetla wszystkie elementy tablicy LLC(oddzielone spacjami)
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + "  ");
        }
        System.out.println();                                   //aby następne rzeczy nie wyswietlały się w tej samej linii
    }

    void arrayPrintln(int[] tab) {                   //wyświetla wszystkie elementy tablicy LLC (każdy w nowej linii)
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i]);
        }
    }

    void arrayPrint(double[] tab){                   //wyświetla wszystkie elementy tablicy LLR (oddzielone spacjami)
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + "  ");
        }
        System.out.println();
    }

    void arrayPrintln(double[] tab){                //wyświetla wszystkie elementy tablicy LLR(każdy w nowej linii)
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i]);
        }
    }

    //metoda do zad. 18.14
    void roundArray(double[] tab, int prec){                //zaokrągla wszystkie liczby w tablicy LLR do określonej liczby miejsc po przecinku
        double wsp = Math.pow(10.0, prec);
        for (int i=0; i < tab.length; i++){
            tab[i] = (int)(tab[i]*wsp+0.5)/wsp;
        }
    }

    //metody do zad. 18.18
    double[] intArrayToDouble(int[] tab){                   //konwertuje tablicęLC na tablicę LR
        double[] tabD = new double[tab.length];
        for (int i=0; i < tab.length; i++){
            tabD[i] = (double)(tab[i]);
        }
        return tabD;
    }

    int[] doubleArrayToInt(double[] tab){                   //konwertuje tablicęLR na tablicę LC
        int[] tabI = new int[tab.length];
        for (int i=0; i < tab.length; i++){
            tabI[i] = (int)(tab[i]);
        }
        return tabI;
    }

}
