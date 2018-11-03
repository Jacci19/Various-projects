//https://www.youtube.com/watch?v=q_HiOFLmj7Q&list=PLpzwMkmxJDUwQuQR7Rezut5UE_8UGDxkU&index=23

package JavaFX.MojaBiblioteczka;

import JavaFX.MojaBiblioteczka.Database.dbutils.DbManager;
import JavaFX.MojaBiblioteczka.Utils.FillDatabase;
import JavaFX.MojaBiblioteczka.Utils.FxmlUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class MyBib_Main extends Application {

    //public static final String BORDER_PANE_MAIN_FXML = "../aa/BorderPaneMain.fxml";                           //ścieżka względem pliku FXMLUtils.java
    public static final String BORDER_PANE_MAIN_FXML = "/JavaFX/MojaBiblioteczka/FXML/BorderPaneMain.fxml";                           //ścieżka względem pliku FXMLUtils.java

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        Locale.setDefault(new Locale("pl"));                                                            //jak zmienimy na "en" to będą przyciski po angielsku
        Pane myBorderPane = FxmlUtils.fxmlLoader(BORDER_PANE_MAIN_FXML);                                         //Klasę "fxmlUtils" stworzyliśmy my w katalogu Utils
        Scene myScene = new Scene(myBorderPane);
        primaryStage.setScene(myScene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));

        primaryStage.show();

        DbManager.initDatabase();
        FillDatabase.fillDatabase();
    }


}
