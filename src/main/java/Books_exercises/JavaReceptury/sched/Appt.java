package Books_exercises.JavaReceptury.sched;

import java.util.*;

/** Prosty obiekt reprezentujący spotkanie o czasie określonym przez 
 * rok, miesiąc, dzień, godzinę i minutę.
 * @author Ian Darwin
 */
public class Appt implements Comparable<Appt> {

    //-----------------------------------------------------------------
    //    GŁÓWNE ZMIENNE -- SPOTKANIE
    //-----------------------------------------------------------------
    /** Co mamy wtedy do zrobienia? */
    String text;    
    /** Rok (wg kalendarza gregoriańskiego) spotkania */
    int year;
    /** Miesiąc (licząc do 0) */
    int month;
    /** Dzień */
    int day;
    /** Godzina */
    int hour;
    /** Minuta */
    int minute;
    //-----------------------------------------------------------------
    //    STAŁE NA POTRZEBY POWTARZANIA 
    //-----------------------------------------------------------------
    /** Stała używana w polach, które nie biorą udziału w porównywaniu.  */
    public static final int N_A = -1;
    /** Typ powtarzania - spotkanie się nie powtarza. */
    public static final int NONE = 0;
    /** Typ powtarzania - spotkanie się nie powtarza. */
    public static final int HOURLY = 1;
    /** Typ powtarzania - spotkanie powtarza się codziennie. */
    public static final int DAILY = 2;
    /** Typ powtarzania - spotkanie powtarza się co tydzień. */
    public static final int WEEKLY = 3;
    /** Typ powtarzania - spotkanie powtarza się co miesiąc i jest określane
     * w sposób "12. każdego miesiąca". */
    public static final int MONTHLY_NUMDAY_OF_M = 41;
    /** Typ powtarzania - spotkanie powtarza się co miesiąc i jest określane
     * w sposób "w 2-gi wtorek miesiąca każdego miesiąca". */
    public static final int MONTHLY_WEEKDAY_OF_M = 42;
    /** Typ powtarzania - spotkanie powtarza się co rok. */
    public static final int YEARLY = 5;
    /** The count factor meaning forever */
    public static final int FOREVER = Integer.MAX_VALUE;

    //-----------------------------------------------------------------
    //    GŁÓWNE ZMIENNE -- POWTARZANIE
    //-----------------------------------------------------------------
    /** Typ powtarzania spotkania. */
    protected int r_type = NONE;
    /** Odstęp czasu pomiędzy powtarzającymi się spotkaniami: 2=co (godzinę, 
     * dzień, miesiąc, rok). */
    protected int r_interval = NONE;
    /** Liczba powtórzeń spotkania. */
    protected int r_count = NONE;
    /** Obiekt Calendar używany do wyliczania daty i godziny spotkania. */
    protected static GregorianCalendar gc;

    //-----------------------------------------------------------------
    //    METODY - KONSTRUKTOR
    //-----------------------------------------------------------------
    /** Konstruktor klasy Appointment. */
    public Appt(String text, int y, int mo, int d, int h, int min) {
        this.text = text;
        year = y;
        month  = mo;
        day  = d;
        hour = h;
        minute = min;
        if (gc == null)
            gc = new GregorianCalendar();
    }

    //-----------------------------------------------------------------
    //    METODY - POWTÓRZENIA
    //-----------------------------------------------------------------
    public void setRep(int typ, int intv, int count) {
        r_type = typ;
        r_interval = intv;
        r_count = count;
    }

    /** Decide whether a given Appointment matches the given y/m/d.
     */
    public boolean matches(int y, int m, int d) {
        // Zaczynamy od najprostszych przypadków!
        if (year == y && month == m && day == d)
            return true;
        // Jeśli spotkanie NIE wypada dziś I nie powtarza się, to nie ma 
        // dla nas znaczenia.
        if (r_count == NONE)
            return false;

        // W przeciwnym razie warto mu się przyjrzeć!

        // System.out.println("ME:"+year+","+month+","+day);
        // System.out.println("YE:"+y   +","+m    +","+d  );

        // Używamy obiektu GregorianCalendar do dalszych obliczeń... 
        gc.set(Calendar.YEAR,  year);
        gc.set(Calendar.MONTH, month-1);
        gc.set(Calendar.DAY_OF_MONTH, day);
        gc.set(Calendar.HOUR,  hour);
        gc.set(Calendar.MINUTE, minute);

        System.out.println(gc.getTime().toString());

        for (int i=0; i<r_count && 
            gc.get(Calendar.YEAR)<=year && 
            gc.get(Calendar.MONTH)<=month && 
            gc.get(Calendar.DAY_OF_MONTH)<=day; i++) {
            switch(r_type) {
            case HOURLY:
                break;
            case DAILY:
                gc.add(Calendar.DAY_OF_MONTH, r_interval);
                break;
            case WEEKLY:
                break;
            case MONTHLY_NUMDAY_OF_M:
                break;
            case MONTHLY_WEEKDAY_OF_M:
                break;
            case YEARLY:
                break;
            }

            // No dobra, dodaliśmy odpowiedni odstęp czasu. Teraz sprawdzimy
            // czy wyliczona data i godzina odpowiada temu co nas intresuje.
            if (gc.get(Calendar.YEAR) == y &&
                gc.get(Calendar.MONTH) == m &&
                gc.get(Calendar.DAY_OF_MONTH) == d)
                return true;
        } 

        // Wyszliśmy z pętli, lecz nie udało nam się niczego znaleźć, 
        // a zatem...
        return false;
    }

    // BEGIN main
// public class Appt implements Comparable<Appt> {
    // Klasa nie jest kompletna.
    //-----------------------------------------------------------------
    //    METODY - PORÓWNYWANIE
    //-----------------------------------------------------------------
    /** compareTo metoda, interfejs Comparable.
     * Porównanie tego obiektu Appt z innym w celu 
     * ich odpowiedniego posortowania.
     * <P>Przy sortowaniu uwzględniane są tylko tekst, data
     * i czas, bez powtórzeń! 
     * Powtórzenia dotyczą zdarzeń powtarzających się co 
     * jakiś czas, np. spotkanie jest w każdy piątek o 9:00.
     * Zgodne z działaniem metody equals().
     * @return -1, jeśli this<a2, +1 jeśli this>a2, 0 
     * w pozostałych przypadkach.
     */
    @Override
    public int compareTo(Appt a2) {
        if (year < a2.year)
            return -1;
        if (year > a2.year)
            return +1;
        if (month < a2.month)
            return -1;
        if (month > a2.month)
            return +1;
        if (day < a2.day)
            return -1;
        if (day > a2.day)
            return +1;
        if (hour < a2.hour)
            return -1;
        if (hour > a2.hour)
            return +1;
        if (minute < a2.minute)
            return -1;
        if (minute > a2.minute)
            return +1;
        return text.compareTo(a2.text);
    }

    /** Porównanie tego obiektu z innym w celu określenia,
     * czy są one równe. Zgodne z compareTo(). Z tego 
     * powodu uwzględniane są wyłącznie tekst, data i czas,
     * bez powtórzeń.
     * @return true, jeśli obiekty są równe, false w przeciwnym
     * przypadku.
     */
    @Override
    public boolean equals(Object o2) {
        Appt a2 = (Appt) o2;
        if (year != a2.year ||
            month != a2.month ||
            day != a2.day ||
            hour != a2.hour ||
            minute != a2.minute)
            return false;
        return text.equals(a2.text);
    }
    // END main

    /** Metoda zwraca łańcuch znaków reprezentujący ten obiekt Appt.
     * Jej wyniki są przeznaczone do celów testowych, a nie od właściwej
     * prezentacji w aplikacji!
     */
    @Override
    public String toString() {
        return new StringBuffer().append(year).append(' ').
            append(month).append(' ').append(day).append(' ').
            append(hour).append(' ').append(minute).append(' ').
            append(text).toString();
    }

    /** Metoda wytwórcza: tworzy obiekt Appt na podstawie łańcucha znaków.
     * Ze względu na większą wydajność działania, jest to metoda statyczna,
     * a zatem konieczne jest użycie rzutowania. Warto się nad tym jeszcze
     * zastanowić.
     */
    public static Appt fromString(String s) {
        StringTokenizer st = new StringTokenizer(s);
        if (st.countTokens() < 6) throw new
            IllegalArgumentException("Zbyt mało pól w łańcuchu: " + s);
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int i = Integer.parseInt(st.nextToken());
        StringBuffer sb = new StringBuffer();
        while (st.hasMoreElements()) {
            sb.append(st.nextToken());
            if (st./*still*/hasMoreElements())
                sb.append(' ');
        }
        return new Appt(sb.toString(), y, m, d, h, i);
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getMonth() {
        return month;
    }
    
    public String getText() {
        return text;
    }
    
    public int getYear() {
        return year;
    }
    
}
