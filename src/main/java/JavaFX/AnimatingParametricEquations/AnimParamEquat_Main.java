package JavaFX.AnimatingParametricEquations;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.*;

/**  https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/parametric/DrawingApp.java
 https://www.youtube.com/watch?v=KvtqeYpvrnk&index=12&list=PL4h6ypqTi3RR_bhBk6PtLfD83YkaJXXxw
 */
public class AnimParamEquat_Main extends Application {

    private GraphicsContext g;

    private double t = 0.0;
    private double oldX = 800, oldY = 500;

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(1600, 1000);

        Canvas canvas = new Canvas(1600, 1000);
        g = canvas.getGraphicsContext2D();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                t += 0.25;                                                         //reguluje szybkość rysowania poprzez gęstość punktóe (kanciatość)
                if (t%2 == 0) draw(Color.RED);
                else draw(Color.BLUE);
                //draw(Color.RED);
            }
        };
        timer.start();

        root.getChildren().add(canvas);
        return root;
    }

    private void draw(Color color) {
        Point2D p = curveFunction();

        g.setStroke(color);

        double newX = 800 + p.getX();                                                       //regulacja połozenia wykresu x,y
        double newY = 500 + p.getY();

        if (oldX != 800 && oldY != 500)
            g.strokeLine(oldX, oldY, newX, newY);

        //g.strokeOval(newX, newY, 5, 5);                                                 //dodaje kółeczka na liniach

        oldX = newX;
        oldY = newY;
    }

    private Point2D curveFunction() {
        double x1 = sin(t) * (pow(E, cos(t)) - 2 * cos(4*t) - pow(sin(t/12), 5));         //motylek
        double y1 = cos(t) * (pow(E, cos(t)) - 2 * cos(4*t) - pow(sin(t/12), 5));
        double x2 = 3 * cos(t);                                                           //okrąg  (owal)
        double y2 = 3 * sin(t);
        double x3 = 40/t * cos(t);                                                        //spirala
        double y3 = 40/t * sin(t);
        double x4 = 0.01 * t * (2 * cos(t) + cos(2 * t));                                 //deltoida       x = a(2cos(t) + cos(2t)), y = a(2sin(t) - sin(2t))
        double y4 = 0.01 * t * (2 * sin(t) - sin(2 * t));
        double x5 = 30/sin(t) * cos(t);                                                   //kratka
        double y5 = 30/cos(t) * sin(t);


        return new Point2D(x4, y4).multiply(50);
    }

    private void saveScreenshot(Scene scene) {
        WritableImage fxImage = scene.snapshot(null);

        BufferedImage awtImage = SwingFXUtils.fromFXImage(fxImage, null);

        try {
            ImageIO.write(awtImage, "png", new File("screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {                             //robi screenshot do pliku png po naciśnięciu ENTER
                saveScreenshot(scene);
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}