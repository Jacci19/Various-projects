package Books_exercises.Rychlicki.Rychlicki_3_Fraction;

public class Czesc_3_main {
    public static void main(String[] args) {


        System.out.println("\n________________________Zad 20_1_______________________________________________________________________________________");
/*
    Zadanie 20.1.
        Zbuduj klasę Fraction, która będzie zawierać dwa prywatne pola reprezentujące licznik i mianownik ułamka.
    W klasie tej umieść konstruktor z dwoma parametrami, który będzie budował ułamek na podstawie dwóch liczb całkowitych (licznika i mianownika),
    oraz publiczną metodę toString(), zwracającąułamek w postaci łańcucha znaków, np. "4/13" (licznik, kreska ułamkowa / i mianownik).
    Napisz program pokazujący działanie konstruktora i zdefiniowanej metody.                                                                        */

        Fraction ulamek = new Fraction(2, 3);
        System.out.println("ulamek - wartość: " + ulamek.wartosc());
        System.out.println("ulamek - postać zwykła (toString): " + ulamek.toString());
        System.out.println("ulamek - postać zwykła: " + ulamek);                       //daje taki sam wynik jak wiersz wyżej. Metoda toString automatycznie się dodaje.



        System.out.println("\n________________________Zad 20_2_______________________________________________________________________________________");
/*
    Zadanie 20.2.
        Dodaj do klasy Fraction konstruktor bezparametrowy budujący ułamek odpowiadający liczbie 0 oraz konstruktor z jednym parametrem całkowitym m
    budujący ułamek m/1. Napisz program pokazujący działanie tych konstruktorów.                */

        Fraction ulamek2a = new Fraction();
        Fraction ulamek2b = new Fraction(5);

        System.out.println("ulamek2a - wartość: " + ulamek2a.wartosc());
        System.out.println("ulamek2a - postać zwykła (toString): " + ulamek2a.toString());

        System.out.println("ulamek2b - wartość: " + ulamek2b.wartosc());
        System.out.println("ulamek2b - postać zwykła (toString): " + ulamek2b);



        System.out.println("\n________________________Zad 20_3_______________________________________________________________________________________");
/*
    Zadanie 20.3.
        Utwórz w klasie Fraction konstruktor kopiujący i napisz program pokazujący działanie tego konstruktora.             */

        Fraction ulamek3 = new Fraction(ulamek);                                //kopiuje ulamek z zadania 20.1
        System.out.println("ulamek3 - postać zwykła (kopia ulamek): " + ulamek3);
        System.out.println("Czy równe: " + (ulamek3 == ulamek));              // zwraca "false" bo operator == porównuje referencje obiektów, a nie ich wartości.
        Fraction ulamek3b = ulamek;
        System.out.println("Czy równe: " + (ulamek3b == ulamek));              // tym razem zwraca "true"



        System.out.println("\n________________________Zad 20_4_______________________________________________________________________________________");
/*
Zadanie 20.4.        (correction)
    Zauważmy, że obiekty new Fraction(-3, 4) i new Fraction(3, -4) reprezentująten sam ułamek -3/4. Po zamianie obiektów na łańcuchy znaków (metodą toString())
otrzymamy odpowiednio "-3/4" i "3/-4". Podobnie wyglądałaby sytuacja dla obiektów new Fraction(2, 5) i new Fraction(-2, -5) reprezentujących ułamek 2/5.
Zapis ułamka w postaci "3/-4" lub "-2/-5" nie wygląda korzystnie (lepszy będzie zapis "-3/4" lub "2/5"). Można przyjąć, że będziemy zapamiętywali
zawsze dodatni mianownik, a znak licznika zadecyduje o znaku ułamka. Dodaj do klasy Fraction prywatną metodę, która wywołana wewnątrz konstruktora
skoryguje licznik i mianownik ułamka zgodnie z przyjętąumową. Napisz program pokazujący skutki działania tej metody.                                */

        Fraction ulamek4a = new Fraction(-2, 3);
        System.out.println("ulamek4a - postać zwykła: " + ulamek4a.toString());           //zwraca -2/3
        Fraction ulamek4b = new Fraction(4, -5);
        System.out.println("ulamek4b - postać zwykła: " + ulamek4b.toString());           //zwraca -4/5
        Fraction ulamek4c = new Fraction(-5, -7);
        System.out.println("ulamek4c - postać zwykła: " + ulamek4c.toString());           //zwraca 5/7



        System.out.println("\n________________________Zad 20_5_______________________________________________________________________________________");
/*
Zadanie 20.5.    (skracanie, rozszerzanie)
Zauważmy, że obiekty new Fraction(3, 4) i new Fraction(15, 20) reprezentująten sam ułamek 3/4. Dodaj do klasy Fraction publiczne metody służące do skracania
ułamka (przez największy wspólny dzielnik licznika i mianownika lub innąpodanąwartość) oraz publiczną metodę pozwalającą na rozszerzanie ułamka.
Napisz program pokazujący działanie tych metod.
 */
        Fraction ulamek5 = new Fraction(18, 24);
        System.out.println("ulamek5: " + ulamek5.toString());
        ulamek5.reduce();
        System.out.println("ulamek5 po skróceniu przez nwd: " + ulamek5.toString());

        Fraction ulamek5b = new Fraction(18, 24);
        System.out.println("ulamek5b: " + ulamek5b.toString());
        ulamek5b.reduce(2);
        System.out.println("ulamek5b po skróceniu przez n: " + ulamek5b.toString());
        ulamek5b.equivalent(5);
        System.out.println("ulamek5b po rozszerzeniu o 5: " + ulamek5b.toString());



        System.out.println("\n________________________Zad 20_6_______________________________________________________________________________________");
/*
Zadanie 20.6.
W klasie Fraction utwórz metody zwracające nowy obiekt Fraction, będący iloczynem ułamka reprezentowanego przez ten obiekt i inny obiekt
lub liczbęcałkowitą. Napisz program pokazujący działanie tych metod.                                                                            */

        Fraction ulamek6a = new Fraction(2, 3);
        Fraction ulamek6b = new Fraction(3, 4);
        System.out.println("Ułamek 6a: " + ulamek6a + "   Ułamek 6b: " + ulamek6b + "   Liczba całkowita 1: 5,  Liczba całkowita 2: 6");
        System.out.println("Wynik mnożenia ułamków: " + ulamek6a.mult(ulamek6b));
        System.out.println("Wynik mnożenia ułamka przez liczbę: " + ulamek6a.mult(5));



        System.out.println("\n________________________Zad 20_7_______________________________________________________________________________________");
/*
Zadanie 20.7.
W klasie Fraction utwórz metody statyczne (o nazwie product(), ang. product — iloczyn) zwracające obiekt Fraction, będący iloczynem dwóch ułamków,
ułamka i liczby całkowitej lub dwóch liczb całkowitych. Napisz program demonstrujłcy działanie tych metod. */

        System.out.println("Iloczyn dwóch ułamków (" + ulamek6a + " i " + ulamek6b + "): " + Fraction.product(ulamek6a, ulamek6b));           //aby działało, metoda product musi być static
        //Statyczna zmienna lub metoda istnieją zawsze, nawet gdy nie nie została utworzona żadna instancja klasy. Czyli odwołać można się do nich bez tworzenia nowego obiektu.
        System.out.println("Iloczyn ułamka " + ulamek6a + " i liczby 5: " + Fraction.product(ulamek6a, 5));
        System.out.println("Iloczyn liczby 5 i liczby 6: " + Fraction.product(5, 6));



        System.out.println("\n________________________Zad 20_8_______________________________________________________________________________________");
/*
Zadanie 20.8.
W klasie Fraction utwórz metodę zwracającą nowy obiekt Fraction, reprezentujący ułamek odwrotny do ułamka zawartego w obiekcie wywołującym tą metodę.
Utwórz metodę statycznąo podobnej funkcjonalności. Napisz program pokazujący działanie tych metod. Wykorzystaj odwrotność do obliczenia ilorazu dwóch ułamków. */

        System.out.println("Odwrotność ułamka " + ulamek6a + " (metoda NIEstatyczna): " + ulamek6a.multInv());
        System.out.println("Odwrotność ułamka " + ulamek6a + " (metoda statyczna): " + Fraction.multInv(ulamek6a));
        System.out.println("Iloraz ułamków " + ulamek6a + " i " + ulamek6b + ": " + Fraction.product(ulamek6a, Fraction.multInv(ulamek6b)));        //dzielenie ułamków to mnożenie przez odwrotność



        System.out.println("\n________________________Zad 20_9_______________________________________________________________________________________");
/*
Zadanie 20.9.
W klasie Fraction utwórz metody zwracające nowy obiekt Fraction, będący ilorazem ułamka reprezentowanego przez ten obiekt i inny obiekt lub liczbęcałkowitą.   */

        System.out.println("Iloraz ułamków " + ulamek6a + " i " + ulamek6b + " (div): " + ulamek6a.div(ulamek6b));
        System.out.println("Iloraz ułamka " + ulamek6a + " i liczby 2 (div): " + ulamek6a.div(2));
        try {
            System.out.println("Iloraz ułamka 6a i liczby 0 (div): " + ulamek6a.div(0));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }



        System.out.println("\n________________________Zad 20_10_______________________________________________________________________________________");
/*
Zadanie 20.10.
W klasie Fraction utwórz metody statyczne (o nazwie quot(), ang. quotient — iloraz) zwracające obiekt Fraction, będący ilorazem dwóch ułamków,
ułamka i liczby całkowitej lub dwóch liczb całkowitych. Napisz program demonstrujący działanie tych metod.               */

        System.out.println("Iloraz ułamków " + ulamek6a + " i " + ulamek6b + " (quot): " + Fraction.quot(ulamek6a, ulamek6b));
        System.out.println("Iloraz ułamka " + ulamek6a + " i liczby 5 (quot): " + Fraction.quot(ulamek6a, 5));
        System.out.println("Iloraz liczb 5 i 6 (quot): " + Fraction.quot(5, 6));



        System.out.println("\n________________________Zad 20_11_______________________________________________________________________________________");
/*
Zadanie 20.11.
W klasie Fraction utwórz metody (o nazwie add(), ang. addition — dodawanie) zwracające nowy obiekt Fraction, będący sumąułamka reprezentowanego
przez ten obiekt i inny obiekt lub liczbę całkowitą. Napisz program pokazujący działanie tych metod.  */

        System.out.println("NNW (najmniejsza wspólna wielokrotność) liczb 6 i 8 wynosi " + Fraction.nww(6, 8));
        System.out.println("Suma ułamków " + ulamek6a + " i " + ulamek6b + " (add): " + ulamek6a.add(ulamek6b));
        System.out.println("Suma ułamka " + ulamek6a + " i liczby " + 5 + " (add): " + ulamek6a.add(5));



        System.out.println("\n________________________Zad 20_12_______________________________________________________________________________________");
/*
Zadanie 20.12.
W klasie Fraction utwórz metody statyczne (o nazwie sum(), ang. sum — suma) zwracające obiekt Fraction, będący sumą dwóch ułamków 
lub ułamka i liczby całkowitej. Napisz program demonstrujący działanie tych metod.               */

        System.out.println("Suma ułamków " + ulamek6a + " i " + ulamek6b + " (add - m.statyczna):  " + Fraction.sum(ulamek6a, ulamek6b));
        System.out.println("Suma ułamka " + ulamek6a + " i liczby 5 (add - m.statyczna):  " + Fraction.sum(ulamek6a, 5));
        System.out.println("Suma liczby 5 i ułamka " + ulamek6a + " (add - m.statyczna):  " + Fraction.sum(5, ulamek6a));
        System.out.println("Suma liczby 5 i liczby 6  (add - m.statyczna):  " + Fraction.sum(5, 6));



        System.out.println("\n________________________Zad 20_13_______________________________________________________________________________________");
/*
Zadanie 20.13.
W klasie Fraction utwórz metodę zwracającą nowy obiekt Fraction, reprezentujący ułamek przeciwny do ułamka zawartego w obiekcie wywołującym tą metodę. 
Utwórz metodę statyczną o podobnej funkcjonalności. Napisz program pokazujący działanie tych metod. 
Wykorzystaj ułamek przeciwny do obliczenia różnicy dwóch ułamków.                                                */

        System.out.println("Ułamek przeciwny do ułamka " + ulamek6a + " wynosi (addInv):  " + ulamek6a.addInv());
        System.out.println("Ułamek przeciwny do ułamka " + ulamek6a + " wynosi (addInv - m.statyczna):  " + Fraction.addInv(ulamek6a));
        System.out.println("Różnica ułamka " + ulamek6b + " i " + ulamek6a + " :  " + Fraction.sum(ulamek6b, Fraction.addInv(ulamek6a)));

/*
Zadanie 20.14.
W klasie Fraction utwórz metody (o nazwie sub(), ang. subtraction — odejmowanie) zwracające nowy obiekt Fraction, będący różnicą ułamka reprezentowanego przez
ten obiekt i inny obiekt lub liczbą całkowitą. Napisz program pokazujący działanie tych metod.                  */

/*
Zadanie 20.15.
W klasie Fraction utwórz metody statyczne (o nazwie diff(), ang. difference — różnica) zwracające obiekt Fraction, będący różnicą dwóch ułamków 
lub ułamka i liczby całkowitej. Napisz program demonstrujący działanie tych metod.                                       */

/*
Zadanie 20.16.
Dodaj do klasy Fraction metody (getNum() i getDen()) pozwalające na odczytanie wartości prywatnych pól obiektu (licznika i mianownika ułamka reprezentowanego
przez obiekt). Napisz program demonstrujący działanie tych metod.                                                            */

/*
Zadanie 20.17.
Dopuszcza się zmiany wartości prywatnych pól obiektu (przy użyciu metod) w celu zmiany jego wartości bez tworzenia nowej instancji obiektu.
Zdefiniuj w klasie Fraction metody setNum(int), setDen(int) i setFrac(int, int), które będą zmieniaćwartość licznika, mianownika 
lub jednocześnie licznika i mianownika ułamka (obiektu). Napisz program demonstrujący działanie tych metod.                              */

/*
Zadanie 20.18.
Metoda Object.equals() jest dziedziczona przez wszystkie klasy i pozwala ustalić, czy dwa obiekty są identyczne. 
W klasie Fraction za równe uznamy te obiekty, które reprezentują ten sam ułamek. Utwórz i dołącz do klasy metodę equals(),
przesłaniającą metodę Object.equals(), oraz napisz program testujący poprawność działania zbudowanej metody.                         */

/*
Zadanie 20.19.
Dodaj do klasy Fraction metody zwracające wartość dziesiętną ułamka reprezentowanego przez obiekt. Napisz program demonstrujący działanie tych metod.        */

/*
Zadanie 20.20.
Ułamek może być przedstawiony jako łańcuch znaków w postaci "4/7" (ułamek zwykły), "5" (liczba całkowita odpowiadająca ułamkowi 5/1), "2.45" (ułamek dziesiętny
odpowiadający ułamkowi 245/100) lub "2,45" (według zasad zapisu obowiązujących w Polsce). Zbuduj konstruktor, który na podstawie łańcucha znaków utworzy 
odpowiedni obiekt klasy Fraction lub zgłosi wyjątek, gdy łańcuch znaków nie będzie przedstawiał ułamka. Napisz program demonstrujący działanie tego konstruktora. */

/*
Zadanie 20.21.
Zbuduj kilka metod statycznych o nazwie valueOf() z jednym parametrem (typu float, double, int, String) lub dwoma parametrami (typu int),
zwracających obiekt Fraction reprezentujący ułamek o wartości odpowiadającej podanemu parametrowi. Napisz program demonstrujący działanie tych metod.

W kolejnych zadaniach (20.22 – 20.27) wykorzystamy obiekty i metody klasy Fraction, uzyskując wyniki obliczeń w postaci dokładnej, wyrażonej ułamkami zwykłymi. */

/*
Zadanie 20.22.
Korzystając z obiektów i metod klasy Fraction, napisz program obliczający 15 początkowych wyrazów ciągu liczbowego (ksiązka str. 60). 
Ile maksymalnie wyrazów tego ciągu mógłbyw tym programie obliczyć?                                                                         */

/*
Zadanie 20.23.
Korzystając z obiektów i metod klasy Fraction, napisz program obliczający sumy częściowe nieskończonego szeregu 1 +1/2 + 1/4 + 1/16....                 */

/*
Zadanie 20.24.
Korzystając z obiektów i metod klasy Fraction, napisz program obliczający sumy częściowe nieskończonego szeregu 1 +1/2 + 1/4 + 1/16.... (to samo?)          */

/*
Zadanie 20.25.
Napisz program rozwiązujący równanie o postaci ax + b = 0 w zbiorze liczb wymiernych, posługując się obiektami i metodami klasy Fraction.                    */

/*
Zadanie 20.26.
Napisz program rozwiązujący metodą wyznaczników układ dwóch równań liniowych z dwiema niewiadomymi w zbiorze liczb wymiernych. Wykorzystaj obiekty i metody
klasy Fraction. Utwórz metodę det(), która będzie obliczać wyznacznik, oraz metodęinputFraction() do wprowadzania z klawiatury wartości obiektów klasy Fraction.   */

/*
Zadanie 20.27.
Pierwiastek drugiego stopnia z liczby nieujemnej a możemy obliczyć ze wzoru iteracyjnego x = (1/2)(x + a/x), biorąc wartości początkowex = 1.
Korzystając z obiektów i metod klasy Fraction, napisz program obliczający przybliżenie liczby pierwiastek(5) w postaci ułamka zwykłego.              */
    }
}
