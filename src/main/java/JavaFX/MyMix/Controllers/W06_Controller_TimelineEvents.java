package JavaFX.MyMix.Controllers;

import javafx.fxml.FXML;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

//public class Window_06_Controller {
public class W06_Controller_TimelineEvents extends Application {



    private MainController mainContr;
                                                //main timeline
    private Timeline timeline;
    private AnimationTimer timer;
                                                //variable for storing actual frame
    private Integer i = 0;
    private Stage stage = null;

    @FXML
    private Pane window06Pane;
    @FXML
    public void initialize(){
        stage = new Stage();
        start (stage);
    }

    @Override public void start(Stage stage) {                                                          //TWORZENIE OBIEKTU________________________
        Group myGroup = new Group();
        Scene scene = new Scene(myGroup);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        myGroup.setTranslateX(100);                                                                     //położenie koła w okienku
        myGroup.setTranslateY(100);

                                                                                                        //create a circle with effect
        final Circle circle = new Circle(20,  Color.rgb(156,216,255));
        circle.setEffect(new Lighting());                                                               //nałożenie efektu wizualnego na koło
                                                                                                        //create a text inside a circle
        final Text text = new Text (i.toString());
        text.setStroke(Color.BLACK);                                                                    //pogrubienie tekstu w kole
                                                                                                        //create a layout for circle with text inside
        final StackPane stack = new StackPane();
        stack.getChildren().addAll(circle, text);
        stack.setLayoutX(30);
        stack.setLayoutY(30);

        myGroup.getChildren().add(stack);
        stage.show();
                                                                                //_____________________TWORZENIE ANIMACJI OBIEKTU________________________
                                                                    //create a timeline for moving the circle

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        //timeline.setAutoReverse(true);

                                                                    //You can add a specific action when each frame is started.
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {                            //Co ma robić w każdej klatce animacji:
                text.setText(i.toString());
                i++;
            }
        };

                                                                                     //create a keyValue with factory: scaling the circle 2times
        KeyValue keyValueX = new KeyValue(stack.scaleXProperty(), 5);       //endValue - ile razy powiększyć obiekt
        KeyValue keyValueY = new KeyValue(stack.scaleYProperty(), 5);

                                                                                     //create a keyFrame, the keyValue is reached at time 2s
        Duration duration = Duration.millis(2000);
                                                                                     //one can add a specific action when the keyframe is reached
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
               stack.setTranslateX(java.lang.Math.random()*200-100);               //zmiana położenia koła

                i = 0;                                                               //reset counter
            }
        };

        KeyFrame keyFrame = new KeyFrame(duration, onFinished , keyValueX, keyValueY);

                                                                                    //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);


        timeline.play();                                            //animacja zmiany rozmiaru koła
        timer.start();                                              //animacja tekstu w kole

    }




    @FXML
    public void onPowrotBtnAction() throws IOException {
        stage.close();
        mainContr.loadMenuScreen();
    }

    public void setMainContr(MainController mainContr) {
        this.mainContr = mainContr;
    }
}


// Animacja obiektu trawa od zera przez czas DURATION do osiągnięcia klatki kluczowej. W tym czasie płynnie zmienia się parametr obiektu określony w "keyValueX\keyValueY"
// Dodatkowa animacja tekstu w obiekcie realizowana jest niezależnie przez AnimationTimer
// Gdy osiągamy czas DURATION wchodzimy w klatkę kluczową. Wywoływany jest eventHandler, który zmienia położenie obiektu