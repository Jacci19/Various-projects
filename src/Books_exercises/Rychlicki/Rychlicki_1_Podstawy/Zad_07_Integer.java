package Books_exercises.Rychlicki.Rychlicki_1_Podstawy;

public class Zad_07_Integer {
    public Zad_07_Integer() {

        /*Zad 7_1 Przeanalizuj kod                                                                                                                              */

        System.out.println("\n________________________Zad 7_1_______________________________________________________________________________________");
        System.out.println("Wybrane stałe i metody statyczne klasy Integer\n");
        System.out.println("Wartość minimalna typu int: "+Integer.MIN_VALUE);
        System.out.println("Wartość maksymalna typu int: "+Integer.MAX_VALUE);
        System.out.println("Rozmiar (w bitach) typu int: "+Integer.SIZE);       //Int zakres: od -2^31 do 2^32-1
        int aa = 179;
        System.out.println("Liczba całkowita a = "+aa+" - zapis (ciąg cyfr):");
        System.out.println("w systemie binarnym: "+Integer.toBinaryString(aa));
        System.out.println("w systemie ósemkowym: "+Integer.toOctalString(aa));
        System.out.println("w systemie szesnastkowym: "+Integer.toHexString(aa));
        System.out.println("w systemie dziesiątkowym: "+Integer.toString(aa));
        System.out.println("w systemie czwórkowym: "+Integer.toString(aa, 4));
        int bb = Integer.parseInt("-177");                                          //zamiana string na int
        System.out.println("b = "+bb);
        int cc = Integer.parseInt("-177", 8);
        System.out.println("c = "+cc);
        System.out.println("Metoda signum():");
        System.out.println("Znak liczby a: "+Integer.signum(aa));
        System.out.println("Znak liczby b: "+Integer.signum(bb));
        System.out.println("Znak liczby 0: "+Integer.signum(0));

        /*Zad 7.2 Przeanalizuj kod
                                                                                                                                                                                 */
        System.out.println("\n________________________Zad 7_2_______________________________________________________________________________________");


        System.out.println("Wybrane metody obiektów klasy Integer\n");
        // Konstruktor tworzący nowy obiekt typu Integer reprezentujący podaną liczbę, w tym przypadku 1024
        Integer a = new Integer(1024);
        // Konstruktor tworzący nowy obiekt klasy Integer na podstawie podanego łańcucha znaków (ciągu cyfr). Zero nieznaczące zostanie pominięte
        Integer b = new Integer("02000");
        // Statyczna metoda decode() zwraca obiekt reprezentujący liczbę zakodowaną w łańcuchu znaków - 0 na początku oznacza liczbę zapisaną w systemie ósemkowym
        Integer c = Integer.decode("02000");
        // J.w. - początek ciągu w postaci 0x oznacza liczbę zakodowaną w systemie szesnastkowym
        Integer d = Integer.decode("0x2000");
        System.out.println("a = "+a);
        System.out.println("b = "+b);
        System.out.println("c = "+c);
        System.out.println("d = "+d);

        /* Metoda equals() sprawdza, czy obiekt wywołujący tę metodę jest równy obiektowi podanemu jako parametr. Wynik porównania jest wartością logiczną (true albo false) */
        System.out.println("Czy obiekt a jest równy obiektowi b? "+ a.equals(b));
        System.out.println("Czy obiekt a jest równy obiektowi c? "+ a.equals(c));
        /* Metoda compareTo() porównuje obiekt  wywołujący tę metodę z obiektem stanowiącym parametr. Wynik jest jedną z liczb: -1, 0 i 1. 0 oznacza równość obiektów,
        -1 oznacza, że pierwszy obiekt (wywołujący metodę) jest mniejszy od drugiego obiektu (parametru), a 1 oznacza, że pierwszy obiekt jest większy od drugiego) */
        System.out.println("Porównanie obiektu a z obiektem c? "+ a.compareTo(c));
        System.out.println("Porównanie obiektu c z obiektem d? "+ c.compareTo(d));
        System.out.println("Porównanie obiektu d z obiektem c? "+ d.compareTo(c));
        /* Metoda statyczna valueOf() zwraca obiekt klasy Integer reprezentujący wartość określoną jako parametr - liczba całkowita lub łańcuch znaków (cyfr dziesiętnych) */
        a = Integer.valueOf(1000);
        b = Integer.valueOf("1000");
        /* Drugi parametr metody statycznej valueOf określa podstawę systemu liczbowego, w którym należy zinterpretować ciąg cyfr podany jako pierwszy parametr metody */
        c = Integer.valueOf("1000", 2);  // 1000 w układzie binarnym
        d = Integer.valueOf("1000", 16); // 1000 w układzie szesnastkowym
        System.out.println("a = "+a);
        System.out.println("b = "+b);
        System.out.println("c = "+c);
        System.out.println("d = "+d);

        /* Porównywanie obiektów */
        System.out.println("Czy obiekt a jest równy obiektowi b? " +  a.equals(b));
        System.out.println("Czy obiekt a jest równy obiektowi c? " +  a.equals(c));
        System.out.println("Porównanie obiektu a z obiektem c? "  +  a.compareTo(c));
        System.out.println("Porównanie obiektu c z obiektem d? "  +  c.compareTo(d));
        System.out.println("Porównanie obiektu d z obiektem c? "  +  d.compareTo(c));
    }
}
