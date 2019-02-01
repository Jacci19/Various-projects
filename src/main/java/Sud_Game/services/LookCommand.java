package Sud_Game.services;

import Sud_Game.domain.Location;
import Sud_Game.domain.NPC;
import Sud_Game.domain.Player;

public class LookCommand implements Command {
    
    private Player player;

    public LookCommand(Player p) {
        this.player = p;
    }

    @Override
    public String execute() {
        return player.getCurrentLocationDescription();
    }
    
}
