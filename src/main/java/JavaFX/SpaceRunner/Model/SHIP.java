package JavaFX.SpaceRunner.Model;

public enum SHIP {

    BLUE("JavaFX/SpaceRunner/ShipChooser/ships/blue_ship.png"),
    GREEN("JavaFX/SpaceRunner/ShipChooser/ships/green_ship.png"),
    ORANGE("JavaFX/SpaceRunner/ShipChooser/ships/orange_ship.png"),
    RED("JavaFX/SpaceRunner/ShipChooser/ships/red_ship.png");

/*
    BLUE("JavaFX/SpaceRunner/ShipChooser/ships/blue_ship.png", "JavaFX/SpaceRunner/ShipChooser/blue_life.png"),
    GREEN("JavaFX/SpaceRunner/ShipChooser/green_ship.png", "JavaFX/SpaceRunner/ShipChooser/green_life.png"),
    ORANGE("JavaFX/SpaceRunner/ShipChooser/orange_ship.png", "JavaFX/SpaceRunner/ShipChooser/orange_life.png"),
    RED("JavaFX/SpaceRunner/ShipChooser/red_ship.png", "JavaFX/SpaceRunner/ShipChooser/red_life.png");
*/

    private String urlShip;
   // private String urlLife;


    private SHIP(String urlShip) {
        this.urlShip = urlShip;
    }

/*
    SHIP(String urlShip, String urlLife) {
        this.urlShip = urlShip;
        this.urlLife = urlLife;
        System.out.println("Ship NIE dziala");

    }
*/

/*
    public String getUrlShip() {
        return this.urlShip;
    }

    public String getUrlLife(){
        return this.urlLife;
    }
*/

}
