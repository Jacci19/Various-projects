package Books_exercises.Kubiak.Kubiak_1_4_podstawy;

import java.util.Random;
import java.util.Scanner;

public class Zad_2_5_ZgadnijRandom {

/*
    Napisz program, w którym użytkownik zgaduje losową liczbę z przedziału od 0 do 9 generowaną przez komputer.        */


    Zad_2_5_ZgadnijRandom(){
        System.out.println("Zad_2_5_ZgadnijRandom\n");
        boolean playAgain;
        Scanner wczytaj = new Scanner(System.in);
        Random r = new Random();
        do {
            System.out.print("\nKomputer wygenerowal losową cyfrę od 0 do 9.\nZgadnij jaka: ");
            int twojaCyfra = wczytaj.nextInt();
            int kompCyfra = r.nextInt(10);
            System.out.println("Komputer wygenerowal: " + kompCyfra);

            if (twojaCyfra == kompCyfra) System.out.println("Zgadłes !!!");
            else System.out.println("Niestety nie zgadłes.");

            System.out.print("Czy gramy dalej(N = nie, inna = tak): ");
            String c = wczytaj.next();

            if (c.equals("N") || c.equals("n")) playAgain = false;
            else playAgain = true;
        }
        while (playAgain);

        System.out.print("\n30 losowych cyfr:  ");
        for(int i=0; i<30; i++){
            int randomCyfra = r.nextInt(10);
            System.out.print(randomCyfra + " , " );
        }
    }
}


/*

Zad. 2_4 jest podobne do poprzednich więc zostało pominiete

https://javastart.pl/baza-wiedzy/darmowy-kurs-java/klasy/klasa-random-generator-pseudolosowy



gdy zamiast (c.equals("N")) użyłem (c == "N") program działał niepoprawnie.
Do porównywania stringów używamy equals a nie ==.





Wersja podstawowa programu (bez playAgain):

public class Zad_2_5_ZgadnijRandom {


   // Napisz program, w którym użytkownik zgaduje losową liczbę z przedziału od 0 do 9 generowaną przez komputer.


    Zad_2_5_ZgadnijRandom(){
        System.out.println("Zad_2_5_ZgadnijRandom\n");
        System.out.print("Komputer wygenerował losową cyfrę od 0 do 9.\nZgadnij jaką: ");
        Scanner wczytaj = new Scanner(System.in);
        int twojaCyfra = wczytaj.nextInt();
        Random r = new Random();
        int kompCyfra = r.nextInt(10);
        System.out.println("Komputer wygenerował: " + kompCyfra);

        if (twojaCyfra==kompCyfra) System.out.println("Zgadłeś !!!");
        else System.out.println("Niestety nie zgadłeś.");
    }
}




*/
