package JavaFX.Izak;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bullet extends Pane {


    private int speed = 6;
    private ImageView imageView = new ImageView();
    private Position direction;


    public Bullet() {                                                                                              //konstruktor
        imageView.setImage(new Image("JavaFx/Izak/png/Tear.png"));
        imageView.setScaleX(0.5);
        imageView.setScaleY(0.5);
        this.getChildren().add(imageView);
    }


    public Position getDirection() {
        return direction;
    }
    public void setDirection(Position direction) {
        this.direction = direction;
    }

}