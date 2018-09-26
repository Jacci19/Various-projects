//https://www.youtube.com/watch?v=hGxQyCTCWic&index=4&list=PLpzwMkmxJDUwQuQR7Rezut5UE_8UGDxkU
//Main z lekcji 3. Przeklej do głównego main jeśli chcesz sprawdzić.
//Zrobienie prostego okna z przyciskiem bez użycia FXMLa

package JavaFX.JFX_P1_oneButton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class temp_Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        System.out.println("TEST");
        StackPane myStackPane = new StackPane();                                      // utworzenie layout
        Button myButton = new Button("Przycisk");                                // utworzenie button
        myStackPane.getChildren().add(myButton);                                      // dodanie button na layout
        Scene myScene = new Scene(myStackPane, 600, 400);                // utworzenie sceny o podanych wymiarach
        primaryStage.setScene(myScene);                                               //ustawienie sceny
        primaryStage.setTitle("Pierwszy program");                                    //ustalenie tytułu

        primaryStage.setWidth(400);                                                   //ustawienie rozmiarów stage (wymiary stage wymuszają wymiary sceny)
        primaryStage.setHeight(400);
        primaryStage.setResizable(false);                                             //blokada zmiany rozmiaru
        //primaryStage.setFullScreen(true);                                           //pełny ekran

        //primaryStage.setX(0);                                                       //ustalenie pozycji pojawienia się okna (0,0 to lewy górny róg ekranu)
        //primaryStage.setY(0);

        //primaryStage.initStyle(StageStyle.DECORATED);                               //ustalenie stylu okna
        primaryStage.setOpacity(0.99);                                                //ustalenie przezroczystosci okna (zakres 0-1 typu double. 1 - nieprzezroczyste)

        primaryStage.show();
    }


    public static void main(String[] args){
        launch(args);
    }
}
