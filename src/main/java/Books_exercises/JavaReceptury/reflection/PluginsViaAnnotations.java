package Books_exercises.JavaReceptury.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// BEGIN findAnnotatedClasses
/** Klasa pozwala odnajdywać "wtyczki" lub inne klasy dodatkowe,
 * wykorzystując w tym celu introspekcję oraz adnotacje. 
 */
public class PluginsViaAnnotations {

    /**
     * Metoda odnajduje wszystkie klasy podanego pakietu, do których
     * dodano konkretną adnotację (określoną w formie klasy adnotacji).
     */
    public static List<Class<?>> findAnnotatedClasses(String packageName,
        Class<? extends Annotation> annotationClass) throws Exception {

        List<Class<?>> ret = new ArrayList<>();
        String[] classes = ClassesInPackage.getPackageContent(packageName);
        for (String clazz : classes) {
            Class<?> c = Class.forName(clazz);
            if (c.isAnnotationPresent(annotationClass))
                ret.add(c);
        }
        return ret;
    }
    // END findAnnotatedClasses
    
    // BEGIN findClassesWithAnnotatedMethods
    /**
     * Metoda odnajduje wszystkie klasy podanego pakietu zawierające 
     * jakieś metody, do których została dodana określona adnotacja.
     */
    public static List<Class<?>> findClassesWithAnnotatedMethods(String packageName, 
            Class<? extends Annotation> methodAnnotationClass) throws Exception {
        List<Class<?>> ret = new ArrayList<>();
        String[] classes = ClassesInPackage.getPackageContent(packageName);
        for (String clazz : classes) {
            Class<?> c = Class.forName(clazz);
            for (Method m : c.getMethods()) {
                if (m.isAnnotationPresent(methodAnnotationClass)) {
                    ret.add(c);
                }
            }
        }
        return ret;
    }
    // END findClassesWithAnnotatedMethods
}
