package Books_exercises.Kubiak.Kubiak_6_pliki_tekstowe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Zad_6_2_OBJ_FileMacierz {

/*
        Napisz zgodnie z zasadami programowania obiektowego program, który tablicę 10×10 o postaci (1 na przekątnej, reszta zera)
        zapisuje do pliku tekstowego dane.txt, a następnie odczytuje z niego zapisane dane i wyświetla je na ekranie komputera.
        Klasa powinna zawierać trzy metody z parametrami:
            1. czytaj_dane(int tablica[][], int rozmiar) — tworzy tablicę 10×10.
            2. zapisz_dane_do_pliku(int tablica[][], int rozmiar) — metoda zapisuje tablicę 10×10 do pliku tekstowego o nazwie dane.txt.
            3. czytaj_dane_z_pliku(int tablica1[][], int rozmiar) — odczytuje tablicę 10×10 z pliku dane.txt i wyświetla ją na ekranie komputera.       */

    int i, j;
    String filePath = "Zad6_2.txt";
    //String filePath = "Books_exercises/Kubiak/Kubiak_6_pliki_tekstowe/Zad6_2.txt";

    void czytaj_dane(int tablica[][], int rozmiar) {
        System.out.println("Taką tablicę zapiszemy do pliku: ");
        for (i = 0; i < rozmiar; i++) {
            for (j = 0; j < rozmiar; j++) {
                if (i == j) tablica[i][j] = 48;
                else tablica[i][j] = 49;
                System.out.print(tablica[i][j] + " ");
            }
            System.out.println();
        }

    }

    void zapisz_dane_do_pliku(int tablica[][], int rozmiar) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        //FileWriter fw = new FileWriter(filePath, true);                      //jeśli nie chcemy aby nadpisywało stare

        System.out.println("Zapisuję tą tablicę do pliku tekstowego");
        for (i = 0; i < rozmiar; i++) {
            for (j = 0; j < rozmiar; j++) {
                fw.write((char) tablica[i][j]);
                //fw.write(Integer.toString(tablica[i][j])+" ");              //po zastosowaniu tego zamiast (char) w pliku wyświetla się dobrze
                System.out.print(tablica[i][j] + " ");
            }
            System.out.println();
            fw.write("\r\n");                                               //przejście do następnej linii (r - aby działało w notatniku), zakomentuj aby w 3. konsoli były tylko 0 i 1
        }
        fw.close();
    }


    void czytaj_dane_z_pliku(int tablica1[][], int rozmiar) throws IOException {
        System.out.println("Wczytuję tą tablicę z pliku tekstowego");
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        for (i = 0; i < rozmiar; i++) {
            for (j = 0; j < rozmiar; j++) {
                tablica1[i][j] = (int) br.read();
                System.out.print(tablica1[i][j] + " ");
            }
            System.out.println();

        }
        fr.close();

    }

}

//Działa ale w plikach tekstowych nie widać zawartości, w przykładzie z ksiązki też nie widać. Nie wiem dlaczego.
//Już wiem bo 0 i 1 w ascii to pustka. jak zamiat 0 i 1 dam 48,49 to w pliku pojawi się 0 i 1 (zamiana na char)

// Jeśli zamiast "FileWriter fw = new FileWriter(filePath);" damy "FileWriter fw = new FileWriter(filePath, true)" to nowe dane będą się dopisywać do pliku bez utraty poprzednich;

