package JavaFX.Izak;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Izak extends Pane {

    private int speed = 12;
    private ImageView imageView = new ImageView();
    private ImageView bodyImageView, headImageView;

    public Izak() {
        this.setTranslateX(700);
        this.setTranslateY(400);
        LoadIzakImages("BodyFront01", "HeadFront");
    }

    private void LoadIzakImages(String bodyImgName, String headImgName) {
        this.getChildren().clear();
        bodyImageView = new ImageView();
        bodyImageView.setImage(new Image("JavaFx/Izak/png/" + bodyImgName +".png"));
        bodyImageView.setX(16);
        bodyImageView.setY(64);
        this.getChildren().add(bodyImageView);

        headImageView = new ImageView();
        headImageView.setImage(new Image("JavaFx/Izak/png/" + headImgName + ".png"));
        this.getChildren().add(headImageView);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void goUp() {
        LoadIzakImages("BodyFront01", "HeadBack");
        this.setTranslateY(this.getTranslateY() - speed);
    }

    public void goDown() {
        LoadIzakImages("BodyFront01", "HeadFront");
        this.setTranslateY(this.getTranslateY() + speed);
    }

    public void goLeft() {
        LoadIzakImages("BodyLeft01", "HeadLeft");
        this.setTranslateX(this.getTranslateX() - speed);
    }

    public void goRight() {
        LoadIzakImages("BodyRight01", "HeadRight");
        this.setTranslateX(this.getTranslateX() + speed);

    }
}
