/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sud_Game;

import Sud_Game.domain.NPC;
import Sud_Game.domain.Player;

/**
 *
 * @author Pawel
 */
public interface FightStrategy {

    public void fight(Player player, NPC targetNPC);
    
}
