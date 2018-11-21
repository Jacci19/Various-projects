package JavaFX.Almas_progs.Calculator;

/**    https://github.com/AlmasB/FXTutorials/tree/master/src/com/almasb/calc
https://www.youtube.com/watch?v=y1ZaBalVZic&index=20&list=PL4h6ypqTi3RR_bhBk6PtLfD83YkaJXXxw      */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalcApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CalcUi.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
