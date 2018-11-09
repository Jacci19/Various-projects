//ParallelTransition - Jednoczesne wykonywanie kilku ruchów

package JavaFX.MyMix.Controllers;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

public class W03_Controller_ParallelTransition {

    private MainController mainContr;

    @FXML
    private Pane window03Pane;
    @FXML
    public void initialize(){
        Rectangle rectParallel = new Rectangle(10,200,50, 50);                                      //Prostokąt
        rectParallel.setArcHeight(15);                                                                                //Zaokrąglenie kątów
        rectParallel.setArcWidth(15);
        rectParallel.setFill(Color.DARKBLUE);
        rectParallel.setTranslateX(50);
        rectParallel.setTranslateY(75);
        window03Pane.getChildren().add(rectParallel);


        FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), rectParallel);                        //Zmiana przezroczystości (fejd)
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.3f);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);


        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2000), rectParallel);         //Ruch po prostej
        translateTransition.setFromX(50);
        translateTransition.setToX(500);
        translateTransition.setFromY(10);
        translateTransition.setToY(300);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);

                                                                                                                        //Obrót
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(3000), rectParallel);                  //w takim czasie...
        rotateTransition.setByAngle(360f);                                                                              //...obróć o taki kąt
        rotateTransition.setCycleCount(2);
        rotateTransition.setAutoReverse(true);
                                                                                                                        //Zmiana rozmiaru
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(2000), rectParallel);                     //w takim czasie...
        scaleTransition.setToX(4f);                                                                                     //...zmień rozmiar w X
        scaleTransition.setToY(2f);                                                                                     //...zmień rozmiar w Y
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);


        ParallelTransition parallelTransition = new ParallelTransition();                                               //Równoległe wykonanie powyższych ruchów
        parallelTransition.getChildren().addAll(
                fadeTransition,
                translateTransition,
                rotateTransition,
                scaleTransition

        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();

    }








    @FXML
    public void onPowrotBtnAction() throws IOException {
        mainContr.loadMenuScreen();
    }

    public void setMainContr(MainController mainContr) {
        this.mainContr = mainContr;
    }
}
