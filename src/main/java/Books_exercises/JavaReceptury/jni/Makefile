# Plik budowy dla Java Native Methods 1.1, przykłady przygotowane dla 
# Learning Tree International Course 471/478.
# Plik został przetestowany w systemie Solaris dla kompilatorów "gcc" 
# oraz SunSoft "cc".
# Przetestowano go także w systemie OpenBSD z rodzimą wersją Javy 
# "devel/jdk/1.2" oraz cc.
# W przypadku wykorzystania na innych platformach systemowych bez wątpienia
# będzie wymagać pewnych przeróbek; proszę poinformować mnie jak dużych! :-)
# Ian Darwin, http://www.darwinsys.com

# Sekcja konfiguracyjna.

CFLAGS_FOR_SO = -G # Solaris
CFLAGS_FOR_SO = -shared
CSRCS         = HelloJni.c
# Wartość JAVA_HOME powinna być określona w środowisku.
#INCLUDES     = -I$(JAVA_HOME)/include -I$(JAVAHOME)/include/solaris
#INCLUDES     = -I$(JAVA_HOME)/include -I$(JAVAHOME)/include/openbsd
INCLUDES      = -I$(JAVA_HOME)/include

all:         testhello testjavafromc

# Ta część dotyczy kodu C wywoływanego z poziomu Javy w HelloJni
testhello:       hello.all
        @echo
        @echo "Testujemy kod w Javie \"HelloJni\" wywołujący kod napisany w C."
        @echo
        LD_LIBRARY_PATH=`pwd`:. java HelloJni        

hello.all:        HelloJni.class libhello.so

HelloJni.class: HelloJni.java
        javac HelloJni.java

HelloJni.h:    HelloJni.class
        javah -jni HelloJni

HelloJni.o::    HelloJni.h

libhello.so:    $(CSRCS) HelloJni.h
    $(CC) $(INCLUDES) -G $(CSRCS) -o libhello.so

# Ta część jest dla kodu Javy wywoływanego z kodu C w javafromc
testjavafromc:    javafromc.all hello.all
    @echo
    @echo "Teraz testujemy HelloJni, używając javafromc zamiast java"
    @echo
    LD_LIBRARY_PATH="$(LIBDIR):." CLASSPATH="$(CLASSPATH)" ./javafromc HelloJni
    @echo
    @echo "To było, gdybyś nie zauważył, C->Java->C. Rozwiązanie to"
    @echo "przez przypadek zastąpiło program  \"java\" dostarczany w JDK!"
    @echo


javafromc.all:    javafromc

javafromc:    javafromc.o
    $(CC) -L$(LIBDIR) javafromc.o -ljava -o $@

javafromc.o:    javafromc.c
    $(CC) -c $(INCLUDES) javafromc.c

clean:
    rm -f core *.class *.o *.so HelloJni.h
clobber: clean
    rm -f javafromc
