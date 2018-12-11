package Books_exercises.JavaReceptury.numbers;

/**
 * Mnożenie macierzy.
 * Operuje tylko na macierzach typu int: TODO: przepisać z wykorzystaniem typów
 * ogólnych wprowadzonych w Java 5, zapewniając w ten sposób obsługę 
 * typów long, float, and double.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class MatrixUse {
    public static void main(String[] argv) {
        // BEGIN main
        int x[][] = {
            { 3, 2, 3 },
            { 5, 9, 8 },
        };
        int y[][] = {
            { 4, 7 },
            { 9, 3 },
            { 8, 1 },
        };
        int z[][] = Matrix.multiply(x, y);
        Matrix.mprint(x);
        Matrix.mprint(y);
        Matrix.mprint(z);
        // END main
    }
}
