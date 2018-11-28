package JavaFX.Izak;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class IzakMain extends Application{

    private Pane root;
    private Izak izak;

    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));

        stage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    izak.goUp();
                    break;
                case S:
                    izak.goDown();
                    break;
                case A:
                    izak.goLeft();
                    break;
                case D:
                    izak.goRight();
                    break;
                default:
                    break;
            }
        });

        stage.show();
    }

    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(1600, 1000);
        izak = new Izak();
        root.getChildren().add(izak);

        return root;
    }



    public static void main(String[] args) {
        launch(args);
    }
}