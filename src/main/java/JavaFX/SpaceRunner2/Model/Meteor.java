package JavaFX.SpaceRunner2.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Meteor extends Pane {

    private ImageView imageView = new ImageView();
    private double radius;
    private double mLength;
    private double mHeight;


    public Meteor() {                                                                                              //konstruktor
        if (Math.random() > 0.5){
            imageView.setImage(new Image("JavaFX/SpaceRunner2/meteor_brown.png"));
        }
        else{
            imageView.setImage(new Image("JavaFX/SpaceRunner2/meteor_gray.png"));
        }
        imageView.setScaleX(random(2.0, 1));
        imageView.setScaleY(random(2.0, 1));
        this.getChildren().add(imageView);
        mLength = imageView.getImage().getWidth() * imageView.getScaleX();
        mHeight = imageView.getImage().getHeight() * imageView.getScaleY();
        radius = ((mLength/2) + (mHeight/2))/2;                                                 //promień jako średnia z połowy długości boków
    }

    private double random(double data, double range) {

        double min = data - range;
        double max = data + range;
        Random rand = new Random();

        return rand.nextDouble() * (max - min) + min;
    }

    public double getRadius() {
        return radius;
    }

    public double getmLength() {
        return mLength;
    }

    public double getmHeight() {
        return mHeight;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
