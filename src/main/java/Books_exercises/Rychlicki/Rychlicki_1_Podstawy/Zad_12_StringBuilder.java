package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

public class Zad_12_StringBuilder {
    public Zad_12_StringBuilder() {

        System.out.println("\n________________________Moje ćwiczenie_______________________________________________________________________________________");
        String tekst = "jedenDwaCzteryPiecSzesc";
        System.out.println("String wejściowy: " + tekst);
        StringBuilder tekstB = new StringBuilder(tekst);
        System.out.println(tekstB.insert(8, "Trzy"));
        System.out.println(tekstB.replace(5,8, "Two"));
        System.out.println(tekstB.replace(5,12, "Two"));
        System.out.println(tekstB.lastIndexOf("e"));
        System.out.println(tekstB.reverse());

        String[] Tabl = tekst.split("y");
        System.out.println("Split podzielił ten tekst na: " + Tabl[0] + " i " + Tabl[1]);

        String tekst2 = "jeden-dwa-trzy-cztery";
        String[] Tabl2 = tekst2.split("-");
        System.out.println(tekst2 + " - Split podzielił ten tekst na: " + Tabl2[0] + ", " + Tabl2[1] + ", " + Tabl2[2] + ", " + Tabl2[3] + ".");



    }
}
