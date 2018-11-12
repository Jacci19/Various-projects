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

public class W08_Controller_CustomInterpolators {

    private MainController mainContr;

    @FXML
    private Pane window08Pane;
    @FXML
    public void initialize(){
        final Rectangle rect = new Rectangle(100, 200, 100, 50);
        rect.setFill(Color.BROWN);
        window08Pane.getChildren().add(rect);

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        //final KeyValue keyValue1 = new KeyValue(rect.xProperty(), 300);
        AnimationBooleanInterpolator yInterp = new AnimationBooleanInterpolator();
        final KeyValue keyValue2 = new KeyValue(rect.yProperty(), 500., yInterp);

        final KeyFrame kf = new KeyFrame(Duration.millis(2000), keyValue2);

        timeline.getKeyFrames().add(kf);
        timeline.play();

    }

    public class AnimationBooleanInterpolator extends Interpolator {
        @Override
        protected double curve(double t) {
            return Math.abs(0.5-t)*2 ;
        }
    }




    @FXML
    public void onPowrotBtnAction() throws IOException {
        mainContr.loadMenuScreen();
    }

    public void setMainContr(MainController mainContr) {
        this.mainContr = mainContr;
    }
}
