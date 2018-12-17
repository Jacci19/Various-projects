package JavaFX.SpaceRunner.Model;

public enum SHIP {

    BLUE("JavaFX/SpaceRunner/ShipChooser/ships/blue_ship.png", "JavaFX/SpaceRunner/life_Icons/blue_life.png"),
    GREEN("JavaFX/SpaceRunner/ShipChooser/ships/green_ship.png", "JavaFX/SpaceRunner/life_Icons/green_life.png"),
    ORANGE("JavaFX/SpaceRunner/ShipChooser/ships/orange_ship.png", "JavaFX/SpaceRunner/life_Icons/orange_life.png"),
    RED("JavaFX/SpaceRunner/ShipChooser/ships/red_ship.png", "JavaFX/SpaceRunner/life_Icons/red_life.png");

    private String shipUrl;
    private String shipLifeUrl;


    private  SHIP(String shipUrl, String shipLifeUrl){
        this.shipUrl = shipUrl;
        this.shipLifeUrl = shipLifeUrl;
    }

    public String getShipUrl() {
        return shipUrl;
    }
    public String getShipLifeUrl() {
        return shipLifeUrl;
    }
}


/*
    BLUE("JavaFX/SpaceRunner/ShipChooser/ships/blue_ship.png", "JavaFX/SpaceRunner/ShipChooser/blue_life.png"),
    GREEN("JavaFX/SpaceRunner/ShipChooser/green_ship.png", "JavaFX/SpaceRunner/ShipChooser/green_life.png"),
    ORANGE("JavaFX/SpaceRunner/ShipChooser/orange_ship.png", "JavaFX/SpaceRunner/ShipChooser/orange_life.png"),
    RED("JavaFX/SpaceRunner/ShipChooser/red_ship.png", "JavaFX/SpaceRunner/ShipChooser/red_life.png");
*/
