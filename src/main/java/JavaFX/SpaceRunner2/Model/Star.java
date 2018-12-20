package JavaFX.SpaceRunner2.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Star extends Pane {

    private ImageView imageView = new ImageView();
    private double sLength;
    private double sHeight;


    public Star() {
        imageView.setImage(new Image("JavaFX/SpaceRunner2/gold_star.png"));
        this.getChildren().add(imageView);
        sLength = imageView.getImage().getWidth();
        sHeight = imageView.getImage().getHeight();

    }

    public ImageView getImageView() {
        return imageView;
    }
    public double getsLength() {
        return sLength;
    }
    public double getsHeight() {
        return sHeight;
    }
}
