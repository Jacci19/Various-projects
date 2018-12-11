/* BEGIN main */
#include <jni.h>
#include "HelloJni.h"
#include <stdio.h>
/*
 * Rodzima implementacja metody displayHelloJni.
 */
JNIEXPORT void JNICALL Java_HelloJni_displayHelloJni(JNIEnv *env, jobject this) {
  jfieldID fldid;
  jint n, nn;

  (void)printf("Witamy z metody rodzimej\n");

  if (this == NULL) {
    fprintf(stderr, "Wskaźnik strumienia wejściowego = null!\n");
    return;
  }
  if ((fldid = (*env)->GetFieldID(env,
        (*env)->GetObjectClass(env, this), "myNumber", "I")) == NULL) {
    fprintf(stderr, "Nieudane wywołanie GetFieldID");
    return;
  }

  n = (*env)->GetIntField(env, this, fldid);/* Pobieramy myNumber. */
  printf("\"myNumber\" ma wartość %d\n", n);

  (*env)->SetIntField(env, this, fldid, ++n);/* Inkrementujemy wartość! */
  nn = (*env)->GetIntField(env, this, fldid);

  printf("Wartość pola \"myNumber\" wynosi teraz %d\n", nn);  /* upewniamy się */

  return;
}
/* END main */
