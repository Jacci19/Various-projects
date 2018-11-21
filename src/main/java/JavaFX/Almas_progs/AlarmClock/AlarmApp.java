package JavaFX.Almas_progs.AlarmClock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** coś nie działa
 * czy intellij w tej wersji (darmowej) obsługuje CSS?
 * dlaczego w fxml nie ma linka do controllera a po wpisaniu jego jest błąd?       */


/**   https://www.youtube.com/watch?v=wIpgGpmFUjA&index=8&list=PL4h6ypqTi3RR_bhBk6PtLfD83YkaJXXxw
 *    https://github.com/AlmasB/FXTutorials/tree/master/src/com/almasb/alarm       */

public class AlarmApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AlarmUi.fxml"));

        AlarmController controller = new AlarmController(new AlarmModel());
        loader.setController(controller);

        stage.setScene(new Scene(loader.load()));
        stage.setOnCloseRequest(e -> controller.onExit());
        stage.setTitle("Alarm");
        stage.setResizable(false);
        stage.show();
    }
}
