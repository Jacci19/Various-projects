package Card_games.BlackJack_FX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BJFX_Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("1");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Card_games/BlackJack_FX/FXML/BJ_mainScreen.fxml"));     //ścieżka względem położenia pliku main
        System.out.println("2");
        //StackPane myStackPane = loader.load();
        //Scene myScene = new Scene(myStackPane, 1200, 800);

        AnchorPane myAnchorPane = loader.load();
        System.out.println("3");

        Scene myScene = new Scene(myAnchorPane, 1200, 800);

        primaryStage.setTitle("Blacjack Card Game (by Jacek)");
        primaryStage.setScene(myScene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
