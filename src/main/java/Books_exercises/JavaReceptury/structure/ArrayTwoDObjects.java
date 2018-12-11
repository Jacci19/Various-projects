package Books_exercises.JavaReceptury.structure;

/** Dwuwymiarowe tablice obiektów. */
// BEGIN main
public class ArrayTwoDObjects {

    /** Zwraca tablicę z łańcuchami zawierającymi opisy 
     * indeksów (niezbyt realistyczne, ale to program 
     * demonstracyjny).
     */
    public static String[][] getArrayInfo() {
        String info[][];
        info = new String[10][10];
        for (int i=0; i < info.length; i++) {
            for (int j = 0; j < info[i].length; j++) {
                info[i][j] = "String[" + i + "," + j + "]";
            }
        }
        return info;
    }

    /** Zwraca listę dozwolonych parametrów (metoda klasy Applet). */
    public static String[][] getParameterInfo() {
        String param_info[][] = {
            {"fontsize",    "9-18",    "Wielkość czcionki"},
            {"URL",    "-",    "Gdzie pobrać"},
        };
        return param_info;
    }

    /** Uruchamiamy obie metody inicjalizujące i wyświetlamy
     * część wyników. 
     */
    public static void main(String[] args) {
        print("zwracana przez metodę getArrayInfo()", getArrayInfo());
        print("zwracana przez metodę getParameterInfo()", getParameterInfo());
    }

    /** Wyświetlamy wybrane elementy tablicy dwuwymiarowej. */
    public static void print(String tag, String[][] array) {
        System.out.println("Tablica " + tag + " ma wymiary " + 
            array.length + " x " + array[0].length);
        System.out.println("Tablica[0][0] = " + array[0][0]);
        System.out.println("Tablica[0][1] = " + array[0][1]);
        System.out.println("Tablica[1][0] = " + array[1][0]);
        System.out.println("Tablica[0][0] = " + array[0][0]);
        System.out.println("Tablica[1][1] = " + array[1][1]);
    }
}
// END main
