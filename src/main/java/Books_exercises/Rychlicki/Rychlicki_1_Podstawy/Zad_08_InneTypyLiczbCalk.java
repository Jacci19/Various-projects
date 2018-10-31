package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

public class Zad_08_InneTypyLiczbCalk {
    public Zad_08_InneTypyLiczbCalk() {

        /*Zad 8_1 Przeanalizuj kod                                                                                                                              */

        System.out.println("\n________________________Zad 8_1_______________________________________________________________________________________");

        long n = Long.MAX_VALUE;
        long m = n + 1;
        System.out.println("n = " + n);
        System.out.println("BIN: " + Long.toBinaryString(n));
        System.out.println("HEX: " + Long.toHexString(n));
        System.out.println("m = " + m);                           //z max przechodzi na min (zapętla się)
        System.out.println("BIN: " + Long.toBinaryString(m));
        System.out.println("HEX: " + Long.toHexString(m));
        Long max = new Long(n);
        System.out.println("max: " + max);
        System.out.println("Zamiana na typ int, m = " + max.intValue());
        System.out.println("BIN: " + Integer.toBinaryString(max.intValue()));
        System.out.println("HEX: " + Integer.toHexString(max.intValue()));
        System.out.println("Zamiana na typ int, m = " + (int) m);
        System.out.println("BIN: " + Integer.toBinaryString((int) m));
        System.out.println("HEX: " + Integer.toHexString((int) m));

/*      Zad 8_2___8_3
        Napisz program wyświetlający minimalne i maksymalne wartości liczb całkowitych typu byte, short, int i long.
        Wyniki wywietl w postaci:
          8.2. nazwa typu <wartośćminimalna, wartośćmaksymalna>.
          8.3. w postaci binarnej i szesnastkowej.

*/
        System.out.println("\n________________________Zad 8_2___8_3___________________________________________________________________________________");
        System.out.println("< MIN, MAX >");
        System.out.println("byte DEC < " + Byte.MIN_VALUE + " , " + Byte.MAX_VALUE + " >");
        System.out.println("byte BIN < " + Integer.toBinaryString(Byte.MIN_VALUE) + " , " + Integer.toBinaryString(Byte.MAX_VALUE) + " >");
        System.out.println("byte HEX < " + Integer.toHexString(Byte.MIN_VALUE) + " , " + Integer.toHexString(Byte.MAX_VALUE) + " >");
        System.out.println("short DEC < " + Short.MIN_VALUE + " , " + Short.MAX_VALUE + " >");
        System.out.println("short BIN < " + Integer.toBinaryString(Short.MIN_VALUE) + " , " + Integer.toBinaryString(Short.MAX_VALUE) + " >");
        System.out.println("short HEX < " + Integer.toHexString(Short.MIN_VALUE) + " , " + Integer.toHexString(Short.MAX_VALUE) + " >");
        System.out.println("int DEC < " + Integer.MIN_VALUE + " , " + Integer.MAX_VALUE + " >");
        System.out.println("int BIN < " + Integer.toBinaryString(Integer.MIN_VALUE) + " , " + Integer.toBinaryString(Integer.MAX_VALUE) + " >");
        System.out.println("int HEX < " + Integer.toHexString(Integer.MIN_VALUE) + " , " + Integer.toHexString(Integer.MAX_VALUE) + " >");
        System.out.println("long DEC < " + Long.MIN_VALUE + " , " + Long.MAX_VALUE + " >");
        System.out.println("long BIN < " + Long.toBinaryString(Long.MIN_VALUE) + " , " + Long.toBinaryString(Long.MAX_VALUE) + " >");
        System.out.println("long HEX < " + Long.toHexString(Long.MIN_VALUE) + " , " + Long.toHexString(Long.MAX_VALUE) + " >");

        /*Zad 8_4      Napisz program zamieniający łańcuch znaków na ciąg bajtów odpowiadających tym znakom. Czyli wypisuje kody ASCII znaków ze Stringa                 */
        System.out.println("\n________________________Zad 8_4___________________________________________________________________________________");
        String inputString = "Programowanie";
        System.out.println("String wejściowy: " + inputString);
        System.out.print("Po zamianie na bajty: ");
        byte[] bajty = inputString.getBytes();
        for (byte b : bajty) System.out.print(" " + b + ",");
        System.out.println("\b.");
        //Zad 8_5     Zamiana tablicy bajtów na string - kontynuacja zadania 8_4
        String stringZbajtow = new String(bajty);       //Jeden z konstruktorów klasy String tworzy łańcuch znaków na podstawie tablicy bajtów.
        System.out.println("Zad. 8_5: String z bajtów: " + stringZbajtow);

/*      Zad 8_6   Napisz program obliczający sumę cyfr liczby całkowitej dodatniej podanej:
        a) w postaci tekstu — łańcucha znaków (cyfr) reprezentujących tą liczbę,
        b) jako wartość typu long.                                                                                              */

        System.out.println("\n________________________Zad 8_6___________________________________________________________________________________");
        String liczba = "123";
        //long n = 3784596320L;                   // dla opcji (b) odkomentuj te dwie linie a zakomentuj linię:   "String liczba = ... "
        //String liczba = Long.toString(n);       //
        System.out.println("Liczba naturalna: " + liczba);
        byte[] kod = liczba.getBytes();
        int suma = 0;
        System.out.print("Cyfry tej liczby zamienione na bajty: ");
        for (byte c : kod) {
            System.out.print(c + ", ");
            suma += c - 48;
        }
        System.out.println("\nSuma cyfr: " + suma);

/*
        Zad 8_7  Napisz program zamieniający łańcuch znaków ASCII na łańcuch cyfr szesnastkowych odpowiadających tym znakom.                                    */

        System.out.println("\n________________________Zad 8_7___________________________________________________________________________________");
        String napis = "szyfr";
        System.out.print(napis + " -> ");
        byte[] kodd = napis.getBytes();
        for (byte nn : kodd) System.out.print(Integer.toHexString(nn).toUpperCase());

/*
        Zad 8_8  Napisz program  zamieniający łańcuch cyfr szesnastkowych na odpowiadający mu łańcuch znaków ASCII (dwie cyfry przypadają na jeden znak).                   */
        System.out.println("\n________________________Zad 8_8___________________________________________________________________________________");
        String hex = "737A796672";
        System.out.println("Hex wejściowy: " + hex);
        byte[] tmp = new byte[hex.length() / 2];
        for (int i = 0; i < tmp.length; ++i){
            tmp[i] = Byte.parseByte(hex.substring(2 * i, 2 * i + 2), 16);
            System.out.println("tmp[i]: " + tmp[i]);
        }
        System.out.println("Łancuch ASCII: " +  new String(tmp));
    }
}
