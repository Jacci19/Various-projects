package Books_exercises.Rychlicki.Rychlicki_2_Javadoc;

import java.util.Scanner;
/** Klasa <code>MyIntArray</code> zawiera statyczne metody u�atwiaj�ce
  * wprowadzanie danych do jednowymiarowej tablicy liczb ca�kowitych,
  * wy�wietlanie zawarto�ci tablicy na konsoli oraz konwertowania tablicy
  * liczb zmiennoprzecinkowych na tablic� liczb ca�kowitych.
  * @author Wies�aw Rychlicki
  * @version 1.0 (2012-02-06)
  */

public class MyIntArray {
/** Umo�liwia wprowadzanie danych liczbowych (typu <code>int</code>)
  * z konsoli do tablicy. Dane wprowadzane s� przy u�yciu metod z klasy
  * <code>java.util.Scanner</code>. Podczas wprowadzania danych 
  * wy�wietlana jest informacja postaci: <code>Liczba 2 z 5, n = </code>.
  * @param tab jednowymiarowa tablica liczb ca�kowitych.
  */
	public static void input(int[] tab) {
	    int n = tab.length;
	    Scanner input = new Scanner(System.in);
	    for(int i = 0; i < n; ++i) { 
		    System.out.printf("Liczba %BlackJack_FX z %BlackJack_FX, n = ", i+1, n);
	        tab[i] = input.nextInt();
        }
    }
/** Umo�liwia wy�wietlanie liczb zawartych w tablicy na konsoli.
  * Kolejne liczby oddzielane s� odst�pem.
  * @param tab jednowymiarowa tablica liczb ca�kowitych.
  */   
    public static void print(int[] tab) {
	    int n = tab.length;
	    for(int i = 0; i < n; ++i)
	        System.out.print(tab[i]+" ");
    }
/** Umo�liwia wy�wietlanie liczb zawartych w tablicy na konsoli.
  * Kolejne liczby oddzielane s� odst�pem. Po wy�wietleniu ostaniej
  * liczby, do konsoli przesy�any jest znak ko�ca linii.
  * @param tab jednowymiarowa tablica liczb ca�kowitych.
  */
    public static void println(int[] tab) {
	    int n = tab.length;
	    for(int i = 0; i < n; ++i)
	        System.out.print(tab[i]+" ");
	    System.out.println();    
    }
/** Zwraca jednowymiarow� tablic� liczb� typu <code>double</code>, 
  * o warto�ciach r�wnych warto�ciom tablicy liczb ca�kowitych 
  * przekazanej jako parametr.
  * @param tab jednowymiarowa tablica liczb ca�kowitych.
  */
	public static double[] toDoubleArray(int[] tab) {
	    int n = tab.length;
	    double[] tmp = new double[n];
	    for(int i = 0; i < n; ++i)  
		    tmp[i] = (double)tab[i];
        return tmp;
    }
/** Zwraca jednowymiarow� tablic� liczb typu <code>int</code>, 
  * o warto�ciach odpowiadaj�cym (zaokr�glenie w BlackJack_FX�) warto�ciom
  * element�w tablicy liczb zmiennoprzecinkowych typu <code>double</code>
  * przekazanej jako parametr.
  * @param tab jednowymiarowa tablica liczb zmiennoprzecinkowych typu 
  * <code>double</code>.
  */
    public static int[] toIntArray(double[] tab) {
        int n = tab.length;
        int[] tmp = new int[n];
        for(int i = 0; i < n; ++i)  
            tmp[i] = (int)tab[i];
        return tmp;
    }
}


