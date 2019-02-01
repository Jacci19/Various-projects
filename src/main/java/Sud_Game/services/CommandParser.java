package Sud_Game.services;

import Sud_Game.AgilityFightStrategy;
import Sud_Game.FightStrategy;
import Sud_Game.FightThread;
import Sud_Game.domain.Direction;
import Sud_Game.domain.NPC;
import Sud_Game.domain.Player;

public class CommandParser {

    public void actOnCommand(String playerCommand, Player player) {
        playerCommand = playerCommand.toLowerCase();

        String[] splitted = playerCommand.split(" ");
        
        Command command = null;

        switch (splitted[0]) {
            case "n":
            case "north":
                command = new MoveCommand(Direction.N, player);
                break;
            case "s":
            case "south":
                command = new MoveCommand(Direction.S, player);
                break;
            case "e":
            case "east":
                command = new MoveCommand(Direction.E, player);
                break;
            case "w":
            case "west":
                command = new MoveCommand(Direction.W, player);
                break;
            case "kill":
                command = new KillCommand(splitted[1], player);
                break;
            case "look":
                command = new LookCommand(player);
                break;
            default:
                System.out.println("Unknown command.");
                break;
        }
        
        if(command!=null) {
            System.out.println(command.execute());
        }
             
    }
}
