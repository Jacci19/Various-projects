package Books_exercises.JavaReceptury.jaxwsservice;

// BEGIN main
import javax.jws.WebService;

@WebService(targetNamespace="http://toy.service/")
public class Calc {
    
    public int add(int a, int b) {
        System.out.println("CalcImpl.add()");
        return a + b;
    }
    // Pozostałe trzy metody są bardzo podobne.
    // END main
    
    public int subtract(int a, int b) {
        System.out.println("CalcImpl.subtract()");
        return a - b;
    }

    public int multiply(int a, int b) {
        System.out.println("CalcImpl.multiply()");
        return a * b;
    }
    
    public int divide(int a, int b) {
        System.out.println("CalcImpl.divide()");        
        if (b == 0) {
            // Nieco lepszy komunikat.
            throw new ArithmeticException(
                "Chciałeś podzielić " + a + " przez 0");
        }
        return a / b;
    }
}
