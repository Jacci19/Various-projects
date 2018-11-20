package JavaFX.LambdaShapesAnimation;

/** https://www.youtube.com/watch?v=VPYskQbq7q4&index=14&list=PL4h6ypqTi3RR_bhBk6PtLfD83YkaJXXxw
 * Program animuje wciskane litery po ścieżce okręgu lub innym kształcie                                    */

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LambdaShapesAnimation extends Application {

    private Pane root = new Pane();

    private Parent createContent() {
        root.setPrefSize(1000, 600);

        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());

/*
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {                    // 1
            @Override
            public void handle(KeyEvent event) {
                handleKeyEvent(event);
            }
        });

        scene.setOnKeyPressed((KeyEvent event) -> handleKeyEvent(event));       // 2
        scene.setOnKeyPressed( event -> handleKeyEvent(event));                 // 3
*/
        scene.setOnKeyPressed( this :: handleKeyEvent);                         // 4        te 4 grupy kodu robią każda to samo

        stage.setScene(scene);
        stage.show();
    }

    private void handleKeyEvent(KeyEvent event) {
        Text text = new Text(event.getCode().toString());
        text.setFont(Font.font(22));                                            //Font Size
        root.getChildren().add(text);

        //Animacja 1
        Circle circle = new Circle(100);
        circle.setTranslateX(200);
        circle.setTranslateY(200);

        PathTransition pt = new PathTransition(Duration.seconds(2), circle, text);
        pt.setAutoReverse(true);
        pt.setCycleCount(Animation.INDEFINITE);
        pt.play();                                                                      //animacja wpisywanych liter po okręgu circle



        //Animacja 2
        Circle circle2 = new Circle(100);

        Shape shape = Shape.subtract(new Rectangle(200, 100), circle2);    //figura shape powstała po wycięciu fragmentu prostokąta przez okrąg
        shape.setTranslateX(600);
        shape.setTranslateY(300);
        shape.setFill(Color.YELLOW);
        root.getChildren().add(shape);

        PathTransition pt2 = new PathTransition(Duration.seconds(2), shape, text);
        pt2.setAutoReverse(true);
        pt2.setCycleCount(Animation.INDEFINITE);
        //pt2.play();                                                                     //animacja wpisywanych liter po figurze shape

                                                                                        //pt i pt2 nie chcą działać jednocześnie, trzeba któreś zakomentować.
    }

    public static void main(String[] args) {
        launch(args);
    }

}

















