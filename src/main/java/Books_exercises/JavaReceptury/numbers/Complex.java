package Books_exercises.JavaReceptury.numbers;

/** Klasa reprezentująca liczby zespolone. Po utworzeniu obiekt 
 * tej klasy nie może być modyfikowany. Metody add (dodawanie), 
 * subtract (odejmowanie) oraz multiply (mnożenie) zwracają nowy
 * obiekt Complex zawierający obliczone wyniki.
 *
 * @author Ian F. Darwin, inspired by David Flanagan.
 */
// BEGIN main
public class Complex {
    /** Część rzeczywista. */
    private double r;
    /** Część urojona. */
    private double i;

    /** Konstruktor. */
    Complex(double rr, double ii) {
        r = rr;
        i = ii;
    }

    /** Wyświetlamy bieżący obiekt w formie łańcucha znaków.
     * Metoda nadaje się do wykorzystania w wywołaniach 
     * metody println() oraz w innych operacjach na łańcuchach.
     */
    public String toString() {
        StringBuffer sb = new StringBuffer().append(r);
        if (i>0)
            sb.append('+');  // W przeciwnym razie append(i) dodaje znak -.
        return sb.append(i).append('i').toString();
    }

    /** Zwraca część rzeczywistą liczby. */
    public double getReal() {
        return r;
    }
    /** Zwraca część urojoną liczby. */
    public double getImaginary() {
        return i;
    }
    /** Zwraca wielkość liczby zespolonej. 
     */
    public double magnitude() {
        return Math.sqrt(r*r + i*i);
    }

    /** Dodawanie liczby zespolonej do tej liczby. 
     */
    public Complex add(Complex other) {
        return add(this, other);
    }

    /** Dodawanie dwóch liczb zespolonych. 
     */
    public static Complex add(Complex c1, Complex c2) {
        return new Complex(c1.r+c2.r, c1.i+c2.i);
    }

    /** Odejmowanie liczby zespolonej od tej liczby.
     */
    public Complex subtract(Complex other) {
        return subtract(this, other);
    }

    /** Odejmowanie dwóch liczb zespolonych. 
     */
    public static Complex subtract(Complex c1, Complex c2) {
        return new Complex(c1.r-c2.r, c1.i-c2.i);
    }

    /** Pomnożenie tej liczby zespolonej przez inną liczbę zespoloną. 
     */
    public Complex multiply(Complex other) {
        return multiply(this, other);
    }

    /** Pomnożenie dwóch liczb zespolonych. 
     */
    public static Complex multiply(Complex c1, Complex c2) {
        return new Complex(c1.r*c2.r - c1.i*c2.i, c1.r*c2.i + c1.i*c2.r);
    }

    /** Dzielenie c1 przez c2.
     * @author Gisbert Selke.
     */
    public static Complex divide(Complex c1, Complex c2) {
        return new Complex(
            (c1.r*c2.r+c1.i*c2.i)/(c2.r*c2.r+c2.i*c2.i),
            (c1.i*c2.r-c1.r*c2.i)/(c2.r*c2.r+c2.i*c2.i));
    }
    
    /* Porównanie dwóch liczb zespolonych
     */
    public boolean equals(Object o) {
        if (o.getClass() != Complex.class) {
            throw new IllegalArgumentException(
                    "Argument Complex.equals musi być klasy Complex");
        }
        Complex other = (Complex)o;
        return r == other.r && i == other.i;
    }
    
    /* Generowanie kodu mieszającego; nie mam pewności, jak dobry jest
     * kod generowany przez tę metodę.
     */
    public int hashCode() {
        return (int)(r) |  (int)i;
    }
}
// END main
