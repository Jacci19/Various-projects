package JavaFX.Izak;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Wall extends Pane {
    private int X;
    private int Y;
    private int width;
    private int height;

    public Wall() {
        Image img = new Image("JavaFx/Izak/png/area/brick_wall_80.png");
        ImageView wallIV = new ImageView(img);
        this.getChildren().add(wallIV);
    }
}
