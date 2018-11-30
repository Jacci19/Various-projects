package JavaFX.Izak;

/**
 * https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
 * https://stackoverflow.com/questions/30146560/how-to-change-animationtimer-speed
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class IzakMain extends Application {

    private Pane root;
    private Izak izak;
    private final int STAGE_WIDTH = 1600;
    private final int STAGE_HEIGHT = 1000;
    public boolean up = false, down = false, left = false, right = false, shot = false;

    Boolean half = true;

    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        up = true;
                        break;
                    case S:
                        down = true;
                        break;
                    case A:
                        left = true;
                        break;
                    case D:
                        right = true;
                        break;
                    case SPACE:
                        shot = true;
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
                    case SPACE:
                        shot = false;
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

                int dx = 0, dy = 0;
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
        root.setStyle("-fx-background-color: #333333;");
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