package Books_exercises.JavaReceptury.lang;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Resource;

// BEGIN main
/**
 * Prosta adnotacja, którą można umieszczać przed typami (klasami
 * lub interfejsami); adnotacja ta będzie dostępna w trakcie 
 * działania aplikacji.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationDemo {
    public boolean fancy() default false;
    public int order() default 42;
}

/** Prosty przykład zastosowania adnotacji. */
@AnnotationDemo(fancy=true)
@Resource(name="Dumbledore")
class FancyClassJustToShowAnnotation {

    /** Wyświetlamy adnotacje dodane do tej klasy. */
    public static void main(String[] args) {
        Class<?> c = FancyClassJustToShowAnnotation.class;
        System.out.println("Klasa " + c.getName() + 
                " została poprzedzona następującymi adnotacjami:");
        for (Annotation a : c.getAnnotations()) {
            if (a instanceof AnnotationDemo) {
                AnnotationDemo ad = (AnnotationDemo)a;
                System.out.println("\t" +a + 
                    " z atrybutem fancy=" + ad.fancy() + 
                    " oraz atrybutem order " + ad.order());
            } else {
                System.out.println("\tInne adnotacje: " + a);
            }
        }
    }
}
// END main
