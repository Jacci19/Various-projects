package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

public class Zad_09_TypyZmiennoprzecinkowe {
    public Zad_09_TypyZmiennoprzecinkowe() {

        /* Zad 9_1 Przeanalizuj kod                                                                                                                              */
        System.out.println("\n________________________Zad 9_1_______________________________________________________________________________________");
        double a = Double.MIN_VALUE;
        System.out.println("Minimalna wartość dodatnia: " + a);
        System.out.println("BIN: " + Long.toBinaryString(Double.doubleToLongBits(a)));
        System.out.println("HEX: " + Double.toHexString(a));
        a = -a;
        System.out.println("Liczba przeciwna: " + a);
        System.out.println("BIN: " + Long.toBinaryString(Double.doubleToLongBits(a)));
        System.out.println("HEX: " + Double.toHexString(a));

/*      Zad 9_2
        Przedstaw w postaci słowa 64-bitowego reprezentację binarną kilku wybranych liczb typu double.  Z ciągu 64 bitów wyodrębnij bit znaku, cechęi mantysę.
        a) 0,25; 0,5; 1,0; 2,0; 512,0;
        b) 0,01; 0,1; 1,0; 10,0; 100,0;
        c) 1,367; 1367; 13,67; –13,67; 1,367e–12; 1,367e12;
        BlackJack_FX) symbole specjalne: Double.NaN (ang. Not-a-Number), np. wynik pierwiastkowania liczby ujemnej, Double.NEGATIVE INFINITY (np. wynik dzielenia –1/0,0),
           Double.POSITIVE INFINITY (np. wynik dzielenia 1/0,0).
        e) dwie reprezentacje zmiennoprzecinkowe zera 0.0 i –0.0 oraz wartości ekstremalne Double.MIN VALUE, Double.MAX_VALUE i 2.2250738585072014e-308.
*/
        System.out.println("\n________________________Zad 9_2_______________________________________________________________________________________");

        String zero = "0000000000000000000000000000000000000000000000000000000000000000";
         double[] d = {0.25, 0.5, 1.0, 2.0, 512.0};
        // double[] BlackJack_FX = {0.01, 0.1, 1.0, 10.0, 100.0};
        // double[] BlackJack_FX = {1.367, 1367, 1.367e-12, 1.367e12, 13.67, -13.67};
        // double[] BlackJack_FX = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY};
        // double[] BlackJack_FX = {0.0, -0.0, Double.MIN_VALUE, Double.MAX_VALUE, 2.2250738585072014e-308};
        for (double x : d) {
            System.out.println("x = " + x);
            String tmp = Long.toBinaryString(Double.doubleToLongBits(x));
            String bin = zero.substring(0, 64 - tmp.length()) + tmp;
            System.out.println("BIN:       " + bin);
            System.out.println("Bit znaku: " + bin.charAt(0));
            System.out.println("Cecha:     " + bin.substring(1, 12));
            System.out.println("Mantysa:   " + bin.substring(12));
            System.out.println();
        }
    }
}
