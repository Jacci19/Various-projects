package JavaFX.SpaceRunner2.Model;

public enum SHIP{

    BLUE("JavaFX/SpaceRunner2/ShipChooser/ships/blue_ship.png", "JavaFX/SpaceRunner2/life_Icons/blue_life.png", 5, 2, 300, 20),
    GREEN("JavaFX/SpaceRunner2/ShipChooser/ships/green_ship.png", "JavaFX/SpaceRunner2/life_Icons/green_life.png",5.5, 5, 400, 30),
    ORANGE("JavaFX/SpaceRunner2/ShipChooser/ships/orange_ship.png", "JavaFX/SpaceRunner2/life_Icons/orange_life.png",6.0, 10, 600, 40),
    RED("JavaFX/SpaceRunner2/ShipChooser/ships/red_ship.png", "JavaFX/SpaceRunner2/life_Icons/red_life.png",8, 15, 800, 10);

    private String shipUrl;
    private String shipLifeUrl;
    private double shipSpeed;
    private int shotSpeed;
    private int shotRange;
    private int shootFrequency;



    SHIP(String shipUrl, String shipLifeUrl, double shipSpeed, int shotSpeed, int shotRange, int shootFrequency){        //konstruktor
        this.shipUrl = shipUrl;
        this.shipLifeUrl = shipLifeUrl;
        this.shipSpeed = shipSpeed;
        this.shotSpeed = shotSpeed;
        this.shotRange = shotRange;
        this.shootFrequency = shootFrequency;
    }
                                                                                                                        //gettery
    public String getShipUrl() {
        return shipUrl;
    }
    public String getShipLifeUrl() {
        return shipLifeUrl;
    }
    public double getShipSpeed() {
        return shipSpeed;
    }
    public int getShotSpeed() {
        return shotSpeed;
    }
    public int getShotRange() {
        return shotRange;
    }
    public int getShootFrequency() {
        return shootFrequency;
    }
}

