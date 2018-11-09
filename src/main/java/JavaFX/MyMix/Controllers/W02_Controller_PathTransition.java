//Ruch obiektu po ścieżce.                       https://docs.oracle.com/javafx/2/animations/basics.htm#CHDEBGBG

package JavaFX.MyMix.Controllers;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

public class W02_Controller_PathTransition {

    private MainController mainContr;

    @FXML
    private Pane window02Pane;
    @FXML
    public void initialize(){
        final Rectangle rectPath = new Rectangle (0, 0, 40, 40);                                         //obiekt
        rectPath.setArcHeight(10);
        rectPath.setArcWidth(10);
        rectPath.setFill(Color.ORANGE);
        window02Pane.getChildren().add(rectPath);

        Path path = new Path();                                                                                             //ścieżka ruchu
        path.getElements().add(new MoveTo(100,150));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));

        PathTransition pathTransition = new PathTransition();                                                               //ruch obiektu po ścieżce
        pathTransition.setDuration(Duration.millis(3000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectPath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        //pathTransition.setOrientation(PathTransition.OrientationType.NONE);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
    }






    @FXML
    public void onPowrotBtnAction() throws IOException {
        mainContr.loadMenuScreen();
    }

    public void setMainContr(MainController mainContr) {
        this.mainContr = mainContr;
    }
}
