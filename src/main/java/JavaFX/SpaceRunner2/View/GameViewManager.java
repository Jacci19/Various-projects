package JavaFX.SpaceRunner2.View;

import JavaFX.SpaceRunner2.Model.SHIP;
import JavaFX.SpaceRunner2.Model.SmallInfoLabel;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

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

    private GridPane gridPane1;                                 //umieścimy w nim tło gry
    private GridPane gridPane2;
    private static final String BACKGROUND_IMAGE = "JavaFX/SpaceRunner/blueBackground.png";

    private static final String METEOR_BROWN_IMAGE = "JavaFX/SpaceRunner/meteor_brown.png";
    private static final String METEOR_GREY_IMAGE = "JavaFX/SpaceRunner/meteor_gray.png";
    private ImageView[] brownMeteors;
    private ImageView[] greyMeteors;
    Random randomPositionGenerator;

    private ImageView star;
    private SmallInfoLabel pointsLabel;
    private ImageView[] playerLifes;
    private int playerLife;
    private int points;

    private final static String GOLD_STAR_IMAGE = "JavaFX/SpaceRunner/gold_star.png";

    private final static int STAR_RADIUS = 12;
    private final static int SHIP_RADIUS = 27;
    private final static int METEOR_RADIUS = 20;



    public GameViewManager() {                                  //konstruktor
        initializeStage();
        createKeyListeners();
        randomPositionGenerator = new Random();
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setResizable(false);

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
        createBackground();
        createShip(chosenShip);
        createGameElements(chosenShip);                                                       //tworzy meteoryty
        createGameLoop();
        gameStage.show();
    }

    private void createGameElements(SHIP chosenShip){

        star = new ImageView(GOLD_STAR_IMAGE);
        setNewElementPosition(star);
        gamePane.getChildren().add(star);

        pointsLabel = new SmallInfoLabel("POINTS: 00");
        pointsLabel.setLayoutX(460);
        pointsLabel.setLayoutY(20);
        gamePane.getChildren().add(pointsLabel);

        playerLife = 2;
        playerLifes = new ImageView[3];

        for (int i = 0; i<playerLifes.length; i++){
            playerLifes[i] = new ImageView(chosenShip.getShipLifeUrl());
            playerLifes[i].setLayoutX(455 + (i * 50));
            playerLifes[i].setLayoutY(80);
            gamePane.getChildren().add(playerLifes[i]);
        }

        brownMeteors = new ImageView[3];
        for (int i = 0; i < brownMeteors.length; i++){
            brownMeteors[i] = new ImageView(METEOR_BROWN_IMAGE);
            setNewElementPosition(brownMeteors[i]);
            gamePane.getChildren().add(brownMeteors[i]);
        }
        greyMeteors = new ImageView[3];
        for (int i = 0; i < greyMeteors.length; i++){
            greyMeteors[i] = new ImageView(METEOR_GREY_IMAGE);
            setNewElementPosition(greyMeteors[i]);
            gamePane.getChildren().add(greyMeteors[i]);
        }
    }

    private void moveGameElements(){
        star.setLayoutY(star.getLayoutY() + 5);

        for (int i = 0; i < brownMeteors.length; i++){
            brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY()+7);
            brownMeteors[i].setRotate(brownMeteors[i].getRotate()+4);
        }
        for (int i = 0; i < greyMeteors.length; i++){
            greyMeteors[i].setLayoutY(greyMeteors[i].getLayoutY()+7);
            greyMeteors[i].setRotate(greyMeteors[i].getRotate()+4);
        }
    }

    private void checkIfElementsAreBehindTheShipAndRelocate(){
        if(star.getLayoutY() > 1200){
            setNewElementPosition(star);
        }

        for (int i = 0; i < brownMeteors.length; i++){
            if (brownMeteors[i].getLayoutY() > 900){
                setNewElementPosition(brownMeteors[i]);
            }
        }
        for (int i = 0; i < greyMeteors.length; i++){
            if (greyMeteors[i].getLayoutY() > 900){
                setNewElementPosition(greyMeteors[i]);
            }
        }
    }

    private void setNewElementPosition(ImageView imageView){
        imageView.setLayoutX(randomPositionGenerator.nextInt(370));
        imageView.setLayoutY(-(randomPositionGenerator.nextInt(3200)+600));

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
                moveBackGround();
                moveGameElements();
                checkIfElementsAreBehindTheShipAndRelocate();
                checkIfElementsCollide();
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

    private void createBackground(){
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i=0; i < 12; i++){                                                         //ten gridPane ma 12 pól
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroundImage1, i%3, i/3);        //ustawienie ograniczeń
            GridPane.setConstraints(backgroundImage2, i%3, i/3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);

        }
        gridPane2.setLayoutY(-1024);
        gamePane.getChildren().addAll(gridPane1, gridPane2);
    }

    private void moveBackGround(){
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);                             //tło przesuwa się w każdej klatce timera
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);

        if (gridPane1.getLayoutY() >= 1024){                                            //gdy tło dołem wyjdzie poza stage to ustawia się na górze
            gridPane1.setLayoutY(-1024);
        }
        if (gridPane2.getLayoutY() >= 1024){
            gridPane2.setLayoutY(-1024);
        }
    }

    private void checkIfElementsCollide(){
        if(SHIP_RADIUS + STAR_RADIUS > calculateDistance(ship.getLayoutX()+49, star.getLayoutX()+15, ship.getLayoutY()+37, star.getLayoutY()+15)){
            setNewElementPosition(star);
            points++;
            String textToSet = "POINTS: ";
            if (points < 10){
                textToSet = textToSet + "0";
            }
            pointsLabel.setText(textToSet + points);
        }

        for (int i = 0; i < brownMeteors.length; i++){
            if(METEOR_RADIUS + SHIP_RADIUS > calculateDistance(ship.getLayoutX()+49, brownMeteors[i].getLayoutX()+20, ship.getLayoutY()+37, brownMeteors[i].getLayoutY()+20)){
                removeLife();
                setNewElementPosition(brownMeteors[i]);
            }
        }
        for (int i = 0; i < greyMeteors.length; i++){
            if(METEOR_RADIUS + SHIP_RADIUS > calculateDistance(ship.getLayoutX()+49, greyMeteors[i].getLayoutX()+20, ship.getLayoutY()+37, greyMeteors[i].getLayoutY()+20)){
                removeLife();
                setNewElementPosition(greyMeteors[i]);
            }
        }
    }

    private void removeLife(){
        gamePane.getChildren().remove(playerLifes[playerLife]);
        playerLife--;
        if (playerLife < 0){
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
}
