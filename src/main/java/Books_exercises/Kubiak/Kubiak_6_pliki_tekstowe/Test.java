package Books_exercises.Kubiak.Kubiak_6_pliki_tekstowe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test {

    String zdanie1, dane;

    void czytaj_dane() {                                                     //wprowadzenie danych do konsoli
        Scanner wczytaj = new Scanner(System.in);
        System.out.println("Podaj tekst: ");
        zdanie1 = wczytaj.nextLine();
        System.out.println("Wprowadziłeś tekst: " + zdanie1 + "\n");
    }

    void zapisz_dane_do_pliku() throws IOException {
        System.out.println("Zapisujemy dane do pliku dane.txt.");
        FileWriter fw = new FileWriter("dane.txt");
        fw.write(zdanie1 + "\n");
        fw.write(zdanie1);                                                  //drugie użycie write nie nadpisuje poprzedniego tylko dodaje za nim.
        fw.close();                                                         // zamknięcie pliku
    }

    void czytaj_dane_z_pliku() throws IOException {                                             //odczytuje cały plik (wszystkie wersy)
        System.out.println("Odczytujemy dane z pliku dane.txt.\n");
        FileReader fr = new FileReader("dane.txt");
        BufferedReader br = new BufferedReader(fr);

        while ((dane = br.readLine()) != null) {                             // pętla odczytuje dane z pliku
            System.out.println(dane);
        }
        fr.close();                                                         // zamknięcie pliku
    }

}

