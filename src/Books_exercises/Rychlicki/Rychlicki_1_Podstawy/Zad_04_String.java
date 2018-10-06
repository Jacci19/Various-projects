package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

public class Zad_04_String {
    public Zad_04_String() {

/*
        Zad 4_1 Przeanalizuj program                                                                                                               */
        System.out.println("\n________________________Zad 4_1_______________________________________________________________________________________");
        System.out.println("Zadania z programowania.");
        System.out.println("Zadania z programowania.".charAt(0));                               //znak na pozycji 0
        System.out.println("Zadania z programowania.".length());                                //ilość znaków
        System.out.println("Zadania z programowania.".charAt(23));                              //znak na pozycji 23
        System.out.println("Zadania z programowania.".toUpperCase());                           //zamiana liter na wielkie
        System.out.println("Zadania z programowania.".toLowerCase());                           //zamiana liter na małe
        System.out.println("Zadania z programowania.".indexOf('z'));                            //pozycja znaku 'z' (liczone od zera)
        System.out.println("Zadania z programowania.".indexOf("prog"));                         //pozycja ciągu znaków 'z' (pozycja pierwszego znaku ciągu liczona od zera)
        System.out.println("1 Zadania z programowania.".replace('.', '?'));                   //zamień znak '.' na','
        System.out.println("2 Zadania z programowania.".replace("adania", "dania"));         //zamień ciąg znaków
        System.out.println("3 Zadania z programowania.".replaceAll("ania", "anka"));         //zamienia wszystkie znalezione ciągi
        System.out.println("4 Zadania z programowania.".replaceFirst("ania", "anka"));       //zamienia pierwszy znaleziony ciąg
        System.out.println("Zadania z programowania.".substring(10));                                         //fragment od indexu 10 do końca
        System.out.println("Zadania z programowania.".substring(10, 17));                                     //fragment od indexu 10 do indeksu 17
        System.out.println("Zadania z programowania.".concat("\b z podpowiedziami."));                        //dodaj nowy ciąg znaków do istniejącego (\b backspace o jeden znak)
        System.out.println("Zadania z programowania." + "\b" + " z odpowiedziami.");                              //dodaj nowy ciąg znaków do istniejącego inną metodą(\b backspace o jeden znak)

/*
        Zad 4_2 Napisz program z zad 4_1., wykorzystujac zamiast literalów zmienne reprezentujace
        obiekty typu String lub zmienne typu char.                                                                                                              */

        System.out.println("\n________________________Zad 4_2_______________________________________________________________________________________");
        String zdanie = "Zadania z programowania.";
        char szukanyZnak = 'z';
        String dodanyTekst = "\b z podpowiedziami.";
        String staryCiag = "ania", nowyCiag = "anka";

        System.out.println(zdanie);
        System.out.println(zdanie.charAt(0));                                       //znak na pozycji 0
        System.out.println(zdanie.length());                                        //ilość znaków
        System.out.println(zdanie.charAt(23));                                      //znak na pozycji 23
        System.out.println(zdanie.toUpperCase());                                   //zamiana liter na wielkie
        System.out.println(zdanie.toLowerCase());                                   //zamiana liter na małe
        System.out.println(zdanie.indexOf(szukanyZnak));                            //pozycja znaku 'z' (liczone od zera)
        System.out.println(zdanie.indexOf("prog"));                                 //pozycja ciągu znaków 'z' (pozycja pierwszego znaku ciągu liczona od zera)
        System.out.println(zdanie.replace('.', '?'));                   //zamień znak '.' na','
        System.out.println(zdanie.replace("adania", "dania"));         //zamień ciąg znaków
        System.out.println(zdanie.replaceAll(staryCiag, nowyCiag));                     //zamienia wszystkie znalezione ciągi
        System.out.println(zdanie.replaceFirst(staryCiag, nowyCiag));                   //zamienia pierwszy znaleziony ciąg
        System.out.println(zdanie.substring(10));                                       //fragment od indexu 10 do końca
        System.out.println(zdanie.substring(10, 17));                                   //fragment od indexu 10 do indeksu 17
        System.out.println(zdanie.concat(dodanyTekst));                        //dodaj nowy ciąg znaków do istniejącego (\b backspace o jeden znak)
        System.out.println(zdanie + dodanyTekst);                              //dodaj nowy ciąg znaków do istniejącego inną metodą(\b backspace o jeden znak)


        /*
        Zad 4_3 Utwórz lancuch zawierajacy napis Dzien dobry. Napisz aplikacje, która wyswietli napis w konsoli:
            a) pionowo — kazdy znak w odrebnym wierszu,
            b) poziomo — znaki rozdzielone dodatkowymi odstepami (tzw. spacjowanie lub rozstrzelenie tekstu),
            c) poziomo — wielkimi literami,
            d) poziomo — malymi literami.
                                                                                                     */

        System.out.println("\n________________________Zad 4_3_______________________________________________________________________________________");
        String zdanie42 = "Dzień dobry";
        System.out.println("______a______");
        for (int i = 0; i < zdanie42.length(); i++) {
            System.out.println(zdanie42.charAt(i));
        }
        System.out.println("______b______");
        for (int i = 0; i < zdanie42.length(); i++) {
            System.out.print(zdanie42.charAt(i) + " ");
        }
        System.out.println("\n______c______");
        System.out.println(zdanie42.toUpperCase());

        System.out.println("______d______");
        System.out.print(zdanie42.toLowerCase());

        /*
        Zad 4_5 Utwórz lancuch znaków zawierajacy slowo programowanie. Napisz program, który znaki zawarte w lancuchu bedzie wyświetlał w kolejności odwrotnej, od końca do początku
                                                                                                     */

        System.out.println("\n________________________Zad 4_5_______________________________________________________________________________________");
        String slowo = "programowanie";
        System.out.println("Normalna kolejność znaków: " + slowo);
        System.out.print("Odwrotna kolejnośc znaków: ");
        for (int i=slowo.length()-1; i>=0; i--){
            System.out.print(slowo.charAt(i));
        }
        //druga metoda
        System.out.println("\nDruga metoda: ");
        char[] revSlowo = new char[slowo.length()];
        int j=0;
        for (int i=slowo.length()-1; i>=0; i--, j++){
            revSlowo[j] = slowo.charAt(i);
        }
        String revSlovoS = String.valueOf(revSlowo);        //zamiana tablicy char-ów na stringa (tu: niekonieczne)
        System.out.println(revSlovoS);

        /*
        Zad 4_7 Napisz program, który utworzy lancuch znaków wypelniony cyframi od 0 do 9 (z wykorzystaniem tablicy ASCII)
                                                                                                     */
        System.out.println("\n________________________Zad 4_7_______________________________________________________________________________________");
        int ascii = 48;
        char[] lancuchCyfr = new char[10];
        char[] lancuchHex = new char[16];
        System.out.print("Łańcuch cyfr: ");
        for (int i=0; i< lancuchCyfr.length; i++, ascii++){
            lancuchCyfr[i] = (char)ascii;
            System.out.print(lancuchCyfr[i]);
        }
        System.out.print("\nŁańcuch cyfr hex: ");
        for (int i=0; i< lancuchHex.length; i++){
            lancuchHex[i] = Character.forDigit(i, 16);          //można też zrobić (char)ascii dla 0-9 i potem dla a-f
            System.out.print(lancuchHex[i]);
        }



    }
}