package Design_Patterns.Factory.ShipFactory;

// This is a factory thats only job is creating ships
// By encapsulating ship creation, we only have one
// place to make modifications

import Design_Patterns.Factory.ShipFactory.EnemyShips.BigUfoEnemyShip;
import Design_Patterns.Factory.ShipFactory.EnemyShips.EnemyShip;
import Design_Patterns.Factory.ShipFactory.EnemyShips.RocketEnemyShip;
import Design_Patterns.Factory.ShipFactory.EnemyShips.UfoEnemyShip;

public class EnemyShipFactory{

    // This could be used as a static method if we
    // are willing to give up subclassing it

    public EnemyShip makeEnemyShip(String newShipType){

        EnemyShip newShip = null;

        if (newShipType.equals("U")){
            return new UfoEnemyShip();
        } else if (newShipType.equals("R")){
            return new RocketEnemyShip();
        } else if (newShipType.equals("B")){
            return new BigUfoEnemyShip();
        } else return null;

    }

}
