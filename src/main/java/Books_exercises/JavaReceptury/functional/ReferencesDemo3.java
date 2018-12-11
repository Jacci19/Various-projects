package Books_exercises.JavaReceptury.functional;

// BEGIN main
public class ReferencesDemo3 {

    interface FunInterface {
        void process(int i, String j, char c, double d);
    }
    
    public static void work(int i, String j, char c, double d){
        System.out.println("Muuu");
    }
    
    public static void main(String[] args) {
        FunInterface sample = ReferencesDemo3::work;
        System.out.println("Główna metoda obliczeniowa: " + sample);
    }
}
// END main
