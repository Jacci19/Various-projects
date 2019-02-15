package Z_inne.Polimormizm_przykl;

public class Kolo implements Figura{

    private int promien;

    //jakiś konstruktor
    Kolo(int promien){
        this.promien=promien;
    }

    @Override public void obliczPole() {
        System.out.println("Pole kola wynosi " + Math.PI * promien * promien);
        //return Math.PI * promien * promien;
    }
}
