package Books_exercises.Rychlicki.Rychlicki_3_Fraction;

public class Fraction {
    private int licznik;
    private int mianownik;

    public Fraction(int l, int m) {
        licznik = l;
        if (m != 0) {
            mianownik = m;
        } else {
            throw new IllegalArgumentException("Mianownik musi być różny od zera!");
        }
        correction();                                   //wywołuje metodę correction (zad. 20.4)
    }

    public Fraction() {                                  //Zad. 20.2a
        licznik = 0;
        mianownik = 1;
    }

    public Fraction(int m) {                             //Zad. 20.2b
        licznik = m;
        mianownik = 1;
    }

    public Fraction(Fraction x) {                        //Zad. 20.3
        licznik = x.licznik;
        mianownik = x.mianownik;
    }

    double wartosc() {                                   //moje
        return (double) licznik / mianownik;
    }

    public String toString() {                           // nadpisuje metodę domyślną    @Override
        return licznik + "/" + mianownik;
    }

    private void correction() {                          //zad. 20.4
        if (mianownik < 0) {
            licznik *= -1;
            mianownik *= -1;
        }
    }

    public int nwd(int a, int b) {                       //zad. 20.5   największy wspólny dzielnik
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public Fraction reduce() {                               //zad. 20.5  zwraca dany ułamek skrócony o największy wspólny dzielnik
        int nWd = nwd(licznik, mianownik);
        if (nWd != 1) {
            licznik /= nWd;
            mianownik /= nWd;
        }
        return this;
    }

    public Fraction reduce(int n) {                          //zad. 20.5  zwraca dany ułamek skrócony o podaną liczbę
        if (n <= 0) throw new IllegalArgumentException("Argument jest < = 0 !!!");
        if (licznik % n == 0 && mianownik % n == 0) {
            licznik /= n;
            mianownik /= n;
        }
        return this;
    }

    public Fraction equivalent(int n) {                      //zad. 20.5  zwraca dany ułamek rozszerzony o podaną liczbę
        if (n == 0) throw new IllegalArgumentException(" Liczba nie może być równa zero !!!");
        licznik *= n;
        mianownik *= n;
        correction();
        return this;
    }

    public Fraction mult(Fraction x) {                      //zad. 20.6 zwraca dany ułamek pomnożony przez inny ułamek
        return new Fraction(this.licznik * x.licznik, this.mianownik * x.mianownik).reduce();
    }

    public Fraction mult(int m) {                           //zad. 20.6 zwraca dany ułamek pomnożony przez liczbę całkowitą
        return new Fraction(this.licznik * m, this.mianownik).reduce();
    }

    public static Fraction product(Fraction x, Fraction y) {        //zad. 20.7 zwraca ułamek będący iloczynem dwóch zadanych ułamków
        return x.mult(y);
    }

    public static Fraction product(Fraction x, int a) {             //zad. 20.7 zwraca ułamek będący iloczynem zadanego ułamka i liczby całkowitej
        return x.mult(a);
    }

    public static Fraction product(int a, Fraction x) {            //zad. 20.7 zwraca ułamek będący iloczynem liczby całkowitej i zadanego ułamka (aby zapewnić przemienność mnożenia)
        return x.mult(a);
    }


    public static Fraction product(int a, int b) {                  //zad. 20.7 zwraca ułamek będący iloczynem dwóch zadanych liczb całkowitych
        return new Fraction(a * b, 1);
        //return new Fraction(a).mult(b);                   // inny sposób
    }

    public Fraction multInv() {
        return new Fraction(this.mianownik, this.licznik);         //zad. 20.8 zwraca ułamek będący odwrotnością zadanego ułamka (multiplicative inverse). Metoda NIEstatyczna
    }

    public static Fraction multInv(Fraction x) {                    //zad. 20.8 zwraca ułamek będący odwrotnością zadanego ułamka (multiplicative inverse). Metoda statyczna
        return new Fraction(x.mianownik, x.licznik);
    }

    public Fraction div(Fraction x) {                               //zad. 20.9 zwraca ułamek będący ilorazem tego i zadanego ułamka
        if (x.mianownik == 0) {
            throw new IllegalArgumentException("Nie można dzielić przez zero !!!");
        } else {
            return new Fraction(this.licznik * x.mianownik, this.mianownik * x.licznik).reduce();
        }
    }

    public Fraction div(int a) {                                     //zad. 20.9 zwraca ułamek będący ilorazem tego ułamka i liczby całkowitej
        if (a == 0) {
            throw new ArithmeticException("Nie można dzielić przez zero !!!");
        } else {
            return new Fraction(this.licznik, this.mianownik * a).reduce();
        }
    }

    public static Fraction quot(Fraction x, Fraction y) {                //zad. 20.10 zwraca ułamek będący ilorazem dwóch zadanych ułamków
        return new Fraction(x.licznik * y.mianownik, x.mianownik * y.licznik);
    }

    public static Fraction quot(Fraction x, int a) {                     //zad. 20.10 zwraca ułamek będący ilorazem zadanego ułamka i liczby całkowitej
        return new Fraction(x.licznik, x.mianownik * a);
    }

    public static Fraction quot(int a, int b) {                     //zad. 20.10 zwraca ułamek będący ilorazem dwóch liczb całkowitych
        return new Fraction(a, b);
    }

    static int nww(int a, int b) {                                  //zad. 20.11 najmniejsza wspólna wielokrotność
        int wm = a;
        int wn = b;
        while (wm != wn)
            if (wm > wn)
                wn += b;
            else
                wm += a;
        return wm;
    }


    public Fraction add(Fraction x) {                                //zad. 20.11 zwraca ułamek będący sumą tego ułamka i innego ułamka
        if (this.mianownik == x.mianownik)
            return new Fraction(this.licznik + x.licznik, this.mianownik).reduce();
        else {
            int wm = nww(this.mianownik, x.mianownik);
            return new Fraction(wm / this.mianownik * this.licznik + wm / x.mianownik * x.licznik, wm).reduce();      //wyprowadzenie wzoru: książka str. 250
        }
    }

    public Fraction add(int a) {                                     //zad. 20.11 zwraca ułamek będący sumą tego ułamka i liczby całkowitej
        return new Fraction(this.licznik + a*this.mianownik, this.mianownik).reduce();
    }

    public static Fraction sum(Fraction x, Fraction y){                                 //zad. 20.12 zwraca ułamek będący sumą dwóch zadanych ułamków
        if (x.mianownik == y.mianownik)
            return new Fraction(x.licznik + y.licznik, x.mianownik).reduce();
        else {
            int wm = nww(x.mianownik, y.mianownik);
            return new Fraction(wm / x.mianownik * x.licznik + wm / y.mianownik * y.licznik, wm).reduce();      //wyprowadzenie wzoru: książka str. 250
        }
    }

    public static Fraction sum(Fraction x, int a) {                                     //zad. 20.12 zwraca ułamek będący sumą ułamka i liczby całkowitej
        return new Fraction(x.licznik + a*x.mianownik, x.mianownik).reduce();
    }

    public static Fraction sum(int a, Fraction x) {                                     //zad. 20.12 zwraca ułamek będący sumą liczby całkowitej i ułamka
        return new Fraction(x.licznik + a*x.mianownik, x.mianownik).reduce();
    }

    public static Fraction sum(int a, int b) {                                          //zad. 20.12 zwraca ułamek będący sumą dwóch liczb całkowitych
        return new Fraction(a+b);
    }
    public Fraction addInv() {                                                          //zad. 20.13 zwraca ułamek przeciwny do zadanego
        return new Fraction(-this.licznik, this.mianownik);
    }

    public static Fraction addInv(Fraction x) {                                         //zad. 20.13 zwraca ułamek przeciwny do zadanego
        return new Fraction(-x.licznik, x.mianownik);
    }
}



/*
        Zadanie 20.1.
            Zbuduj klasę Fraction, która będzie zawierać dwa prywatne pola reprezentujące licznik i mianownik ułamka.
        W klasie tej umieść konstruktor z dwoma parametrami, który będzie budowałułamek na podstawie dwóch liczb całkowitych (licznika i mianownika),
        oraz publiczną metodę toString(), zwracającą ułamek w postaci łańcucha znaków, np. "4/13" (licznik, kreska ułamkowa / i mianownik).
        Napisz program pokazujący działanie konstruktora i zdefiniowanej metody.                                                                        */

/*Ponieważ w tym przykładzie nazwy identyfikatorów (parametrów i pól klasy) nie powtarzają się, wykorzystanie słowa this nie było konieczne
 — wystarczyło podstawienie w postaci:  licznik = m; mianownik = n;    */

/*
    Zadanie 20.2.
        Dodaj do klasy Fraction konstruktor bezparametrowy budujący ułamek odpowiadający liczbie 0 oraz konstruktor z jednym parametrem całkowitym m
    budujący ułamek m/1. Napisz program pokazujący działanie tych konstruktorów.                */


/*
    Zadanie 20.3.
        Utwórz w klasie Fraction konstruktor kopiujący i napisz program pokazujący działanie tego konstruktora.             */

/*
Zadanie 20.4.
Zauważmy, że obiekty new Fraction(-3, 4) i new Fraction(3, -4) reprezentująten sam ułamek -3/4. Po zamianie obiektów na łańcuchy znaków (metodą toString())
otrzymamy odpowiednio "-3/4" i "3/-4". Podobnie wyglądałaby sytuacja dla obiektów new Fraction(2, 5) i new Fraction(-2, -5) reprezentujących ułamek 2/5.
Zapis ułamka w postaci "3/-4" lub "-2/-5" nie wygląda korzystnie (lepszy będzie zapis "-3/4" lub "2/5"). Można przyjąć, że będziemy zapamiętywali
zawsze dodatni mianownik, a znak licznika zadecyduje o znaku ułamka. Dodaj do klasy Fraction prywatną metodę, która wywołana wewnątrz konstruktora
skoryguje licznik i mianownik ułamka zgodnie z przyjętąumową. Napisz program pokazujący skutki działania tej metody.                                */

/*
Zadanie 20.6.
W klasie Fraction utwórz metody zwracające nowy obiekt Fraction, będący iloczynem ułamka reprezentowanego przez ten obiekt i inny obiekt
lub liczbęcałkowitą. Napisz program pokazujący działanie tych metod.                                                                            */

/*
Zadanie 20.7.
W klasie Fraction utwórz metody statyczne (o nazwie product(), ang. product — iloczyn) zwracające obiekt Fraction, będący iloczynem dwóch ułamków,
ułamka i liczby całkowitej lub dwóch liczb całkowitych. Napisz program demonstrujłcy działanie tych metod. */


/*
Zadanie 20.8.
W klasie Fraction utwórz metodę zwracającą nowy obiekt Fraction, reprezentujący ułamek odwrotny do ułamka zawartego w obiekcie wywołującym tą metodę.
Utwórz metodę statycznąo podobnej funkcjonalności. Napisz program pokazujący działanie tych metod. Wykorzystaj odwrotność do obliczenia ilorazu dwóch ułamków. */


/*
Zadanie 20.9.
W klasie Fraction utwórz metody zwracające nowy obiekt Fraction, będący ilorazem ułamka reprezentowanego przez ten obiekt i inny obiekt lub liczbęcałkowitą.   */


/*
Zadanie 20.10.
W klasie Fraction utwórz metody statyczne (o nazwie quot(), ang. quotient — iloraz) zwracające obiekt Fraction, będący ilorazem dwóch ułamków,
ułamka i liczby całkowitej lub dwóch liczb całkowitych. Napisz program demonstrujący działanie tych metod.               */

/*
Zadanie 20.11.
W klasie Fraction utwórz metody (o nazwie add(), ang. addition — dodawanie) zwracające nowy obiekt Fraction, będący sumąułamka reprezentowanego
przez ten obiekt i inny obiekt lub liczbę całkowitą. Napisz program pokazujący działanie tych metod.  */


/*
Zadanie 20.12.
W klasie Fraction utwórz metody statyczne (o nazwie sum(), ang. sum — suma) zwracające obiekt Fraction, będący sumą dwóch ułamków
lub ułamka i liczby całkowitej. Napisz program demonstrujący działanie tych metod.               */

/*
Zadanie 20.13.
W klasie Fraction utwórz metodę zwracającą nowy obiekt Fraction, reprezentujący ułamek przeciwny do ułamka zawartego w obiekcie wywołującym tą metodę.
Utwórz metodę statyczną o podobnej funkcjonalności. Napisz program pokazujący działanie tych metod.
Wykorzystaj ułamek przeciwny do obliczenia różnicy dwóch ułamków.                                                */

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

