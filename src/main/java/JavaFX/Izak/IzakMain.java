package JavaFX.Izak;

/**  Cwiczenie na podstawie gry The binding of Isaac. Sterowanie: WSAD, strzałki.
 * https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
 * https://stackoverflow.com/questions/30146560/how-to-change-animationtimer-speed
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class IzakMain extends Application {

    private Pane root;
    private Izak izak;
    private final int STAGE_WIDTH = 1600;
    private final int STAGE_HEIGHT = 1000;
    public boolean up = false, down = false, left = false, right = false, shot = false, shotUp = false, shotDown = false, shotLeft = false, shotRight = false;

    Boolean half = true;

    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
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
                    case UP:
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
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
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
                }
            }
        });

        stage.setTitle("Izak (by Jacek)");
        stage.setScene(scene);
        stage.show();

        AnimationTimerExt timer = new AnimationTimerExt((int)(100 / (0.6 * izak.getSpeed()))) {
            int index = 0;
            @Override
            public void handle() {
                if (up || down || left || right) {
                    izak.setMoving(true);
                }
                else{
                    izak.setMoving(false);
                }
                if (shotUp || shotDown || shotLeft || shotRight) {
                    izak.setShooting(true);
                }
                else{
                    izak.setShooting(false);
                }

                int dx = 0, dy = 0;
                if (up) {                                                                                                      //gdy izak idzie
                    dy -= izak.getSpeed();
                    loadShotOrNonshotImage(izak.getBodyBackList(), "HeadBack", "HeadBackShot", index);
                }
                if (down) {
                    dy += izak.getSpeed();
                    loadShotOrNonshotImage(izak.getBodyFrontList(), "HeadFront", "HeadFrontShot", index);
                }
                if (left) {
                    dx -= izak.getSpeed();
                    loadShotOrNonshotImage(izak.getBodyLeftList(), "HeadLeft", "HeadLeftShot", index);
                }
                if (right) {
                    dx += izak.getSpeed();
                    loadShotOrNonshotImage(izak.getBodyRightList(), "HeadRight", "HeadRightShot", index);
                }



                if (!izak.getMoving()) {                                                                                      //gdy izak nie idzie
                    switch (izak.getPosition()) {
                        case BACK:
                            loadShotOrNonshotImage(izak.getBodyBackList(), "HeadBack", "HeadBackShot", 0);
                            break;
                        case FRONT:
                            loadShotOrNonshotImage(izak.getBodyFrontList(), "HeadFront", "HeadFrontShot", 0);
                            break;
                        case LEFT:
                            loadShotOrNonshotImage(izak.getBodyLeftList(), "HeadLeft", "HeadLeftShot", 0);
                            break;
                        case RIGHT:
                            loadShotOrNonshotImage(izak.getBodyRightList(), "HeadRight", "HeadRightShot", 0);
                            break;
                    }

                }
                moveHeroBy(dx, dy);
                index = incrementIndex(index);
            }
        };
        timer.start();
    }

    private int incrementIndex(int index) {

        if (index < 9 && half) {
            index++;
        }if (index >= 9){
            index = 0;
        }
        half = !half;                                   //aby animacja działała 2x wolniej
        return index;
    }

    private void loadShotOrNonshotImage(ArrayList<String> bodyList, String nonShotImg, String shotImg, int idx){
        if (izak.getShooting()){                                                                                         //gdy izak strzela
            izak.LoadIzakImages(bodyList.get(idx), shotImg);
            System.out.println("Izak strzela");
        }
        else{                                                                                                           //gdy izak nie strzela
            izak.LoadIzakImages(bodyList.get(idx), nonShotImg);
        }
    }

    private void moveHeroBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = izak.getBoundsInLocal().getWidth() / 2;
        final double cy = izak.getBoundsInLocal().getHeight() / 2;

        double x = cx + izak.getLayoutX() + dx;
        double y = cy + izak.getLayoutY() + dy;

        moveHeroTo(x, y);
    }

    private void moveHeroTo(double x, double y) {
        final double cx = izak.getBoundsInLocal().getWidth() / 2;
        final double cy = izak.getBoundsInLocal().getHeight() / 2;

        if ((x - cx >= 0) && (x + cx <= STAGE_WIDTH) && (y - cy >= 0) && (y + cy <= STAGE_HEIGHT)) {
            izak.relocate(x - cx, y - cy);
        }
    }


    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(STAGE_WIDTH, STAGE_HEIGHT);
        root.setStyle("-fx-background-color: #222;");
        izak = new Izak();
        izak.setLayoutX(750);                                                                   //aby stał na środku
        izak.setLayoutY(450);
        root.getChildren().add(izak);

        return root;
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
                        izak.LoadIzakImages(izak.getBodyBackList().get(index), "HeadBack");
                    }
                    if (down) {
                        dy += izak.getSpeed();
                        izak.LoadIzakImages(izak.getBodyFrontList().get(index), "HeadFront");
                    }
                    if (left) {
                        dx -= izak.getSpeed();
                        izak.LoadIzakImages(izak.getBodyLeftList().get(index), "HeadLeft");
                    }
                    if (right) {
                        dx += izak.getSpeed();
                        izak.LoadIzakImages(izak.getBodyRightList().get(index), "HeadRight");
                    }

                    moveHeroBy(dx, dy);
                    index = incrementIndex(index);
                    lastUpdate = now;
                }
            }
        };
        timer.start();
*/