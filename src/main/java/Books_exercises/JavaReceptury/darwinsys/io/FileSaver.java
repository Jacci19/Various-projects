/*
 * Copyright Notice:
 * This code is copyright by Ian Darwin but is BSD licensed and
 * can thus be used for anything by anybody.
 * If you get rich off it, send me all your money. :-)
 */

package Books_exercises.JavaReceptury.darwinsys.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Klasa służy do zapisywania pliku danych użytkownika w możliwie 
 * najbezpieczniejszy sposób.
 * Oto podstawowy algorytm jej działania: 
 <ol>
 <li>Tworzymy plik tymczasowy umieszczony w tym samym katalogu,
 co plik wejściowy; dzięki czemu możemy zmienić jego nazwę.
 Po utworzeniu pliku wywołujemy metodę deleteOnExit();
 <li>Nasz klient zapisuje dane w utworzonym wcześniej pliku 
 tymczasowym. Podczas tego procesu będą zgłaszane wszystkie wyjątki
 związane z błędami formatu lub konwersji, dzięki zemu początkowy
 plik użytkownika nie będzie w żaden sposób modyfikowany. 
 Następnie klient zamyka plik.
 <li>Usuwamy wcześniejszy plik kopii bezpieczeństwa, jeśli taki istnieje;
 <li>Zmieniamy nazwę dotychczasowego pliku użytkownika na nazwaPliku.bak;
 <li>Zmieniamy nazwę pliku tymczasowego na właściwą nazwę pliku użytkownika.
 </ol>
 * Ten algorytm zabezpiecza jedynie przed takimi problemami jak: pełny 
 * dysk, brak uprawnień do zapisu, itd. Można zastosować także inne 
 * algorytmy, które pozwolą na zachwanie początkowego właściciela pliku 
 * oraz praw dostępu do niego (w systemach plików POSIX), one jednak
 * nie gwarantują prawidłowego działania w przypadku braku miejsca
 * na dysku.
 * <p>
 * Krok 1. jest zaimplementowany w konstruktorze.
 * Krok 2. realizuje użytkownik klasy, wywołując jedną z metod getWriter lub 
 *  getOutputStream (lecz nie obie).
 * Kroki 3., 4. i 5. są realizowane przez metodę finish().
 * <p>
 * Oto standardowy sposób użycia tej klasy:
 * <pre>
 * try {
 *     FileSaver saver = new FileSaver(file);
 *     final Writer writer = saver.getWriter();
 *     PrintWriter out = new PrintWriter(writer);
 *     myWriteOutputFile(out);
 *     out.close();
 *     saver.finish();
 *     System.out.println("Zapis OK");
 * } catch (IOException e) {
 *     System.out.println("Zapis NIE POWIÓDŁ SIĘ!");
 * }
 * </pre>
 * <p>
 * Obiektów tej klasy można używać wielokrotnie (dla tego samego pliku),
 * nie są jednak bezpieczne pod względem wielowątkowym i nie powinny
 * być jednocześnie używane przez więcej niż jeden wątek.
 * @author Kod wyodrębniony i zmodyfikowany przez Iana Darwina na potrzeby
 * aplikacji, którą kiedś pisałem, w odpowiedzi na dyskusję wywołaną przez
 * Brendona McLeana na prywatnej liście korespondencyjnej.
 */
// BEGIN main
// package com.darwinsys.io;
public class FileSaver {

    private enum State {
        /** Stan przed użyciem i po nim. */
        AVAILABLE,
        /** Stan w trakcie używania. */
        INUSE
    }
    private State state;
    private final File inputFile;
    private final File tmpFile;
    private final File backupFile;

    public FileSaver(File input) throws IOException {

        // Krok 1. Tworzymy plik tymczasowy w odpowiednim miejscu.
        this.inputFile = input;
        tmpFile = new File(inputFile.getAbsolutePath() + ".tmp");
        tmpFile.createNewFile();
        tmpFile.deleteOnExit();
        backupFile = new File(inputFile.getAbsolutePath() + ".bak");
        state = State.AVAILABLE;
    }

    /**
     * Metoda zwraca odwołanie do używanego obiektu File, zachęcając
     * tym samym do jego wielokrotnego użycia (obiekty File 
     * są niezmienne, więc takie rozwiązanie jest umiarkowanie
     * bezpieczne). Tak może wyglądać typowy przykład użycia tej
     * metody:
     * <pre>
     * if (fileSaver == null ||
     *   !(fileSaver.getFile().equals(file))) {
     *        fileSaver = new FileSaver(file);
     * }
     * </pre>
     */
    public File getFile() {
        return inputFile;
    }

    /** Metoda zwraca plik wyjściowy, w którym klient powinien
     * zapisywać dane użytkownika.
     * @return Obiekt OutputStream, który w celu zapewnienia rozsądnej
     *   wydajności działania powinien zostać użyty do utworzenia 
     *   obiektu OutputStream.
     * @throws IOException jeśli nie uda się utworzyć pliku tymczasowego. 
     */
    public OutputStream getOutputStream() throws IOException {

        if (state != State.AVAILABLE) {
            throw new IllegalStateException("Obiekt FileSaver nie został utworzony!");
        }
        OutputStream out = new FileOutputStream(tmpFile);
        state = State.INUSE;
        return out;
    }

    /** Metoda zwraca plik wyjściowy, w którym klient powinien 
     * zapisywać dane użytkownika.
     * @return Obiekt Writer, który, w celu zapewnienia rozsądnej
     *   wydajności działania, powinien zostać użyty do utworzenia 
     *   buforowanego obiektu Writer.
     * @throws IOException jeśli nie uda się utworzyć pliku tymczasowego.
     */
    public Writer getWriter() throws IOException {

        if (state != State.AVAILABLE) {
            throw new IllegalStateException("Obiekt FileSaver nie został utworzony!");
        }
        Writer out = new FileWriter(tmpFile);
        state = State.INUSE;
        return out;
    }

    /** Metoda zamyka plik wyjściowy i zmienia jego nazwę na docelową.
     * @throws IOException, jeśli pojawią się jakiekolwiek problemy.
     */
    public void finish() throws IOException {

        if (state != State.INUSE) {
            throw new IllegalStateException("Obiekt FileSaver nie jest używany.");
        }

        // Usuwamy wcześniejszy plik kopii bezpieczeństwa, 
        // jeśli taki istnieje. 
        backupFile.delete();

        // Zmieniamy nazwę wcześniejszego pliku z danymi użytkownika na 
        // wczesnieszaNazwa.bak.
        // CZYBA ŻE: to jest nowy plik.
        if (inputFile.exists() && !inputFile.renameTo(backupFile)) {
            throw new IOException("Nie udało się zmienić nazwy pliku " + 
                                    "na nazwę pliku kopii bezpieczeństwa.");
        }

        // Zmieniamy nazwę pliku tymczasowego na docelową nazwę pliku 
        // z danymi użytkownika.
        if (!tmpFile.renameTo(inputFile)) {
            throw new IOException("Nie udało się zmienić nazwy pliku " + 
                                                "tymczasowego na docelową.");
        }
        state = State.AVAILABLE;
    }
}
// END main
