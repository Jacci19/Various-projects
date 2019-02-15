package Z_inne.Polimormizm_przykl;

public class Main {
    public static void main(String[] args) {
        Figura obiekt = new Kolo(10);
        Figura obiekt1 = new Prostokat(10,2);
        obiekt.obliczPole();
        obiekt1.obliczPole();

        /* i w tym momencie program wie, że ma obliczyć pole ze wzoru na koło, bo obiekt jest kołem,
        a z drugiej strony program wie, że obiekt jest też figurą, możemy zrobić sobie jakąś listę elementów typu figura,
        i przelecieć ją pętlą nie wnikając czy w danej chwili figura jest kołęm czy kwadratem,
        a jak nam się zamarzy dodać trapez to jest to tylko kwestia dodania nowej klasy i zaimplementowania metody obliczPole w niej. */
    }
}
