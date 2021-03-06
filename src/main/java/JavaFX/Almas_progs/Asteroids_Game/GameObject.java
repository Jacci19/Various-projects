package JavaFX.Almas_progs.Asteroids_Game;

import javafx.geometry.Point2D;
import javafx.scene.Node;

/**   https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/asteroids/GameObject.java                     */

public class GameObject {

    private Node view;
    private Point2D velocity = new Point2D(0, 0);
    private double speed = 0.8;
    private final double MAX_SPEED = 10.0;
    private final double MIN_SPEED = 0.4;
    private final double TURN_ANGLE = 8;

    private boolean alive = true;

    public GameObject(Node view) {
        this.view = view;
    }

    public void update() {
        view.setTranslateX(view.getTranslateX() + speed * velocity.getX());
        view.setTranslateY(view.getTranslateY() + speed * velocity.getY());
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }
    public Point2D getVelocity() {
        return velocity;
    }

    public Node getView() {
        return view;
    }

    public boolean isAlive() {
        return alive;
    }
    public boolean isDead() {
        return !alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public double getRotate() {
        return view.getRotate();
    }

    public void rotateRight() {
        view.setRotate(view.getRotate() + TURN_ANGLE);
        setVelocity(new Point2D( Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));         //aby pojazd zieniał kierunek ruchu po obrocie
    }

    public void rotateLeft() {
        view.setRotate(view.getRotate() - TURN_ANGLE);
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));         //aby pojazd zieniał kierunek ruchu po obrocie
    }

    public boolean isColliding(GameObject other) {
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }

    public void accelerate() {                                                                                              //MOJE
        if (speed < MAX_SPEED){
            this.speed += 0.2;
        }
    }

    public void brake() {                                                                                                   //MOJE
        if (speed > MIN_SPEED) {
            this.speed -= 0.4;
        }
    }
}
