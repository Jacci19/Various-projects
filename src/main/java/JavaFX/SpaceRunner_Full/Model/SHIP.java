package JavaFX.SpaceRunner_Full.Model;

public enum SHIP {

    BLUE("JavaFX/SpaceRunner/ShipChooser/blue_ship.png", "JavaFX/SpaceRunner/ShipChooser/blue_life.png"),
    GREEN("JavaFX/SpaceRunner/ShipChooser/green_ship.png", "JavaFX/SpaceRunner/ShipChooser/green_life.png"),
    ORANGE("JavaFX/SpaceRunner/ShipChooser/orange_ship.png", "JavaFX/SpaceRunner/ShipChooser/orange_life.png"),
    RED("JavaFX/SpaceRunner/ShipChooser/red_ship.png", "JavaFX/SpaceRunner/ShipChooser/red_life.png");

    private String urlShip;
    private String urlLife;

    SHIP(String urlShip, String urlLife) {
        this.urlShip = urlShip;
        this.urlLife = urlLife;
        System.out.println("Ship NIE dziala");

    }

    public String getUrlShip() {
        return this.urlShip;
    }

    public String getUrlLife(){
        return this.urlLife;
    }

}
