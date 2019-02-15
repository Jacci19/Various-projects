package Z_inne.Polimormizm_przykl;

public class Prostokat implements Figura{
    private int a,b;

    //jakiś konstruktor
    Prostokat(int a, int b){
        this.a=a;
        this.b=b;
    }

    @Override
    public void obliczPole() {
        System.out.println("Pole prostokata wynosi " + a * b);
        //return a*b;
    }
}
