package JavaFX.Paint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**     https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/paint/PaintApp.java
 https://www.youtube.com/watch?v=0u5aotARyU8&index=7&list=PL4h6ypqTi3RR_bhBk6PtLfD83YkaJXXxw        */

public class PaintApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("paint.fxml"))));
        stage.setTitle("Paint App");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}