package Time;

import java.util.Date;
import java.util.GregorianCalendar;

public class Time_Main {

    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());                         // liczba milisekund od 1.01.1970r. do chwili obecnej
        System.out.println(System.currentTimeMillis()/(1000*3600*24*365.5));    //liczba lat od 1.01.1970r. do chwili obecnej

        //mierzenie czasu wykonania się kodu
        long t1 = System.currentTimeMillis();
        for (int i=0; i<1000000; i++){
            double x = Math.pow(Math.random(), Math.random());
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Powyższa pętla wykonała się w czasie " + (t2-t1)/1000.0 + "s.");

        //data
        Date today = new Date();
        System.out.println("Dzisiejsza data: " + today);
        System.out.printf("Dzisiejsza data POL: %tc\n", today);
        System.out.printf("Dzisiejsza data POL: %tc\n", new Date());              //można też tak, wtedy niepotrzebne jest: Date today = new Date();

        //klasa Date jest odradzana, zastąpiła ją klasa GregorianCalendar
        GregorianCalendar todayGC = new GregorianCalendar();
        System.out.println("\nDzisiejsza data GC: " + todayGC);
        System.out.printf("Dzisiejsza data GC POL: %tc\n", todayGC);



    }
}
