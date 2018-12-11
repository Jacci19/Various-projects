package Books_exercises.JavaReceptury.threads;

/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *        This product includes software developed by Ian F. Darwin.
 * 4. Neither the name of the author nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java 
 * language and environment is gratefully acknowledged.
 * 
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */

// import - brak

/** Prezentacja zastosowania obiektów Thread do automatycznego,
 * cyklicznego zapisywania pracy użytkownika.
 */
// BEGIN main
public class AutoSave extends Thread {
    /** Interfejs FileSave jest implementowany przez klasę główną. */
    protected FileSaver model;
    /** Okres oczekiwania pomiędzy próbami. */
    public static final int MINUTES = 5;
    private static final int SECONDS = MINUTES * 60;

    public AutoSave(FileSaver m) {
        super("AutoSave Thread");
        setDaemon(true);    // A zatem główna aplikacja nie musi działać.
        model = m;
    }

    public void run() {
        while (true) {      // Cała metoda działa bez końca.
            try {
                sleep(SECONDS*1000);
            } catch (InterruptedException e) {
                // Nic nie robimy.
            }
            if (model.wantAutoSave() && model.hasUnsavedChanges())
                model.saveFile(null);
        }
    }

    // Czego nie pokazano:
    // 1) Metoda saveFile() nie może być synchronizowana.
    // 2) Metoda zamykająca program główny musi być synchronizowana
    //    na *tym samym* obiekcie. 
}

/** Lokalna kopia interfejsu FileSave, niezbędna do kompilacji 
 * programu AutoSave. */
interface FileSaver {
    /** Wczytujemy model z pliku o nazwie fn; jeśli jest równy null, 
     * to prosimy o podanie nowej nazwy. */
    public void loadFile(String fn);

    /** Pytamy model, czy mają być wykonywane automatyczne zapisy. */
    public boolean wantAutoSave();

    /** Pytamy model, czy są jakieś niezapisane zmiany, jeśli nie ma,
     * to operacja zapisu nie zostanie wykonana. */
    public boolean hasUnsavedChanges();

    /** Zapisujemy bieżącą zawartość modelu w pliku o nazwie fn.
     * Jeśli fn jest równe null, używamy bieżącej nazwy pliku lub 
     * prosimy o podanie nowej.
     */
    public void saveFile(String fn);
}
// END main
