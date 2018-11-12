package JavaFX.MyMix.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

public class W05_Controller_BasicTimeline {

    private MainController mainContr;

    @FXML
    private Pane window05Pane;
    @FXML
    public void initialize(){
        final Rectangle rectBasicTimeline = new Rectangle(100, 200, 100, 50);
        rectBasicTimeline.setFill(Color.RED);
        window05Pane.getChildren().add(rectBasicTimeline);

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        final KeyValue kv = new KeyValue(rectBasicTimeline.xProperty(), 300);                      // wartość klatki kluczowej
        final KeyFrame kf = new KeyFrame(Duration.millis(200), kv);                                         // klatka kluczowa

        timeline.getKeyFrames().add(kf);                                                                    // dodanie kk do timeline

        //final KeyValue kv2 = new KeyValue(rectBasicTimeline.yProperty(), 300);                            //  to działa równocześnie z poprzednim
        //final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);                                        //
        //timeline.getKeyFrames().add(kf2);                                                                    //

        timeline.play();
    }








    @FXML
    public void onPowrotBtnAction() throws IOException {
        mainContr.loadMenuScreen();
    }

    public void setMainContr(MainController mainContr) {
        this.mainContr = mainContr;
    }
}
