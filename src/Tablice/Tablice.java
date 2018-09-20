package Tablice;

public class Tablice {
    public static void main(String[] args) {


/*
    Zadanie 3.5.
        Utwórz tablice zawierajaca znaki slowa programowanie. Napisz program odwracajcy kolejnosc znaków w tablicy
        Dodalem jak wyswietlają się tablice różnych typów
*/
        System.out.println("Rekurencja_static.SumaLiczb");

        char TabChar[] = {'p', 'r', 'o', 'g', 'r', 'a', 'm', 'o', 'w', 'a', 'n', 'i', 'e'};
        System.out.print("Normalna kolejność znaków: ");
        System.out.println(TabChar);

        for(int i = 0, j = TabChar.length-1; i < j ; i++, j--) {                //połączenie dwóch petli for w jedną
            char tmp = TabChar[i];
            TabChar[i] = TabChar[j];
            TabChar[j] = tmp;
        }
        System.out.print("Odwrotna kolejność znaków: ");
        System.out.println(TabChar);

//________________________________________________________________________________________________
        System.out.println("\nJak wyswietlają się tablice różnych typów:");

        //char TabChar[] = {'p', 'r', 'o', 'g', 'r', 'a', 'm', 'o', 'w', 'a', 'n', 'i', 'e'};       // koment bo jest już wyżej
        String TabString[] = {"jeden", "dwa", "trzy", "cztery"};
        int TabInt[] = {3,2,54,12,11,10};

        System.out.println("TabChar: " +  TabChar);
        System.out.print("TabChar bez stringa:");
        System.out.println(TabChar);

        System.out.println("TabInt: " + TabInt);
        System.out.print("TabInt bez stringa:");
        System.out.println(TabInt);

        System.out.println("TabString" + TabString);
        System.out.print("TabString bez stringa:");
        System.out.println(TabString);



    }
}
