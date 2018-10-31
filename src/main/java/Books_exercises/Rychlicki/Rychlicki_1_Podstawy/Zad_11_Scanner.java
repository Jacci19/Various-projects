package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

import java.io.*;
import java.util.Scanner;

public class Zad_11_Scanner{
    public Zad_11_Scanner() throws IOException{

/*
        Zadanie 11.5.
            Napisz program, który zamieni ułamek zwykły na procent. Ułamek zwykły wprowadzamy z klawiatury w postaci łańcucha znaków złożonego z dwóch liczb całkowitych
            (licznika i mianownika) oddzielonych znakiem / (bez odstępów). Wynik należy wyświetlić z dokładnością do 0,1%                                       */

/*        System.out.println("\n________________________Zad 11_5_______________________________________________________________________________________");
        int licznik = 0, mianownik = 0, pozycjaKreski;
        String daneWej;
        float wynik;

        Scanner wczytaj = new Scanner(System.in);
        do {
            System.out.print("Podaj ułamek zwykły (licznik/mianownik): ");
            daneWej = wczytaj.nextLine();
            pozycjaKreski = daneWej.indexOf("/");
            if (pozycjaKreski != -1) {
                licznik = Integer.parseInt(daneWej.substring(0, pozycjaKreski));
                mianownik = Integer.parseInt(daneWej.substring(pozycjaKreski + 1));
            } else System.out.println("Oddziel dane znakiem /");
        }
        while (pozycjaKreski == -1);
        System.out.print(licznik + "/" + mianownik);
        wynik = (float)licznik/mianownik;
        //System.out.println("Wynik: " + wynik + " ( " + wynik * 100 + "% )");
        System.out.printf(" = %1$.2f (%2$.1f%%)\n ", wynik, wynik*100);
*/

/*
        Zadanie 11.7.
            Plik tekstowy dane.txt zawiera wiersz tekstu, a w nim oddzielone odstępami imię i nazwisko pracownika, liczbę przepracowanych godzin i stawkę godzinową.
            Napisz program obliczający na tej podstawie wynagrodzenie należne pracownikowi. Zapisz te dane także do pliku.                                                        */

        System.out.println("\n________________________Zad 11_7_______________________________________________________________________________________");
        File plik = new File("Dane_Zad11_7.txt");       //plik jest tam gdzie .iml i katalogi: out, src
        String imie, nazwisko, dane, godzinyString;
        int godziny;
        float stawka;                                   // w pliku float pisze się z kropką

/*      z użyciem scanner
        Scanner wczytaj = new Scanner(plik);            //jeśli chcesz odczytać wszystkie linie w pętli to lepszy będzie FileRewader niż Scanner
        System.out.println(wczytaj.nextLine());
        System.out.println(wczytaj.nextLine());
        System.out.println(wczytaj.next());
        System.out.println(wczytaj.next());

        for (int i=0; i<4; i++){
            imie = wczytaj.next();
            nazwisko = wczytaj.next();
            godziny = wczytaj.nextInt();
            stawka = wczytaj.nextFloat();
            System.out.println(imie + "___" + nazwisko + "___" + godziny + "___" + stawka);
        }
*/

        //z użyciem FileReader
        FileReader wczytajPlik = new FileReader(plik);
        FileWriter plikWyj = new FileWriter("Zad11_7_plikWyjsciowy.txt");
        BufferedReader br = new BufferedReader(wczytajPlik);

        while ((dane = br.readLine()) != null){                             // pętla odczytuje i wypisuje w konsoli dane z pliku
            imie = dane.substring(0, dane.indexOf(" "));
            System.out.println(imie);

            int nazwiskoEnd = dane.indexOf(' ', imie.length()+1);                   //wyszukujemy indeks drugiej spacji w wierszu
            nazwisko = dane.substring(imie.length()+1, nazwiskoEnd);
            System.out.println(nazwisko);

            int godzinyEnd = dane.indexOf(' ', imie.length() + nazwisko.length() + 2);              //wyszukujemy indeks trzeciej spacji w wierszu
            godzinyString = dane.substring(imie.length() + nazwisko.length() + 2, godzinyEnd);
            godziny = Integer.parseInt(godzinyString);
            System.out.println(godziny);

            stawka = Float.parseFloat(dane.substring(imie.length() + nazwisko.length() +  godzinyString.length() + 3));
            System.out.println(stawka);


           System.out.printf("%s %s %.2f\n\n", imie, nazwisko, godziny*stawka);
           plikWyj.write(imie + " " + nazwisko + ":      " + godziny*stawka + "zł\n");


        }
        wczytajPlik.close();
        plikWyj.close();
    }
}

//myślę że z użyciem split poszło by jeszcze szybciej.