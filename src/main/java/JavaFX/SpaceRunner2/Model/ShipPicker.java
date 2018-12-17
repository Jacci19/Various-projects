package JavaFX.SpaceRunner2.Model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

//jest to VBox z jedną kształtką i jednym statkiem

public class ShipPicker extends VBox {

    private ImageView circleImage;
    private ImageView shipImage;

    private  String circleNotChosenPath = "JavaFX/SpaceRunner/shipChooser/shape_notChosen.png";
    private  String circleChosenPath = "JavaFX/SpaceRunner/shipChooser/shape_Chosen.png";
    private SHIP ship;
    private  boolean isCircleChosen;

    public ShipPicker(SHIP ship) {
        this.circleImage = new ImageView(circleNotChosenPath);
        this.shipImage = new ImageView(ship.getShipUrl());
        this.ship = ship;                                                       //ship przesyłamy parametrem konstruktora
        isCircleChosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(30);                                                    // odległość statków od kształtek wyboru
        this.getChildren().add(circleImage);                                    // wstawienie jednej kształtki wyboru
        this.getChildren().add(shipImage);                                      // wstawienie jednego statku (to jest w Vboxie więc doda się pod kształtką)
    }

    public SHIP getShip(){
        return ship;
    }

    public boolean getIsCircleChosen() {
        return isCircleChosen;
    }

    public void setIsCircleChosen(boolean isCircleChosen) {
        this.isCircleChosen = isCircleChosen;
        String imageToSet = this.isCircleChosen ? circleChosenPath : circleNotChosenPath;  // (A?B:C - if A is true then do B, if A is false then do C
        circleImage.setImage(new Image(imageToSet));
    }
}
