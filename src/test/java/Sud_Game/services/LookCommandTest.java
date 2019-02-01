package Sud_Game.services;

import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import Sud_Game.domain.Direction;
import Sud_Game.domain.Location;
import Sud_Game.domain.Player;

public class LookCommandTest {
    
    private Location mainLocation;

    @Before
    public void initTest() {
        mainLocation = new Location("Short", "Long");
    }
    
    @Test
    public void lookTest() {
        Player p = new Player("TestPlayer");
        p.setCurrentLocation(mainLocation);
        LookCommand look = new LookCommand(p);
        String result = look.execute();
        assertEquals(mainLocation.getDescription(), result);
    }
    
}
