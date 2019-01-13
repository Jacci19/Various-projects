package JavaFX.Izak;

/**
 * Cwiczenie na podstawie gry The binding of Isaac. Sterowanie: WSAD, strzałki.
 * https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
 * https://stackoverflow.com/questions/30146560/how-to-change-animationtimer-speed
 * https://stackoverflow.com/questions/28242260/rectangle-wall-collision-in-java
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class IzakMain extends Application {

    private Pane root = new Pane();
    private Izak izak;
    private Wall wall;
    private Bullet bullet;
    private List<Bullet> bulletsList = new ArrayList<>();
    private List<Wall> wallsList = new ArrayList<>();

    //private String collisionSide = "";
    private double diagRatio = 0.8;
    private int shotFrequencyCounter = 0;
    private final int STAGE_WIDTH = 1600;
    private final int STAGE_HEIGHT = 1000;
    private double izakCenterX;
    private double izakCenterY;
    private double prevIzakPosX, prevIzakPosY;

    private Rectangle izakRectangle;


    public boolean up = false, down = false, left = false, right = false, shot = false, shotUp = false, shotDown = false, shotLeft = false, shotRight = false, lCtrlPress = false;

    Boolean half = true;

    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                detectKeysPress(event);                                                                     //Setting booleans variables on press (up, down, ..., shotUp....)
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                detectKeysRelease(event);                                                                   //Setting booleans variables on release (up, down, ..., shotUp....)
            }
        });
        stage.setTitle("Izak (by Jacek)");
        stage.setScene(scene);
        stage.show();

        AnimationTimerExt timer = new AnimationTimerExt((int) (100 / (0.5 * izak.getSpeed()))) {
            int index = 0;

            @Override
            public void handle() {                                                                                           //wykonywane co ramke timera

                setIzakMovingAndShootingStates();
                int dx = 0, dy = 0;
                if (up) {                                                                                                      //gdy izak idzie
                    dy = calculateDy(-1);
                    loadIzakBodyOrHeadImage(izak.getBodyBackList(), "HeadBack", index);
                }
                if (down) {
                    dy = calculateDy(1);
                    loadIzakBodyOrHeadImage(izak.getBodyFrontList(), "HeadFront", index);
                }
                if (left) {
                    dx = calculateDx(-1);
                    loadIzakBodyOrHeadImage(izak.getBodyLeftList(), "HeadLeft", index);
                }
                if (right) {
                    dx = calculateDx(1);
                    loadIzakBodyOrHeadImage(izak.getBodyRightList(), "HeadRight", index);
                }
                if (lCtrlPress) {                                                                                           //usunięcie widocznych pocisków
                    for (Bullet bullet : bulletsList) {
                        root.getChildren().remove(bullet);
                    }
                    bulletsList.clear();
                }
                if (izak.getShooting()) {                                                                                      //gdy izak strzela
                    izakShoot();
                }
                loadIzakHeadAndBodyImagesDuringStanding();
                moveIzakAndCheckCollisionsVsWalls(dx, dy);
                bulletMoving();
                index = incrementIndex(index);
            }
        };
        timer.start();
    }



    private void detectKeysPress(KeyEvent event) {
        switch (event.getCode()) {
            case W:                                                                                             //ruch
                up = true;
                izak.setPosition(Position.BACK);
                break;
            case S:
                down = true;
                izak.setPosition(Position.FRONT);
                break;
            case A:
                left = true;
                izak.setPosition(Position.LEFT);
                break;
            case D:
                right = true;
                izak.setPosition(Position.RIGHT);
                break;
            case UP:                                                                                            //strzelanie
                shotUp = true;
                izak.setPosition(Position.BACK);
                break;
            case DOWN:
                shotDown = true;
                izak.setPosition(Position.FRONT);
                break;
            case LEFT:
                shotLeft = true;
                izak.setPosition(Position.LEFT);
                break;
            case RIGHT:
                shotRight = true;
                izak.setPosition(Position.RIGHT);
                break;
            case CONTROL:
                lCtrlPress = true;
                break;
        }
    }

    private void detectKeysRelease(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                up = false;
                break;
            case S:
                down = false;
                break;
            case A:
                left = false;
                break;
            case D:
                right = false;
                break;
            case UP:
                shotUp = false;
                break;
            case DOWN:
                shotDown = false;
                break;
            case LEFT:
                shotLeft = false;
                break;
            case RIGHT:
                shotRight = false;
                break;
            case CONTROL:
                lCtrlPress = false;
                break;
        }
    }

    private void loadIzakHeadAndBodyImagesDuringStanding() {
        if (!izak.getMoving()) {                                                                                      //gdy izak nie idzie
            switch (izak.getPosition()) {
                case BACK:
                    loadIzakBodyOrHeadImage(izak.getBodyBackList(), "HeadBack", 0);
                    break;
                case FRONT:
                    loadIzakBodyOrHeadImage(izak.getBodyFrontList(), "HeadFront", 0);
                    break;
                case LEFT:
                    loadIzakBodyOrHeadImage(izak.getBodyLeftList(), "HeadLeft", 0);
                    break;
                case RIGHT:
                    loadIzakBodyOrHeadImage(izak.getBodyRightList(), "HeadRight", 0);
                    break;
            }
        }
    }

    private void moveIzakAndCheckCollisionsVsWalls(int dx, int dy){
        izakRectangle = new Rectangle(izak.getLayoutX() + dx, izak.getLayoutY() + dy, izak.getBoundsInLocal().getWidth(), izak.getBoundsInLocal().getHeight());
        for (Wall wall : wallsList){
            if (izakRectangle.intersects(wall.getBoundsInParent())) {
                izak.setColliding(true);
                break;                                                                          //jeśli koliduje choć z jednym blokiem to nie może się ruszyć w tej ramce
            } else {
                izak.setColliding(false);
            }
        }
        if (!izak.getColliding()) {
            moveHeroBy(dx, dy);                                                                                     //zmiana położenia izaka
        }
    }


    private void setIzakMovingAndShootingStates() {

        if (up || down || left || right) {
            izak.setMoving(true);
        } else {
            izak.setMoving(false);
        }
        if (shotUp || shotDown || shotLeft || shotRight) {
            izak.setShooting(true);
        } else {
            izak.setShooting(false);
            clearBulletList();
        }
    }

    private void clearBulletList() {
        if (!root.getChildren().contains(bullet)) {                                                               //jeśli na plansze nie ma pocisków to wyzeruj tablice pocisków
            bulletsList.clear();
        }
    }


    private int calculateDx(int sign) {
        double dX = 0;
        if (up || down) {                                                                                        //aby ruch na ukos nie był szybszy od innych
            dX += sign * izak.getSpeed() * diagRatio;
        } else {
            dX += sign * izak.getSpeed();
        }
        return (int) dX;
    }

    private int calculateDy(int sign) {
        double dY = 0;
        if (left || right) {                                                                                     //aby ruch na ukos nie był szybszy od innych
            dY += sign * izak.getSpeed() * diagRatio;
        } else {
            dY += sign * izak.getSpeed();
        }
        return (int) dY;
    }

    private int incrementIndex(int index) {

        final int animationFramesNumber = 9;
        if (index < animationFramesNumber && half) {
            index++;
        }
        if (index >= animationFramesNumber) {
            index = 0;
        }
        half = !half;                                                                                                   //aby animacja działała 2x wolniej
        return index;
    }

    private void loadIzakBodyOrHeadImage(ArrayList<String> bodyList, String nonShotImg, int idx) {
        if (izak.getMoving())                                                                                            //gdy izak chodzi
            izak.loadIzakBodyImage(bodyList.get(idx));

        if (izak.getShooting()) {                                                                                         //gdy izak strzela
            izakShoot();
        } else {                                                                                                            // gdy izak chodzi i nie strzela
            izak.loadIzakHeadImage(nonShotImg);
        }
    }

    private void izakShoot() {
        if (shotFrequencyCounter == 0) {
            bullet = new Bullet();

            if (shotUp) setBulletInitialConditions(14, -16, Position.BACK, "HeadBackShot");
            else if (shotDown) setBulletInitialConditions(14, 10, Position.FRONT, "HeadFrontShot");
            else if (shotRight) setBulletInitialConditions(16, 12, Position.RIGHT, "HeadRightShot");
            else if (shotLeft) setBulletInitialConditions(15, 12, Position.LEFT, "HeadLeftShot");

            root.getChildren().add(bullet);
            bulletsList.add(bullet);
            shotFrequencyCounter = izak.getShootFrequency();
        }
        shotFrequencyCounter--;
    }

    private void setBulletInitialConditions(int X, int Y, Position position, String headImageName) {
        bullet.setLayoutX(izak.getLayoutX() + X);
        bullet.setLayoutY(izak.getLayoutY() + Y);
        bullet.setDirection(position);
        izak.loadIzakHeadImage(headImageName);
    }

    private void bulletMoving() {
        for (Bullet bullet : bulletsList) {

            switch (bullet.getDirection()) {
                case BACK:
                    bullet.setLayoutY(bullet.getLayoutY() - izak.getShotSpeed());
                    if (bullet.getLayoutY() < (izak.getLayoutY() - izak.getShotRange()))
                        root.getChildren().remove(bullet);         // usunięcie pocisku po osiągnięciu jego zasięgu
                    break;

                case FRONT:
                    bullet.setLayoutY(bullet.getLayoutY() + izak.getShotSpeed());
                    if (bullet.getLayoutY() > (izak.getLayoutY() + izak.getShotRange()))
                        root.getChildren().remove(bullet);
                    break;

                case LEFT:
                    bullet.setLayoutX(bullet.getLayoutX() - izak.getShotSpeed());
                    if (bullet.getLayoutX() < (izak.getLayoutX() - izak.getShotRange()))
                        root.getChildren().remove(bullet);
                    break;

                case RIGHT:
                    bullet.setLayoutX(bullet.getLayoutX() + izak.getShotSpeed());
                    if (bullet.getLayoutX() > (izak.getLayoutX() + izak.getShotRange()))
                        root.getChildren().remove(bullet);
                    break;
            }

            if (bullet.getLayoutX() > (izak.getLayoutX() + izak.getShotRange())) {
                root.getChildren().remove(bullet);
            }
            //System.out.println("bulletsList.size(): " + bulletsList.size());
        }
    }

    private void moveHeroBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;
        double x = izakCenterX + izak.getLayoutX() + dx;
        double y = izakCenterY + izak.getLayoutY() + dy;
        moveHeroTo(x, y);
    }

    private void moveHeroTo(double x, double y) {
        if ((x - izakCenterX >= 0) && (x + izakCenterX <= STAGE_WIDTH) && (y - izakCenterY >= 0) && (y + izakCenterY <= STAGE_HEIGHT)) {
            izak.relocate(x - izakCenterX, y - izakCenterY);
        }
    }

    private Parent createContent() {
        root.setPrefSize(STAGE_WIDTH, STAGE_HEIGHT);
        root.setStyle("-fx-background-color: #222;");
        izak = new Izak();
        izak.setLayoutX(STAGE_WIDTH / 2 - izak.getBoundsInLocal().getWidth() / 2);                                      //aby stał na środku na początku gry
        izak.setLayoutY(STAGE_HEIGHT / 2 - izak.getBoundsInLocal().getHeight() / 2);
        root.getChildren().add(izak);
        izakCenterX = izak.getBoundsInLocal().getWidth() / 2;
        izakCenterY = izak.getBoundsInLocal().getHeight() / 2;
        makeWalls();


        return root;
    }

    private void makeWalls() {
        placeWallHorizontal(20, 0, -40);
        placeWallVertical(12, 1560, 40);
        placeWallHorizontal(20, 0, 960);
        placeWallVertical(12, -40, 40);

        placeWallVertical(4, 760, 40);



    }

    private void placeWallHorizontal(int quantity, int startX, int y){
        for(int i =0; i < quantity; i++){
            wall = new Wall();
            wall.setLayoutX(startX + (i * wall.getBoundsInParent().getWidth()));
            wall.setLayoutY(y);
            wallsList.add(wall);
            root.getChildren().add(wall);
        }
    }
    private void placeWallVertical(int quantity, int x, int startY){
        for(int i =0; i < quantity; i++){
            wall = new Wall();
            wall.setLayoutX(x);
            wall.setLayoutY(startY + (i * wall.getBoundsInParent().getHeight()));
            wallsList.add(wall);
            root.getChildren().add(wall);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}









/*
        AnimationTimer timer = new AnimationTimer() {

            String bodyFileName, headFileName;
            int index = 0;
            private long lastUpdate = 0 ;
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;
                if (now - lastUpdate >= (200000000 / izak.getSpeed())) {
                    if (up) {
                        dy -= izak.getSpeed();
                        izak.loadIzakImages(izak.getBodyBackList().get(index), "HeadBack");
                    }
                    if (down) {
                        dy += izak.getSpeed();
                        izak.loadIzakImages(izak.getBodyFrontList().get(index), "HeadFront");
                    }
                    if (left) {
                        dx -= izak.getSpeed();
                        izak.loadIzakImages(izak.getBodyLeftList().get(index), "HeadLeft");
                    }
                    if (right) {
                        dx += izak.getSpeed();
                        izak.loadIzakImages(izak.getBodyRightList().get(index), "HeadRight");
                    }

                    moveHeroBy(dx, dy);
                    index = incrementIndex(index);
                    lastUpdate = now;
                }
            }
        };
        timer.start();
*/
/*
            if ((izak.getLayoutX() < wall.getLayoutX()) && (izak.getPosition() == Position.RIGHT)) collisionSide = "LEFT";
            if ((izak.getLayoutY() < wall.getLayoutY()) && (izak.getPosition() == Position.FRONT)) collisionSide = "UP";
            if ((izak.getLayoutX() > wall.getLayoutX()) && (izak.getPosition() == Position.LEFT)) collisionSide = "RIGHT";
            if ((izak.getLayoutY() > wall.getLayoutY()) && (izak.getPosition() == Position.BACK)) collisionSide = "DOWN";
*/

//aby łzy opadały pod koniec lotu
//                    if (bullet.getLayoutX() < 0.9 * (izak.getLayoutX() + izak.getShotRange())){
//                        bullet.setLayoutX(bullet.getLayoutX() + izak.getShotSpeed());
//                    }
//                    else if((bullet.getLayoutX() >= 0.9 * (izak.getLayoutX() + izak.getShotRange())) && (bullet.getLayoutX() < (izak.getLayoutX() + izak.getShotRange()))){
//                        bullet.setLayoutX(bullet.getLayoutX() + izak.getShotSpeed());
//                        bullet.setLayoutY(bullet.getLayoutY() + 0.35 * izak.getShotSpeed());        //aby łzy opadały pod koniec lotu
//                    }
//                    else{
//                        root.getChildren().remove(bullet);
//                    }
