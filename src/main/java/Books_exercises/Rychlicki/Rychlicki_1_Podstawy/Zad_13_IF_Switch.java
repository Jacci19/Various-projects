package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;//import com.sun.org.apache.xpath.internal.operations.Number;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Zad_13_IF_Switch {

    //String data;           // do zadania 13.12

    public Zad_13_IF_Switch() {

/*      Zad. 13_11
        Napisz program, który podaną liczbę naturalną mniejsząod 1000 zapisze słowami.                                                                                                     */

/*
        System.out.println("\n________________________Zad 13_11_______________________________________________________________________________________");
        int st, dz, jd;
        int liczba = 0;
        String liczbaSt, liczbaSlownie = "x";
        String[] jednosci = {"", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};
        String[] nastki = {"dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście", "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"};
        String[] dziesiatki = {"", "", "dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt", "siedemdziesiąt", "osiemdziesiąt", "dziewięćdziesiąt"};
        String[] setki = {"", "sto", "dwieście", "trzysta", "czterysta", "piećset", "sześćset", "siedemset", "osiemset", "dziewięćset"};

        Scanner wczytaj = new Scanner(System.in);

        do {
            System.out.print("Podaj liczbę naturalną (< 1000): ");
            liczba = wczytaj.nextInt();
            if (liczba >= 1000) System.out.println("Za duża liczba!");
        }
        while (liczba >= 1000 || liczba < 0);

        liczbaSt = String.valueOf(liczba);          //zamiana int na String
        switch (liczbaSt.length()){
            case 1:
                if (liczba>0) liczbaSlownie = jednosci[liczba];
                else liczbaSlownie = "zero";
                break;
            case 2:
                jd = Integer.parseInt(liczbaSt.substring(1));
                dz = Integer.parseInt(liczbaSt.substring(0,1));
                //System.out.println(dz + "_" + jd);
                if (liczba>=20) liczbaSlownie = dziesiatki[dz] + " " + jednosci[jd];
                else liczbaSlownie = nastki[jd];
                break;
            case 3:
                jd = Integer.parseInt(liczbaSt.substring(2));
                dz = Integer.parseInt(liczbaSt.substring(1,2));
                st = Integer.parseInt(liczbaSt.substring(0,1));
                //System.out.println(st + "_" + dz + "_" + jd);
                if (dz==1) liczbaSlownie = setki[st] + " " + nastki[jd];
                else liczbaSlownie = setki[st] + " " + dziesiatki[dz] + " " + jednosci[jd];
                break;
        }
        System.out.println("Liczba: " + liczba + " słownie: " + liczbaSlownie);
*/


/*
        Zadanie 13.12.
            Napisz program, który sprawdzi poprawność daty z obecnego wieku, zapisanej w postaci dd.mm.rrrr, i wyświetli odpowiedni komunikat.                                           */

/*
        System.out.println("\n________________________Zad 13_12_______________________________________________________________________________________");
        String dzien, miesiac, rok;
        Boolean jest10znakow = true, nieMaLiter = true, czySaKropki = true, dobryRozmiarWartosci = true;

        do{
            wprowadzDate();
            jest10znakow = data.length() == 10;
            System.out.println("- jest 10 znakow: " + jest10znakow);
            if (jest10znakow == false){
                System.out.println("BŁĄD: niewłasciwa liczba znaków");
            }
            else{
                nieMaLiter = Character.isDigit(data.charAt(0)) && Character.isDigit(data.charAt(1)) &&  Character.isDigit(data.charAt(3)) && Character.isDigit(data.charAt(4))
                        && Character.isDigit(data.charAt(6)) && Character.isDigit(data.charAt(7)) && Character.isDigit(data.charAt(8)) && Character.isDigit(data.charAt(9));
                System.out.println("- nie ma liter: " + nieMaLiter);
                if (nieMaLiter == false) {
                    System.out.println("BŁĄD: W dacie są niedozwolone znaki");
                }
                else{
                    czySaKropki = data.charAt(2)=='.' && data.charAt(5)=='.';
                    System.out.println("- czy sa kropki: " + czySaKropki);
                    if (czySaKropki == false){
                        System.out.println("BŁĄD: Brak kropek w odpowiednich miejscach");
                    }
                    else{
                        dzien = data.substring(0,2);
                        miesiac = data.substring(3,5);
                        rok = data.substring(6,10);

                        dobryRozmiarWartosci = Integer.parseInt(dzien) < 31 &&  Integer.parseInt(miesiac) < 12 &&  Integer.parseInt(rok) < 2018 &&  Integer.parseInt(rok) > 1900;
                        System.out.println("- dobry rozmiar wartosci: " + dobryRozmiarWartosci);
                        if (dobryRozmiarWartosci == false){
                            System.out.println("BŁĄD: Niewłasciwy rozmiar poszczególnych wartości");
                        }
                        else{
                            System.out.println("Data jest poprawna");
                        }
                    }
                }
            }
        }
        while (!jest10znakow || !nieMaLiter || !czySaKropki || !dobryRozmiarWartosci);

    }
    public void wprowadzDate(){
        System.out.print("\nPodaj datę w formacie \"dd.mm.rrrr\": ");
        Scanner wczytaj = new Scanner(System.in);
        data = wczytaj.nextLine();
        System.out.println("Wprowadziłeś datę: " + data);
*/

/*
        Zadanie 13.13.
                Napisz program, który datępodaną w postaci dd.mm.rrrr zapisze w formacie rrrr-mm-dd. Przyjmij, że wprowadzana data jest poprawna.                                  */

        System.out.println("\n________________________Zad 13_13_______________________________________________________________________________________");
        System.out.println("Zmiana formatu daty");
        Scanner input = new Scanner(System.in);
        System.out.print("Podaj datę w formacie dd.mm.rrrr: ");
        String str = input.next();
        /* Zakładamy poprawność formatu i wartości wprowadzonej daty */
        StringBuilder data = new StringBuilder(str);
        data.replace(2, 3, "-").replace(5, 6, "-");                               //zamiana kropek na kreski, można ten wers podzielić na dwa wersy
        data.append(data.substring(0,2)).delete(0, 2);
        data.insert(0, data.substring(4, 8)).delete(8, 12);
        System.out.println(data);


    }
}








































//na później - popraw fragment z zad 13.11 (sprawdzy czy wczytany tekst jest liczbą):


/*
    Scanner wczytaj = new Scanner(System.in);
        do{
            System.out.print("Podaj liczbę naturalną (< 1000): ");
            poprawnaLiczba=true;
            try{
                liczba=wczytaj.nextInt();
            }
            catch(InputMismatchException err){
                System.out.println("Zły format danych!!!");
                poprawnaLiczba=false;
            }
            if(liczba>=1000) System.out.println("Za duża liczba!");
        }
            while(liczba>=1000 || poprawnaLiczba==false);

*/
