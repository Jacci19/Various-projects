package JavaFX.SpaceRunner2.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bullet extends Pane {

    private int speed = 6;
    private ImageView imageView = new ImageView();
    private double radius = 10;

    public Bullet() {                                                                                              //konstruktor
        imageView.setImage(new Image("JavaFX/SpaceRunner2/bullet.png"));
        imageView.setScaleX(2.5);
        imageView.setScaleY(2.5);
        this.getChildren().add(imageView);


    }

    public ImageView getImageView() {
        return imageView;
    }

    public double getRadius() {
        return radius;
    }
}
