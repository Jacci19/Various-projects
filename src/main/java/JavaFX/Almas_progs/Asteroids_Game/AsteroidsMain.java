package JavaFX.Almas_progs.Asteroids_Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/** https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/asteroids/AsteroidsApp.java
 *  https://www.youtube.com/watch?v=l2XhUHW8Oa4             */

public class AsteroidsMain extends Application {

    private Pane root;

    private List<GameObject> bullets = new ArrayList<>();
    private List<GameObject> enemies = new ArrayList<>();

    private GameObject player;
    private final int stageWidth = 1600;
    private final int stageHeight = 1000;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                player.rotateLeft();
            } else if (e.getCode() == KeyCode.RIGHT) {
                player.rotateRight();
            } else if (e.getCode() == KeyCode.UP) {
                player.accelerate();
            } else if (e.getCode() == KeyCode.DOWN) {
                player.brake();
            } else if (e.getCode() == KeyCode.SPACE) {
                Bullet bullet = new Bullet();
                bullet.setVelocity(player.getVelocity().normalize().multiply(10));
                addBullet(bullet, player.getView().getTranslateX(), player.getView().getTranslateY());
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(stageWidth, stageHeight);           //Wymiary obszaru gry

        player = new Player();
        player.setVelocity(new Point2D(1, 0));              // x = 1 oznacza, że obiekt na starcie będzie się poruszał wzdłuż osi x w prawo
        addGameObject(player, 100, 300);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private void onUpdate() {
        for (GameObject bullet : bullets) {
            for (GameObject enemy : enemies) {
                if (bullet.isColliding(enemy)) {
                    bullet.setAlive(false);
                    enemy.setAlive(false);

                    root.getChildren().removeAll(bullet.getView(), enemy.getView());
                }
            }
        }
/*
        for (GameObject enemy : enemies) {                                                              //MOJE
            if (player.isColliding(enemy)) {
                player.setAlive(false);
                enemy.setAlive(false);

                root.getChildren().removeAll(player.getView(), enemy.getView());
            }
        }
*/


        bullets.removeIf(GameObject::isDead);                                                           //   ::  !!!
        enemies.removeIf(GameObject::isDead);                                                 //aby bullety nie znikały w miejscu gdzie zniknął enemy

        bullets.forEach(GameObject::update);
        enemies.forEach(GameObject::update);

        player.update();

        if (Math.random() < 0.04) {                                                         //im większa liczba tym więcej wrogów
            addEnemy(new Enemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        }

        blockStageBorders();
    }

    private void blockStageBorders() {
        if (player.getView().getTranslateX() > stageWidth - 40){
            player.getView().setTranslateX(stageWidth-45);
        }
        if (player.getView().getTranslateY() > stageHeight-20){
            player.getView().setTranslateY(stageHeight-25);
        }
        if (player.getView().getTranslateX() < 0){
            player.getView().setTranslateX(0 + 5);
        }
        if (player.getView().getTranslateY() < 10){
            player.getView().setTranslateY(10 + 5);
        }
    }

    private void addBullet(GameObject bullet, double x, double y) {
        bullets.add(bullet);
        addGameObject(bullet, x, y);
    }

    private void addEnemy(GameObject enemy, double x, double y) {
        enemies.add(enemy);
        addGameObject(enemy, x, y);
    }

    private void addGameObject(GameObject object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }

    private static class Player extends GameObject {
        Player() {
            super(new Rectangle(40, 20, Color.BLUE));
        }
    }

    private static class Enemy extends GameObject {
        Enemy() {
            super(new Circle(15, 15, 15, Color.RED));
        }
    }

    private static class Bullet extends GameObject {
        Bullet() {
            super(new Circle(5, 5, 5, Color.BROWN));
        }
    }

}