package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

public class Zad_05_TablStringArgs {
    public Zad_05_TablStringArgs(String[] args) {

/*
        Zad 5_1  Napisz program, który wywietli na ekranie liczb argumentów wywołania aplikacji oraz podane argumenty.
        Każdy argument powinien być wyświetlony w odrębnym wierszu
*/
        System.out.println("\n________________________Zad 5_1________________________________________________________________________________");
        System.out.println("___Liczba argumentów "+args.length);
        // Lista argumentów - pętla typu for each
        for(String argument: args) System.out.println(argument);

        System.out.println("___Zad 5_3____________________________Lista argumentów w odwrotnej kolejności (zad 5_3): ");
        for (int i=args.length-1; i>=0; i--){
            System.out.print(args[i] + " , ");
        }

        System.out.println("\n___Zad 5_4______________Lista argumentów, każdy z odwrotną kolejnością liter (zad 5_4): ");
        for (int i=0; i < args.length; i++){
            for (int j = args[i].length()-1; j>=0; j--){
                System.out.print(args[i].charAt(j));
            }
            System.out.println();
        }

/*
        Napisz program, który uruchamiany z dwoma parametrami, imiei nazwisko, wyswietli na ekranie w kolejnych wierszach te dane wedlug schematu:
        Nazwisko: Kowalska
        Imie: Maria
        Nazwisko i imie: KOWALSKA Maria
        Inicjały: MK
        Login: KOmar
        Wielkości liter na wydruku powinny być zgodne z przykładem, niezależnie od tego, jakie wielkości liter wykorzysta użytkownik, podając imięi nazwisko.
        Wypisz błędy, które powstanąpodczas uruchomienia aplikacji z niewłaściwąliczbąparametrów.
*/
        System.out.println("\n________________________Zad 5_2_______________________________________________________________________________________");

        System.out.println("Nazwisko: " + Character.toUpperCase(args[1].charAt(0)) + args[1].substring(1).toLowerCase());                     //przed uruchomieniem wpisz parametry w edytorze kompilacji
        //System.out.println("Nazwisko: " + args[1].substring(0, 1).toUpperCase()+args[1].substring(1).toLowerCase());        //inna metoda
        System.out.println("Imie: " + Character.toUpperCase(args[0].charAt(0)) + args[0].substring(1).toLowerCase());
        System.out.println("Nazwisko i imie: " + args[1].toUpperCase() + " " + Character.toUpperCase(args[0].charAt(0)) + args[0].substring(1).toLowerCase());
        System.out.println("Inicjały: " + Character.toUpperCase(args[0].charAt(0)) + Character.toUpperCase(args[1].charAt(0)));
        System.out.println("Login: " + (args[1].substring(0,2)).toUpperCase() + (args[0].substring(0,3)).toLowerCase());

        /*
            Napisz program, który wyświetli na ekranie etykietkę zawierającą imię i nazwisko podane jako parametry wywołania aplikacji
                                                                                                                                                            */
        System.out.println("\n________________________Zad 5_5_______________________________________________________________________________________");

        int dlugoscTekstu = args[0].length() + args[1].length() + 1;       //1 bo jest spacja między wyrazami
        int margBoczny = 10;
        int margGoraDol = 1;
        int dlugoscRamki = dlugoscTekstu + (2 * margBoczny);
        int wysokoscRamki = 2 * margGoraDol + 1;
        String poziomaRamka = "";
        String jedenWersPusty = "*";
        String margBocznyString = "";

        for (int i=0; i < dlugoscRamki; i++) poziomaRamka +="*";
        for (int i=0; i < dlugoscRamki-2; i++) jedenWersPusty += " ";
        jedenWersPusty += "*";
        for (int i=0; i < margBoczny-1; i++) margBocznyString += " ";

        System.out.println(poziomaRamka);
        for (int i=0; i < margGoraDol; i++) System.out.println(jedenWersPusty);
        System.out.println("*" + margBocznyString + args[0] + " " + args[1] + margBocznyString + "*");
        for (int i=0; i < margGoraDol; i++) System.out.println(jedenWersPusty);
        System.out.println(poziomaRamka);

    }
}





















/*
nietypowa konstrukcja, tu w konstruktorze jest wyjątkowo "String[] args" bo jest to przesłane z programu main.
Normalnie program po uruchomieniu pokazuje liczbę argumentów = 0. Argumenty programu można wpisać wybierając z menu kompilacji "Edit configurations"
i tam w polu "Program arguments" podać je rozdzielone tylko spacją (np. ArnOLd bocZeK jeden dwa trzy).

*/