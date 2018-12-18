package JavaFX.SpaceRunner2.View;

import JavaFX.SpaceRunner2.Model.Bullet;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameViewManager {

    private static final int GAME_WIDTH = 1600;
    private static final int GAME_HEIGHT = 1060;

    private static final int METEOR_FREQ = 15;
    private static final int METEOR_SPEED = 6;
    private static final int STAR_SPEED = 5;

    private static final double BACKGROUND_SPEED = 2.0;



    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private ImageView shipIV;
    private SHIP myShip;

    private boolean isLeftKeyPressed, isRightKeyPressed, isUpKeyPressed, isDownKeyPressed, isSpaceKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;

    private GridPane gridPane1;                                 //umieścimy w nim tło gry
    private GridPane gridPane2;
    private static final String BACKGROUND_IMAGE = "JavaFX/SpaceRunner2/blueBackground.png";

    private static final String METEOR_BROWN_IMAGE = "JavaFX/SpaceRunner2/meteor_brown.png";
    private static final String METEOR_GREY_IMAGE = "JavaFX/SpaceRunner2/meteor_gray.png";
    private ImageView[] brownMeteors;
    private ImageView[] greyMeteors;
    private Random randomPositionGenerator;

    private ImageView star;
    private SmallInfoLabel pointsLabel;
    private ImageView[] playerLifes;
    private int playerLife;
    private int points;
    private int shotFrequencyCounter = 0;
    private List<Bullet> bulletsList = new ArrayList<>();


    private final static String GOLD_STAR_IMAGE = "JavaFX/SpaceRunner2/gold_star.png";

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
                } else if (event.getCode() == KeyCode.UP){                                      //UP press
                    isUpKeyPressed = true;
                } else if (event.getCode() == KeyCode.DOWN){                                   //DOWN press
                    isDownKeyPressed = true;
                } else if (event.getCode() == KeyCode.SPACE){                                   //SPACE press
                    isSpaceKeyPressed = true;
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
                } else if (event.getCode() == KeyCode.UP){                                      //UP press
                    isUpKeyPressed = false;
                } else if (event.getCode() == KeyCode.DOWN){                                   //DOWN press
                    isDownKeyPressed = false;
                } else if (event.getCode() == KeyCode.SPACE) {                                   //DOWN press
                    isSpaceKeyPressed = false;
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

        star = new ImageView(GOLD_STAR_IMAGE);                                                  //star made
        setNewElementPosition(star);
        gamePane.getChildren().add(star);

        pointsLabel = new SmallInfoLabel("POINTS: 00");                                   //score field made
        pointsLabel.setLayoutX(GAME_WIDTH - 150);
        pointsLabel.setLayoutY(20);
        gamePane.getChildren().add(pointsLabel);

        playerLife = 2;
        playerLifes = new ImageView[3];

        for (int i = 0; i<playerLifes.length; i++){
            playerLifes[i] = new ImageView(chosenShip.getShipLifeUrl());
            playerLifes[i].setLayoutX(GAME_WIDTH - 150 + (i * 50));
            playerLifes[i].setLayoutY(80);
            gamePane.getChildren().add(playerLifes[i]);                                         //life icons made
        }

        brownMeteors = new ImageView[METEOR_FREQ];
        for (int i = 0; i < brownMeteors.length; i++){
            brownMeteors[i] = new ImageView(METEOR_BROWN_IMAGE);
            setNewElementPosition(brownMeteors[i]);
            brownMeteors[i].setScaleX(random(2.0, 1));
            brownMeteors[i].setScaleY(random(2.0, 1));
            gamePane.getChildren().add(brownMeteors[i]);                                        //brown meteors made
        }
        greyMeteors = new ImageView[METEOR_FREQ];
        for (int i = 0; i < greyMeteors.length; i++){
            greyMeteors[i] = new ImageView(METEOR_GREY_IMAGE);
            setNewElementPosition(greyMeteors[i]);
            greyMeteors[i].setScaleX(random(2.0, 1));
            greyMeteors[i].setScaleY(random(2.0, 1));
            gamePane.getChildren().add(greyMeteors[i]);                                         //grey meteors made
        }
    }

    private void moveGameElements(){
        star.setLayoutY(star.getLayoutY() + STAR_SPEED);

        for (int i = 0; i < brownMeteors.length; i++){                                          //brown meteors move
            brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY() + METEOR_SPEED + (i/2));    //i/2 zapewnia zmienną prędkość meteorów
            brownMeteors[i].setRotate(brownMeteors[i].getRotate()+4);
        }
        for (int i = 0; i < greyMeteors.length; i++){                                           //grey meteors move
            greyMeteors[i].setLayoutY(greyMeteors[i].getLayoutY() + METEOR_SPEED + (i/2));
            greyMeteors[i].setRotate(greyMeteors[i].getRotate()+4);
        }
    }

    private void checkIfElementsAreBehindTheShipAndRelocate(){
        if(star.getLayoutY() > GAME_HEIGHT + 100){
            setNewElementPosition(star);
        }

        for (int i = 0; i < brownMeteors.length; i++){
            if (brownMeteors[i].getLayoutY() > GAME_HEIGHT + 100){
                setNewElementPosition(brownMeteors[i]);
            }
        }
        for (int i = 0; i < greyMeteors.length; i++){
            if (greyMeteors[i].getLayoutY() > GAME_HEIGHT + 100){
                setNewElementPosition(greyMeteors[i]);
            }
        }
    }

    private void setNewElementPosition(ImageView imageView){
        imageView.setLayoutX(randomPositionGenerator.nextInt(GAME_WIDTH - 40));
        imageView.setLayoutY(-(randomPositionGenerator.nextInt(GAME_HEIGHT * 3)));

    }

    private void createShip(SHIP chosenShip){
        shipIV = new ImageView(chosenShip.getShipUrl());
        shipIV.setLayoutX(GAME_WIDTH / 2);
        shipIV.setLayoutY(GAME_HEIGHT - 90);
        myShip = chosenShip;
        gamePane.getChildren().add(shipIV);
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
                shooting();
                bulletMoving();
            }
        };
        gameTimer.start();
    }

    private void moveShip(){
        if (isUpKeyPressed && !isDownKeyPressed){                                // UP on - DOWN off
            if(shipIV.getLayoutY() > 0){
                shipIV.setLayoutY(shipIV.getLayoutY() - myShip.getShipSpeed());
            }
        }
        if (!isUpKeyPressed && isDownKeyPressed){                                // UP off - DOWN on
            if(shipIV.getLayoutY() < GAME_HEIGHT - 74){
                shipIV.setLayoutY(shipIV.getLayoutY() + myShip.getShipSpeed());
            }
        }
        if (isLeftKeyPressed && !isRightKeyPressed){                                // LEFT on - RIGHT off
            if(angle > -30){
                angle -= 5;
            }
            shipIV.setRotate(angle);
            if(shipIV.getLayoutX() > -20){
                shipIV.setLayoutX(shipIV.getLayoutX() - myShip.getShipSpeed());
            }
        }
        if (!isLeftKeyPressed && isRightKeyPressed){                                // LEFT off - RIGHT on
            if(angle < 30){
                angle += 5;
            }
            shipIV.setRotate(angle);
            if(shipIV.getLayoutX() < GAME_WIDTH - 60){
                shipIV.setLayoutX(shipIV.getLayoutX() + myShip.getShipSpeed());
            }
        }
        if ((!isLeftKeyPressed && !isRightKeyPressed) || (isLeftKeyPressed && isRightKeyPressed)){        // LEFT off - RIGHT off        // LEFT on - RIGHT on
            if(angle < 0){
                angle += 5;
            }else if (angle > 0){
                angle -=5;
            }
            shipIV.setRotate(angle);
        }
    }

    private void shooting(){
        if (isSpaceKeyPressed){
            if (shotFrequencyCounter == 0) {
                Bullet bullet = new Bullet();

                bullet.setLayoutX(shipIV.getLayoutX() + 48);                                  //48 aby strzelał ze środka dzioba
                bullet.setLayoutY(shipIV.getLayoutY());

                gamePane.getChildren().add(bullet);
                bulletsList.add(bullet);
                shotFrequencyCounter = myShip.getShootFrequency();
            }
            shotFrequencyCounter--;
        }
    }

    private void bulletMoving() {
        for (Bullet bullet: bulletsList) {
            bullet.setLayoutY(bullet.getLayoutY() - myShip.getShotSpeed());

            if (bullet.getLayoutY() < (shipIV.getLayoutY() - myShip.getShotRange())) {
                gamePane.getChildren().remove(bullet);
            }
        }
    }

    private void createBackground(){
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i=0; i < 49; i++){
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);                   //tekstura ma 256 x 256 pix
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroundImage1, i/7, i%5);        //ustawienie ograniczeń. 7 kolumn tekstury i 5 wierszy
            GridPane.setConstraints(backgroundImage2, i/7, i%5);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
            //System.out.println(i + ":  col_IDX: " + i/7 + "    row_IDX: " + i%5);
        }
        gridPane2.setLayoutY(-GAME_HEIGHT);
        gamePane.getChildren().addAll(gridPane1, gridPane2);
    }

    private void moveBackGround(){
        gridPane1.setLayoutY(gridPane1.getLayoutY() + BACKGROUND_SPEED);                             //tło przesuwa się w każdej klatce timera
        gridPane2.setLayoutY(gridPane2.getLayoutY() + BACKGROUND_SPEED);

        if (gridPane1.getLayoutY() >= GAME_HEIGHT){                                            //gdy tło dołem wyjdzie poza stage to ustawia się na górze
            gridPane1.setLayoutY(-GAME_HEIGHT);
        }
        if (gridPane2.getLayoutY() >= GAME_HEIGHT){
            gridPane2.setLayoutY(-GAME_HEIGHT);
        }
    }

    private void checkIfElementsCollide(){
        if(SHIP_RADIUS + STAR_RADIUS > calculateDistance(shipIV.getLayoutX()+49, star.getLayoutX()+15, shipIV.getLayoutY()+37, star.getLayoutY()+15)){
            setNewElementPosition(star);
            points++;
            String textToSet = "POINTS: ";
            if (points < 10){
                textToSet = textToSet + "0";
            }
            pointsLabel.setText(textToSet + points);
        }

        for (int i = 0; i < brownMeteors.length; i++){
            if(METEOR_RADIUS + SHIP_RADIUS > calculateDistance(shipIV.getLayoutX()+49, brownMeteors[i].getLayoutX()+20, shipIV.getLayoutY()+37, brownMeteors[i].getLayoutY()+20)){
                removeLife();
                setNewElementPosition(brownMeteors[i]);
            }
        }
        for (int i = 0; i < greyMeteors.length; i++){
            if(METEOR_RADIUS + SHIP_RADIUS > calculateDistance(shipIV.getLayoutX()+49, greyMeteors[i].getLayoutX()+20, shipIV.getLayoutY()+37, greyMeteors[i].getLayoutY()+20)){
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

    private int random(int data, int range) {                                       //losowanie liczb z zakresu (data +- range)

        int min = data - range;
        int max = data + range;
        Random rand = new Random();

        return rand.nextInt(max - min + 1) + min;
    }
    private double random(double data, double range){

        double min = data - range;
        double max = data + range;
        Random rand = new Random();

        return rand.nextDouble() * (max - min) + min;
    }
}