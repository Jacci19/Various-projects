package Sud_Game.repository;

import Sud_Game.domain.Direction;
import Sud_Game.domain.Location;
import Sud_Game.domain.NPC;

public class LocationRepository {
    
    private Location startLoc;

    public LocationRepository() {
        startLoc = new Location("Small room","You're in small, dark room. Single bed standing next to the wall is only furniture in here.");
        Location secondLocation = new Location("Dark corridor","In dim, flickering lights you're not able to see much. Narrow space of the corridor is surrounded by steel walls.");
        
        startLoc.addExit(Direction.N, secondLocation);
        secondLocation.addExit(Direction.S,startLoc);
        
        NPC ork = new NPC("Ork",50,5);
              
        startLoc.addNpc(ork);
        
    }

    public Location getStartLocation() {
       return this.startLoc;
    }


    
}
