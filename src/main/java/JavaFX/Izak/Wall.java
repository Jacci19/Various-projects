package JavaFX.Izak;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Pane {
    private int X;
    private int Y;
    private int width;
    private int height;

    public Wall() {
        Rectangle rect = new Rectangle(100, 100, Color.BROWN);
        this.getChildren().add(rect);
    }
}
