package Books_exercises.Kubiak.Kubiak_6_pliki_tekstowe;

import java.io.*;
import java.util.Scanner;


public class Zad_6_1_OBJ_ReadWriteFile {
    String zdanie1, dane;

/*      Zad 6_1
    Napisz zgodnie z zasadami programowania obiektowego program, który wczytuje z klawiatury imię i nazwisko,
    zapisuje te dane do pliku tekstowego dane.txt, a następnie odczytuje je z tego pliku i wyświetla na ekranie komputera.
    Klasa powinna zawierać trzy metody:
        1. czytaj_dane() — metoda wczytuje z klawiatury imię i nazwisko.
        2. zapisz_dane_do_pliku() — metoda zapisuje imię i nazwisko do pliku tekstowego o nazwie dane.txt.
        3. czytaj_dane_z_pliku() — metoda odczytuje dane z pliku dane.txt i wyświetla je na ekranie komputera.                                  */


    void czytaj_dane(){                                                     //wprowadzenie danych do konsoli
        Scanner wczytaj = new Scanner(System.in);
        System.out.println("Podaj imie i nazwisko: ");
        zdanie1 = wczytaj.nextLine();
        System.out.println("Wprowadziłeś tekst: " + zdanie1 + "\n");
    }

    void zapisz_dane_do_pliku() throws IOException{
        System.out.println("Zapisujemy dane do pliku dane.txt.");
        FileWriter fw = new FileWriter("dane.txt");                         //zapisuje do katalogu src
        //FileWriter fw = new FileWriter("Books_exercises/Kubiak/Kubiak_6_pliki_tekstowe/dane.txt");
        fw.write(zdanie1 + "\n");
        fw.write(zdanie1);                                                  //drugie użycie write nie nadpisuje poprzedniego tylko dodaje za nim.
        fw.close();                                                         // zamknięcie pliku
    }

    void czytaj_dane_z_pliku() throws IOException{                                             //odczytuje cały plik (wszystkie wersy)
        System.out.println("Odczytujemy dane z pliku dane.txt.\n");
        FileReader fr = new FileReader("dane.txt");
        //FileReader fr = new FileReader("Books_exercises/Kubiak/Kubiak_6_pliki_tekstowe/dane.txt");
        BufferedReader br = new BufferedReader(fr);

        while ((dane = br.readLine()) != null){                             // pętla odczytuje dane z pliku
            System.out.println(dane);
        }
        fr.close();                                                         // zamknięcie pliku
    }
}

/*      Scanner - wczytanie danych z konsoli
        FileWriter - zapis danych do pliku
        FileReader + BufferedReader - odczyt danych z pliku

Polskie znaki w pliku tekstowym w konsoli widać jako znak zapytania


--------------------------------------------------------------------------------
We "void czytaj_dane()" można zamiast scanner użyć: BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  wtedy dalej zamiast:         zdanie1 = wczytaj.nextLine();
                 jest:          zdanie1 = br.readLine();
--------------------------------------------------------------------------------
Notatnik nie rozpoznaje "\n" jako przejścia do nowej linii, trzeba pisać "\r\n". Programowi Notepad++ wystarczy samo "\n".
--------------------------------------------------------------------------------
W ścieżkach dostępu do plików używamy w javie znaku "\\" zamiast "\", np.c:\\pliki\\plik.txt
--------------------------------------------------------------------------------
java.io.FileReader - pozwala programiście na tekstowy dostęp do pliku znak po znaku.
java.io.BufferedReader - pozwala na dostęp do pliku linijka po linijce.
--------------------------------------------------------------------------------

 Zastosowanie jak poniżej "Scanner" zamiast "bufferedReader" daje błąd:
     public void czytaj_dane_z_pliku() throws IOException {
        System.out.println("Odczytujemy dane z pliku dane.txt.\n");
        FileReader fr = new FileReader("dane.txt");
        Scanner br = new Scanner(fr);                         //Zastosowanie Scanner zamiast bufferedReader daje błąd

        while ((dane1 = br.nextLine()) != null){                // pętla odczytuje dane z pliku
            System.out.println(dane1);
        }
        fr.close();                                             // zamknięcie pliku
    }




 */