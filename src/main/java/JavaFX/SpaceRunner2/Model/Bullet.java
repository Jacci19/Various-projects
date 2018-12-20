package JavaFX.SpaceRunner2.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bullet extends Pane {

    private int speed = 6;
    private ImageView imageView = new ImageView();
    private double bLength;
    private double bHeight;
    private double radius = 10;

    public Bullet() {                                                                                              //konstruktor
        imageView.setImage(new Image("JavaFX/SpaceRunner2/bullet.png"));
        imageView.setScaleX(2.5);
        imageView.setScaleY(2.5);
        this.getChildren().add(imageView);

        bLength = imageView.getImage().getWidth() * imageView.getScaleX();
        bHeight = imageView.getImage().getHeight() * imageView.getScaleY();
//        radius = ((bLength/2) + (bHeight/2))/2;                                                 //promień jako średnia z połowy długości boków

    }

    public ImageView getImageView() {
        return imageView;
    }

    public double getbLength() {
        return bLength;
    }

    public double getbHeight() {
        return bHeight;
    }

    public double getRadius() {
        return radius;
    }
}
