package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Zad_18b_Random_klasaMyRandomArray {
    public Zad_18b_Random_klasaMyRandomArray() {

/*
Rozwiązując kolejne zadania (18.8 – 18.18), zbudujemy klasę MyRandomArray, gromadzącą metody umożliwiające tworzenie jednowymiarowych tablic wypełnionych
pseudolosowymi liczbami typu całkowitego int lub zmiennoprzecinkowego double.                                                                               */

        System.out.println("\n________________________Zad 18_8_______________________________________________________________________________________");
/*                Zadanie 18.8.
        Utwórz statyczną metodę int[] rndArray(int n, int m) służącą do budowania tablicy liczb całkowitych o podanym wymiarze n, wypełnionej pseudolosowymi wartościami
    z zakresu 0, 1, …, m-1. Zdefiniuj metody ułatwiające wyświetlanie elementów tablicy oddzielonych odstępem <void arrayPrint(int[] tab)> i <void arrayPrintln(int[] tab)>
    — nazwy sugerująróżnicę w działaniu tych metod. Utwórz równieżmetodędodającą do wszystkich elementów tablicy określonąwartość<void addToArray(int[] tab, int BlackJack_FX)>.
    Napisz program demonstrujący działanie tych metod.                                                                                              */

        MyRandomArray MRA = new MyRandomArray();                                // musi być, bo będzie błąd: Non-static method cannot be referenced from a static context” error

        int ile = 28, pula = 30;
        int[] tablica = MRA.rndArray(ile, pula);
        System.out.println("_____Wyświetlenie tablicy LLC poziomo (arrayPrint)(Zad 18_8): ");
        MRA.arrayPrint(tablica);
        System.out.println("_____Wyświetlenie tablicy LLC pionowo (arrayPrintln)(Zad 18_8): ");
        MRA.arrayPrintln(tablica);
        System.out.println("_____Dodanie stałej do każdego elementu tablicy LLC(addToArray)(Zad 18_8):");
        MRA.addToArray(tablica, 100);
        MRA.arrayPrint(tablica);


        System.out.println("\n________________________Zad 18_9_______________________________________________________________________________________");
/*
        Zadanie 18.9.
            Utwórz statycznąmetodę<int[] rndUniqueArray(int n, int m)> służącą do budowania tablicy liczb całkowitych o podanym wymiarze (n)
        i niepowtarzających się elementach, wypełnionej pseudolosowymi wartościami z określonego zakresu 0, 1, …, m-1.
        Napisz program demonstrujący działanie tej metody. Dołącz ją do klasy MyRandomArray.                                                                    */

        int[] tablicaUniq = MRA.rndUniqueArray(ile, pula);
        System.out.println("_____Wyświetlenie tablicy LLC z unikalnymi elementami (rndUniqueArray)(Zad 18_9): ");
        MRA.arrayPrint(tablicaUniq);



        System.out.println("\n________________________Zad 18_10_______________________________________________________________________________________");
/*
        Zadanie 18.10.
            Utwórz statyczne metody int[] rndSortArray(int n, int m) i int[] rndSortUniqueArray(int n, int m), które będą służyćdo budowania posortowanych
        tablic liczb całkowitych o podanym wymiarze (n), wypełnionych pseudolosowymi wartościami z zakresu 0, 1, …, m-1.
        Druga metoda powinna zwracaćtablicę o niepowtarzających się elementach. Napisz program demonstrujący działanie tych metod. Dołącz je do klasy MyRandomArray.           */

        System.out.println("_____Wyświetlenie tablicy LLC z posortowanymi elementami (rndSortArray)(Zad 18_10): ");
        int[] tablicaSort = MRA.rndSortArray(ile, pula);
        MRA.arrayPrint(tablicaSort);
        System.out.println("_____Wyświetlenie tablicy LLC z posortowanymi niepowtarzającymi się elementami (rndSortUniqueArray)(Zad 18_10): ");
        int[] tablicaSortUniq = MRA.rndSortUniqueArray(ile, pula);
        MRA.arrayPrint(tablicaSortUniq);




        System.out.println("\n________________________Zad 18_11_______________________________________________________________________________________");
/*
        Zadanie 18.11.
            Utwórz statycznąmetodę<double[] rndArray(int n, double a)>, która będzie służyć do budowania tablicy liczb zmiennoprzecinkowych typu double o podanym wymiarze (n),
        wypełnionej pseudolosowymi wartościami z przedziału <0, a). Zdefiniuj metody ułatwiające wyświetlanie elementów tablicy oddzielonych odstępem
        void arrayPrint(double[] tab), void arrayPrintln(double[] tab) i void arrayPrintf(String spec, double[] tab) — nazwy sugerują sposób działania tych metod.
        Ponadto utwórz metodędodającądo wszystkich elementów tablicy określonąwartość<void addToArray(double[] tab, double BlackJack_FX)>. Napisz program demonstrujący działanie tych metod.

        Info: Metody te mająnazwy takie same jak metody istniejące w klasie MyRandomArray, ale różniąsiętypami parametrów wywołania.
        Możemy więc te metody dodawać do klasy MyRandomArray — wykorzystujemy tutaj możliwość przeciążania metod.                                                */

        int ile_R = 8;
        double pula_R = 16.5;
        double[] tablicaR = MRA.rndArray(ile_R, pula_R);
        System.out.println("_____Wyświetlenie tablicy LLR poziomo (arrayPrint)(Zad 18_11): ");
        MRA.arrayPrint(tablicaR);
        System.out.println("_____Wyświetlenie tablicy LLR pionowo (arrayPrintln)(Zad 18_11): ");
        MRA.arrayPrintln(tablicaR);
        System.out.println("_____Dodanie stałej do każdego elementu tablicy LLR (addToArray)(Zad 18_11):");
        MRA.addToArray(tablicaR, 100.1);
        MRA.arrayPrint(tablicaR);
        System.out.println("_____Wyświetlenie tablicy LLR sformatowanej printf (arrayPrintf)(Zad 18_11): ");
        MRA.arrayPrintf("%4.2f\n", tablicaR);



        System.out.println("\n________________________Zad 18_12_______________________________________________________________________________________");
/*
        Zadanie 18.12.
            Utwórz statycznąmetodę< double[] rndUniqueArray(int n, double a) >, która będzie służyć do budowania tablicy liczb zmiennoprzecinkowych o podanym wymiarze (n)
        i niepowtarzających się elementach, wypełnionej pseudolosowymi wartościami z przedziału <0, a). Napisz program demonstrujący działanie tej metody. Dołącz ją do klasy MyRandomArray.    */

        double[] tablicaUniqR = MRA.rndUniqueArray(ile_R, pula_R);
        System.out.println("_____Wyświetlenie tablicy LLR z unikalnymi elementami (rndUniqueArray)(Zad 18_12): ");
        MRA.arrayPrint(tablicaUniqR);



        System.out.println("\n________________________Zad 18_13_______________________________________________________________________________________");
/*
        Zadanie 18.13.
            Utwórz statyczne metody < double[] rndSortArray(int n, double a) > i < double[] rndSortUniqueArray(int n, double a), które będąsłużyły do budowania posortowanych
        tablic liczb zmiennoprzecinkowych o podanym wymiarze (n), wypełnionych pseudolosowymi wartościami z przedziału <0, a). Druga metoda powinna zwracać tablicę
        o niepowtarzających sięelementach. Napisz program demonstrujący działanie tych metod. Dołącz je do klasy MyRandomArray.                               */

        System.out.println("_____Wyświetlenie tablicy LLR z posortowanymi elementami (rndSortArray)(Zad 18_13): ");
        double[] tablicaSortR = MRA.rndSortArray(ile_R, pula_R);
        MRA.arrayPrint(tablicaSortR);
        System.out.println("_____Wyświetlenie tablicy LLR z posortowanymi niepowtarzającymi się elementami (rndSortUniqueArray)(Zad 18_13): ");
        double[] tablicaSortUniqR = MRA.rndSortUniqueArray(ile_R, pula_R);
        MRA.arrayPrint(tablicaSortUniqR);


        System.out.println("\n________________________Zad 18_14_______________________________________________________________________________________");
/*
        Zadanie 18.14.
            Utwórz statycznąmetodę< void roundArray(double[] tab, int prec) > , która będzie zaokrąglać wszystkie liczby w tablicy tab do określonej liczby miejsc po przecinku
        (parametr prec). Napisz program demonstrujący działanie tej metody. Dołącz ją do klasy MyRandomArray.                                                      */

        MRA.roundArray(tablicaSortUniqR, 3);
        System.out.println(" Tablica z zaokrąglonymi wartościami: ");
        MRA.arrayPrint(tablicaSortUniqR);



        System.out.println("\n________________________Zad 18_15_______________________________________________________________________________________");
/*
        Zadanie 18.15.
            Utwórz statyczną metodę < double[] rndArray(int n, double a, int prec) > , która będzie służyć do budowania tablicy liczb zmiennoprzecinkowych o podanym rozmiarze (n)
        i wartościach z przedziału <0, a) oraz o określonej liczbie miejsc po przecinku (parametr prec). Napisz program demonstrujący działanie tej metody. Dołącz ją do klasy MyRandomArray.            */

        System.out.println(" Utworzona nowa tablica z zaokrąglonymi wartościami: ");
        double[] tablRound = MRA.rndArray(10, 100, 3);
        MRA.arrayPrint(tablRound);



        System.out.println("\n________________________Zad 18_16_______________________________________________________________________________________");
/*
        Zadanie 18.16.
            Utwórz statyczną metodę < double[] rndArray(int n, double a, double step) > , która będzie służyć do budowania tablicy liczb zmiennoprzecinkowych o podanym rozmiarze (n)
        i wartościach z przedziału <0, a) będących wielokrotnością parametru step. Napisz program demonstrujący działanie tej metody. Dołącz ją do klasy MyRandomArray.            */

        System.out.println(" Utworzona nowa tablica z wartościami będącymi wielokrotnością zadanej liczby: ");
        double[] tablRoundStep = MRA.rndArray(10, 100, 3.255);
        MRA.arrayPrint(tablRoundStep);



        System.out.println("\n________________________Zad 18_17_______________________________________________________________________________________");
/*
        Zadanie 18.17.
            Utwórz statyczne metody < void inputArray(int[] tab) > i < void inputArray(double[] tab) > , które będąumożliwiać wprowadzanie danych z klawiatury do tablicy odpowiedniego typu. Napisz program demonstrujący działanie tych metod.
        Utwórz klasę MyArray i umieść w niej te dwie metody oraz wcześniej zdefiniowane metody służące do wyświetlania zawartości tablic jednowymiarowych
        i do zaokrąglania wartości w tablicy liczb typu double (z klasy MyRandomArray).                                                                                 */

/*
        int[] a = new int[5];
        inputArray(a);
        MRA.arrayPrint(a);
        double[] b = new double[3];
        inputArray(b);
        MRA.arrayPrint(b);
*/



        System.out.println("\n________________________Zad 18_18_______________________________________________________________________________________");
/*
        Zadanie 18.18.
            Utwórz statycznąmetodę  < double[] intArrayToDouble(int[] tab) >  konwertującą tablicęliczb całkowitych na tablicę liczb zmiennoprzecinkowych
        oraz metodę  < int[] doubleArrayToInt(double[] tab) >  działającąodwrotnie.                                                                           */

        int[] tabInt = {1, 2, 10, 15, 20};
        System.out.println("Tablica int: ");
        MRA.arrayPrint(tabInt);
        System.out.println("Tablica int po przekształceniu na double: ");
        MRA.arrayPrint(MRA.intArrayToDouble(tabInt));

        double[] tabDouble = {1.5, 2.34, 0.12, 12.95, 23.7721};
        System.out.println("Tablica double: ");
        MRA.arrayPrint(tabDouble);
        System.out.println("Tablica double po przekształceniu na int: ");
        MRA.arrayPrint(MRA.doubleArrayToInt(tabDouble));


    } // ============================================================= METODY ========================================================================

//
// !!! Program korzysta z metod z zewnętrznego, stworzonego przeze mnie, pliku "MyRandomArray.java" więc metody poniżej mogą być zakomentowane a i tak program działa (nie korzysta z nich). LLC - losowe liczby całkowite. LLR - LLrzeczywiste
//

    //metody do zad. 18.8
    int[] rndArray(int n, int m) {               //buduje tablicę n-elementową zawierającą LLC z przedziału od 0 do (m-1)
        int[] tabl = new int[n];
        for (int i = 0; i < n; i++) {
            tabl[i] = (int) (Math.random() * (m-1));
        }
        return tabl;
    }

    void arrayPrint(int[] tab) {                     //wyświetla wszystkie elementy tablicy LLC (oddzielone spacjami)
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + "  ");
        }
        System.out.println();                                   //aby następne rzeczy nie wyswietlały się w tej samej linii
    }

    void arrayPrintln(int[] tab) {                   //wyświetla wszystkie elementy tablicy LLC(każdy w nowej linii)
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i]);
        }
    }


    void addToArray(int[] tab, int d) {             //dodaje do każdego elementu tablicy LLC stałą BlackJack_FX
        for (int i = 0; i < tab.length; i++) {
            tab[i] += d;
        }
    }

    //metody do zad. 18.9
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

    //metody do zad. 18.10
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

    //metody do zad. 18.11
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

    //metody do zad. 18.12
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
