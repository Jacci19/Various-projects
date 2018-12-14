package JavaFX.SpaceRunner.View;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameViewManager {

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    public GameViewManager() {                                  //konstruktor
        initializeStage();
        createKeyListeners();
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    private void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT){

                } else if (event.getCode() == KeyCode.RIGHT){

                }
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT){

                } else if (event.getCode() == KeyCode.RIGHT){

                }
            }
        });
    }

}
