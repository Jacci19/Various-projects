package Sud_Game;
/**
 *
Gra tekstowa skopiowana z:  https://github.com/pawelcwik/SUDGame
Opis na YouTube:            https://www.youtube.com/watch?v=x9f-cYThkcM&index=4&list=PLFr4Gn266-7qkEqQn6hmXUv4jbimDESJd
Brak moich modyfikacji
 */
import java.util.Scanner;
import Sud_Game.domain.Player;
import Sud_Game.repository.LocationRepository;
import Sud_Game.services.CommandParser;

public class SudGame_Main {
    
    public static void main(String[] args) {
        
        
        LocationRepository locationRepository = new LocationRepository();
        CommandParser parser = new CommandParser();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your name?");
        String playerName = scanner.nextLine();
        
        Player player = new Player(playerName, 100, 10, 20);
        
        player.setCurrentLocation(locationRepository.getStartLocation());
        
        System.out.println(player.getCurrentLocationDescription());
        
        String command = "";
        while(!command.equals("quit")) {
            command = readPlayerInput(scanner);
            parser.actOnCommand(command,player);
        }
        
        System.out.println("Goodbye!");
    }

    private static String readPlayerInput(Scanner scanner) {
       System.out.print(">");
       String command = scanner.nextLine();
       return command;
    } 
}
