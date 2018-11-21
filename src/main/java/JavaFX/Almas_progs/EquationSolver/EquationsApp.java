package JavaFX.Almas_progs.EquationSolver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** https://www.youtube.com/watch?v=Fz-zdHVrDOY&list=PL4h6ypqTi3RR_bhBk6PtLfD83YkaJXXxw&index=4    */

public class EquationsApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AlarmUi.fxml"));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


//przykładowy wpis: 5x - 5 = 15