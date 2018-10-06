package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

import java.util.Scanner;

public class Zad_16_For {
    public Zad_16_For() {


        System.out.println("\n________________________Zad 16_2_______________________________________________________________________________________");

/*
        Zadanie 16.2.
            Napisz program, który sprawdzi, czy wprowadzone z klawiatury słowo jest palindromem, czyli brzmi tak samo czytane od strony lewej do prawej i od prawej do lewej.   */
        String slowoWej;
        Scanner wczytaj = new Scanner(System.in);
        System.out.print("Czy to palindrom? Podaj słowo: ");
        slowoWej = wczytaj.nextLine();
        System.out.print("Podałeś słowo: " + slowoWej);
        StringBuilder slowoWyj = new StringBuilder();
        int dlugosc = slowoWej.length();

        for (int i=0; i < dlugosc; i++){
            slowoWyj.insert(i, slowoWej.charAt(dlugosc-i-1));   //aby porównywać całe zdania (zad. 16.3) trzeba w tym miejscu wyrzucać spacje (if i != " ")
        }
        System.out.println("\nOdwrotna kolejność liter: " + slowoWyj);
        if (slowoWej.toLowerCase().equals(slowoWyj.toString().toLowerCase())) System.out.println("To słowo jest palindromem");
        else System.out.println("To słowo NIE jest palindromem");
    }
}
