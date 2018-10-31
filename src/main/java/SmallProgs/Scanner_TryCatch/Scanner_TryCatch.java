//program dzieli przez siebie dwie wprowadzone z klawiatury liczby
//wykorzystanie: Try..Catch, Scanner
//na podstawie filmu Coraxa: https://www.youtube.com/watch?v=Nl16BzP6Fao&list=PLED70A92187B1406A&index=29

package SmallProgs.Scanner_TryCatch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scanner_TryCatch {

    public static void main(String[] args) {
        boolean isNotCorrect = true;
        Scanner skanuj = new Scanner(System.in);
        System.out.println("Program dzieli dwie podane liczby.");
        do {
            try {
                System.out.println("Podaj licznik: ");
                int licznik = skanuj.nextInt();
                System.out.println("Podaj mianownik: ");
                int mianownik = skanuj.nextInt();
                System.out.println(licznik + "/" + mianownik + " = " + (double) licznik / mianownik);
                isNotCorrect = false;
            }
            catch (InputMismatchException ime) {
                ime.printStackTrace();
                System.err.println("WYJ•TEK! Z≥y format danych wejúciowych");
                skanuj.nextLine();
                System.out.println("Wprowadü dane ponownie.");
            }
            catch (ArithmeticException ae) {
                ae.printStackTrace();
                System.err.println("WYJ•TEK! Dzielenie przez zero.");
                System.out.println("Wprowadü dane ponownie.");
            }
        } while (isNotCorrect);
        skanuj.close();
    }

}


//przy dzieleniu przez zero nie generuje b≥Ídu - DZIWNE!