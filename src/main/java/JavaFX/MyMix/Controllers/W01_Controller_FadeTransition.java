//Transition, animacja przezroczystości obiektu.         https://docs.oracle.com/javafx/2/animations/basics.htm#CHDEBGBG


package JavaFX.MyMix.Controllers;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

public class W01_Controller_FadeTransition {

    private MainController mainContr;

    @FXML
    private Pane window01Pane;
    @FXML
    public void initialize(){
        final Rectangle rect1 = new Rectangle(100, 100, 200, 200);
        rect1.setArcHeight(20);                                                              //promień zaokrąglenia kątów
        rect1.setArcWidth(20);
        rect1.setFill(Color.RED);

        // Group group = new Group();
        //group.getChildren().add(rect1);
        window01Pane.getChildren().add(rect1);

        FadeTransition ft = new FadeTransition(Duration.millis(1000), rect1);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    public void onPowrotBtnAction() throws IOException{
        mainContr.loadMenuScreen();
    }

    public void setMainContr(MainController mainContr) {
        this.mainContr = mainContr;
    }
}
