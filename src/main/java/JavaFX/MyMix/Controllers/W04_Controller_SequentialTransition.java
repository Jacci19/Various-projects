//SequentialTransition - Sekwencyjne wykonywanie kilku ruchów

package JavaFX.MyMix.Controllers;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

public class W04_Controller_SequentialTransition {

    private MainController mainContr;

    @FXML
    private Pane window04Pane;
    @FXML
    public void initialize(){
        Rectangle rectSeq = new Rectangle(50,100,50,50);
        rectSeq.setArcHeight(15);
        rectSeq.setArcWidth(15);
        rectSeq.setFill(Color.CRIMSON);
        rectSeq.setTranslateX(50);
        rectSeq.setTranslateY(50);
        window04Pane.getChildren().add(rectSeq);


        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rectSeq);                             //Zmiana przezroczystości (fejd)
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.3f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(true);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2000), rectSeq);              //Ruch po prostej
        translateTransition.setFromX(50);
        translateTransition.setToX(375);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(2000), rectSeq);                       //Obrót
        rotateTransition.setByAngle(180f);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(2000), rectSeq);                          //Zmiana rozmiaru
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);

        SequentialTransition sequentialTransition = new SequentialTransition();                                         //Sekwencyjne wykonanie powyższych ruchów
        sequentialTransition.getChildren().addAll(
                fadeTransition,
                translateTransition,
                rotateTransition,
                scaleTransition);
        sequentialTransition.setCycleCount(Timeline.INDEFINITE);
        sequentialTransition.setAutoReverse(true);

        sequentialTransition.play();

    }








    @FXML
    public void onPowrotBtnAction() throws IOException {
        mainContr.loadMenuScreen();
    }

    public void setMainContr(MainController mainContr) {
        this.mainContr = mainContr;
    }
}
