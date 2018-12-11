package Books_exercises.JavaReceptury.reflection;

import java.lang.reflect.Field;

// BEGIN main
class X {
    @SuppressWarnings("unused") // Potajemnie używane poniżej.
    private int p = 42;
    int q = 3;
}

/**
 * Program pokazuje, że w rzeczywistości za pomocą introspekcji 
 * w bardzo prosty sposób można uzyskać dostęp do prywatnych składowych 
 * klas, używając przy tym domyślnego menedżera bezpieczeństwa 
 * (SecurityManager, co oznacza, że to rozwiązanie zapewne nie będzie
 * działać w apletach).
 */
public class DefeatPrivacy {

    public static void main(String[] args) throws Exception {
        new DefeatPrivacy().process();
    }
    
    private void process() throws Exception {
        X x = new X();
        System.out.println(x);
        // System.out.println(x.p); // Tego nie uda się skompilować.
        System.out.println(x.q);
        Class<? extends X> class1 = x.getClass();
        Field[] flds = class1.getDeclaredFields();
        for (Field f : flds) {
            f.setAccessible(true);    // Żegnaj, modyfikatorze "private".
            System.out.println(f + "==" + f.get(x));
            f.setAccessible(false);    // Wracamy do "właściwego" stanu.
        }
    }
}
// END main
