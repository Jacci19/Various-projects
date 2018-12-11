/* BEGIN main */
/*
 * To program napisany w języku C, który wywołuje program
 * napisany w Javie. Może on zostać zastosowany jako model
 * rozwiązania, w którym używamy Javy jako języka pozwalającego na
 * rozszerzenie już istniejącego oprogramowania.
 */

#include <stdio.h>
#include <jni.h>

int
main(int argc, char *argv[]) {
    int i;
    JavaVM *jvm;        /* Używana wirtualna maszyna Javy. */
    JNIEnv *myEnv;        /* Wskaźnik do środowiska. */
    JDK1_1InitArgs jvmArgs; /* Argumenty inicjalizacyjne JNI.  */
    jclass myClass, stringClass;    /* Wskaźnik do typu klasy. */
    jmethodID myMethod;    /* Wskaźnik do metody main(). */
    jarray args;        /* To będzie tablica obiektów String. */
    jthrowable tossed;    /* Obiekt wyjątku, jeśli zostanie zgłoszony. */
    
    JNI_GetDefaultJavaVMInitArgs(&jvmArgs);    /* Przygotowujemy wskaźnik
                                                  argumentu */
    /* Teraz można by zmieniać wartości, na przykład:
     * jvmArgs.classpath = ...; */
    
    /* Inicjalizujemy JVM! */
    if (JNI_CreateJavaVM(&jvm, &myEnv, &jvmArgs) < 0) {
        fprintf(stderr, "Wywołanie CreateJVM zakończone niepowodzeniem.\n");
        exit(1);
    }
    
    /* Odnajdujemy klasę o nazwie podanej w argv[1] */
    if ((myClass = (*myEnv)->FindClass(myEnv, argv[1])) == NULL) {
        fprintf(stderr, "Wywołanie FindClass %s zakończone niepowodzeniem.\n", argv[1]);
        exit(1);
    }

    /* Znajdujemy statyczną metodę void main(String[]) podanej klasy. */
    myMethod = (*myEnv)->GetStaticMethodID(
        myEnv, myClass, "main", "([Ljava/lang/String;)V");
    /* myMethod = (*myEnv)->GetMethodID(myEnv, myClass, "test", "(I)I"); */
    if (myMethod == NULL) {
        fprintf(stderr, "Wywołanie GetStaticMethodID zakończone niepowodzeniem.\n");
        exit(1);
    }

    /* Ponieważ wywołujemy metodę main(), musimy do niej przekazać
     * argumenty wiersza poleceń w formie tablicy łańcuchów znaków
     * (obiektów String).
     */
    if ((stringClass = (*myEnv)->FindClass(myEnv, "java/lang/String")) == NULL){
        fprintf(stderr, "Nie udało się pobrać klasy String!!\n");
        exit(1);
    }

    /* Tworzymy tablicę łańcuchów znaków, usuwając jeden łańcuch
     * reprezentujący nazwę programu oraz drugi reprezentujący nazwę klasy.
     */
    if ((args = (*myEnv)->NewObjectArray(myEnv, argc-2, stringClass, NULL))==NULL) {
        fprintf(stderr, "Nie udało się utworzyć tablicy!\n");
        exit(1);
    }

    /* Wypełniamy tablicę. */
    for (i=2; i<argc; i++)
        (*myEnv)->SetObjectArrayElement(myEnv,
            args, i-2, (*myEnv)->NewStringUTF(myEnv, argv[i]));

    /* I w końcu wywołujemy metodę. */
    (*myEnv)->CallStaticVoidMethodA(myEnv, myClass, myMethod, &args);

    /* I sprawdzamy wyjątki. */
    if ((tossed = (*myEnv)->ExceptionOccurred(myEnv)) != NULL) {
        fprintf(stderr, "%s: Wykryto wyjątek:\n", argv[0]);
        (*myEnv)->ExceptionDescribe(myEnv); /* Wyświetlamy w standardowym
                                             * strumieniu błędów. */
        (*myEnv)->ExceptionClear(myEnv);    /* W porządku - gotowe. */
    }
    
    (*jvm)->DestroyJavaVM(jvm);    /* Nie sprawdzamy błędów, bo wszystko
                                    * już zostało zrobione. */
    return 0;
}
/* END main */
