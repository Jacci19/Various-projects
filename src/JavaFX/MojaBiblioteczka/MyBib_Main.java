//https://www.youtube.com/watch?v=q_HiOFLmj7Q&list=PLpzwMkmxJDUwQuQR7Rezut5UE_8UGDxkU&index=23

package JavaFX.MojaBiblioteczka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class MyBib_Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("FXML/BorderPaneMain.fxml"));

        Locale.setDefault(new Locale("en"));                                                            //jak zmienimy na "en" to będą przyciski po angielsku
        ResourceBundle bundle = ResourceBundle.getBundle("JavaFX.MojaBiblioteczka.Bundles.messages");
        loader.setResources(bundle);

        BorderPane myBorderPane = loader.load();
        Scene myScene = new Scene(myBorderPane);
        primaryStage.setScene(myScene);
        primaryStage.setTitle(bundle.getString("title.application"));
        primaryStage.show();
    }


}
