
package Sud_Game.services;

import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import Sud_Game.domain.Location;
import Sud_Game.domain.NPC;
import Sud_Game.domain.Player;

public class KillCommandTest {

    private Location mainLocation;
    NPC ork;
    
    @Before
    public void initTest() {
        mainLocation = new Location("Short", "Long");
        ork = new NPC("ork");
        mainLocation.addNpc(ork);
    }
    
    @Test
    public void beginCombatIfTargetIsThere() {
        Player testPlayer = new Player("TestPlayer");
        testPlayer.setCurrentLocation(mainLocation);
        KillCommand kill = new KillCommand("ork",testPlayer);
        KillCommand killSpy = Mockito.spy(kill);
        killSpy.execute();
        Mockito.verify(killSpy,times(1)).beginCombat(testPlayer, ork);
    }
    
    @Test
    public void returnTargetNotThereInfoIfTargetIsNoOnLocation() {
        Player testPlayer = new Player("TestPlayer");
        testPlayer.setCurrentLocation(mainLocation);
        KillCommand kill = new KillCommand("goblin", testPlayer);
        String result = kill.execute();
        assertEquals("There's no one like that around.  ",result);
    }
    
}
