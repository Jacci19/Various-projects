//https://www.youtube.com/watch?v=hGxQyCTCWic&index=4&list=PLpzwMkmxJDUwQuQR7Rezut5UE_8UGDxkU           lekcja 4

package JavaFX.JFX_P1_oneButton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));             //domyślny wers intellij
        //Parent root = FXMLLoader.load(getClass().getResource("StackPaneWindow.fxml"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("JavaFX/JFX_P1_oneButton/StackPaneWindow.fxml"));

        //StackPaneController controller = new StackPaneController();           jeśli w scenebuilder (lub w fxml) nie wpiszemy kontrolera to trzeba to zrobić tak jak w tych dwóch wersach
        //loader.setController(controller);

        StackPane myStackPane = loader.load();                                  //ładujemy główny element (kontener) na którym jest cała reszta
        StackPaneController controller = loader.getController();                //utworzenie instancji kontrolera
                                                                                //jeśli w scenebuilder (lub w fxml) wpiszemy nazwę kontrolera to kontroler sam się inicjalizuje, nie trzeba tworzyć obiektu tego typu w main poleceniem new




        Scene myScene = new Scene(myStackPane);                                 //tworzymy scenę
        primaryStage.setScene(myScene);                                         //przypisujemy scenę do stage
        primaryStage.setTitle("Pierwszy program");                              //ustalenie tytułu
        primaryStage.show();
    }


        public static void main(String[] args){
        launch(args);
    }
}
