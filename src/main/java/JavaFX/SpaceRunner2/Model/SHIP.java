package JavaFX.SpaceRunner2.Model;

public enum SHIP{

    BLUE("JavaFX/SpaceRunner2/ShipChooser/ships/blue_ship.png", "JavaFX/SpaceRunner2/life_Icons/blue_life.png", 2, 300),
    GREEN("JavaFX/SpaceRunner2/ShipChooser/ships/green_ship.png", "JavaFX/SpaceRunner2/life_Icons/green_life.png", 5, 400),
    ORANGE("JavaFX/SpaceRunner2/ShipChooser/ships/orange_ship.png", "JavaFX/SpaceRunner2/life_Icons/orange_life.png", 10, 600),
    RED("JavaFX/SpaceRunner2/ShipChooser/ships/red_ship.png", "JavaFX/SpaceRunner2/life_Icons/red_life.png", 15, 800);

    private String shipUrl;
    private String shipLifeUrl;
    private int shotSpeed;
    private int shotRange;


    private  SHIP(String shipUrl, String shipLifeUrl, int shotSpeed, int shotRange){
        this.shipUrl = shipUrl;
        this.shipLifeUrl = shipLifeUrl;
        this.shotSpeed = shotSpeed;
        this.shotRange = shotRange;
    }

    public String getShipUrl() {
        return shipUrl;
    }
    public String getShipLifeUrl() {
        return shipLifeUrl;
    }

    public int getShotSpeed() {
        return shotSpeed;
    }

    public int getShotRange() {
        return shotRange;
    }
}


/*
    BLUE("JavaFX/SpaceRunner/ShipChooser/ships/blue_ship.png", "JavaFX/SpaceRunner/ShipChooser/blue_life.png"),
    GREEN("JavaFX/SpaceRunner/ShipChooser/green_ship.png", "JavaFX/SpaceRunner/ShipChooser/green_life.png"),
    ORANGE("JavaFX/SpaceRunner/ShipChooser/orange_ship.png", "JavaFX/SpaceRunner/ShipChooser/orange_life.png"),
    RED("JavaFX/SpaceRunner/ShipChooser/red_ship.png", "JavaFX/SpaceRunner/ShipChooser/red_life.png");
*/
