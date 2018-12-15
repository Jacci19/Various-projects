package JavaFX.SpaceRunner.View;

import JavaFX.SpaceRunner.Model.SHIP;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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
    private Stage menuStage;
    private ImageView ship;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;

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
                if (event.getCode() == KeyCode.LEFT){                                           //LEFT press
                    isLeftKeyPressed = true;
                } else if (event.getCode() == KeyCode.RIGHT){                                   //RIGHT press
                    isRightKeyPressed = true;
                }
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT){                                           //LEFT release
                    isLeftKeyPressed = false;
                } else if (event.getCode() == KeyCode.RIGHT){                                   //RIGHT release
                    isRightKeyPressed = false;
                }
            }
        });
    }

    public void createNewGame(Stage menuStage, SHIP chosenShip){                    //metoda wywoływana z klasy ViewManager po naciśnięciu START
        this.menuStage = menuStage;
        this.menuStage.hide();
        createShip(chosenShip);
        createGameLoop();
        gameStage.show();
    }

    private void createShip(SHIP chosenShip){
        ship = new ImageView(chosenShip.getShipUrl());
        ship.setLayoutX(GAME_WIDTH / 2);
        ship.setLayoutY(GAME_HEIGHT - 90);
        gamePane.getChildren().add(ship);
    }

    private void createGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveShip();
            }
        };
        gameTimer.start();
    }

    private void moveShip(){
        if (isLeftKeyPressed && !isRightKeyPressed){                                // LEFT on - RIGHT off
            if(angle > -30){
                angle -= 5;
            }
            ship.setRotate(angle);
            if(ship.getLayoutX() > -20){
                ship.setLayoutX(ship.getLayoutX() - 3);
            }
        }
        if (!isLeftKeyPressed && isRightKeyPressed){                                // LEFT off - RIGHT on
            if(angle < 30){
                angle += 5;
            }
            ship.setRotate(angle);
            if(ship.getLayoutX() < 522){
                ship.setLayoutX(ship.getLayoutX() + 3);
            }
        }
        if (!isLeftKeyPressed && !isRightKeyPressed){                               // LEFT off - RIGHT off
            if(angle < 0){
                angle += 5;
            }else if (angle > 0){
                angle -=5;
            }
            ship.setRotate(angle);
        }
        if (isLeftKeyPressed && isRightKeyPressed){                                // LEFT on - RIGHT on
            if(angle < 0){
                angle += 5;
            }else if (angle > 0){
                angle -=5;
            }
            ship.setRotate(angle);
        }

    }
}
