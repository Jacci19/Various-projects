package JavaFX.SpaceRunner.Model;

public enum SHIP {

    BLUE("JavaFX/SpaceRunner/ShipChooser/ships/blue_ship.png"),
    GREEN("JavaFX/SpaceRunner/ShipChooser/ships/green_ship.png"),
    ORANGE("JavaFX/SpaceRunner/ShipChooser/ships/orange_ship.png"),
    RED("JavaFX/SpaceRunner/ShipChooser/ships/red_ship.png");

    private String shipUrl;


    private  SHIP(String shipUrl){
        this.shipUrl = shipUrl;
    }

    public String getShipUrl() {
        return shipUrl;
    }
    public void setShipUrl(String shipUrl) {
        this.shipUrl = shipUrl;
    }
}


/*
    BLUE("JavaFX/SpaceRunner/ShipChooser/ships/blue_ship.png", "JavaFX/SpaceRunner/ShipChooser/blue_life.png"),
    GREEN("JavaFX/SpaceRunner/ShipChooser/green_ship.png", "JavaFX/SpaceRunner/ShipChooser/green_life.png"),
    ORANGE("JavaFX/SpaceRunner/ShipChooser/orange_ship.png", "JavaFX/SpaceRunner/ShipChooser/orange_life.png"),
    RED("JavaFX/SpaceRunner/ShipChooser/red_ship.png", "JavaFX/SpaceRunner/ShipChooser/red_life.png");
*/
