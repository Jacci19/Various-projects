//Mix różnych programów. Animacje są z: https://docs.oracle.com/javafx/2/animations/basics.htm

package JavaFX.MyMix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MyMix_Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/MyMix/FXML/MainScreen.fxml"));
        StackPane myStackPane = loader.load();
        Scene myScene = new Scene(myStackPane, 1200, 800);

        primaryStage.setTitle("Simple Button Menu");
        primaryStage.setScene(myScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
