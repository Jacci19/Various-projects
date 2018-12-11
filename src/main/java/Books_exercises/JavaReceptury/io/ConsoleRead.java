package Books_exercises.JavaReceptury.io;

/**
 * Program wczytuje imię użytkownia. Wymaga wersji Java 6; 
 * program nie będzie działał prawidłowo w przypadku uruchamiania z poziomu 
 * zintegrowanego środowiska programistacznego Eclipse. 
 */
// BEGIN main
public class ConsoleRead {
    public static void main(String[] args) {
        String name = System.console().readLine("Jak masz na imię?");
        System.out.println("Cześć, " + name.toUpperCase());
    }
}
// END main
