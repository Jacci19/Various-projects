package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

import java.util.Scanner;

public class Zad_17_TryCatch {
    public Zad_17_TryCatch() {


        //System.out.println("\n________________________Zad 17_1_______________________________________________________________________________________");

/*
        Zadanie 17.1.
        Napisz program umożliwiający wczytywanie z klawiatury liczby zmiennoprzecinkowej typu double przy użyciu metody nextDouble() z klasy Scanner
        i reagujący poprawnie na popełniony błąd.                                                                                                                   */

/*
        double liczba = 0.0;
        boolean end;
        Scanner wczytaj = new Scanner(System.in);
        System.out.print("Podaj liczbę: ");
        do {
            try {
                System.out.print("Podaj liczbę: ");
                liczba = wczytaj.nextDouble();
                end = true;
            } catch (java.util.InputMismatchException err) {                    //err - zmienna błędu (można tu dać jej dowolną nazwę zamiast err)
                System.out.println("BŁĄD: Niepoprawny format danych!");
                wczytaj.next();                                                 //usunięcie ze strumienia "wczytaj" błędnego tokenu
                end = false;
            }
        }
        while (!end);
        System.out.println("Podałeś liczbę: " + liczba);
*/
        // inny sposób (z while i break):
/*
        double liczba = 0.0;
        Scanner wczytaj = new Scanner(System.in);
        System.out.print("Podaj liczbę: ");
        while (true) {                                                          //nieskończona pętla, wyjście jedynie przez break
            try {
                System.out.print("Podaj liczbę: ");
                liczba = wczytaj.nextDouble();
                break;
            } catch (java.util.InputMismatchException err) {                    //err - zmienna błędu (można tu dać jej dowolną nazwę zamiast err)
                System.out.println("BŁĄD: Niepoprawny format danych!");
                wczytaj.next();                                                 //usunięcie ze strumienia "wczytaj" błędnego tokenu
            }
        }
        System.out.println("Podałeś liczbę: " + liczba);
*/


        //System.out.println("\n________________________Zad 17_2_______________________________________________________________________________________");

/*      Zadanie 17.2.
            Dane liczbowe ze standardowego wejścia możemy wczytywaćw postaci łańcucha znaków, a następnie konwertowaćje na liczby odpowiedniego typu.
        Napisz program, który w ten sposób wczyta z klawiatury liczbęzmiennoprzecinkowąi zareaguje poprawnie na popełnione błędy.                                           */

/*
        String tekst = "0";
        double liczba;
        Scanner wczytaj = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("Podaj liczbę: ");
                tekst = wczytaj.next();
                liczba = Double.parseDouble(tekst);
                break;
            } catch (java.lang.NumberFormatException err) {
                System.out.println("BŁĄD: Niepoprawny format danych!");
                //wczytaj.next();                           //tutaj nie dajemy tej linii (tak jak w zad.17_1) bo błąd nie powstał przy wczytywaniu danych ale przy konwersji typów
            }
        }
        System.out.println("Podałeś liczbę: " + liczba);
*/


        //System.out.println("\n________________________Zad 17_3_______________________________________________________________________________________");
/*
        Zadanie 17.3.
            Napisz program, który odczyta i obliczy sumę pięciu liczb całkowitych, wprowadzonych ze standardowego wejścia.
            Pomiń liczby zmiennoprzecinkowe i łańcuchy znaków niebędące liczbami.                                                                                   */

/*
        int licznik=1, suma=0, liczba;
        String s;
        Scanner wczytaj = new Scanner(System.in);

        System.out.println("Program wczyta pięć liczb całkowitych.");
        do {
            try {
                System.out.print("Liczba nr " + licznik + ": ");
                s = wczytaj.next();
                liczba = Integer.parseInt(s);
                suma += liczba;
                licznik++;
            } catch (java.lang.NumberFormatException err) {                 //po obsłudze błedu wykonuje się od nowa blok "try" bo jest pętla "doWhile" a licznik się nie zwiększył
                System.out.println("BŁĄD: Niepoprawny format danych!");
            }
        }
        while (licznik <= 5);
        System.out.println("Suma wczytanych liczb: " + suma);
*/
        //drugi sposób (wykorzystanie "Scanner.hasNextInt()" zamiast wyjątków)

/*
        System.out.println("Obliczanie sumy pięciu liczb całkowitych");
        System.out.println("Podaj pięć liczb całkowitych:");
        Scanner input = new Scanner(System.in);
        int suma = 0, licznik = 0;
        do {
            if (input.hasNextInt()) {
                int n = input.nextInt();
                ++licznik;
                suma += n;
                System.out.println("Liczba "+licznik+": "+n);
            } else
                System.out.println("To nie jest liczba całkowita: "+input.next());
        } while(licznik < 5);
        input.close();
        System.out.printf("Suma liczb S = %d\n", suma);
*/


        //System.out.println("\n________________________Zad 17_4_______________________________________________________________________________________");
/*
        Zadanie 17.4.
            Napisz program obliczający odwrotnośćliczby całkowitej wprowadzonej z klawiatury.
        a) Przechwyći obsłużwszystkie wyjątki, jakie mogąpojawićsiępodczas wczytywania danych i wykonywania obliczeń.
        b) Zrealizuj zadanie bez obsługi wyjątków.
*/

/*
        System.out.println("Odwrotność liczby całkowitej");
        Scanner input = new Scanner(System.in);
        System.out.print("n = ");
        String s = input.next();
        try {
            int n = Integer.parseInt(s);
            if (n != 0)
                System.out.println("1/n = "+1.0/n);
            else
                throw new ArithmeticException("Nie istnieje odwrotność liczby 0.");
        }
        catch(NumberFormatException err) {
            System.out.println(err.getLocalizedMessage());                  //wyświetla tekst: For input string: "liczbaWejściowa"
            System.out.println("To nie jest liczba całkowita: "+s);
        }
        catch(ArithmeticException err) {
            System.out.println(err.getMessage());
        }
        input.close();
*/

        // b) drugi sposób (z wykorzystaniem hasNextInt)

        /*      System.out.println("Odwrotność liczby całkowitej");
        Scanner input = new Scanner(System.in);
        System.out.print("n = ");
        if (input.hasNextInt()) {
            int n = input.nextInt();
            if (n != 0)
                System.out.println("1/n = "+1.0/n);
            else
                System.out.println("Nie istnieje odwrotność liczby 0.");
        } else
            System.out.println("To nie jest liczba całkowita: "+input.next());
        input.close();
*/


        System.out.println("\n________________________Zad 17_5_______________________________________________________________________________________");
/*
        Zadanie 17.5.
            Napisz program obliczający wynik dzielenia z resztą dwóch liczb całkowitych wprowadzonych z klawiatury.
        Przechwyć i obsłuż wyjątki, jakie mogą pojawić się podczas wczytywania danych i wykonywania obliczeń.                                  */

        String s1, s2;
        int liczba1, liczba2, wynik = 0, reszta = 0;
        Scanner wczytaj = new Scanner(System.in);
        System.out.println("Program obliczający wynik dzielenia z resztą dwóch liczb całkowitych.");
        System.out.print("Pierwsza liczba: ");
        s1 = wczytaj.next();
        System.out.print("Druga liczba: ");
        s2 = wczytaj.next();

        try {
            liczba1 = Integer.parseInt(s1);
            liczba2 = Integer.parseInt(s2);

            //if (liczba2 != 0) {                       //można nie dawać tego if i throw, błąd dzielenia przez zero i tak zostanie wyłapany przez "catch (ArithmeticException err)"
                wynik = liczba1 / liczba2;
                reszta = liczba1 % liczba2;
           // } else {
                //throw new ArithmeticException(" Nie można dzielić przez zero!");
           // }

        } catch (java.lang.NumberFormatException err) {
            System.out.println("BŁĄD: nieprawidłowy format liczby");

        } catch (ArithmeticException err) {
            System.out.println("Błąd. Dzielenie przez zero");
            System.out.println(err.getMessage());
        }

        wczytaj.close();

        System.out.println("Wynik dzielenia: " + wynik + ", reszta: " + reszta);
    }
}

//Mniejsze problemy z obsługą błędów są gdy wprowadza się dane w postaci String a potem dopiero zamienia na int