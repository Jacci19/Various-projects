package JavaFX.MyMix.Controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

public class W07_Controller_BuiltInInterpolators {

    private MainController mainContr;

    @FXML
    private Pane window07Pane;
    @FXML
    public void initialize(){
        final Rectangle rectBasicTimeline = new Rectangle(100, 200, 100, 50);
        rectBasicTimeline.setFill(Color.BROWN);
        window07Pane.getChildren().add(rectBasicTimeline);

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        final KeyValue kv = new KeyValue(rectBasicTimeline.xProperty(), 500, Interpolator.EASE_BOTH);
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);

        timeline.getKeyFrames().add(kf);
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

//To co w W05 plus dodano interpolator powodujący delikatne wyhamowanie obiektu na krańcach animacji