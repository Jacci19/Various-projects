//Proste menu przełączające do innych okien. Templejt do innych projektów

package JavaFX.SimpleButtonMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SBM_Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/JavaFX/SimpleButtonMenu/FXML/MainScreen.fxml"));
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
