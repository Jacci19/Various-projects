//https://www.youtube.com/watch?v=q_HiOFLmj7Q&list=PLpzwMkmxJDUwQuQR7Rezut5UE_8UGDxkU&index=23

package JavaFX.MojaBiblioteczka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MyBib_Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("FXML/BorderPaneMain.fxml"));
        BorderPane myBorderPane = loader.load();
        Scene myScene = new Scene(myBorderPane);
        primaryStage.setScene(myScene);
        primaryStage.setTitle("Moja biblioteczka");
        primaryStage.show();
    }


}
