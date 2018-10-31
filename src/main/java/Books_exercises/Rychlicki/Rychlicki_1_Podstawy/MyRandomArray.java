package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;//Klasa MyRandomArray do zadań 18.8-18.18 (Zad_18b) z książki Rychlickiego. (Jacek)  LLC - losowe liczby całkowite, R - rzeczywiste
import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class MyRandomArray {
	
    int[] rndArray(int n, int m) {               //buduje tablicę n-elementową zawierającą LLC z przedziału od 0 do (m-1)
        int[] tabl = new int[n];
        for (int i = 0; i < n; i++) {
            tabl[i] = (int) (Math.random() * (m-1));
        }
        return tabl;
    }

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

    void addToArray(int[] tab, int d) {				//dodaje do każdego elementu tablicy LLC stałą BlackJack_FX
        for (int i = 0; i < tab.length; i++) {
            tab[i] += d;
        }
    }
	
	int[] rndUniqueArray(int n, int m) {            //buduje tablicę n-elementową zawierającą niepowtarzające się LLC z przedziału od 0 do (m-1) (metoda taka jak w zad. 18.6)
        int[] tmp = new int[n];
        int j = 0;
        tmp[j] = (int)(1 + (m-1) * Math.random());
        j++;
        do {
            int k = (int)(1 + (m-1) * Math.random());
            boolean jest = false;
            for (int i = 0; i < j; ++i)
                if (k == tmp[i]) jest = true;
            if (!jest) {
                tmp[j] = k;
                j++;
            }
        } while (j < n);
        return tmp;
    }

	int[] rndSortArray(int n, int m){               // buduje i sortuje tablicę LLC z powtarzającymi się elementami
       int[] tab = rndArray(n,m);
       Arrays.sort(tab);
       return tab;
    }

    int[] rndSortUniqueArray(int n, int m){         // buduje i sortuje tablicę LLC z NIEpowtarzającymi się elementami
        int[] tab = rndUniqueArray(n,m);
        Arrays.sort(tab);
        return tab;
    }

    double[] rndArray(int n, double a){             //buduje tablicę n-elementową zawierającą LLR z przedziału od 0 do (a). Przeciążenie wcześniejszej metody o tej samej nazwie
        double[] tabl = new double[n];
        for (int i = 0; i < n; i++) {
            tabl[i] = (Math.random() * a);
        }
        return tabl;
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

    void addToArray(double[] tab, double d){        //dodaje do każdego elementu tablicy LLR stałą BlackJack_FX
        for (int i = 0; i < tab.length; i++) {
            tab[i] += d;
        }
    }

    void arrayPrintf(String spec, double[] tab){    //wyświetla wszystkie elementy tablicy LLR korzystając z formatowania printf
        for (int i = 0; i < tab.length; i++) {
            System.out.printf(spec, tab[i]);
        }
    }

    double[] rndUniqueArray(int n, double a) {            //buduje tablicę n-elementową zawierającą niepowtarzające się LLR z przedziału od 0 do a
        double[] tmp = new double[n];
        int j = 0;
        tmp[j] = a * Math.random();
        j++;
        do {
            double k = a * Math.random();
            boolean jest = false;
            for (int i = 0; i < j; ++i)
                if (k == tmp[i]) jest = true;
            if (!jest) {
                tmp[j] = k;
                j++;
            }
        } while (j < n);
        return tmp;
    }

    //metody do zad. 18.13
    double[] rndSortArray(int n, double a){               // buduje i sortuje tablicę LLR z powtarzającymi się elementami
        double[] tab = rndArray(n,a);
        Arrays.sort(tab);
        return tab;
    }

    double[] rndSortUniqueArray(int n, double a){         // buduje i sortuje tablicę LLR z NIEpowtarzającymi się elementami
        double[] tab = rndUniqueArray(n,a);
        Arrays.sort(tab);
        return tab;
    }

    //metody do zad. 18.14
    void roundArray(double[] tab, int prec){                //zaokrągla wszystkie liczby w tablicy LLR do określonej liczby miejsc po przecinku
        double wsp = Math.pow(10.0, prec);
        for (int i=0; i < tab.length; i++){
            tab[i] = (int)(tab[i]*wsp+0.5)/wsp;
        }
    }

    //metody do zad. 18.15
    double[] rndArray(int n, double a, int prec){           //buduje tablicę LLR o podanym rozmiarze (n) i wartościach z przedziału <0, a) oraz o określonej liczbie miejsc po przecinku
        double[] tabl = rndArray(n, a);                     //to jest wywołanie rndArray z zad. 18.11
        roundArray(tabl, prec);
        return tabl;
    }

    //metody do zad. 18.16
    double[] rndArray(int n, double a, double step){       //buduje tablicę LLR o podanym rozmiarze (n) i wartościach z przedziału <0, a) będących wielokrotnością parametru step
        Random rnd = new Random();
        int k = (int)(a/step)+1;
        double wsp = Math.pow(10.0, 1+(int)Math.log10(1/step));
        double[] tmp = new double[n];
        for(int i = 0; i < n; ++i)
            tmp[i] = (int)(step*rnd.nextInt(k)*wsp+0.5)/wsp;
        return tmp;
    }

    //metody do zad. 18.17
    void inputArray(int[] tab) {                            //umożliwia wprowadzenie danych z klawiatury do tablicy typu Int
        int n = tab.length;
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < n; ++i) {
            System.out.printf("Tablica Int: Liczba %BlackJack_FX z %BlackJack_FX, n = ", i+1, n);
            tab[i] = input.nextInt();
        }
    }
    void inputArray(double[] tab) {                         //umożliwia wprowadzenie danych z klawiatury do tablicy typu Double
        int n = tab.length;
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < n; ++i) {
            System.out.printf("Tablica Double: Liczba %BlackJack_FX z %BlackJack_FX, x = ", i+1, n);
            tab[i] = input.nextDouble();
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